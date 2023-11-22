package cn.czh0123.garbageclassification.mapper;


import cn.czh0123.garbageclassification.pojo.domain.Exchange;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
* 兑换持久层接口
*/
@Mapper
public interface ExchangeMapper extends BaseMapper<Exchange>{

    /**
     * 获取每月销售额
     * @return
     */
    @Select("SELECT\n" +
            "    CONCAT(LOWER(SUBSTRING(MONTHNAME(DATE_ADD('2000-01-01', INTERVAL months.month - 1 MONTH)), 1, 1)), SUBSTRING(MONTHNAME(DATE_ADD('2000-01-01', INTERVAL months.month - 1 MONTH)), 2)) AS month,\n" +
            "    COALESCE(exchange_count, 0) AS exchange_count\n" +
            "FROM\n" +
            "    (SELECT 1 AS month UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10 UNION SELECT 11 UNION SELECT 12) AS months\n" +
            "    LEFT JOIN\n" +
            "    (SELECT MONTH(exchange_time) AS month, COUNT(*) AS exchange_count FROM exchange WHERE YEAR(exchange_time) = YEAR(CURRENT_DATE()) GROUP BY MONTH(exchange_time)) AS exchange_counts\n" +
            "    ON months.month = exchange_counts.month\n" +
            "ORDER BY\n" +
            "    months.month;\n")
    List<Map<String, Object>> monthlySales();
}
