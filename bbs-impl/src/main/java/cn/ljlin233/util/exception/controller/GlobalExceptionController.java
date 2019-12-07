package cn.ljlin233.util.exception.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.NoHandlerFoundException;

import cn.ljlin233.util.exception.entity.DataCheckedException;
import cn.ljlin233.util.exception.entity.ExceptionResponse;
import cn.ljlin233.util.exception.entity.QueryException;
import cn.ljlin233.util.exception.entity.SystemException;

/**
 * GlobalExceptionController
 *
 * @author lvjinlin42@foxmail.com
 */
@ControllerAdvice
public class GlobalExceptionController extends DispatcherServlet {

    private static final long serialVersionUID = 5449367539552766714L;

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public Map<String, String> handleNotFound404Exception(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "404");
        map.put("url", request.getRequestURI());
        return map;
    }

    @ExceptionHandler(DataCheckedException.class)
    @ResponseBody
    public ExceptionResponse handleDataCheckedException(DataCheckedException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setCode("400");
        exceptionResponse.setMessage(ex.getMessage());
        exceptionResponse.setError(ex.getError());

        return exceptionResponse;
    }

    @ExceptionHandler(SystemException.class)
    @ResponseBody
    public ExceptionResponse handleSystemException(SystemException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setCode("500");
        exceptionResponse.setMessage(ex.getFriendMessage());
        exceptionResponse.setError(ex.getMessage());

        return exceptionResponse;
    }

    @ExceptionHandler(QueryException.class)
    @ResponseBody
    public ExceptionResponse handleQueryException(QueryException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setCode("500");
        exceptionResponse.setMessage(ex.getFriendMessage());
        exceptionResponse.setError(ex.getMessage());

        return exceptionResponse;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ExceptionResponse handleAllException(Exception ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setCode("500");
        exceptionResponse.setMessage("服务器内部错误");
        exceptionResponse.setError(ex.getMessage());
        ex.printStackTrace();
        return exceptionResponse;
    }

}