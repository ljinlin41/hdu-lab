package cn.ljlin233.user.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/14 16:39
 */
@Data
@Builder
public class DeleteUserOriginRequestDto {

    private String account;
}
