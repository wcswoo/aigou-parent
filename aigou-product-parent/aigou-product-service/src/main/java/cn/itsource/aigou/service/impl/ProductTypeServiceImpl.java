package cn.itsource.aigou.service.impl;

import cn.itsource.aigou.domain.ProductType;
import cn.itsource.aigou.mapper.ProductTypeMapper;
import cn.itsource.aigou.service.IProductTypeService;
import cn.itsource.basic.util.AjaxResult;
import cn.itsource.client.RedisClient;
import cn.itsource.client.StaticPageClient;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
@Service
public class  ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements IProductTypeService {

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private StaticPageClient  staticPageClient;

    @Override
    public void genHomePage() {
     /*   Map<String,Object> map =new HashMap();
        staticPageClient.genStaticPage(map);*/
        Map<String,Object> map = new HashMap<>();
        //模板路径
        String templatePath = "E:\\ideaCode\\aigou-parent\\aigou-product-parent\\aigou-product-service\\src\\main\\resources\\template\\product.type.vm";
        //s生成的目标文件路径
        String targetPath = "E:\\ideaCode\\aigou-parent\\aigou-product-parent\\aigou-product-service\\src\\main\\resources\\template\\product.type.vm.html";
        //model 存放所有对象 也即是商品类型
        List<ProductType> productTypes = loadTypeTree();
        map.put("model",productTypes);
        map.put("templatePath",templatePath);
        map.put("targetPath",targetPath);
        staticPageClient.genStaticPage(map);

        //第二步 ： 生成home.html
        map = new HashMap<>();
        templatePath = "E:\\ideaCode\\aigou-parent\\aigou-product-parent\\aigou-product-service\\src\\main\\resources\\template\\home.vm";
        targetPath = "E:\\ideaCode\\aigou-web-parent\\aigou-web-home\\home.html";
        //staticRoot
        Map<String,String> model = new HashMap<>();
        model.put("staticRoot","E:\\ideaCode\\aigou-parent\\aigou-product-parent\\aigou-product-service\\src\\main\\resources\\");
        map.put("model",model);
        map.put("templatePath",templatePath);
        map.put("targetPath",targetPath);

        staticPageClient.genStaticPage(map);

    }

    @Override
    public List<ProductType> loadTypeTree() {
        AjaxResult productType = redisClient.getRedis("productTypes");
        String productType1 = (String) productType.getRestObj();
        //将字符串转为list
        List<ProductType> productTypes = JSON.parseArray(productType1, ProductType.class);
        //如果缓存没有值
        if(productTypes==null||productTypes.size()<=0){
            productTypes = loop();
            //存入redis缓存中
            redisClient.setRedis("productTypes",JSON.toJSONString(productTypes));
        }
        //返回数据
        return productTypes;

    }


    private List<ProductType> loop() {
        List<ProductType> productTypes = baseMapper.selectList(null);
        List<ProductType> list = new ArrayList<>();
        Map<Long,ProductType> map = new HashMap<>();
        for (ProductType pt : productTypes) {
            //map存放所有菜单
            map.put(pt.getId(),pt);
        }
        for (ProductType productType : productTypes) {
            if(productType.getPid()==0){
                //List存放一级菜单
                list.add(productType);
            }else{
                //不是一级菜单 从map中取到一级菜单
                ProductType parent = map.get(productType.getPid());
                List<ProductType> children = parent.getChildren();
                if(children==null){
                    children=new ArrayList<>();
                }
                children.add(productType);
                parent.setChildren(children);
            }
        }
        return list;
    }

    private List<ProductType> recursive(Long id) {
        List<ProductType> parents = baseMapper.selectList(new QueryWrapper<ProductType>().eq("pid", id));
        for (ProductType parent : parents) {
            List<ProductType> children = recursive(parent.getId());
            if(!children.isEmpty()){
                parent.setChildren(children);
            }
        }
        return parents;
    }

}
