package com.wanted.wantedbackend.recruitment.controller;

import com.wanted.wantedbackend.recruitment.model.dto.RecruitmentSubmitDto;
import com.wanted.wantedbackend.recruitment.service.RecruitmentService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecruitmentController {
  private final RecruitmentService recruitmentService;

  @PostMapping("/recruitment/create")
  public ResponseEntity<?> createRecruitment(
      @RequestParam(value = "companyId") Long companyId,
      @Valid @RequestBody RecruitmentSubmitDto request
  ) {
    recruitmentService.createRecruitment(companyId, request);
    return ResponseEntity.ok("생성 성공");
  }

  @PutMapping("/recruitment/update")
  public ResponseEntity<?> updateRecruitment(
      @RequestParam(value = "companyId") Long companyId,
      @RequestParam(value = "recruitmentId") Long recruitmentId,
      @Valid @RequestBody RecruitmentSubmitDto request
  ) {
    recruitmentService.updateRecruitment(companyId, recruitmentId, request);
    return ResponseEntity.ok("수정 성공");
  }

  @DeleteMapping("/recruitment/delete")
  public ResponseEntity<?> deleteRecruitment(
      @RequestParam(value = "companyId") Long companyId,
      @RequestParam(value = "recruitmentId") Long recruitmentId
  ) {
    recruitmentService.deleteRecruitment(companyId, recruitmentId);
    return ResponseEntity.ok("삭제 성공");
  }

  @GetMapping("/recruitment/get")
  public ResponseEntity<?> getAllRecruitments() {
    return ResponseEntity.ok(recruitmentService.getAllRecruitments());
  }

  @GetMapping("/recruitment/get")
  public ResponseEntity<?> getRecruitmentDetail(
      @RequestParam(value = "recruitmentId") Long recruitmentId
  ) {
    return ResponseEntity.ok(recruitmentService.getRecruitmentDetail(recruitmentId));
  }
}