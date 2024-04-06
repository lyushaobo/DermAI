package com.dermai.project.common;

import com.dermai.common.Utils.FileUploadUtils;
import com.dermai.common.Utils.FileUtils;
import com.dermai.framework.config.DermAIConfiguration;
import com.dermai.framework.config.ServerConfiguration;
import com.dermai.framework.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Shaobo
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private ServerConfiguration serverConfiguration;
    /**
     * upload
     */
    @PostMapping("/upload")
    public AjaxResult uploadFile(MultipartFile file) throws Exception {
        try {
            // get upload path
            String filePath = DermAIConfiguration.getUploadPath();
            // upload file and get file name
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfiguration.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("url", url);
            ajax.put("fileName", fileName);
            ajax.put("newFileName", FileUtils.getName(fileName));
            ajax.put("originalFilename", file.getOriginalFilename());
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }
}
