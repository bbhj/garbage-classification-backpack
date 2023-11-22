package cn.czh0123.garbageclassification.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeMsg implements Serializable {
    private Integer code;
    private String msg;
}
