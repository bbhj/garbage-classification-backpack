package cn.czh0123.garbageclassification.service;

import cn.czh0123.garbageclassification.pojo.domain.UserNum;
import cn.czh0123.garbageclassification.pojo.vo.QuestionReportVo;
import cn.czh0123.garbageclassification.pojo.vo.SearchGarbageVo;
import cn.czh0123.garbageclassification.query.UserNumQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;


/**
 * 用户各次数服务接口
 */
public interface IUserNumService extends IService<UserNum> {
    /**
     * 分页
     *
     * @param qo 分页参数
     * @return
     */
    IPage<UserNum> queryPage(UserNumQuery qo);

    /**
     * AI扫描图片
     *
     * @param file 图片
     * @return
     */
    SearchGarbageVo scan(MultipartFile file, Long userId);

    /**
     * 获取题集页面图标数据
     * @param id 用户ID
     * @return
     */
    QuestionReportVo getReportData(Long id);

    /**
     * 获取用户各数据次数
     * @param id
     * @return
     */
    UserNum getByUserId(Long id);

}
