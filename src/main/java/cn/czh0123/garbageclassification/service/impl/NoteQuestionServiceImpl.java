package cn.czh0123.garbageclassification.service.impl;

import cn.czh0123.garbageclassification.pojo.domain.NoteQuestion;
import cn.czh0123.garbageclassification.mapper.NoteQuestionMapper;
import cn.czh0123.garbageclassification.mongo.repository.QuestionRepository;
import cn.czh0123.garbageclassification.mongo.repository.UserAnswerRepository;
import cn.czh0123.garbageclassification.query.NoteQuestionQuery;
import cn.czh0123.garbageclassification.service.INoteQuestionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* 笔记服务接口实现
*/
@Service
@Transactional
public class NoteQuestionServiceImpl extends ServiceImpl<NoteQuestionMapper,NoteQuestion> implements INoteQuestionService  {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserAnswerRepository userAnswerRepository;

    @Override
    public IPage<NoteQuestion> queryPage(NoteQuestionQuery qo) {
        IPage<NoteQuestion> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<NoteQuestion> wrapper = Wrappers.<NoteQuestion>query();
        return super.page(page, wrapper);
    }

}
