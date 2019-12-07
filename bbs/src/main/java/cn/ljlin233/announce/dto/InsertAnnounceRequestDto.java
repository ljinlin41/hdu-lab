package cn.ljlin233.announce.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/7 19:32
 */
@Data
@Builder
public class InsertAnnounceRequestDto {

    /**
     * 上传用户ID
     */
    private Integer upUserId;

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

}
