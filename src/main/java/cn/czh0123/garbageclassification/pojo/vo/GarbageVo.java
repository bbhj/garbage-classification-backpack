package cn.czh0123.garbageclassification.pojo.vo;

import cn.czh0123.garbageclassification.pojo.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GarbageVo extends BaseDomain {
    private String name;
    private String content;
    private String type;
    private String typeId;
}
