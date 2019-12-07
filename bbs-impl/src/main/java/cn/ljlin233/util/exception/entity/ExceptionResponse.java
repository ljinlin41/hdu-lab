package cn.ljlin233.util.exception.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ExceptionResponse
 *
 * @author lvjinlin42@foxmail.com
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

    private String code;

    private String message;

    private String error;

}