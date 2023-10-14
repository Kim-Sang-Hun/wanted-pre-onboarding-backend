package com.wanted.wantedbackend.appliance.service;

import com.wanted.wantedbackend.appliance.model.Appliance;
import com.wanted.wantedbackend.appliance.model.dto.ApplianceReadDto;
import com.wanted.wantedbackend.appliance.repository.ApplianceRepository;
import com.wanted.wantedbackend.recruitment.repository.RecruitmentRepository;
import com.wanted.wantedbackend.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplianceService {

  private final ApplianceRepository applianceRepository;
  private final UserRepository userRepository;
  private final RecruitmentRepository recruitmentRepository;
  public Appliance createAppliance(Long userId, Long recruitmentId) {

    if (applianceRepository.findByUserIdAndRecruitmentId(userId,
        recruitmentId).isPresent()) {
      throw new IllegalArgumentException("이미 지원한 채용공고입니다.");
    }
    if (userRepository.findById(userId).isEmpty()) {
      throw new IllegalArgumentException("존재하지 않는 유저입니다.");
    }
    if (recruitmentRepository.findById(recruitmentId).isEmpty()) {
      throw new IllegalArgumentException("존재하지 않는 채용공고입니다.");
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