package cn.ljlin233.announce.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/7 19:41
 */
@Data
@Builder
public class UpdateAnnounceRequestDto {

    /**
     * 标题
     */
    private String title;

    /**
     * 正文
     */
    private String content;

    /**
     * 附件路径
     */
    private String savePath;

    private Integer announceId;

    private Integer upUserId;
}
