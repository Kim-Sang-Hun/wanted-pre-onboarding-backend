package com.wanted.wantedbackend.appliance.repository;

import com.wanted.wantedbackend.appliance.model.Appliance;
import com.wanted.wantedbackend.appliance.model.dto.ApplianceReadDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplianceRepository extends JpaRepository<Appliance, Long> {

  @Query("select e from Appliance e"
      + " where e.userId = :userId and e.recruitmentId = :recruitmentId")
  Optional<Appliance> findByUserIdAndRecruitmentId(Long userId, Long recruitmentId);
  @Query("select new com.wanted.wantedbackend.appliance.model.dto.ApplianceReadDto("
      + " r.id, r.companyName, r.nation, r.location,"
      + " r.position, r.reward, r.techStack, a.lastModifiedAt, r.expireDate)"
      + " from Appliance a "
      + " left join Recruitment r on a.recruitmentId = r.id"
      + " where a.userId = :userId"
      + " order by a.lastModifiedAt desc")
  List<ApplianceReadDto> findRecruitmentsByUserId(Long userId);
}