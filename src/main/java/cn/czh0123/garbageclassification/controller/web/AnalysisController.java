package cn.czh0123.garbageclassification.controller.web;

import cn.czh0123.garbageclassification.service.IExchangeService;
import cn.czh0123.garbageclassification.service.IGarbageTypeService;
import cn.czh0123.garbageclassification.service.IUserService;
import cn.czh0123.garbageclassification.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: czh
 * @description:
 * @date: 2023/5/8 21:36
 */
@RestController
@RequestMapping("/api/analysis")
public class AnalysisController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IExchangeService exchangeService;
    @Autowired
    private IGarbageTypeService garbageTypeService;

    @GetMapping("/weeklyUserActivity")
    public JsonResult weeklyUserActivity() {
        return JsonResult.success(userService.weeklyUserActivity());
    }

    @GetMapping("/monthlySales")
    public JsonResult monthlySales() {
        return JsonResult.success(exchangeService.monthlySales());
    }

    @GetMapping("/garbageAnalysis")
    public JsonResult garbageAnalysis() {
        return JsonResult.success(garbageTypeService.garbageAnalysis());
    }
}
