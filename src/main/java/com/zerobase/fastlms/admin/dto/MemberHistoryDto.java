package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.member.entity.MemberHistory;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberHistoryDto {
    Long id;

    String userId;
    LocalDateTime logDt;
    String ip;
    String agent;

    long totalCount;
    long seq;
    public static MemberHistoryDto toMemberHistory(MemberHistory memberHistory) {
        return MemberHistoryDto.builder()
                .userId(memberHistory.getUserId())
                .logDt(memberHistory.getLogDt())
                .ip(memberHistory.getIp())
                .agent(memberHistory.getAgent())
                .build();
    }

}
