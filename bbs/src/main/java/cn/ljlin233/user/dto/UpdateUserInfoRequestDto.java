package cn.ljlin233.user.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/13 20:17
 */
@Data
@Builder
public class UpdateUserInfoRequestDto {

    private Integer id;

    private String name;

    private String description;

    private String picture;

    private String email;

    private String phone;
}
