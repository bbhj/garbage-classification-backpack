package cn.czh0123.garbageclassification.controller.wechat;

import cn.czh0123.garbageclassification.pojo.domain.Favorites;
import cn.czh0123.garbageclassification.service.IFavoritesService;
import cn.czh0123.garbageclassification.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 坐标控制层
 */
@RestController
@RequestMapping("favorites")
public class FavoritesController {

    @Autowired
    private IFavoritesService favoritesService;

    @PostMapping("/updateStatus")
    public JsonResult updateStatus(@RequestBody Favorites favorites) {
        return JsonResult.success(favoritesService.updateStatus(favorites));
    }

    @GetMapping("/getFav")
    public JsonResult getFav(Long userId) {
        return JsonResult.success(favoritesService.getFav(userId));
    }

}
