package cn.ljlin233.introduce.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

/**
 * Department
 *
 * @author lvjinlin42@foxmail.com
 */
@Data
@Builder
@Table(name = "intro_department")
public class Department {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}