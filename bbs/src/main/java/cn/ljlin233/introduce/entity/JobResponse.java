package cn.ljlin233.introduce.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * JobResponse
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JobResponse {

    private int totalCount;

    private List<Job> jobs;

}