package cn.ljlin233.introduce.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/12 17:04
 */
@Builder
@Data
public class DeleteAwardRequestDto {

    private Integer upUserId;

    private Integer awardId;
}
