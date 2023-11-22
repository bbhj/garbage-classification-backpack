package cn.czh0123.garbageclassification.mongo.repository;

import cn.czh0123.garbageclassification.mongo.domain.UserAnswer;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAnswerRepository extends MongoRepository<UserAnswer, ObjectId> {

    /**
     * 根据用户ID返回用户的答题卡
     * @param id
     * @return
     */
//    UserAnswer findUserAnswerByUserId(Long id);

    /**
     * 根据用户ID查询所有答题卡 按照时间排序
     * @param userId
     * @return
     */
    List<UserAnswer> findUserAnswersByUserIdOrderByCommitTimeDesc(Long userId);

    /**
     * 根据考试ID查询答题卡
     * @param examId
     * @return
     */
    UserAnswer findUserAnswerByExamId(Long examId);
}
