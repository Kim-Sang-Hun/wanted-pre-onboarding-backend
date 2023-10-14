package com.wanted.wantedbackend.appliance.controller;

import com.wanted.wantedbackend.appliance.model.Appliance;
import com.wanted.wantedbackend.appliance.model.dto.ApplianceReadDto;
import com.wanted.wantedbackend.appliance.service.ApplianceService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApplianceController {

  private final ApplianceService applianceService;

  @PostMapping("/appliance/create")
  public ResponseEntity<?> createAppliance(
      @RequestParam(value = "userId") Long userId,
      @RequestParam(value = "recruitmentId") Long recruitmentId
  ) {
    Appliance appliance = applianceService.createAppliance(userId, recruitmentId);
    return ResponseEntity.ok("생성 성공, id: " + appliance.getId());
  }

  @GetMapping("/appliance")
  public ResponseEntity<?> getAppliances(
      @RequestParam(value = "userId") Long userId
  ) {
    List<ApplianceReadDto> list = applianceService.getAppliances(userId);
    return ResponseEntity.ok(list);
  }
}