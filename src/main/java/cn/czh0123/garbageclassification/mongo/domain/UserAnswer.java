package cn.czh0123.garbageclassification.mongo.domain;

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
@Document("user_answer")
public class UserAnswer extends BaseDomain {

    public static final int TYPE_PRACTISE = 0;
    public static final int TYPE_TEST = 1;

    @Id
    private ObjectId _id;
    private Long userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Long commitTime;
//    private Date commitTime;
    private List<Long> questionIds = new ArrayList<>();
    private List<Integer> userAnswer = new ArrayList<>();
    private List<Integer> rightAnswer = new ArrayList<>();
    private Long examId;
    private Integer type;
    private Integer difficulty;
}
