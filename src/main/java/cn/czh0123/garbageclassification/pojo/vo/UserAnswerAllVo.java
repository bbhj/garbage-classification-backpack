package cn.czh0123.garbageclassification.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswerAllVo {
    private String question;
    private Integer userAnswer;
    private Integer rightAnswer;
    private List<OptionsVo> options = new ArrayList<>();
}
