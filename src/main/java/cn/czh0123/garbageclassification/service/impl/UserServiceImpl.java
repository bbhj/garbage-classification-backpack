package cn.czh0123.garbageclassification.service.impl;

import cn.czh0123.garbageclassification.enums.DayOfWeekEnum;
import cn.czh0123.garbageclassification.exception.BusinessException;
import cn.czh0123.garbageclassification.mapper.UserLoginLogMapper;
import cn.czh0123.garbageclassification.pojo.domain.Integral;
import cn.czh0123.garbageclassification.pojo.domain.User;
import cn.czh0123.garbageclassification.pojo.domain.UserLoginLog;
import cn.czh0123.garbageclassification.pojo.domain.UserNum;
import cn.czh0123.garbageclassification.mapper.UserMapper;
import cn.czh0123.garbageclassification.pojo.dto.SaveOrUpdateUserDto;
import cn.czh0123.garbageclassification.pojo.vo.AnalysisReport;
import cn.czh0123.garbageclassification.pojo.vo.UserVo;
import cn.czh0123.garbageclassification.query.UserQuery;
import cn.czh0123.garbageclassification.service.IIntegralService;
import cn.czh0123.garbageclassification.service.IUserLoginLogService;
import cn.czh0123.garbageclassification.service.IUserNumService;
import cn.czh0123.garbageclassification.service.IUserService;
import cn.czh0123.garbageclassification.util.JsonResult;
import cn.czh0123.garbageclassification.util.WeChatUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import sun.security.pkcs11.wrapper.Functions;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 用户服务接口实现
 */
@Slf4j
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private IIntegralService integralService;
    @Autowired
    private IUserNumService userNumService;
    @Autowired
    private IUserLoginLogService userLoginLogService;
    @Resource
    private UserLoginLogMapper userLoginLogMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public IPage<User> queryPage(UserQuery qo) {
        IPage<User> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<User> wrapper = Wrappers.<User>query()
                .like(!StringUtils.isEmpty(qo.getOpenId()), "open_id", qo.getOpenId())
                .like(!StringUtils.isEmpty(qo.getUsername()), "username", qo.getUsername());
        return super.page(page, wrapper);
    }

    @Override
    public User getByOpenId(String openId) {
        return super.getOne(new QueryWrapper<User>().eq("open_id", openId));
    }

    @Override
    public User login(String jsCode, HttpServletRequest request) {
        String openId = WeChatUtil.getOpenId(jsCode);
        User user = this.getByOpenId(openId);

        if (StringUtils.isEmpty(user)) {
            user = User.builder().openId(openId).username("微信用户-" + UUID.randomUUID().toString().replace("-", "").substring(0, 5)).headImg("https://czh0123-test.oss-cn-guangzhou.aliyuncs.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20210702010840.jpg")
                    .password("112233").phone("12345678").build();
            super.save(user);
            userNumService.save(UserNum.builder().userId(user.getId())
                    .scanNum(0L).studyNum(0L).testNum(0L).rightNum(0L).build());
            integralService.save(new Integral(user.getId(), 0L));
        }
        userLoginLogService.save(UserLoginLog.builder().userId(user.getId()).loginTime(LocalDateTime.now()).addr(request.getRemoteAddr()).build());

        return user;
    }

    @Override
    public List<UserVo> findAllUsers() {
        List<UserVo> list = new ArrayList<>();
        userMapper.selectList(new QueryWrapper<User>()).forEach(user -> {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user, userVo);
            list.add(userVo);
        });
        return list;
    }

    @Override
    public Boolean saveOrUpdate(SaveOrUpdateUserDto userDto) {
        log.info("用户Dto userDto：{}", userDto);
        if (StringUtils.isEmpty(userDto.getUsername()) || StringUtils.isEmpty(userDto.getPhone()) || StringUtils.isEmpty(userDto.getOpenId()) ||
                StringUtils.isEmpty(userDto.getHeadImg())) {
            throw new BusinessException(JsonResult.CODE_ERROR, "用户信息未填写完整!");
        }
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        int i = 0;
        log.info("用户user：{}", user);
        if (userDto.getUserId() == null || userDto.getUserId() == 0) {
            user.setId(null);
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("open_id", userDto.getOpenId());
            Integer count = userMapper.selectCount(wrapper);
            if (count > 0) {
                throw new BusinessException(JsonResult.CODE_ERROR, "该用户已存在！");
            }
            i = userMapper.insert(user);
        } else {
            user.setId(userDto.getUserId());
            i = userMapper.updateById(user);
        }
        return i == 1;
    }

    @Override
    public List<AnalysisReport> weeklyUserActivity() {
        List<AnalysisReport> list = new ArrayList<>();
        Map<String, BigDecimal> map = userLoginLogMapper.selectLoginCountByDayOfWeek();
        for (String s : map.keySet()) {
            AnalysisReport analysisReport = new AnalysisReport();
            analysisReport.setName("analysis." + s);
            analysisReport.setValue(map.get(s).longValue());
            list.add(analysisReport);
        }
        return list;
    }
}
