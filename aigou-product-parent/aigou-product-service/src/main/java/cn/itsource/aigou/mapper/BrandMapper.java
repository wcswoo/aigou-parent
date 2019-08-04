package cn.itsource.aigou.mapper;

import cn.itsource.aigou.domain.Brand;
import cn.itsource.aigou.query.BrandQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

public interface BrandMapper extends BaseMapper<Brand> {

    IPage<Brand> queryPage(Page page, @Param("query")BrandQuery query);
}
