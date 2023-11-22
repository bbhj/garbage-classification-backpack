package cn.czh0123.garbageclassification.service;

import cn.czh0123.garbageclassification.pojo.domain.ProductType;
import cn.czh0123.garbageclassification.query.ProductTypeQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 商品分类服务接口
 */
public interface IProductTypeService extends IService<ProductType>{
    /**
    * 分页
    * @param qo
    * @return
    */
    IPage<ProductType> queryPage(ProductTypeQuery qo);
}
