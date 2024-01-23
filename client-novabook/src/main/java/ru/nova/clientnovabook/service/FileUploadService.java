package ru.nova.clientnovabook.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.nova.clientnovabook.model.User;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileUploadService {
    @Value("${upload.avatarFile.path}")
    private String uploadPosterFilePath;
    public void saveAvatar(MultipartFile multipartFile, User user) {
        if(multipartFile != null) {
            File uploadDir = new File(uploadPosterFilePath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String resultFilename =  UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();
            File file = new File(uploadPosterFilePath + resultFilename);
            try {
                multipartFile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            user.setAvatarName(resultFilename);
        }
    }
}
