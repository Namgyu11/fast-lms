package com.zerobase.fastlms.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.UUID;

@Slf4j
public class FileUtil {
    public static String[] getNewSaveFile(String baseLocalPath, String baseUrlPath,
                                          String original) {

        LocalDate now = LocalDate.now();

        String[] dirs = {
                String.format("%s/%d/", baseLocalPath, now.getYear()),
                String.format("%s/%d/%02d/", baseLocalPath, now.getYear(), now.getMonthValue()),
                String.format("%s/%d/%02d/%02d/", baseLocalPath, now.getYear(), now.getMonthValue()
                        , now.getDayOfMonth())};

        String urlDir = String.format("%s/%d/%02d/%02d", baseUrlPath, now.getYear(),
                now.getMonthValue(), now.getDayOfMonth());

        for (String dir : dirs) {
            File file = new File(dir);
            if (!file.isDirectory()) {
                file.mkdir();
            }
        }
        String fileExtension = "";
        if (original != null) {
            int dotPos = original.lastIndexOf(".");
            if (dotPos > -1) {
                fileExtension = original.substring(dotPos + 1);
            }
        }


        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String newFilename = String.format("%s%s", dirs[2], uuid);
        String newUrlFilename = String.format("%s%s", urlDir, uuid);

        if (!fileExtension.isEmpty()) {
            newFilename += "." + fileExtension;
            newUrlFilename += "." + fileExtension;
        }

        return new String[]{newFilename, newUrlFilename};
    }

    public static String[] getFilePath(MultipartFile file) {
        String saveFilename = "";
        String urlFilename = "";

        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            String baseLocalPath = "C:\\Users\\hagaj\\Desktop\\냄규\\학습관리시스템_프로젝트_시작소스_fastlms3.zip-20231127T153207Z-001\\학습관리시스템_프로젝트_시작소스_fastlms3\\fastlms3\\src\\main\\webapp\\files";
            String baseUrlPath = "/files";
            String[] arrFilename = FileUtil.getNewSaveFile(baseLocalPath, baseUrlPath,
                    originalFilename);

            saveFilename = arrFilename[0];
            urlFilename = arrFilename[1];

            try {
                File newFile = new File(saveFilename);
                FileCopyUtils.copy(file.getInputStream(), Files.newOutputStream(newFile.toPath()));

            } catch (IOException e) {
                log.info("############################ - 1");
                log.info(e.getMessage());
            }
        }
        return new String[]{saveFilename, urlFilename};
    }
}

