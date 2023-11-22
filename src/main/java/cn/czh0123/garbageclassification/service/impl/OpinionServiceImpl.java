package cn.czh0123.garbageclassification.service.impl;

import cn.czh0123.garbageclassification.pojo.domain.Opinion;
import cn.czh0123.garbageclassification.mapper.OpinionMapper;
import cn.czh0123.garbageclassification.query.OpinionQuery;
import cn.czh0123.garbageclassification.service.IOpinionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* 意见反馈服务接口实现
*/
@Service
@Transactional
public class OpinionServiceImpl extends ServiceImpl<OpinionMapper,Opinion> implements IOpinionService  {

    @Override
    public IPage<Opinion> queryPage(OpinionQuery qo) {
        IPage<Opinion> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<Opinion> wrapper = Wrappers.<Opinion>query();
        return super.page(page, wrapper);
    }
}
