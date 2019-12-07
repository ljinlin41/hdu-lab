package cn.ljlin233.util.email.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ActiveEmail
 *
 * @author lvjinlin42@foxmail.com
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ActiveEmail {

    private String sendTo;

    private String title;

    private String message;

    private String sendFrom;

}