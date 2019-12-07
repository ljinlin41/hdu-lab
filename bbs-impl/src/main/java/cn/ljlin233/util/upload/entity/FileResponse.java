package cn.ljlin233.util.upload.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * FileResponse
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FileResponse {

    private int code;

    private String path;

}