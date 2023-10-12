package com.wanted.wantedbackend.repository;

import com.wanted.wantedbackend.domain.entity.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

  boolean existsByCompanyIdAndPositionAndRewardAndDescriptionAndTechStack(Long companyId,
      String position, Long reward, String description, String techStack);
}