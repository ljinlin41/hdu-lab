package cn.ljlin233.util.exception.entity;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * DataCheckedException
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Setter
@Getter
public class DataCheckedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String message;

    private String error;

    public DataCheckedException() {}

    public DataCheckedException(String message) {
        this(message, null);
    }

    public DataCheckedException(String message, String error) {
        this.message = message;
        this.error = error;
    }
}