package cn.czh0123.garbageclassification.pojo.vo;

import cn.czh0123.garbageclassification.pojo.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo extends BaseDomain {
    private String username;
    private String phone;
    private String headImg;
    private String openId;
}
