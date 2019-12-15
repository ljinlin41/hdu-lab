package cn.ljlin233.resource.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Resource
 *
 * @author lvjinlin42@foxmail.com
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "res_resource")
public class Resource {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    /**
     * TODO 改为枚举类型
     */
    @Column(name = "category")
    private String category;

    @Column(name = "up_userid")
    private Integer upUserId;

    @Column(name = "up_nickname")
    private String upNickname;

    @Column(name = "up_date")
    private String upDate;

    @Column(name = "visit_count")
    private Integer visitCount;

    @Column(name = "download_count")
    private Integer downloadCount;

    @Column(name = "url")
    private String url;

}