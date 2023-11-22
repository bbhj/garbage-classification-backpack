package cn.czh0123.garbageclassification.service.impl;

import cn.czh0123.garbageclassification.pojo.domain.Integral;
import cn.czh0123.garbageclassification.mapper.IntegralMapper;
import cn.czh0123.garbageclassification.query.IntegralQuery;
import cn.czh0123.garbageclassification.service.IIntegralService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* 积分服务接口实现
*/
@Service
@Transactional
public class IntegralServiceImpl extends ServiceImpl<IntegralMapper, Integral> implements IIntegralService {

    @Override
    public IPage<Integral> queryPage(IntegralQuery qo) {
        IPage<Integral> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<Integral> wrapper = Wrappers.<Integral>query();
        return super.page(page, wrapper);
    }

    @Override
    public Long getIntegralByUserId(Long id) {
        return super.getOne(new QueryWrapper<Integral>().eq("user_id",id)).getIntegral();
    }
}
