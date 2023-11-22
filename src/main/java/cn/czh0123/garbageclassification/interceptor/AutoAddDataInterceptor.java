package cn.czh0123.garbageclassification.interceptor;

import cn.czh0123.garbageclassification.anno.AutoAddIntegral;
import cn.czh0123.garbageclassification.anno.AutoAddStudyNum;
import cn.czh0123.garbageclassification.pojo.domain.Integral;
import cn.czh0123.garbageclassification.pojo.domain.UserNum;
import cn.czh0123.garbageclassification.service.IIntegralService;
import cn.czh0123.garbageclassification.service.IUserNumService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 自动添加数据拦截器
 */
@Slf4j
@Component
public class AutoAddDataInterceptor implements HandlerInterceptor {

    @Autowired
    private IIntegralService integralService;
    @Autowired
    private IUserNumService userNumService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("开启拦截器--CheckLoginInterceptor");
        //判断一下，跨域问题
        /*if (handler instanceof HandlerMethod) {
            log.info("不是跨域--放行");
            return true;
        }*/
        //判断是否有自定义注解
        try{
            HandlerMethod hm = (HandlerMethod) handler;
            if (hm.hasMethodAnnotation(AutoAddIntegral.class)) {
                long userId = getUserId(request);
                //自动增加积分
                addIntegral(userId);
            }
            if (hm.hasMethodAnnotation(AutoAddStudyNum.class)) {
                long userId = getUserId(request);
                //自动增加学习次数
                addStudyNum(userId);
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 获取路径中的userId
     *
     * @param request
     * @return
     */
    private long getUserId(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        Iterator<Map.Entry<String, String[]>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String[]> me = iterator.next();
            String key = me.getKey();
            String value = me.getValue()[0];
            map.put(key, value);
        }
        return Long.parseLong(map.get("userId").toString());
    }

    /**
     * 增加学习次数
     * @param userId
     */
    private void addStudyNum(Long userId) {
        UserNum userNum = userNumService.getOne(new QueryWrapper<UserNum>().eq("user_id", userId));
        userNum.setStudyNum(userNum.getStudyNum() + 1);
        userNumService.saveOrUpdate(userNum);
    }

    /**
     * 增加积分
     *
     * @param userId
     */
    private void addIntegral(Long userId) {
        Integral integral = integralService.getOne(new QueryWrapper<Integral>().eq("user_id", userId));
        integral.setIntegral(integral.getIntegral() + 100);
        integralService.saveOrUpdate(integral);
    }


}
