package cn.czh0123.garbageclassification.mongo.domain;

import cn.czh0123.garbageclassification.pojo.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试题
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("question")
public class Question extends BaseDomain {

    public static final int TYPE_SIMPLE = 0;
    public static final int TYPE_SECONDARY = 1;
    public static final int TYPE_DIFFICULTY = 2;

//    public static final int ANSWER_FALSE = 0;
//    public static final int ANSWER_TRUE = 1;
    public static final int ANSWER_A = 0;
    public static final int ANSWER_B = 1;
    public static final int ANSWER_C = 2;
    public static final int ANSWER_D = 3;

    @Id
    private ObjectId _id;
    private Integer type;
    private String question;
    private List<String> options = new ArrayList<>();
    private Integer answer;
    private String imgUrl;
}
