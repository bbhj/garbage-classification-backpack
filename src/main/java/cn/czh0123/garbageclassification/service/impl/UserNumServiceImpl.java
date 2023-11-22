package cn.czh0123.garbageclassification.service.impl;

import cn.czh0123.garbageclassification.mapper.NoteQuestionMapper;
import cn.czh0123.garbageclassification.mongo.domain.Question;
import cn.czh0123.garbageclassification.pojo.domain.NoteQuestion;
import cn.czh0123.garbageclassification.pojo.domain.UserNum;
import cn.czh0123.garbageclassification.exception.BusinessException;
import cn.czh0123.garbageclassification.mapper.UserNumMapper;
import cn.czh0123.garbageclassification.pojo.vo.QuestionReportVo;
import cn.czh0123.garbageclassification.pojo.vo.ReportVo;
import cn.czh0123.garbageclassification.pojo.vo.SearchGarbageVo;
import cn.czh0123.garbageclassification.query.UserNumQuery;
import cn.czh0123.garbageclassification.service.IGarbageTypeService;
import cn.czh0123.garbageclassification.service.IUserNumService;
import cn.czh0123.garbageclassification.util.AdvancedGeneral;
import cn.czh0123.garbageclassification.util.JsonResult;
import cn.czh0123.garbageclassification.pojo.vo.Advanced;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用户各次数服务接口实现
 */
@Slf4j
@Service
@Transactional
public class UserNumServiceImpl extends ServiceImpl<UserNumMapper, UserNum> implements IUserNumService {

    @Value("${ai-scan-path}")
    private String path;
    @Autowired
    private IGarbageTypeService garbageTypeService;
    @Resource
    private UserNumMapper userNumMapper;
    @Resource
    private NoteQuestionMapper noteQuestionMapper;
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public IPage<UserNum> queryPage(UserNumQuery qo) {
        IPage<UserNum> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<UserNum> wrapper = Wrappers.<UserNum>query();
        return super.page(page, wrapper);
    }

    @Override
    public SearchGarbageVo scan(MultipartFile file, Long userId) {
/*        try {
            file.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        File thisFile = null;
        try {
            byte[] bytes = file.getBytes();
            File tempFile = File.createTempFile("temp", null);
            Files.write(tempFile.toPath(), bytes);
            thisFile = FileUtils.getFile(tempFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s = AdvancedGeneral.advancedGeneral(thisFile);
        JSONObject jsonObject = JSON.parseObject(s);
        Advanced advanced = new Advanced();
        List<Advanced> result = JSON.parseArray(jsonObject.getString("result"), Advanced.class);

        result.stream().limit(1).forEach(a -> advanced.setKeyword(a.getKeyword()));
        SearchGarbageVo searchGarbageVO = null;
        boolean isFind = false;
        for (Advanced res : result) {
            if (isFind) {
                break;
            }
            searchGarbageVO = garbageTypeService.searchGarbage(res.getKeyword());
            if (!StringUtils.isEmpty(searchGarbageVO.getName())) {
                isFind = true;
            }
        }
        try {
            if (!isFind) {
                for (Advanced res : result) {
                    if (isFind) {
                        break;
                    }
                    String keyword = res.getKeyword();
                    a :for (int i = keyword.length(); i >= 2; i--) {
                        for (int j = 0; j < keyword.length() - 1 ; j++) {
                            String sub = keyword.substring(j, j + i);
                            if (sub.length() >= 2) {
                                searchGarbageVO = garbageTypeService.searchGarbage(res.getKeyword());
                                if (!StringUtils.isEmpty(searchGarbageVO.getName())) {
                                    isFind = true;
                                    break a;
                                }
                            }
                        }
                    }
                }
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new BusinessException(JsonResult.CODE_ERROR, advanced.getKeyword() + "暂未录入入数据库");
        }


        if (StringUtils.isEmpty(searchGarbageVO)) {
            throw new BusinessException(JsonResult.CODE_ERROR, advanced.getKeyword() + "暂未录入入数据库");
        }
        UserNum userNum = getOne(new QueryWrapper<UserNum>().eq("user_id", userId));
        update(new UpdateWrapper<UserNum>().eq("user_id", userId).set("scan_num", userNum.getScanNum() + 1)
                .set("study_num", userNum.getStudyNum() + 1));
        return searchGarbageVO;
    }

    @Override
    public QuestionReportVo getReportData(Long id) {
        UserNum userNum = userNumMapper.selectOne(new QueryWrapper<UserNum>().eq("user_id", id));
        List<NoteQuestion> noteQuestions = noteQuestionMapper.selectList(new QueryWrapper<NoteQuestion>().eq("user_id", id));
        Integer totalNum = noteQuestions.size()/5;
        AtomicInteger easy = new AtomicInteger();
        AtomicInteger secondary = new AtomicInteger();
        AtomicInteger difficult = new AtomicInteger();
        noteQuestions.forEach(noteQuestion -> {
            Query query = new Query();
            query.addCriteria(Criteria.where("id").is(noteQuestion.getQuestionId()));
            switch (Objects.requireNonNull(mongoTemplate.findOne(query, Question.class, "question")).getType()) {
                case 0:
                    easy.getAndIncrement();
                    break;
                case 1:
                    secondary.getAndIncrement();
                    break;
                default:
                    difficult.getAndIncrement();
            }
        });
        log.debug("简单难度次数: {}", easy);
        log.debug("中等难度次数: {}", secondary.longValue());
        log.debug("困难难度次数: {}", difficult.get());
        List<ReportVo> reportVos = new ArrayList<ReportVo>();
        reportVos.add(new ReportVo("简单题数", easy.longValue()));
        reportVos.add(new ReportVo("中等题数", secondary.longValue()));
        reportVos.add(new ReportVo("困难题数    ", easy.longValue()));
        return QuestionReportVo.builder().totalNum(totalNum.longValue()).testNum(userNum.getTestNum())
                .playNum(totalNum - userNum.getTestNum()).rightNum(userNum.getRightNum()).series(reportVos).build();
    }

    @Override
    public UserNum getByUserId(Long id) {
        return userNumMapper.selectOne(new QueryWrapper<UserNum>().eq("user_id", id));
    }
}
