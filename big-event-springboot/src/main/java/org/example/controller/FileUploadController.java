package org.example.controller;

import org.example.pojo.Result;
import org.example.utils.AliOssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
public class FileUploadController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        try {
            // 保证文件的名字是唯一的,使用UUID+文件后缀的方式存储文件
            String originalFilename = file.getOriginalFilename();
            String filename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
            // 把文件存储到指定的目录下
            Path staticDir = Paths.get(uploadDir);
            // 确保目录存在
            if (!Files.exists(staticDir)) {
                Files.createDirectories(staticDir);
            }
            // 构建文件的完整路径
            Path filePath = staticDir.resolve(filename);
            // 将文件保存到指定路径
            file.transferTo(filePath.toFile());

            // 返回文件路径
            String fileUrl = "/files/" + filename;
            return Result.success(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("上传失败");
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return Result.error("上传失败");
        }
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam String filename) {
        try {
            // 构建文件的完整路径
            Path filePath = Paths.get(uploadDir).resolve(filename);
            File file = filePath.toFile();

            // 检查文件是否存在
            if (!file.exists()) {
                return ResponseEntity.notFound().build();
            }

            // 创建文件资源
            Resource resource = new FileSystemResource(file);

            // 设置响应头
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");

            // 返回文件
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}

