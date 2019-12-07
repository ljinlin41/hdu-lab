package cn.ljlin233.util;

import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * Page 对分页的基本数据进行一个简单的封装
 *
 * @author lvjinlin42@foxmail.com
 */
@Builder
@Data
public class Page<T> {

    /**
     * 数据总数
     */
    private long totalNum;

    /**
     * 每页大小
     */
    private int pageSize;

    /**
     * 当前页数
     */
    private long pageNum;

    /**
     * 返回数据
     */
    private List<T> data;

}
