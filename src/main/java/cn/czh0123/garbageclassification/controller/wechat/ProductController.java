package cn.czh0123.garbageclassification.controller.wechat;

import cn.czh0123.garbageclassification.service.IProductService;
import cn.czh0123.garbageclassification.service.IProductTypeService;
import cn.czh0123.garbageclassification.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
* 商品控制层
*/
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private IProductService productService;
    @Autowired
    private IProductTypeService productTypeService;

    @RequestMapping("/get")
    public Object get(Long id){
        return JsonResult.success(productService.getById(id));
    }

    @GetMapping("/getSecondData")
    public JsonResult getSecondData(Long type) {
        return JsonResult.success(productService.getSecondData(type));
    }

    @GetMapping("/getLeftData")
    public JsonResult getSecondData() {
        return JsonResult.success(productTypeService.list());
    }
}
