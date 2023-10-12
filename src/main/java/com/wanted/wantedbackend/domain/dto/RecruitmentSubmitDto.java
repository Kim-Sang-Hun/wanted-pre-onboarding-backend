package com.wanted.wantedbackend.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecruitmentSubmitDto {
  private Long companyId;
  private String position;
  private Long reward;
  private String description;
  private String techStack;

  public RecruitmentSubmitDto(Long companyId, String position, Long reward, String description,
      String techStack) {
    this.companyId = companyId;
    this.position = position;
    this.reward = reward;
    this.description = description;
    this.techStack = techStack;
  }
}