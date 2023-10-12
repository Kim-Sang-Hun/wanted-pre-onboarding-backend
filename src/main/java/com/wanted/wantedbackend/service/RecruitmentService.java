package com.wanted.wantedbackend.service;

import com.wanted.wantedbackend.domain.dto.RecruitmentSubmitDto;
import com.wanted.wantedbackend.repository.RecruitmentRepository;
import com.wanted.wantedbackend.domain.entity.Recruitment;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RecruitmentService {

  private final RecruitmentRepository recruitmentRepository;
  public boolean createRecruitment(RecruitmentSubmitDto request) {
    if (recruitmentRepository.existsByCompanyIdAndPositionAndRewardAndDescriptionAndTechStack
        (request.getCompanyId(), request.getPosition(), request.getReward(), request.getDescription(),
            request.getTechStack())
    ) {
      return false;
    }
    recruitmentRepository.save(Recruitment.from(request));
    return true;
  }

  public boolean updateRecruitment(RecruitmentSubmitDto request, Long recruitmentId) {
    Optional<Recruitment> recruitment = recruitmentRepository.findById(recruitmentId);
    if (recruitment.isEmpty()) {
      return false;
    }

    Recruitment changedRecruitment = Recruitment.update(recruitment.get().getId(), request);
    recruitmentRepository.save(changedRecruitment);
    return true;
  }

  public boolean deleteRecruitment(Long recruitmentId) {
    Optional<Recruitment> recruitment = recruitmentRepository.findById(recruitmentId);
    if (recruitment.isEmpty()) {
      return false;
    }

    recruitmentRepository.delete(recruitment.get());
    return true;
  }


}