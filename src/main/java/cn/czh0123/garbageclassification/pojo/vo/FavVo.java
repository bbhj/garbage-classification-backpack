package cn.czh0123.garbageclassification.pojo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FavVo {
    private Long queId;
    private String title;
    private String answer;

    public FavVo(Long queId, String title, String answer) {
        this.queId = queId;
        this.title = title;
        this.answer = answer.substring(3);
    }
}
