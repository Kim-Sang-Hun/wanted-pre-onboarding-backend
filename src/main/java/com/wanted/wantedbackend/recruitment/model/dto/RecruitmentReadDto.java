package com.wanted.wantedbackend.recruitment.model.dto;

import com.wanted.wantedbackend.recruitment.model.Recruitment;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public class RecruitmentReadDto {
  private Long recruitmentId;
  private String companyName;
  private String nation;
  private String location;
  private String position;
  private Long reward;
  private String techStack;
  private LocalDateTime expireDate;

  public static RecruitmentReadDto from(Recruitment recruitment) {
    return RecruitmentReadDto.builder()
        .recruitmentId(recruitment.getId())
        .companyName(recruitment.getCompanyName())
        .nation(recruitment.getNation())
        .location(recruitment.getLocation())
        .position(recruitment.getPosition())
        .reward(recruitment.getReward())
        .techStack(recruitment.getTechStack())
        .expireDate(recruitment.getExpireDate())
        .build();
  }
}