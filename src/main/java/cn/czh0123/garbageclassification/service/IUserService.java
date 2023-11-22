package cn.czh0123.garbageclassification.service;

import cn.czh0123.garbageclassification.pojo.domain.User;
import cn.czh0123.garbageclassification.pojo.dto.SaveOrUpdateUserDto;
import cn.czh0123.garbageclassification.pojo.vo.AnalysisReport;
import cn.czh0123.garbageclassification.pojo.vo.UserVo;
import cn.czh0123.garbageclassification.query.UserQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 用户服务接口
 */
public interface IUserService extends IService<User>{
    /**
    * 分页
    * @param qo
    * @return
    */
    IPage<User> queryPage(UserQuery qo);

    /**
     * 根据微信openId查询用户
     * @param openId
     * @return
     */
    User getByOpenId(String openId);

    /**
     * 登录
     * @return
     */
    User login(String jsCode, HttpServletRequest request);

    /**
     * 查询所有用户
     * @return
     */
    List<UserVo> findAllUsers();

    /**
     * 保存或修改
     * @param userDto
     * @return
     */
    Boolean saveOrUpdate(SaveOrUpdateUserDto userDto);

    /**
     * 用户周活跃度
     * @return
     */
    List<AnalysisReport> weeklyUserActivity();

}
