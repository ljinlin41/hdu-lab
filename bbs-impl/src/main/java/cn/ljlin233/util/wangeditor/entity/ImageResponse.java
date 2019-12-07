package cn.ljlin233.util.wangeditor.entity;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

/**
 * ImageResponse
 */
@Setter
@Getter
public class ImageResponse {

    private int errno;

    private ArrayList<String> data;

    public ImageResponse() {
        this.errno = 0;
        this.data = new ArrayList<>();
    }

    public void addImage(String path) {
        data.add(path);
    }

    @Override
    public String toString() {
        return "ImageResponse [data=" + data + ", errno=" + errno + "]";
    }

}