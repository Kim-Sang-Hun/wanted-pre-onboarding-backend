package com.wanted.wantedbackend.recruitment.model.dto;

import com.wanted.wantedbackend.recruitment.model.Recruitment;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;

@Builder
public class RecruitmentDetailReadDto {
  private Long recruitmentId;
  private String companyName;
  private String nation;
  private String location;
  private String position;
  private Long reward;
  private String description;
  private String techStack;
  private LocalDateTime expireDate;
  private List<Long> otherRecruitments;

  public static RecruitmentDetailReadDto from(Recruitment recruitment, List<Long> otherRecruitmentIds) {
    return RecruitmentDetailReadDto.builder()
        .recruitmentId(recruitment.getId())
        .companyName(recruitment.getCompanyName())
        .nation(recruitment.getNation())
        .location(recruitment.getLocation())
        .position(recruitment.getPosition())
        .reward(recruitment.getReward())
        .description(recruitment.getDescription())
        .techStack(recruitment.getTechStack())
        .expireDate(recruitment.getExpireDate())
        .otherRecruitments(otherRecruitmentIds)
        .build();
  }
}