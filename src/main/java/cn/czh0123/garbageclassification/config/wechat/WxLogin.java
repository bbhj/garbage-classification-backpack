package cn.czh0123.garbageclassification.config.wechat;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wx-login")
public class WxLogin {
    private String appid;
    private String secret;
    private String grantType;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    @Override
    public String toString() {
        return "WxLogin{" +
                "appid='" + appid + '\'' +
                ", secret='" + secret + '\'' +
                ", grantType='" + grantType + '\'' +
                '}';
    }
}
