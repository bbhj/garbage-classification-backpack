package cn.czh0123.garbageclassification.pojo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("coordinate")
public class Coordinate extends BaseDomain {
    private String longitude;
    private String latitude;
    private String iconPath;
    private double width;
    private double height;
}
