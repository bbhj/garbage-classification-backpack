package cn.czh0123.garbageclassification.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 百度AI 识别
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Advanced implements Serializable {
    private String score;
    private String root;
    private String keyword;
}
