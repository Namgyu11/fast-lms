package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.admin.entity.Banner;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BannerDto {
    Long id;

    String bannerName;  //배너명
    String bannerUrl;   //배너 링크

    int openCase;
    int sortNum;
    boolean showYn; //공개 여부

    LocalDateTime regDt;

    String fileName;
    String urlFileName;

    //추가컬럼
    long totalCount;
    long seq;

    public static BannerDto toBanner(Banner banner){
        return BannerDto.builder()
                .id(banner.getId())
                .bannerName(banner.getBannerName())
                .build();
    }
}
