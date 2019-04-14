package com.mycompany.util;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

//단일 file upload
public class FileHelper {

	private static final Logger logger = LoggerFactory.getLogger(FileHelper.class);
	
    public static String upload(String uploadPath, MultipartFile multipartFile, HttpServletRequest request) {
        String uploadedFileUrl = null;

        logger.info("upload enter");
        
        
        //실제 톰캣에서 저장되는 경로 webapps 이하까지를 구한다.
        ServletContext contextPath = request.getSession().getServletContext();
        String rootPath = contextPath.getRealPath("/");
        
        //resources 위치 아래에 이미지 파일을 업로드한다.
        String realUploadPath = rootPath + "/resources" + uploadPath;
        logger.info("디렉토리 생성을 위한 realUploadPath >>" + realUploadPath);
        File dir = new File(realUploadPath);
        
        if (!dir.exists())
            dir.mkdirs();
        
        
        File file = new File(dir.getAbsolutePath() + File.separator + multipartFile.hashCode()
                + multipartFile.getOriginalFilename());
        
        try {
            multipartFile.transferTo(file); //파일 저장
            
            //img src 로 할때는 /resources로 읽어와야 하니 아래처럼 작성.
            uploadedFileUrl = "/resources/" + uploadPath + File.separator + file.getName();
            logger.info("uploadedFileUrl >> " + uploadedFileUrl);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadedFileUrl;
    }

}
