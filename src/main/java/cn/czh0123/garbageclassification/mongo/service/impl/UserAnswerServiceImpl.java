package cn.czh0123.garbageclassification.mongo.service.impl;

import cn.czh0123.garbageclassification.pojo.domain.Examination;
import cn.czh0123.garbageclassification.pojo.domain.Integral;
import cn.czh0123.garbageclassification.pojo.domain.NoteQuestion;
import cn.czh0123.garbageclassification.pojo.domain.UserNum;
import cn.czh0123.garbageclassification.mapper.ExaminationMapper;
import cn.czh0123.garbageclassification.mongo.domain.Question;
import cn.czh0123.garbageclassification.mongo.domain.UserAnswer;
import cn.czh0123.garbageclassification.mongo.repository.QuestionRepository;
import cn.czh0123.garbageclassification.mongo.repository.UserAnswerRepository;
import cn.czh0123.garbageclassification.mongo.service.IUserAnswerService;
import cn.czh0123.garbageclassification.pojo.dto.UserAnswerDto;
import cn.czh0123.garbageclassification.pojo.vo.OptionsVo;
import cn.czh0123.garbageclassification.pojo.vo.UserAnswerAllVo;
import cn.czh0123.garbageclassification.pojo.vo.UserAnswerVo;
import cn.czh0123.garbageclassification.service.IIntegralService;
import cn.czh0123.garbageclassification.service.INoteQuestionService;
import cn.czh0123.garbageclassification.service.IUserNumService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserAnswerServiceImpl implements IUserAnswerService {

    @Autowired
    private UserAnswerRepository userAnswerRepository;
    @Autowired
    private IIntegralService integralService;
    @Autowired
    private INoteQuestionService noteQuestionService;
    @Autowired
    private IUserNumService userNumService;
    @Autowired
    private ExaminationMapper examinationMapper;
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void insert(UserAnswerDto userAnswerDto) {
        UserAnswer userAnswer = new UserAnswer();
        BeanUtils.copyProperties(userAnswerDto, userAnswer);
        Date date = new Date();
        Examination examination = new Examination();
        examination.setTestTime(date);
        examinationMapper.insert(examination);
        userAnswer.setCommitTime(date.getTime());
        userAnswer.setExamId(examination.getId());
        userAnswer.setType(userAnswer.getType() == UserAnswer.TYPE_PRACTISE ? UserAnswer.TYPE_PRACTISE : UserAnswer.TYPE_TEST);
        //增加错题
        List<NoteQuestion> noteQuestions = new ArrayList<>();
        long count = 0L;
        for (int i = 0; i < userAnswer.getQuestionIds().size(); i++) {
            NoteQuestion question = new NoteQuestion();
            question.setUserId(userAnswer.getUserId());
            question.setQuestionId(userAnswer.getQuestionIds().get(i));
            question.setMyChoose(userAnswer.getUserAnswer().get(i));
            question.setExamId(examination.getId());
            //正确
            if (userAnswer.getUserAnswer().get(i).equals(userAnswer.getRightAnswer().get(i))) {
                question.setType(0);
                count++;
            } else {
                question.setType(1);
            }
            noteQuestions.add(question);
        }
        //增加积分
//      long rightCount = userAnswer.getRightAnswer().stream().filter(userAnswer.getUserAnswer()::contains).count();
//        Integral integral = integralService.getOne(new QueryWrapper<Integral>().eq("user_id", userAnswer.getUserId()));
        if (userAnswer.getType() == 1) {
            Integral integral = integralService.getOne(new QueryWrapper<Integral>().eq("user_id", userAnswer.getUserId()));
            if (StringUtils.isEmpty(integral)) {
                integral.setUserId(userAnswer.getUserId());
                integral.setIntegral(count * 100);
            } else {
                integral.setIntegral(integral.getIntegral() + (count * 100));
            }
            integralService.saveOrUpdate(integral);
        }
        //测试次数+1
        UserNum userNum = userNumService.getOne(new QueryWrapper<UserNum>().eq("user_id", userAnswer.getUserId()));
        userNum.setUserId(userAnswer.getUserId());
        if (StringUtils.isEmpty(userNum)) {
            userNum.setRightNum(count);
            if (userAnswer.getType() == 1) {
                userNum.setTestNum(1L);
            } else {
                userNum.setStudyNum(1L);
            }
        } else {
            userNum.setRightNum(userNum.getRightNum() + count);
            if (userAnswer.getType() == 1) {
                userNum.setTestNum(userNum.getTestNum() + 1);
            } else {
                userNum.setStudyNum(userNum.getStudyNum() + 1);
            }

        }
        userNumService.saveOrUpdate(userNum);

        noteQuestionService.saveBatch(noteQuestions);
        userAnswerRepository.insert(userAnswer);
    }

    @Override
    public List<UserAnswerVo> list(Long userId) {
        List<UserAnswerVo> userAnswerVOS = new ArrayList<>();
        List<UserAnswer> userAnswers = userAnswerRepository.findUserAnswersByUserIdOrderByCommitTimeDesc(userId);
//        long count = userAnswers.stream().filter(userAnswer -> userAnswer.getUserAnswer() == userAnswer.getRightAnswer()).count();
        userAnswers.forEach(userAnswer -> {
            UserAnswerVo userAnswerVO = new UserAnswerVo();
            BeanUtils.copyProperties(userAnswer, userAnswerVO);
            userAnswerVO.setRightNum(noteQuestionService.count(new QueryWrapper<NoteQuestion>().eq("user_id", userAnswer.getUserId())
                    .eq("exam_id", userAnswer.getExamId())
                    .eq("type", 0)));
            userAnswerVOS.add(userAnswerVO);
        });

        return userAnswerVOS;
    }

    @Override
    public List<UserAnswerAllVo> getAnswerByExamId(Long examId) {
        List<UserAnswerAllVo> userAnswerAllVOS = new ArrayList<>();
        UserAnswer userAnswer = userAnswerRepository.findUserAnswerByExamId(examId);
/*        userAnswer.getQuestionIds()
                .forEach(questionId -> list.add(questionRepository.findQuestionById(questionId).getQuestion()));*/
        for (int i = 0; i < userAnswer.getQuestionIds().size(); i++) {
            UserAnswerAllVo userAnswerAllVO = new UserAnswerAllVo();
            Question question = questionRepository.findQuestionById(userAnswer.getQuestionIds().get(i));
            userAnswerAllVO.setQuestion(question.getQuestion());
            userAnswerAllVO.setUserAnswer(userAnswer.getUserAnswer().get(i));
            userAnswerAllVO.setRightAnswer(userAnswer.getRightAnswer().get(i));

            List<String> options = question.getOptions();
            List<OptionsVo> optionsVOS = new ArrayList<>();
            int index = 0;
            for (String option : options) {
                OptionsVo optionsVO = new OptionsVo();
                optionsVO.setValue(index);
                optionsVO.setText(option);
                optionsVOS.add(optionsVO);
                index++;
            }

            userAnswerAllVO.setOptions(optionsVOS);
            userAnswerAllVOS.add(userAnswerAllVO);
        }

        return userAnswerAllVOS;
    }
}
