package cn.czh0123.garbageclassification.mongo.service.impl;

import cn.czh0123.garbageclassification.mongo.domain.Question;
import cn.czh0123.garbageclassification.mongo.repository.QuestionRepository;
import cn.czh0123.garbageclassification.mongo.service.IQuestionService;
import cn.czh0123.garbageclassification.pojo.vo.Option;
import cn.czh0123.garbageclassification.pojo.vo.QuestionsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<QuestionsVo> findQuestionsByType(Integer type) {
        List<QuestionsVo> questionsVOS = new ArrayList<>();
        List<Question> questions = questionRepository.findQuestionsByType(type);
        Random random = new Random();
        random.ints(20,0, questions.size()).distinct()
                .limit(5).forEach(r -> {
            Question question = questions.get(r);
            QuestionsVo questionsVO = new QuestionsVo();
            BeanUtils.copyProperties(question, questionsVO);
            List<Option> options = new ArrayList<>();
            for (int i = 0; i < question.getOptions().size(); i++) {
                Option option = new Option();
                option.setValue(i);
                option.setText(question.getOptions().get(i));
                options.add(option);
            }
            questionsVO.setOptions(options);
            questionsVOS.add(questionsVO);
        });
        return questionsVOS;
    }
}
