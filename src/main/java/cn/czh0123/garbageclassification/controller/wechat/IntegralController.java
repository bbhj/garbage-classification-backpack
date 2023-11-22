package cn.czh0123.garbageclassification.controller.wechat;

import cn.czh0123.garbageclassification.pojo.domain.Exchange;
import cn.czh0123.garbageclassification.service.IExchangeService;
import cn.czh0123.garbageclassification.service.IIntegralService;
import cn.czh0123.garbageclassification.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
* 积分控制层
*/
@RestController
@RequestMapping("integral")
public class IntegralController {

    @Autowired
    private IExchangeService exchangeService;

    @Autowired
    private IIntegralService IntegralService;


    @RequestMapping("/get")
    public Object get(Long id){
        return JsonResult.success(IntegralService.getById(id));
    }

    @GetMapping("/getIntegralByUserId")
    public JsonResult getIntegralByUserId(Long id) {
        return JsonResult.success(IntegralService.getIntegralByUserId(id));
    }

    @PostMapping("/exchange")
    public JsonResult exchange(@RequestBody Exchange exchange) {
        return JsonResult.success(exchangeService.exchange(exchange));
    }

    @GetMapping("/getExchanges")
    public JsonResult getExchanges(Long userId) {
        return JsonResult.success(exchangeService.getExchanges(userId));
    }
}
