package cn.luoyanze.homework.controller;

import cn.luoyanze.homework.Config.PathConfig;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class MyController {


    @PostMapping("/upload")
    public String upload(@RequestPart("meta-data") String metadata,
                         @RequestPart("file-data") MultipartFile file) throws IOException {

        Path rootLocation = Paths.get(PathConfig.UPLOAD_DIR);
        if (Files.notExists(rootLocation)) {
            Files.createDirectories(rootLocation);
        }
        Path path = rootLocation.resolve(Objects.requireNonNull(file.getOriginalFilename()));
        if ("0".equals(metadata)) {
            Files.write(path, file.getBytes());
        } else {
            Files.write(path, file.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        }
        return "OK";
    }

    @GetMapping("/check/{filename}/{size}")
    public String check(@PathVariable("filename") String filename,
                        @PathVariable("size") long size) {

        Path path = Paths.get(PathConfig.UPLOAD_DIR + File.separator + filename);
        if (path.toFile().length() == size) {
            return "OK";
        } else {
            return "file upload failed";
        }
    }
}
