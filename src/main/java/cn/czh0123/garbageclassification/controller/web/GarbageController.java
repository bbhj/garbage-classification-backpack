package cn.czh0123.garbageclassification.controller.web;

import cn.czh0123.garbageclassification.pojo.domain.GarbageType;
import cn.czh0123.garbageclassification.pojo.dto.SaveOrUpdateUserDto;
import cn.czh0123.garbageclassification.query.GarbageTypeQuery;
import cn.czh0123.garbageclassification.service.IGarbageTypeService;
import cn.czh0123.garbageclassification.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "api-garbage")
@RequestMapping("/api/garbage")
public class GarbageController {

    @Autowired
    private IGarbageTypeService garbageTypeService;

    @PostMapping("list")
    public JsonResult findGarbage(@RequestBody GarbageTypeQuery qo) {
        return JsonResult.success(garbageTypeService.queryPage(qo));
    }

    @GetMapping("/getType")
    public JsonResult getType() {
        return JsonResult.success(garbageTypeService.getType());
    }

    @PostMapping("saveOrUpdate")
    public JsonResult saveOrUpdate(@RequestBody GarbageType garbageType) {
        return JsonResult.success(garbageTypeService.saveOrUpdateGarbage(garbageType));
    }
    
    @PostMapping("/delete")
    public JsonResult delete(@RequestBody Long id) {
        return JsonResult.success(garbageTypeService.removeById(id));
    }
}
