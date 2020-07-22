package com.guzx.chapter2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileController {

    @GetMapping("/view")
    public String fileView() {
        return "file/upload";
    }

    @RequestMapping("/request")
    @ResponseBody
    public Map<String, Object> uploadRequest(HttpServletRequest request) {
        boolean flag = false;
        MultipartHttpServletRequest multipartHttpServletRequest = null;
        if (request instanceof MultipartHttpServletRequest) {
            multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        } else {
            return dealResultMap(false, "上传失败");
        }
        // 获取文件信息
        MultipartFile file = multipartHttpServletRequest.getFile("file");
        // 获取文件名称
        String fileName = file.getOriginalFilename();
        File saveFile = new File(fileName);
        try {
            // 保存文件
            file.transferTo(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
            return dealResultMap(false, "上传失败！");
        }
        return dealResultMap(true, "上传成功！");
    }

    @PostMapping("/request/MultipartRequest")
    @ResponseBody
    public Map<String,Object> uploadRequestByMultipartRequest(MultipartFile file){
        String fileName = file.getOriginalFilename();
        File dest = new File(fileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            return dealResultMap(false, "上传失败！");
        }
        return dealResultMap(true, "上传成功！");
    }

    // 推荐使用
    @PostMapping("/request/part")
    @ResponseBody
    public Map<String,Object> uploadRequestByPart(Part file){
        String filename = file.getSubmittedFileName();
        try {
            file.write(filename);
        } catch (IOException e) {
            e.printStackTrace();
            return dealResultMap(false, "上传失败！");
        }
        return dealResultMap(true, "上传成功！");
    }


    public Map<String, Object> dealResultMap(boolean flag, String message) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", flag);
        result.put("msg", message);
        return result;
    }
}
