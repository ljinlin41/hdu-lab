package cn.ljlin233.util.wangeditor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.ljlin233.util.wangeditor.entity.ImageResponse;
import cn.ljlin233.util.wangeditor.service.ImageUploadService;

/**
 * ImageUploadController
 */
@Controller
@RequestMapping("/api")
public class ImageUploadController {

    private ImageUploadService imageUploadService;

    public ImageUploadController() {}

    @Autowired
    public ImageUploadController(ImageUploadService imageUploadService) {
        this.imageUploadService = imageUploadService;
    }

    @RequestMapping(value = "/editorimage", method = RequestMethod.POST)
    @ResponseBody
    public ImageResponse imageUpload(@RequestParam("imageFiles") MultipartFile[] imageFiles) {

        ImageResponse reponse = imageUploadService.saveImages(imageFiles);

        return reponse;

    }

}