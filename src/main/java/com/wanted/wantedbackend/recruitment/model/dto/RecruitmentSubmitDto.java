package com.wanted.wantedbackend.recruitment.model.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecruitmentSubmitDto {
  private String position;
  private Long reward;
  private String description;
  private String techStack;
  private LocalDateTime expireDate;
}