package cn.czh0123.garbageclassification.pojo.vo;

import cn.czh0123.garbageclassification.pojo.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionsVo extends BaseDomain {

    private Integer type;
    private String question;
    private List<Option> options = new ArrayList<>();
    private Integer answer;
    private String imgUrl;


/*    public String getQuestion() {
        return "<h4>"+question+"</h4>";
    }*/
}

