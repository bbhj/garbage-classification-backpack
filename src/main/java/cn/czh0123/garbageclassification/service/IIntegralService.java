package cn.czh0123.garbageclassification.service;

import cn.czh0123.garbageclassification.pojo.domain.Integral;
import cn.czh0123.garbageclassification.query.IntegralQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 积分服务接口
 */
public interface IIntegralService extends IService<Integral>{
    /**
    * 分页
    * @param qo
    * @return
    */
    IPage<Integral> queryPage(IntegralQuery qo);

    Long getIntegralByUserId(Long id);

}
