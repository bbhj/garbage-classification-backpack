package cn.czh0123.garbageclassification.pojo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("garbage_type")
public class GarbageType extends BaseDomain {
    private String name;
    private String garbageClass;
    private String detail;
    private Long typeId;
    private String imgUrl;
}
