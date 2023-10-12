package com.wanted.wantedbackend.domain.entity;

import com.wanted.wantedbackend.domain.dto.RecruitmentSubmitDto;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Recruitment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long companyId;
  private String position;
  private Long reward;
  private String description;
  private String techStack;

  public static Recruitment from(RecruitmentSubmitDto request) {
    return Recruitment.builder()
        .companyId(request.getCompanyId())
        .position(request.getPosition())
        .reward(request.getReward())
        .description(request.getDescription())
        .techStack(request.getTechStack()).build();
  }

  public static Recruitment update(Long recruitmentId, RecruitmentSubmitDto request) {
    return Recruitment.builder()
        .id(recruitmentId)
        .companyId(request.getCompanyId())
        .position(request.getPosition())
        .reward(request.getReward())
        .description(request.getDescription())
        .techStack(request.getTechStack()).build();
  }

}