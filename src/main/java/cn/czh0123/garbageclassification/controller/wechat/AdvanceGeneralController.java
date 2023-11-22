package cn.czh0123.garbageclassification.controller.wechat;

import cn.czh0123.garbageclassification.anno.AutoAddIntegral;
import cn.czh0123.garbageclassification.anno.AutoAddStudyNum;
import cn.czh0123.garbageclassification.service.IUserNumService;
import cn.czh0123.garbageclassification.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 百度AI扫描识别垃圾分类
 */
@RestController
@RequestMapping("/baiduAI")
public class AdvanceGeneralController {

    @Autowired
    private IUserNumService userNumService;

    @AutoAddIntegral
    @AutoAddStudyNum
    @PostMapping("/scan")
    public JsonResult scanImg(MultipartFile file, Long userId) {
        return JsonResult.success(userNumService.scan(file, userId));
    }

    @GetMapping("/testAdd")
//    @AutoAddIntegral
    @AutoAddStudyNum
    public JsonResult testAdd(Long userId) {
        return JsonResult.success("测试自动增加注解");
    }


}
