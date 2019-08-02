package cn.itsource.aigou.service.impl;

import cn.itsource.aigou.domain.ProductType;
import cn.itsource.aigou.mapper.ProductTypeMapper;
import cn.itsource.aigou.service.IProductTypeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品目录 服务实现类
 * </p>
 *
 * @author wcswoo
 * @since 2019-07-30
 */
@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements IProductTypeService {
/*
    @Override
    public List<ProductType> loadTypeTree() {

       // return digui(0L);
        return loop();
    }
    //循环查询所有 的子
    private List<ProductType> loop() {
        List<ProductType> productTypes = baseMapper.selectList(null);
        List<ProductType> list =new ArrayList<>();
        Map<Long,ProductType> map=new HashMap<>();
        for (ProductType productType : productTypes) {
            //先将所有菜单的id和内容存入map
            map.put(productType.getId(),productType);
        }
        for (ProductType type : productTypes) {
            if (type.getId()==0){
                //list集合只放一级菜单
                list.add(type);
            }else {
                ProductType parent = map.get(type.getId());
                parent.getList().add(type);
            }
        }

        return list;
    }

    private List<ProductType> digui(Long id ) {
        List<ProductType> parents = baseMapper.selectList(new QueryWrapper<ProductType>().eq("pid", id));
        for (ProductType parent : parents) {
            List<ProductType> child = digui(parent.getId());
            if(!child.isEmpty()){
                parent.setList(child);
            }
        }
        return null;
    }*/
@Override
public List<ProductType> loadTypeTree() {
    //return recursive(0L);
    return loop();
}

    private List<ProductType> loop() {
        List<ProductType> productTypes = baseMapper.selectList(null);
        //List存放一级菜单
        List<ProductType> list = new ArrayList<>();
        Map<Long,ProductType> map = new HashMap<>();
        for (ProductType pt : productTypes) {
            map.put(pt.getId(),pt);
        }
        for (ProductType productType : productTypes) {
            if(productType.getPid()==0){
                list.add(productType);
            }else{
                ProductType parent = map.get(productType.getPid());
                parent.getChildren().add(productType);
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
