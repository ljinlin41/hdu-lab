package cn.ljlin233.introduce.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/11 20:30
 */
@Data
@Builder
public class UpdateAchievementRequestDto {

    private String title;

    private String content;
}
