package cn.ljlin233.introduce.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Job
 *
 * @author lvjinlin42@foxmail.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "intro_job")
public class Job {

    @Id
    @Column(name = "id")
    public Integer id;

    @Column(name = "title")
    public String title;

    @Column(name = "content")
    public String content;

    @Column(name = "up_userid")
    public Integer upUserId;

    @Column(name = "up_nickname")
    public String upNickname;

    @Column(name = "up_date")
    public String upDate;

    @Column(name = "visit_count")
    public Integer visitCount;

}