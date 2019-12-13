package cn.ljlin233.introduce.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/13 15:22
 */
@Data
@Builder
public class InsertApplyRequestDto {

    private Integer userId;

    private String username;

    private String applyType;

    private Integer departmentId;

}
