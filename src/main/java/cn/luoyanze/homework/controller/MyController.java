package cn.luoyanze.homework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Collection;

@RestController
@RequestMapping("/api")
public class MyController {

    @PostMapping("/upload")
    public String upload(@RequestPart("meta-data") String metadata,
                         @RequestPart("file-data") MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        System.out.println("metadata = " + metadata);
        return "OK";
    }

    @GetMapping("/merge/{filename}/{slice}")
    public String merge(@PathVariable("filename") String filename,
                        @PathVariable("slice") Integer slice) {
        System.out.println("               " + filename);
        System.out.println("               " + slice);
        return "OK";
    }
}
