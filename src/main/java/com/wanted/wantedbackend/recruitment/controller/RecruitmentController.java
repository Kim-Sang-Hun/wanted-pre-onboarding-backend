package com.wanted.wantedbackend.recruitment.controller;

import com.wanted.wantedbackend.recruitment.model.Recruitment;
import com.wanted.wantedbackend.recruitment.model.dto.RecruitmentSubmitDto;
import com.wanted.wantedbackend.recruitment.service.RecruitmentService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruitment")
public class RecruitmentController {

  private final RecruitmentService recruitmentService;

  @PostMapping("/create")
  public ResponseEntity<?> createRecruitment(
      @RequestParam(value = "companyId") Long companyId,
      @Valid @RequestBody RecruitmentSubmitDto request
  ) {
    Recruitment recruitment = recruitmentService.createRecruitment(companyId, request);
    return ResponseEntity.ok("생성 성공, id: " + recruitment.getId());
  }

  @PutMapping("/update")
  public ResponseEntity<?> updateRecruitment(
      @RequestParam(value = "companyId") Long companyId,
      @RequestParam(value = "recruitmentId") Long recruitmentId,
      @Valid @RequestBody RecruitmentSubmitDto request
  ) {
    Recruitment recruitment = recruitmentService.updateRecruitment(companyId, recruitmentId, request);
    return ResponseEntity.ok("수정 성공, id: " + recruitment.getId());
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> deleteRecruitment(
      @RequestParam(value = "companyId") Long companyId,
      @RequestParam(value = "recruitmentId") Long recruitmentId
  ) {
    Long id = recruitmentService.deleteRecruitment(companyId, recruitmentId);
    return ResponseEntity.ok("삭제 성공, id:" + id);
  }

  @GetMapping("")
  public ResponseEntity<?> getAllRecruitments() {
    return ResponseEntity.ok(recruitmentService.getAllRecruitments());
  }

  @GetMapping("/{recruitmentId}")
  public ResponseEntity<?> getRecruitmentDetail(
      @PathVariable Long recruitmentId
  ) {
    return ResponseEntity.ok(recruitmentService.getRecruitmentDetail(recruitmentId));
  }

}