package cn.czh0123.garbageclassification.pojo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteQuestion extends BaseDomain {
    private Long userId;
    private Long questionId;
    private Integer myChoose;
    private Integer type;
    private Long examId;
}
