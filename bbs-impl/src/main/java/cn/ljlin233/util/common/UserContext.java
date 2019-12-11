package cn.ljlin233.util.common;

import lombok.Builder;
import lombok.Data;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/11 18:45
 */
@Data
@Builder
public class UserContext {

    private Integer id;

    private String nickName;
}
