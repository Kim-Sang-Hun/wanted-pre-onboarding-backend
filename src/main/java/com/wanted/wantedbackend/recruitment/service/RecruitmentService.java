package com.wanted.wantedbackend.recruitment.service;

import com.wanted.wantedbackend.recruitment.model.dto.RecruitmentDetailReadDto;
import com.wanted.wantedbackend.recruitment.model.dto.RecruitmentReadDto;
import com.wanted.wantedbackend.recruitment.model.dto.RecruitmentSubmitDto;
import com.wanted.wantedbackend.company.model.Company;
import com.wanted.wantedbackend.company.repository.CompanyRepository;
import com.wanted.wantedbackend.recruitment.repository.RecruitmentRepository;
import com.wanted.wantedbackend.recruitment.model.Recruitment;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RecruitmentService {

  private final RecruitmentRepository recruitmentRepository;
  private final CompanyRepository companyRepository;

  public void createRecruitment(Long companyId, RecruitmentSubmitDto request) {

    Optional<Recruitment> recruitment = recruitmentRepository.findSameRecruitments(
        companyId, request.getPosition(), request.getReward(), request.getDescription(),
        request.getTechStack());
    if (recruitment.isPresent()) {
      throw new IllegalArgumentException("똑같은 채용공고가 이미 있습니다.");
    }

    Company company = companyRepository.findById(companyId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회사입니다."));

    recruitmentRepository.save(Recruitment.from(company, request));
  }

  public void updateRecruitment(Long companyId, Long recruitmentId, RecruitmentSubmitDto request) {
    Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채용공고입니다."));
    if (!companyId.equals(recruitment.getCompanyId())) {
      throw new IllegalArgumentException("다른 회사의 채용공고입니다.");
    }

    recruitment.updateRecruitment(request);
  }

  public void deleteRecruitment(Long companyId, Long recruitmentId) {
    Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채용공고입니다."));
    if (!companyId.equals(recruitment.getCompanyId())) {
      throw new IllegalArgumentException("다른 회사의 채용공고입니다.");
    }

    recruitmentRepository.delete(recruitment);
  }

  @Transactional(readOnly = true)
  public List<RecruitmentReadDto> getAllRecruitments() {
    List<Recruitment> recruitments = recruitmentRepository.findNotExpiredRecruitments();

    return recruitments.stream().map(RecruitmentReadDto::from).collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public RecruitmentDetailReadDto getRecruitmentDetail(Long recruitmentId) {
    Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채용공고입니다."));

    List<Long> otherRecruitmentIds = this.getOtherRecruitmentIds(recruitment.getCompanyId(), recruitmentId);

    return RecruitmentDetailReadDto.from(recruitment, otherRecruitmentIds);
  }

  private List<Long> getOtherRecruitmentIds(Long companyId, Long currentRecruitmentId) {
    List<Recruitment> otherRecruitments = recruitmentRepository
        .findOtherNotExpiredRecruitmentsByCompanyId(companyId, currentRecruitmentId);

    return otherRecruitments.stream().map(Recruitment::getCompanyId).collect(
        Collectors.toList());
  }
}