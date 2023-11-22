package cn.czh0123.garbageclassification.config.wechat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Code2Session {
    private String session_key;
    private String openid;
}
