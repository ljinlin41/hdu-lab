package cn.ljlin233.util.upload.service;

import org.springframework.web.multipart.MultipartFile;

import cn.ljlin233.util.upload.entity.FileResponse;

/**
 * FileUploadService
 */
public interface FileUploadService {

    FileResponse uploadFile(MultipartFile file);

}