package cn.czh0123.garbageclassification.service.impl;

import cn.czh0123.garbageclassification.pojo.domain.Coordinate;
import cn.czh0123.garbageclassification.mapper.CoordinateMapper;
import cn.czh0123.garbageclassification.query.CoordinateQuery;
import cn.czh0123.garbageclassification.service.ICoordinateService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* 坐标服务接口实现
*/
@Service
@Transactional
public class CoordinateServiceImpl extends ServiceImpl<CoordinateMapper,Coordinate> implements ICoordinateService  {

    @Override
    public IPage<Coordinate> queryPage(CoordinateQuery qo) {
        IPage<Coordinate> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<Coordinate> wrapper = Wrappers.<Coordinate>query();
        return super.page(page, wrapper);
    }
}
