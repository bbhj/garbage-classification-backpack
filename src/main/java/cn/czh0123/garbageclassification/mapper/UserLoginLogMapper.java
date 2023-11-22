package cn.czh0123.garbageclassification.mapper;


import cn.czh0123.garbageclassification.pojo.domain.UserLoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.Map;

/**
* 用户登录日志持久层接口
*/
@Mapper
public interface UserLoginLogMapper extends BaseMapper<UserLoginLog>{

    /**
     * 周活跃度
     * @return
     */
    @Select("SELECT " +
            "IFNULL(SUM(CASE WHEN DAYOFWEEK(login_time) = 1 THEN 1 ELSE 0 END), 0) AS sunday, " +
            "IFNULL(SUM(CASE WHEN DAYOFWEEK(login_time) = 2 THEN 1 ELSE 0 END), 0) AS monday, " +
            "IFNULL(SUM(CASE WHEN DAYOFWEEK(login_time) = 3 THEN 1 ELSE 0 END), 0) AS tuesday, " +
            "IFNULL(SUM(CASE WHEN DAYOFWEEK(login_time) = 4 THEN 1 ELSE 0 END), 0) AS wednesday, " +
            "IFNULL(SUM(CASE WHEN DAYOFWEEK(login_time) = 5 THEN 1 ELSE 0 END), 0) AS thursday, " +
            "IFNULL(SUM(CASE WHEN DAYOFWEEK(login_time) = 6 THEN 1 ELSE 0 END), 0) AS friday, " +
            "IFNULL(SUM(CASE WHEN DAYOFWEEK(login_time) = 7 THEN 1 ELSE 0 END), 0) AS saturday " +
            "FROM user_login_log")
    Map<String, BigDecimal> selectLoginCountByDayOfWeek();
}
