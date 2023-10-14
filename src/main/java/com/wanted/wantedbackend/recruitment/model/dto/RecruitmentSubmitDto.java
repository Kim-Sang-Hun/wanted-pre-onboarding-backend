package com.wanted.wantedbackend.recruitment.model.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Builder
public class RecruitmentSubmitDto {
  @NotBlank
  private String position;
  @NotNull
  private Long reward;
  @NotBlank
  private String description;
  @NotBlank
  private String techStack;
  @NotNull
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime expireDate;
}