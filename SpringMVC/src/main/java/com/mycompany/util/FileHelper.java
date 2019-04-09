package com.mycompany.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

//file upload
/**  디테일 한 기능 구현 위해 작성하다가 멈춤. 추후 필요시 수정 및 작성하여 사용하기
public class FileHelper {

	private static final Logger logger = LoggerFactory.getLogger(FileHelper.class);
	
    public static String upload(String uploadPath, MultipartFile multipartFile) {
        String uploadedFileUrl = null;

        logger.info("upload enter");
        
        //String rootPath = request.getSession().getServletContext().getRealPath("/");
        //logger.info("rootPath >> " + rootPath);
        //여기 추후 수정해야함 - 프로젝트 상의 경로로 파일을 저장하는기능임.
        //String realUploadPath = rootPath + "\resources" + uploadPath;
        //logger.info("realUploadPath >>" + realUploadPath);
        
        
        File dir = new File("c://zzz");
        if (!dir.exists())
            dir.mkdirs();
        
        File file = new File(dir.getAbsolutePath() + File.separator + multipartFile.hashCode()
                + multipartFile.getOriginalFilename());
        try {
            multipartFile.transferTo(file);//파일저장
            
            
            
            logger.info("uploadedFileUrl >> " + uploadedFileUrl);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadedFileUrl;
    }

}*/
