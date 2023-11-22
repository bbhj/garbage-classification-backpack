package cn.czh0123.garbageclassification.mongo.repository;

import cn.czh0123.garbageclassification.mongo.domain.Question;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends MongoRepository<Question, ObjectId> {
    /**
     * 通过题型查询题目
     * @param type
     * @return
     */
    List<Question> findQuestionsByType(Integer type);

    /**
     * 根据题目ID查询题目
     * @param id
     * @return
     */
    Question findQuestionById(Long id);
}
