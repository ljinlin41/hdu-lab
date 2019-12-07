package cn.ljlin233.util.wangeditor.service;

import org.springframework.web.multipart.MultipartFile;

import cn.ljlin233.util.wangeditor.entity.ImageResponse;

/**
 * ImageUploadService
 */
public interface ImageUploadService {

    ImageResponse saveImages(MultipartFile[] imageFiles);

}