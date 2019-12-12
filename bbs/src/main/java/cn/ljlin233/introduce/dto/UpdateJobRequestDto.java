package cn.ljlin233.introduce.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/12 22:16
 */
@Data
@Builder
public class UpdateJobRequestDto {

    private Integer upUserId;

    private Integer jobId;

    private String title;

    private String content;
}
