package cn.czh0123.garbageclassification.pojo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("product")
public class Product extends BaseDomain {
    private String productName;
    private String productImg;
    private String content;
    private Long nowIntegral;
    private Long oldIntegral;
    private Long typeId;
}
