package com.datamining.servide.impl;

import com.datamining.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    ServletContext app;


    @Override
    public File uploadFile(MultipartFile file, String folder) {
        File dir = new File(app.getRealPath("/img/" + folder));
        if(!dir.exists()) {
            dir.mkdirs();
        }

        String s =  file.getResource().getFilename();
        String name = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));
        try {
            File saveFile = new File(dir, s);
            file.transferTo(saveFile);
            return saveFile;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
