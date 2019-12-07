package cn.ljlin233.util.exception.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/5 18:19
 */
@Setter
@Getter
public class QueryException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String friendMessage;

    public QueryException(String friendMessage, String message) {
        super(message);
        this.friendMessage = friendMessage;
    }

}
