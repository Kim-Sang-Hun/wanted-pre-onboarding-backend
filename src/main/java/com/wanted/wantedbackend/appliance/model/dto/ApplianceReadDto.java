package com.wanted.wantedbackend.appliance.model.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplianceReadDto {
  private Long companyId;
  private String companyName;
  private String nation;
  private String location;
  private String position;
  private Long reward;
  private String techStack;
  private LocalDateTime lastModifiedAt;
  private LocalDateTime expireDate;
}