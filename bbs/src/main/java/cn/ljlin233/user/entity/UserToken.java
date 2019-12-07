package cn.ljlin233.user.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * UserToken
 *
 * @author lvjinlin42@foxmail.com
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserToken {

    private Integer userId;
    private String token;
    private List<String> userRole;

    @Override
    public String toString() {
        return "UserToken [token=" + token + ", userId=" + userId + ", userRole=" + userRole + "]";
    }

}