package com.wanted.wantedbackend.appliance.service;

import com.wanted.wantedbackend.appliance.model.Appliance;
import com.wanted.wantedbackend.appliance.model.dto.ApplianceReadDto;
import com.wanted.wantedbackend.appliance.repository.ApplianceRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ApplianceService {

  private ApplianceRepository applianceRepository;
  public Appliance createAppliance(Long userId, Long recruitmentId) {

    Optional<Appliance> existingAppliance = applianceRepository.findByUserIdAndRecruitmentId(userId,
        recruitmentId);
    if (existingAppliance.isPresent()) {
      throw new IllegalArgumentException("이미 지원한 채용공고입니다.");
    }
    Appliance appliance = Appliance.builder()
        .userId(userId)
        .recruitmentId(recruitmentId)
        .build();
    return applianceRepository.save(appliance);
  }

  public List<ApplianceReadDto> getAppliances(Long userId) {
    return applianceRepository.findRecruitmentsByUserId(userId);
  }
}