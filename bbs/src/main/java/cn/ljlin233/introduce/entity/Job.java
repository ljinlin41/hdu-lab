package cn.ljlin233.introduce.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Job
 *
 * @author lvjinlin42@foxmail.com
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "intro_job")
public class Job {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "up_userid")
    private Integer upUserId;

    @Column(name = "up_nickname")
    private String upNickname;

    @Column(name = "up_date")
    private String upDate;

    @Column(name = "visit_count")
    private Integer visitCount;

}