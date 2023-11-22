package cn.czh0123.garbageclassification.service;

import cn.czh0123.garbageclassification.pojo.domain.UserLoginLog;
import cn.czh0123.garbageclassification.query.UserLoginLogQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 用户登录日志服务接口
 */
public interface IUserLoginLogService extends IService<UserLoginLog>{
    /**
    * 分页
    * @param qo
    * @return
    */
    IPage<UserLoginLog> queryPage(UserLoginLogQuery qo);
}
