package cn.czh0123.garbageclassification.pojo.vo;

import cn.czh0123.garbageclassification.pojo.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteVo extends BaseDomain {
    private Integer type;
    private String question;
    private List<String> options = new ArrayList<>();
    private Integer answer;
    private String imgUrl;
    private Integer userChoose;
    private Date testDateTime;
}
