package cn.czh0123.garbageclassification.pojo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Examination extends BaseDomain {
    private Date testTime;
}
