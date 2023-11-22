package cn.czh0123.garbageclassification.pojo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("user_num")
public class UserNum extends BaseDomain{
    private Long userId;
    private Long scanNum;
    private Long studyNum;
    private Long testNum;
    private Long rightNum;
}
