package cn.ljlin233.announce.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/12 16:32
 */
@Data
@Builder
public class DeleteAnnounceRequestDto {

    private Integer upUserId;

    private Integer announceId;
}
