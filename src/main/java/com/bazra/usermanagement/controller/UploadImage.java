package com.bazra.usermanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

//import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.ServletContext;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/imgs")

public class UploadImage implements ServletContextAware {
    private ServletContext servletContext;
    @RequestMapping(value = "upload", method = RequestMethod.POST)
//    @PostMapping("/api/imgs")
        public ResponseEntity<Void> upload(@RequestParam("files") MultipartFile[] files) {
            try {
                System.out.println("file list");
                for(MultipartFile file : files){
                    System.out.println("FIle name:" + file.getOriginalFilename());
                    System.out.println("FIle size:" + file.getSize());
                    System.out.println("FIle type:" + file.getContentType());
                    System.out.println("-----------------------------------");
                    save(file);
                }
                return new ResponseEntity<Void>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }
        }

        private String save(MultipartFile file) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
                String newFileName = simpleDateFormat.format(new Date()) + file.getOriginalFilename();

                byte[] bytes = file.getBytes();
                Path path = Paths.get(this.servletContext.getRealPath("/uploads" + newFileName));
                Files.write(path, bytes);
                return newFileName;

            } catch (Exception e) {
                return null;
            }
        }
        @Override
        public void setServletContext(ServletContext servletContext){
            this.servletContext = servletContext;
        }
    }


