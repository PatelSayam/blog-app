package com.example.blog.services.impl;

import com.example.blog.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        // File name
        String name = file.getOriginalFilename();     // abc.png

        // generate the random filename
        String randomID = UUID.randomUUID().toString();
        String randomFileName = randomID.concat(name.substring(name.lastIndexOf(".")));

        //Fullpath
        String filePath = path + File.separator + randomFileName;

        //create folder if not created
        File f = new File(path);
        if(!f.exists()) {
            f.mkdir();
        }

        // file copy
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return randomFileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream is = new FileInputStream(fullPath);

        // db logic to inputStream

        return is;
    }
}
