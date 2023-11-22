package cn.czh0123.garbageclassification.service;

import cn.czh0123.garbageclassification.pojo.domain.Exchange;
import cn.czh0123.garbageclassification.pojo.vo.AnalysisReport;
import cn.czh0123.garbageclassification.pojo.vo.ExchangeVo;
import cn.czh0123.garbageclassification.query.ExchangeQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 兑换服务接口
 */
public interface IExchangeService extends IService<Exchange>{
    /**
    * 分页
    * @param qo
    * @return
    */
    IPage<Exchange> queryPage(ExchangeQuery qo);

    boolean exchange(Exchange exchange);

    List<ExchangeVo> getExchanges(Long userId);

    List<AnalysisReport> monthlySales();
}
