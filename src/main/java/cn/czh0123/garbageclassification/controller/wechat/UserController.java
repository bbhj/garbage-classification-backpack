package cn.czh0123.garbageclassification.controller.wechat;

import cn.czh0123.garbageclassification.anno.AutoAddStudyNum;
import cn.czh0123.garbageclassification.mongo.service.IUserAnswerService;
import cn.czh0123.garbageclassification.pojo.domain.Opinion;
import cn.czh0123.garbageclassification.pojo.dto.UserAnswerDto;
import cn.czh0123.garbageclassification.service.IOpinionService;
import cn.czh0123.garbageclassification.service.IUserNumService;
import cn.czh0123.garbageclassification.service.IUserService;
import cn.czh0123.garbageclassification.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
* 用户控制层
*/
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserNumService userNumService;

    @Autowired
    private IUserAnswerService userAnswerService;

    @Autowired
    private IOpinionService opinionService;

    @RequestMapping("/get")
    public Object get(Long id){
        return JsonResult.success(userService.getById(id));
    }

    @GetMapping("/getByOpenId")
    public JsonResult getByOpenId(String openId) {
        return JsonResult.success(userService.getByOpenId(openId));
    }

    @PostMapping("/login")
    public JsonResult login(String jsCode, HttpServletRequest request) {
        return JsonResult.success(userService.login(jsCode, request));
    }

    @AutoAddStudyNum
    @GetMapping("/addUserStudyNum")
    public void addUserStudyNum(Long userId) {

    }

    @RequestMapping("/getUserNum")
    public Object getUserNum(Long id){
        return JsonResult.success(userNumService.getByUserId(id));
    }

    @GetMapping("/getAnswerByExamId")
    public JsonResult getAnswerByExamId(Long examId) {
        return JsonResult.success(userAnswerService.getAnswerByExamId(examId));
    }

    @GetMapping("/listAnswer")
    public JsonResult listAnswer(Long userId) {
        return JsonResult.success(userAnswerService.list(userId));
    }

    @PostMapping("/saveAnswer")
    public JsonResult saveAnswer(@RequestBody UserAnswerDto userAnswer) {
        userAnswerService.insert(userAnswer);
        return JsonResult.success();
    }

    @RequestMapping("/saveOrUpdateOpinion")
    public Object saveOrUpdate(Opinion opinion) {
        opinionService.saveOrUpdate(opinion);
        return JsonResult.success();
    }

    @GetMapping("/getReportData")
    public JsonResult getReportData(Long id) {
        return JsonResult.success(userNumService.getReportData(id));
    }
}
