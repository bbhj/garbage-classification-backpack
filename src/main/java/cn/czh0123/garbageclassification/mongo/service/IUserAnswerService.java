package cn.czh0123.garbageclassification.mongo.service;

import cn.czh0123.garbageclassification.mongo.domain.UserAnswer;
import cn.czh0123.garbageclassification.pojo.dto.UserAnswerDto;
import cn.czh0123.garbageclassification.pojo.vo.UserAnswerAllVo;
import cn.czh0123.garbageclassification.pojo.vo.UserAnswerVo;

import java.util.List;

/**
 * 答题卡服务层接口
 */
public interface IUserAnswerService {

    /**
     * 新增用户答题卡
     * @param userAnswer
     */
    void insert(UserAnswerDto userAnswer);

    /**
     * 根据用户ID查询所有UserAnswerVO
     * @param userId
     * @return
     */
    List<UserAnswerVo> list(Long userId);

    /**
     * 根据考试ID查询答题卡
     * @param examId
     * @return
     */
    List<UserAnswerAllVo> getAnswerByExamId(Long examId);
}
