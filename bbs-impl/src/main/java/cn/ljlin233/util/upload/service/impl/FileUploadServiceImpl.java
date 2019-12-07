package cn.ljlin233.util.upload.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.ljlin233.util.exception.entity.DataCheckedException;
import cn.ljlin233.util.exception.entity.SystemException;
import cn.ljlin233.util.upload.entity.FileResponse;
import cn.ljlin233.util.upload.service.FileUploadService;

/**
 * FileUploadServiceImpl
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    //@Value("${savaPath}")
    private String basePath;

    @Override
    public FileResponse uploadFile(MultipartFile file) {

        if (file.getSize() == 0) {
            throw new DataCheckedException("上传文件为空");
        }

        // 替换文件名
        String originalFilename = file.getOriginalFilename();

        File path = new File(this.basePath + getSavePath(originalFilename));
        // 判断是否重名
        while (path.exists()) {
            path = new File(this.basePath + getSavePath(originalFilename));
        }

        try {
            file.transferTo(path);
        } catch (IOException e) {
            throw new SystemException("服务器存储文件失败", e.getMessage());
        }

        String url = "http://47.100.114.6:8081/resources/" + path.toString().substring(20);

        FileResponse response = new FileResponse();
        response.setCode(200);
        response.setPath(url);

        return response;
    }

    private String getSavePath(String fileName) {

        String preName = dateFormat.format(new Date());
        String lastName = fileName.substring(fileName.lastIndexOf("."));
        String newName = preName + lastName;

        return newName;
    }
}