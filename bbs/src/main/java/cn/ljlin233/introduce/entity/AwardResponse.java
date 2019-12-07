package cn.ljlin233.introduce.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * AwardResponse
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AwardResponse {

    private int totalCount;

    private List<Award> awards;

}