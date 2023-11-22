package cn.czh0123.garbageclassification.service.impl;

import cn.czh0123.garbageclassification.pojo.domain.Product;
import cn.czh0123.garbageclassification.mapper.ProductMapper;
import cn.czh0123.garbageclassification.query.ProductQuery;
import cn.czh0123.garbageclassification.service.IProductService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 商品服务接口实现
*/
@Service
@Transactional
public class ProductServiceImpl extends ServiceImpl<ProductMapper,Product> implements IProductService  {

    @Override
    public IPage<Product> queryPage(ProductQuery qo) {
        IPage<Product> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<Product> wrapper = Wrappers.<Product>query();
        return super.page(page, wrapper);
    }

    @Override
    public List<Product> getSecondData(Long type) {
        return super.list(new QueryWrapper<Product>().eq("type_id", type));
    }
}
