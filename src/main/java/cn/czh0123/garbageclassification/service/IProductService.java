package cn.czh0123.garbageclassification.service;

import cn.czh0123.garbageclassification.pojo.domain.Product;
import cn.czh0123.garbageclassification.query.ProductQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 商品服务接口
 */
public interface IProductService extends IService<Product>{
    /**
    * 分页
    * @param qo
    * @return
    */
    IPage<Product> queryPage(ProductQuery qo);

    List<Product> getSecondData(Long type);

}
