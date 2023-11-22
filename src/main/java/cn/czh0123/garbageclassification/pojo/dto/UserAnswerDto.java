package cn.czh0123.garbageclassification.pojo.dto;

import cn.czh0123.garbageclassification.pojo.domain.BaseDomain;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswerDto extends BaseDomain {

    private Long userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Long commitTime;
    private List<Long> questionIds = new ArrayList<>();
    private List<Integer> userAnswer = new ArrayList<>();
    private List<Integer> rightAnswer = new ArrayList<>();
    private Long examId;
    private Integer type;
    private Integer difficulty;
}
