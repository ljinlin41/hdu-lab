package cn.ljlin233.introduce.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/12 22:18
 */
@Data
@Builder
public class DeleteJobRequestDto {

    private Integer upUserId;

    private Integer jobId;
}
