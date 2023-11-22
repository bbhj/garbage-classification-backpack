package cn.czh0123.garbageclassification.service;

import cn.czh0123.garbageclassification.pojo.domain.Opinion;
import cn.czh0123.garbageclassification.query.OpinionQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 意见反馈服务接口
 */
public interface IOpinionService extends IService<Opinion>{
    /**
    * 分页
    * @param qo
    * @return
    */
    IPage<Opinion> queryPage(OpinionQuery qo);
}
