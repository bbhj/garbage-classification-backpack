package cn.czh0123.garbageclassification.service;

import cn.czh0123.garbageclassification.pojo.domain.NoteQuestion;
import cn.czh0123.garbageclassification.query.NoteQuestionQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 笔记服务接口
 */
public interface INoteQuestionService extends IService<NoteQuestion>{
    /**
    * 分页
    * @param qo
    * @return
    */
    IPage<NoteQuestion> queryPage(NoteQuestionQuery qo);

//    List<NoteVO> findWongQuestion(Long userId);
}
