package com.soumya.blog_application.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.soumya.blog_application.service.FileService;

@Service
public class FileServiceImpl implements FileService{

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        //File name
        String name=file.getOriginalFilename();
        System.out.println(path);
        System.out.println(name);
        //Random name for Genrate File
        String randomId=UUID.randomUUID().toString();
        String filename1=randomId.concat(name.substring(name.lastIndexOf(".")));

        String filePath=path+File.separator+filename1;

        //Create folder if not found
        File f=new File(path);

        if(!f.exists()){
            f.mkdir();
        }
        //File Copy
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return filename1;
    }

    @Override
    public InputStream getresourse(String path, String fileName) throws FileNotFoundException {
        String fullPath=path + File.separator + fileName;
        InputStream in=new FileInputStream(fullPath);
        return in;
    }
    
}
