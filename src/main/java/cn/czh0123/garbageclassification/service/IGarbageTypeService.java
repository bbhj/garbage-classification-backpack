package cn.czh0123.garbageclassification.service;

import cn.czh0123.garbageclassification.pojo.domain.GarbageType;
import cn.czh0123.garbageclassification.pojo.dto.SaveOrUpdateUserDto;
import cn.czh0123.garbageclassification.pojo.vo.AnalysisReport;
import cn.czh0123.garbageclassification.pojo.vo.GarbageTabsVo;
import cn.czh0123.garbageclassification.pojo.vo.GarbageTypeVo;
import cn.czh0123.garbageclassification.pojo.vo.SearchGarbageVo;
import cn.czh0123.garbageclassification.query.GarbageTypeQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;


/**
 * 垃圾分类服务接口
 */
public interface IGarbageTypeService extends IService<GarbageType>{
    /**
    * 分页
    * @param qo
    * @return
    */
    IPage<GarbageType> queryPage(GarbageTypeQuery qo);

    GarbageTypeVo findListByTypeId(Long id, Integer pageSize);

    List<GarbageTabsVo> getTabs();

    SearchGarbageVo searchGarbage(String name);

    boolean hasGarbageByName(String name);

    Map<String, Object> getType();

    boolean saveOrUpdateGarbage(GarbageType garbageType);

    List<AnalysisReport> garbageAnalysis();

}
