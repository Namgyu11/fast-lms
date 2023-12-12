package com.zerobase.fastlms.admin.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BannerInput {

    Long id;

    String bannerName; // 배너이름
    String bannerUrl; // 배너 URL

    int openCase;
    int sortNum;
    boolean showYn; // 공개 여부

    LocalDateTime regDt;
    String fileName;
    String urlFileName;


    String idList;

}
