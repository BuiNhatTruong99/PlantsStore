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
        public File save(MultipartFile file, String folder) {
            File dir = new File(app.getRealPath("/img/" + folder));
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String fileName =  file.getOriginalFilename();
//
//            String tempFilePath = "C:/temp/" + fileName; // Thay đổi đường dẫn đến thư mục tạm thời
//            File tempFile = new File(tempFilePath);

//            // Di chuyển tệp tin ảnh vào thư mục ảnh chính trong project
//            String imagePath = app.getRealPath("/img/" + folder + "/" + fileName);
//            File imageFile = new File(imagePath);
//
//            if (!imageFile.exists()) {
//                // Tạo thư mục nếu chưa tồn tại
//
//                if (!dir.exists()) {
//                    dir.mkdirs();
//                }
//
//                // Di chuyển tệp tin ảnh vào thư mục ảnh chính trong project
//                tempFile.renameTo(imageFile);
//
//            }

//            String name = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));

            try {
                File saveFile = new File(dir, fileName);
                file.transferTo(saveFile);

                return saveFile;
            } catch (Exception e) {
//                System.out.println("Kiểm tra lại đường dẫn ảnh");
                throw new RuntimeException();
            }

        }

}
