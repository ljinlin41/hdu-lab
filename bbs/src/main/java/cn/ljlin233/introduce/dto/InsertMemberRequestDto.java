package cn.ljlin233.introduce.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/12 22:59
 */
@Data
@Builder
public class InsertMemberRequestDto {

    private Integer memberId;

    private Integer departmentId;

    private String memberName;

    private String memberType;
}
