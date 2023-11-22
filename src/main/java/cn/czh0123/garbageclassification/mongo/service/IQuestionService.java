package cn.czh0123.garbageclassification.mongo.service;

import cn.czh0123.garbageclassification.pojo.vo.QuestionsVo;

import java.util.List;

/**
 * 测试题服务层接口
 */
public interface IQuestionService {

    /**
     * 根据题型随机返回五道题目
     * @param type
     * @return
     */
    List<QuestionsVo> findQuestionsByType(Integer type);

}
