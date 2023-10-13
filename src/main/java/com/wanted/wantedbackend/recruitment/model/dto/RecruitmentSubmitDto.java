package com.wanted.wantedbackend.recruitment.model.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecruitmentSubmitDto {
  @NotBlank
  private String position;
  @NotBlank
  private Long reward;
  @NotBlank
  private String description;
  @NotBlank
  private String techStack;
  @NotBlank
  private LocalDateTime expireDate;
}