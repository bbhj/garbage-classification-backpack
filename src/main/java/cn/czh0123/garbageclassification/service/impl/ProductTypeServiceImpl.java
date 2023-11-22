package cn.czh0123.garbageclassification.service.impl;

import cn.czh0123.garbageclassification.pojo.domain.ProductType;
import cn.czh0123.garbageclassification.mapper.ProductTypeMapper;
import cn.czh0123.garbageclassification.query.ProductTypeQuery;
import cn.czh0123.garbageclassification.service.IProductTypeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* 商品分类服务接口实现
*/
@Service
@Transactional
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper,ProductType> implements IProductTypeService  {

    @Override
    public IPage<ProductType> queryPage(ProductTypeQuery qo) {
        IPage<ProductType> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<ProductType> wrapper = Wrappers.<ProductType>query();
        return super.page(page, wrapper);
    }
}
