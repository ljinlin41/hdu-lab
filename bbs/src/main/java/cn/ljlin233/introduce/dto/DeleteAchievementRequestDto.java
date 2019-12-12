package cn.ljlin233.introduce.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/12 16:49
 */
@Data
@Builder
public class DeleteAchievementRequestDto {

    private Integer upUserId;

    private Integer achievementId;
}
