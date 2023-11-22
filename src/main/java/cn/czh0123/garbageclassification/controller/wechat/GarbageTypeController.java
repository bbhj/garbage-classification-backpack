package cn.czh0123.garbageclassification.controller.wechat;

import cn.czh0123.garbageclassification.pojo.domain.GarbageType;
import cn.czh0123.garbageclassification.service.ICoordinateService;
import cn.czh0123.garbageclassification.service.IGarbageTypeService;
import cn.czh0123.garbageclassification.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
* 垃圾分类控制层
*/
@RestController
@RequestMapping("garbageType")
public class GarbageTypeController {

    @Autowired
    private IGarbageTypeService garbageTypeService;
    @Autowired
    private ICoordinateService coordinateService;

    @RequestMapping("/get")
    public Object get(Long id){
        return JsonResult.success(garbageTypeService.getById(id));
    }

    @RequestMapping("/saveOrUpdate")
    public Object saveOrUpdate(GarbageType garbageType){
        garbageTypeService.saveOrUpdate(garbageType);
        return JsonResult.success();
    }

    @RequestMapping("/delete")
    public Object delete(Long id){
        garbageTypeService.removeById(id);
        return JsonResult.success();
    }

    @GetMapping("/findListByType")
    public JsonResult findListByType(Long id, Integer pageSize) {
        return JsonResult.success(garbageTypeService.findListByTypeId(id, pageSize));
    }

    @GetMapping("/getTabs")
    public JsonResult getTabs() {
        return JsonResult.success(garbageTypeService.getTabs());
    }

    @GetMapping("/searchGarbage")
    public JsonResult searchGarbage(String name) {
        return JsonResult.success(garbageTypeService.searchGarbage(name));
    }

    @GetMapping("/getCoordinateData")
    public JsonResult getCoordinateData() {
        return JsonResult.success(coordinateService.list());
    }
}
