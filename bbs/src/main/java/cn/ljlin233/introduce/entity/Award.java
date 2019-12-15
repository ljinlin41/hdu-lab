package cn.ljlin233.introduce.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Award 奖项
 *
 * @author lvjinlin42@foxmail.com
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "intro_award")
public class Award {

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
    private String nickname;

    @Column(name = "up_date")
    private String datetime;

    @Column(name = "visit_count")
    private Integer visitCount;

}