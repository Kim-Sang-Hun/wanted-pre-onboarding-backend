package com.wanted.wantedbackend.recruitment.repository;

import com.wanted.wantedbackend.recruitment.model.Recruitment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

  @Query("select e from Recruitment e"
      + " where e.companyId = :companyId and e.position = :position"
      + " and e.reward = :reward and e.description = :description"
      + " and e.techStack = :techStack")
  Optional<Recruitment> findSameRecruitments(Long companyId,
      String position, Long reward, String description, String techStack);

  @Query("select e from Recruitment e"
      + " where e.expireDate >= current_timestamp"
      + " order by e.expireDate")
  List<Recruitment> findNotExpiredRecruitments();

  @Query("select e from Recruitment e"
      + " where e.companyId = :companyId and e.id <> :id and e.expireDate >= current_timestamp"
      + " order by e.expireDate")
  List<Recruitment> findOtherNotExpiredRecruitmentsByCompanyId(Long companyId, Long id);

}