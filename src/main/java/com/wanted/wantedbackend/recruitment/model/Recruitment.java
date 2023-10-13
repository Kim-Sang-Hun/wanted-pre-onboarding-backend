package com.wanted.wantedbackend.recruitment.model;

import com.wanted.wantedbackend.company.model.Company;
import com.wanted.wantedbackend.BaseEntity;
import com.wanted.wantedbackend.recruitment.model.dto.RecruitmentSubmitDto;
import java.time.LocalDateTime;
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
public class Recruitment extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long companyId;
  private String companyName;
  private String nation;
  private String location;
  private String position;
  private Long reward;
  private String description;
  private String techStack;
  private LocalDateTime expireDate;

  public static Recruitment from(Company company, RecruitmentSubmitDto request) {
    return Recruitment.builder()
        .companyName(company.getName())
        .nation(company.getNation())
        .location(company.getLocation())
        .position(request.getPosition())
        .reward(request.getReward())
        .description(request.getDescription())
        .techStack(request.getTechStack())
        .expireDate(request.getExpireDate())
        .build();
  }

  public void updateRecruitment(RecruitmentSubmitDto request) {
    this.position = request.getPosition();
    this.reward = request.getReward();
    this.description = request.getDescription();
    this.techStack = request.getTechStack();
    this.expireDate = request.getExpireDate();
  }
}