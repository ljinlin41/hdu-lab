package cn.ljlin233.introduce.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/12 23:03
 */
@Data
@Builder
public class DeleteMemberRequestDto {

    private Integer memberId;
}
