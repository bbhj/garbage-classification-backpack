package cn.czh0123.garbageclassification.util;

import cn.czh0123.garbageclassification.config.wechat.Code2Session;
import cn.czh0123.garbageclassification.config.wechat.WxLogin;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Component
public class WeChatUtil {
    @Autowired
    private WxLogin wxLoginProperties;

    private static WxLogin wxLogin;
    @PostConstruct
    public void init() {
        wxLogin = wxLoginProperties;
    }
    public static String getOpenId(String jsCode) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=" + wxLogin.getAppid() +
                "&secret=" + wxLogin.getSecret() +
                "&js_code=" + jsCode +
                "&grant_type=" + wxLogin.getGrantType();
        String json = restTemplate.getForObject(url, String.class);
        System.out.println(json);
        Code2Session code2Session = JSON.parseObject(json, Code2Session.class);
        assert code2Session != null;
        return code2Session.getOpenid();
    }
}
