package cn.czh0123.garbageclassification.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author czh
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult<T> {
    public static final int CODE_SUCCESS = 200;
    public static final String MSG_SUCCESS = "操作成功";
    public static final int CODE_ERROR = 500;
    public static final String MSG_ERROR = "系统异常，请联系管理员";
    public static final int CODE_NOT_LOGIN = 505;
    public static final String MSG_NOT_LOGIN = "请先登录！";
    
    private int code;
    private String msg;
    private T data;

    public static <T> JsonResult success(T data) {
        return new JsonResult(CODE_SUCCESS, MSG_SUCCESS, data);
    }

    public static <T> JsonResult success() {
        return new JsonResult(CODE_SUCCESS, MSG_SUCCESS, null);
    }

    public static <T> JsonResult error(int code, String msg, T data) {
        return new JsonResult(code, msg, data);
    }

    public static <T> JsonResult defaultError() {
        return new JsonResult(CODE_ERROR, MSG_ERROR, null);
    }
}