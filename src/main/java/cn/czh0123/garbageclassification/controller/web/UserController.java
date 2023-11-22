package cn.czh0123.garbageclassification.controller.web;

import cn.czh0123.garbageclassification.pojo.dto.SaveOrUpdateUserDto;
import cn.czh0123.garbageclassification.query.UserQuery;
import cn.czh0123.garbageclassification.service.IUserService;
import cn.czh0123.garbageclassification.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "api-user")
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("list")
    public JsonResult list() {
        return JsonResult.success(userService.findAllUsers());
    }

    @PostMapping("userPage")
    public JsonResult userPage(@RequestBody UserQuery qo) {
        return JsonResult.success(userService.queryPage(qo));
    }

    @PostMapping("saveOrUpdate")
    public JsonResult saveOrUpdate(@RequestBody SaveOrUpdateUserDto userDto) {
        return JsonResult.success(userService.saveOrUpdate(userDto));
    }

    @PostMapping("delete")
    public JsonResult delete(@RequestBody Long id) {
        return JsonResult.success(userService.removeById(id));
    }
}
