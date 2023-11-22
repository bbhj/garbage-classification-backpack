package cn.czh0123.garbageclassification.pojo.vo;

import lombok.*;

/**
 * @author: czh
 * @description:
 * @date: 2023/5/8 21:30
 */
@Getter
@Setter
public class AnalysisReport {
    private String name;
    private Long value;

    private Long estimate;
    private Long actual = 0L;
}
