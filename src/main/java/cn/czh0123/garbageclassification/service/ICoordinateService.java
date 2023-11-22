package cn.czh0123.garbageclassification.service;

import cn.czh0123.garbageclassification.pojo.domain.Coordinate;
import cn.czh0123.garbageclassification.query.CoordinateQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 坐标服务接口
 */
public interface ICoordinateService extends IService<Coordinate>{
    /**
    * 分页
    * @param qo
    * @return
    */
    IPage<Coordinate> queryPage(CoordinateQuery qo);
}
