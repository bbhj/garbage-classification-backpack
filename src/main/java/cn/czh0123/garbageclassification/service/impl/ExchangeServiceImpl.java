package cn.czh0123.garbageclassification.service.impl;

import cn.czh0123.garbageclassification.pojo.domain.Exchange;
import cn.czh0123.garbageclassification.pojo.domain.Integral;
import cn.czh0123.garbageclassification.pojo.domain.Product;
import cn.czh0123.garbageclassification.exception.BusinessException;
import cn.czh0123.garbageclassification.mapper.ExchangeMapper;
import cn.czh0123.garbageclassification.pojo.vo.AnalysisReport;
import cn.czh0123.garbageclassification.pojo.vo.ExchangeVo;
import cn.czh0123.garbageclassification.query.ExchangeQuery;
import cn.czh0123.garbageclassification.service.IExchangeService;
import cn.czh0123.garbageclassification.service.IIntegralService;
import cn.czh0123.garbageclassification.service.IProductService;
import cn.czh0123.garbageclassification.util.JsonResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 兑换服务接口实现
 */
@Service
@Transactional
public class ExchangeServiceImpl extends ServiceImpl<ExchangeMapper, Exchange> implements IExchangeService {

    @Resource
    private ExchangeMapper exchangeMapper;
    @Autowired
    private IProductService productService;
    @Autowired
    private IIntegralService integralService;

    @Override
    public IPage<Exchange> queryPage(ExchangeQuery qo) {
        IPage<Exchange> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<Exchange> wrapper = Wrappers.<Exchange>query();
        return super.page(page, wrapper);
    }

    @Override
    public boolean exchange(Exchange exchange) {
        //判断积分 > 物品所需积分
        //减少积分
        Integral integral = integralService.getOne(new QueryWrapper<Integral>()
                .eq("user_id", exchange.getUserId()));
        if (StringUtils.isEmpty(integral)) throw new BusinessException(JsonResult.CODE_ERROR, JsonResult.MSG_NOT_LOGIN);
        Product product = productService.getById(exchange.getProductId());
        if (integral.getIntegral() < product.getNowIntegral()) throw new BusinessException(JsonResult.CODE_ERROR, "积分不足！");
        integralService.update(new UpdateWrapper<Integral>()
                .set("integral", integral.getIntegral() - product.getNowIntegral()));
        //保存兑换
        exchange.setExchangeTime(LocalDateTime.now());
        return super.save(exchange);
    }

    @Override
    public List<ExchangeVo> getExchanges(Long userId) {
        List<ExchangeVo> exchangeVOS = new ArrayList<>();
        super.list(new QueryWrapper<Exchange>()
                .eq("user_id", userId)).forEach(exchange -> {
            Product product = productService.getById(exchange.getProductId());
            ExchangeVo exchangeVO = new ExchangeVo();
            BeanUtils.copyProperties(product, exchangeVO);
            exchangeVO.setExchangeTime(exchange.getExchangeTime());
            exchangeVOS.add(exchangeVO);
        });
        return exchangeVOS;
    }

    @Override
    public List<AnalysisReport> monthlySales() {
        List<AnalysisReport> analysisReports = new ArrayList<>();
        List<Map<String, Object>> list = exchangeMapper.monthlySales();
        for (Map<String, Object> map : list) {
            AnalysisReport analysisReport = new AnalysisReport();
            analysisReport.setName("analysis." + map.get("month").toString());
            analysisReport.setEstimate(Long.parseLong(map.get("exchange_count").toString()));
            analysisReports.add(analysisReport);
        }
        return analysisReports;
    }
}
