package com.zerobase.fastlms.admin.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    String bannerName;  //배너명
    String bannerUrl;   //배너 링크

    int openCase;
    int sortNum;
    boolean showYn; //공개 여부

    LocalDateTime regDt;

    String fileName;
    String urlFileName;
}
