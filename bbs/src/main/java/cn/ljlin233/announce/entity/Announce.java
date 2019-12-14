package cn.ljlin233.announce.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

/**
 * Announce
 *
 * @author lvjinlin42@foxmail.com
 */
@Data
@Builder
@Table(name = "anno_announce")
public class Announce {

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
    private String upUserNickname;

    @Column(name = "up_date")
    private String upDate;

    @Column(name = "visit_count")
    private Integer visitCount;

    @Column(name = "save_path")
    private String savePath;

}