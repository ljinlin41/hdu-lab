package cn.ljlin233.introduce.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/13 14:43
 */
@Data
@Builder
public class UpdateDepartmentRequestDto {

    private Integer id;

    private String name;

    private String description;
}
