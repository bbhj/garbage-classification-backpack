package cn.czh0123.garbageclassification.pojo.dto;

import cn.czh0123.garbageclassification.pojo.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveOrUpdateUserDto {
    private Long userId;
    private String username;
    private String openId;
    private String headImg;
    private String phone;
}
