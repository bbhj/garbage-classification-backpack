package cn.czh0123.garbageclassification.service.impl;

import cn.czh0123.garbageclassification.pojo.domain.UserLoginLog;
import cn.czh0123.garbageclassification.mapper.UserLoginLogMapper;
import cn.czh0123.garbageclassification.query.UserLoginLogQuery;
import cn.czh0123.garbageclassification.service.IUserLoginLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* 用户登录日志服务接口实现
*/
@Service
@Transactional
public class UserLoginLogServiceImpl extends ServiceImpl<UserLoginLogMapper,UserLoginLog> implements IUserLoginLogService  {

    @Override
    public IPage<UserLoginLog> queryPage(UserLoginLogQuery qo) {
        IPage<UserLoginLog> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<UserLoginLog> wrapper = Wrappers.<UserLoginLog>query();
        return super.page(page, wrapper);
    }
}
