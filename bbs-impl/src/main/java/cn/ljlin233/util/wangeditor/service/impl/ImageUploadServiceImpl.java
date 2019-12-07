package cn.ljlin233.util.wangeditor.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.ljlin233.util.exception.entity.DataCheckedException;
import cn.ljlin233.util.exception.entity.SystemException;
import cn.ljlin233.util.wangeditor.entity.ImageResponse;
import cn.ljlin233.util.wangeditor.service.ImageUploadService;

/**
 * ImageUploadServiceImpl
 */
@Service
public class ImageUploadServiceImpl implements ImageUploadService {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    //private String basePath = new Configure("properties/wangEditor.properties").getValue("imageSavaPath");

    //@Value("${imageSavaPath}")
    private String basePath;

    public ImageUploadServiceImpl() {}

    @Override
    public ImageResponse saveImages(MultipartFile[] imageFiles) {

        ImageResponse reponse = new ImageResponse();
        reponse.setErrno(0);

        for (MultipartFile imageFile : imageFiles) {
            String path = saveOneImage(imageFile);
            reponse.addImage(path.toString());
        }

        return reponse;
    }

    private String saveOneImage(MultipartFile imageFile) {

        if (imageFile.getSize() == 0) {
            throw new DataCheckedException("上传图片为空");
        }

        String fileType = imageFile.getContentType();
        if (fileType == null || !fileType.startsWith("image")) {
            throw new DataCheckedException("上传图片类型错误为空");
        }

        // 替换文件名
        String originalFilename = imageFile.getOriginalFilename();

        File path = new File(this.basePath + getSavePath(originalFilename));
        // 判断是否重名
        while (path.exists()) {
            path = new File(this.basePath + getSavePath(originalFilename));
        }

        try {
            imageFile.transferTo(path);
        } catch (IOException e) {
            throw new SystemException("服务器存储图片失败", e.getMessage());
        }

        String url = "http://47.100.114.6:8081/image/" + path.toString().substring(16);

        return url;
    }

    private String getSavePath(String fileName) {

        String preName = dateFormat.format(new Date());
        String lastName = fileName.substring(fileName.lastIndexOf("."));
        String newName = preName + lastName;

        return newName;
    }

}