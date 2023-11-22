package cn.czh0123.garbageclassification.advice;

import cn.czh0123.garbageclassification.exception.BusinessException;
import cn.czh0123.garbageclassification.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class CommonExceptionHandler {
    private static Logger log = LoggerFactory.getLogger(CommonExceptionHandler.class);
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public JsonResult handlerBusinessException(BusinessException e, HttpServletResponse response) {
        log.error("异常", e);
        return JsonResult.error(JsonResult.CODE_ERROR, e.getMessage(), null);
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseBody
    public JsonResult handlerNumberFormatException(NumberFormatException e, HttpServletResponse response) {
        log.error("异常", e);
        return JsonResult.error(JsonResult.CODE_NOT_LOGIN, JsonResult.MSG_NOT_LOGIN, null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult handlerException(Exception e, HttpServletResponse response) {
        log.error("异常", e);
        return JsonResult.defaultError();
    }
}
