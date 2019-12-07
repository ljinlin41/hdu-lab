package cn.ljlin233.resource.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Ban
 *
 * @author lvjinlin42@foxmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "res_ban")
public class Ban {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "ban_id")
    private Integer banId;

    @Column(name = "add_id")
    private Integer addId;
}