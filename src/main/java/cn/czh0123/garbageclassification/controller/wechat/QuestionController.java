package cn.czh0123.garbageclassification.controller.wechat;

import cn.czh0123.garbageclassification.mongo.service.IQuestionService;
import cn.czh0123.garbageclassification.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private IQuestionService questionService;

    @GetMapping("/findQuestionsByType")
    public JsonResult findQuestionsByType(Integer type) {
        return JsonResult.success(questionService.findQuestionsByType(type));
    }
}
