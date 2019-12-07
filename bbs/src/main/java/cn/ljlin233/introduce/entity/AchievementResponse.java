package cn.ljlin233.introduce.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * AchievementResponse
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AchievementResponse {

    private int totalCount;

    private List<Achievement> achievements;
}