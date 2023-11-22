package cn.czh0123.garbageclassification.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionReportVo {
    private Long totalNum;
    private Long playNum;
    private Long testNum;
    private Long rightNum;
    private List<ReportVo> series;
}
