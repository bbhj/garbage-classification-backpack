
import cn.czh0123.garbageclassification.GCBApplication;
import cn.czh0123.garbageclassification.mongo.domain.UserAnswer;
import cn.czh0123.garbageclassification.mongo.repository.QuestionRepository;
import cn.czh0123.garbageclassification.mongo.repository.UserAnswerRepository;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.czh0123.garbageclassification.mongo.domain.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = GCBApplication.class)
@RunWith(SpringRunner.class)
public class TestMongo {

    @Autowired
    private QuestionRepository repository;
    @Autowired
    private UserAnswerRepository userAnswerRepository;

    @Test
    public void testSaveOrUpdate() {
        Question question = new Question();
        question.set_id(new ObjectId());
        question.setType(Question.TYPE_DIFFICULTY);
        question.setQuestion("属于其他垃圾的电池类包括下列哪些？");
        question.setAnswer(Question.ANSWER_A);
        question.setId(22L);
        question.setImgUrl("https://bpic.588ku.com/element_origin_min_pic/19/07/30/b99dc9690db360d721fab30f551c475e.jpg");
        List<String> list = new ArrayList<String>();
        list.add("A. 碱性电池");
        list.add("B. 蓄电池 ");
        list.add("C. 旧充电池");
        list.add("D. 纽扣电池");
        question.setOptions(list);

        repository.insert(question);
    }

    @Test
    public void testFindQuestionsByType() {
        repository.findQuestionsByType(Question.TYPE_SIMPLE).forEach(System.out::println);
    }

    @Test
    public void testInsertUserAnswer() {
        UserAnswer userAnswer = new UserAnswer();
        userAnswer.set_id(new ObjectId());
        userAnswer.setUserId(1L);
        ArrayList<Long> questionsIds = new ArrayList<>();
        questionsIds.add(1L);
        questionsIds.add(2L);
        questionsIds.add(3L);
        questionsIds.add(4L);
        questionsIds.add(5L);
        userAnswer.setQuestionIds(questionsIds);
        userAnswer.setUserAnswer(Arrays.asList(2, 3, 1, 0, 2));
        userAnswer.setRightAnswer(Arrays.asList(2, 3, 1, 0, 3));
        userAnswer.setId(1L);

        userAnswerRepository.insert(userAnswer);
    }
}
