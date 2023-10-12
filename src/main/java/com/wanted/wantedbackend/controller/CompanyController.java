package com.wanted.wantedbackend.controller;

import com.wanted.wantedbackend.domain.dto.RecruitmentSubmitDto;
import com.wanted.wantedbackend.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CompanyController {

  private final RecruitmentService recruitmentService;

  @PostMapping("/recruitment/create")
  public ResponseEntity<?> createRecruitment(@RequestBody RecruitmentSubmitDto request) {
    if (!recruitmentService.createRecruitment(request)) {
      return ResponseEntity.badRequest().body("생성 실패");
    }

    return ResponseEntity.ok("생성 성공");
  }

  @PutMapping("/recruitment/update")
  public ResponseEntity<?> updateRecruitment(@RequestBody RecruitmentSubmitDto request,
      Long recruitmentId) {
    if (!recruitmentService.updateRecruitment(request, recruitmentId)) {
      return ResponseEntity.badRequest().body("수정 실패");
    }

    return ResponseEntity.ok("수정 성공");
  }

  @DeleteMapping("/recruitment/delete")
  public ResponseEntity<?> deleteRecruitment(@RequestBody Long recruitmentId) {
    if (!recruitmentService.deleteRecruitment(recruitmentId)) {
      return ResponseEntity.badRequest().body("삭제 실패");
    }

    return ResponseEntity.ok("삭제 성공");
  }
}