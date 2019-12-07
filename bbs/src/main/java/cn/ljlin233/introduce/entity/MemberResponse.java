package cn.ljlin233.introduce.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * MemberResponse
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {

    private int totalCount;

    private List<Member> members;

}