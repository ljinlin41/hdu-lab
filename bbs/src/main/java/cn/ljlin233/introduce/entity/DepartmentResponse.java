package cn.ljlin233.introduce.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DepartmentResponse
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponse {

    private int totalCount;

    private List<Department> departments;
}