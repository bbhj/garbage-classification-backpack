package cn.czh0123.garbageclassification.service;

import cn.czh0123.garbageclassification.pojo.domain.Favorites;
import cn.czh0123.garbageclassification.query.FavoritesQuery;
import cn.czh0123.garbageclassification.pojo.vo.FavVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 收藏服务接口
 */
public interface IFavoritesService extends IService<Favorites>{
    /**
    * 分页
    * @param qo
    * @return
    */
    IPage<Favorites> queryPage(FavoritesQuery qo);

    Integer updateStatus(Favorites favorites);

    List<FavVo> getFav(Long userId);
}
