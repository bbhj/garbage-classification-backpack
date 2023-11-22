package cn.czh0123.garbageclassification.service.impl;

import cn.czh0123.garbageclassification.exception.BusinessException;
import cn.czh0123.garbageclassification.pojo.domain.GarbageType;
import cn.czh0123.garbageclassification.mapper.GarbageTypeMapper;
import cn.czh0123.garbageclassification.pojo.vo.*;
import cn.czh0123.garbageclassification.query.GarbageTypeQuery;
import cn.czh0123.garbageclassification.service.IGarbageTypeService;
import cn.czh0123.garbageclassification.pojo.vo.SearchGarbageVo;
import cn.czh0123.garbageclassification.util.JsonResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 垃圾分类服务接口实现
*/
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class GarbageTypeServiceImpl extends ServiceImpl<GarbageTypeMapper,GarbageType> implements IGarbageTypeService  {
    @Resource
    private GarbageTypeMapper garbageTypeMapper;

    @Override
    public IPage<GarbageType> queryPage(GarbageTypeQuery qo) {
        IPage<GarbageType> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<GarbageType> wrapper = Wrappers.<GarbageType>query();
        wrapper.eq(qo.getType() != null, "type_id", qo.getType());
        wrapper.like(!StringUtils.isEmpty(qo.getName()), "name", qo.getName());
        wrapper.isNotNull("type_id");
        wrapper.orderByDesc("id");
        return super.page(page, wrapper);
    }

    @Override
    public GarbageTypeVo findListByTypeId(Long id, Integer pageSize) {
        QueryWrapper<GarbageType> wrapper = new QueryWrapper<>();
        wrapper.eq("type_id", id).last("limit " + pageSize);
        GarbageType garbageType = getById(id);
        GarbageTypeVo garbageTypeVo = new GarbageTypeVo();
        garbageTypeVo.setName(garbageType.getName());
        garbageTypeVo.setGarbageClass(garbageType.getGarbageClass());
        garbageTypeVo.setDetail(garbageType.getDetail());
        garbageTypeVo.setTypeId(garbageType.getTypeId());
        garbageTypeVo.setImgUrl(garbageType.getImgUrl());
        garbageTypeVo.setId(id);
        garbageTypeVo.setGarbageTypes(list(wrapper));

        return garbageTypeVo;
    }

    @Override
    public List<GarbageTabsVo> getTabs() {
        List<GarbageType> list = super.list(new QueryWrapper<GarbageType>().isNull("type_id"));
        List<GarbageTabsVo> vos = new ArrayList<>();
        list.forEach(garbageType -> {
            GarbageTabsVo vo = new GarbageTabsVo();
            vo.setTitle(garbageType.getName());
            vo.setId(garbageType.getId());
            vos.add(vo);
        });
        return vos;
    }

    @Override
    public SearchGarbageVo searchGarbage(String name) {
        GarbageType garbage = super.getOne(new QueryWrapper<GarbageType>().eq("name", name));
        SearchGarbageVo searchGarbageVo = new SearchGarbageVo();
        if (StringUtils.isEmpty(garbage)) {
            super.list(new QueryWrapper<GarbageType>()
                    .like("name",name)).stream().limit(1).forEach(g -> {
                searchGarbageVo.setName(g.getName());
                searchGarbageVo.setType(super.getById(g.getTypeId()).getName());
            });
        } else {
            searchGarbageVo.setName(garbage.getName());
            searchGarbageVo.setType(super.getById(garbage.getTypeId()).getName());
        }

        return searchGarbageVo;
    }

    @Override
    public boolean hasGarbageByName(String name) {
        GarbageType garbage = this.getOne(new QueryWrapper<GarbageType>().eq("name", name));
        return garbage != null;
    }

    @Override
    public Map<String, Object> getType() {
        QueryWrapper<GarbageType> wrapper = new QueryWrapper<>();
        wrapper.isNull("type_id");
        List<GarbageType> list = this.list(wrapper);
        Map<String, Object> map = new HashMap<>();
        for (GarbageType garbageType : list) {
            map.put(garbageType.getName(), garbageType.getId());
        }
        return map;
    }

    @Override
    public boolean saveOrUpdateGarbage(GarbageType garbageType) {
        log.info("垃圾分类:{}", garbageType);
        if (StringUtils.isEmpty(garbageType.getName())) {
            throw new BusinessException(JsonResult.CODE_ERROR, "垃圾名称不能为空！");
        }
        if (StringUtils.isEmpty(garbageType.getDetail())) {
            throw new BusinessException(JsonResult.CODE_ERROR, "垃圾描述不能为空！");
        }
        if (garbageType.getId() == null || garbageType.getId() == 0) {
            garbageType.setId(null);
            QueryWrapper<GarbageType> wrapper = new QueryWrapper<>();
            wrapper.eq("name", garbageType.getName());
            Integer count = garbageTypeMapper.selectCount(wrapper);
            if (count > 0) {
                throw new BusinessException(JsonResult.CODE_ERROR, "该垃圾分类已存在");
            }
        }
        return this.saveOrUpdate(garbageType);
    }

    @Override
    public List<AnalysisReport> garbageAnalysis() {
        QueryWrapper<GarbageType> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("type_id")
                .select("type_id", "COUNT(*) AS garbage_count")
                .groupBy("type_id");
        List<Map<String, Object>> list = garbageTypeMapper.selectMaps(wrapper);
        List<AnalysisReport> analysisReports = new ArrayList<>();
        list.forEach(map -> {
            AnalysisReport analysisReport = new AnalysisReport();
            switch (map.get("type_id").toString()) {
                case "1":
                    analysisReport.setName("analysis.dryGarbage");
                    break;
                case "2":
                    analysisReport.setName("analysis.wetGarbage");
                    break;
                case "3":
                    analysisReport.setName("analysis.recyclableGarbage");
                    break;
                case "4":
                    analysisReport.setName("analysis.harmfulGarbage");
                    break;
                default:
                    analysisReport.setName("analysis.bulkyGarbage");
            }
            analysisReport.setValue(Long.parseLong(map.get("garbage_count").toString()));
            analysisReports.add(analysisReport);
        });
        return analysisReports;
    }

}
