package cn.ljlin233.util.upload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.ljlin233.util.upload.entity.FileResponse;
import cn.ljlin233.util.upload.service.FileUploadService;

/**
 * FileUploadController
 */
@Controller
@RequestMapping("/api")
public class FileUploadController {

    private FileUploadService fileUploadService;

    public FileUploadController() {
        super();
    }

    @Autowired
    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public FileResponse imageUpload(@RequestParam("file") MultipartFile file) {

        FileResponse reponse = fileUploadService.uploadFile(file);

        return reponse;
    }

}