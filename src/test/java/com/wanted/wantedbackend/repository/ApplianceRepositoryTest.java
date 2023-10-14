package com.wanted.wantedbackend.repository;

import com.wanted.wantedbackend.appliance.model.Appliance;
import com.wanted.wantedbackend.appliance.model.dto.ApplianceReadDto;
import com.wanted.wantedbackend.appliance.repository.ApplianceRepository;
import com.wanted.wantedbackend.recruitment.model.Recruitment;
import com.wanted.wantedbackend.recruitment.repository.RecruitmentRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ApplianceRepositoryTest {

  @Autowired
  private ApplianceRepository applianceRepository;
  @Autowired
  private RecruitmentRepository recruitmentRepository;

  @Test
  @DisplayName("유저Id와 채용공고Id로 가져와지는지 확인")
  void findByUserIdAndRecruitmentId() {
    //given
    Appliance appliance1 = Appliance.builder()
        .userId(1L)
        .recruitmentId(1L)
        .build();
    applianceRepository.save(appliance1);
    //when
    Optional<Appliance> found = applianceRepository.findByUserIdAndRecruitmentId(1L, 1L);
    Optional<Appliance> notFound1 = applianceRepository.findByUserIdAndRecruitmentId(2L, 1L);
    Optional<Appliance> notFound2 = applianceRepository.findByUserIdAndRecruitmentId(1L, 2L);
    //then
    Assertions.assertThat(found).isPresent();
    Assertions.assertThat(notFound1).isEmpty();
    Assertions.assertThat(notFound2).isEmpty();
  }

  @Test
  @DisplayName("유저Id로 채용공고의 정보들을 가져올 수 있는지 확인")
  void findRecruitmentsByUserId() {
    //given
    Recruitment recruitment1 = recruitmentRepository.save(Recruitment.builder()
        .companyName("원티드")
        .location("서울")
        .position("백엔드")
        .reward(100L)
        .description("자바 개발자 구함")
        .techStack("자바")
        .expireDate(LocalDateTime.of(3000, 1, 1, 1, 1))
        .build());
    Recruitment recruitment2 = recruitmentRepository.save(Recruitment.builder()
        .companyName("원티드")
        .location("서울")
        .position("백엔드")
        .reward(10L)
        .description("파이썬 개발자 구함")
        .techStack("파이썬")
        .expireDate(LocalDateTime.of(3000, 1, 1, 1, 1))
        .build());
    Recruitment recruitment3 = recruitmentRepository.save(Recruitment.builder()
        .companyName("원티드")
        .location("서울")
        .position("프론트엔드")
        .reward(1L)
        .description("리액트 개발자 구함")
        .techStack("리액트")
        .expireDate(LocalDateTime.of(3000, 1, 1, 1, 1))
        .build());
    Appliance appliance1 = applianceRepository.save(Appliance.builder()
        .userId(1L)
        .recruitmentId(recruitment1.getId())
        .build());
    Appliance appliance2 = applianceRepository.save(Appliance.builder()
        .userId(1L)
        .recruitmentId(recruitment2.getId())
        .build());
    Appliance appliance3 = applianceRepository.save(Appliance.builder()
        .userId(1L)
        .recruitmentId(recruitment3.getId())
        .build());
    //when
    List<ApplianceReadDto> list = applianceRepository.findRecruitmentsByUserId(1L);
    //then
    //lastModifiedAt 역순으로 정렬해서 가져오므로, 가장 마지막에 등록한 appliance3이 제일 처음 와야 함
    Assertions.assertThat(list.get(0).getApplianceId()).isEqualTo(appliance3.getId());
    Assertions.assertThat(list.get(1).getApplianceId()).isEqualTo(appliance2.getId());
    Assertions.assertThat(list.get(2).getApplianceId()).isEqualTo(appliance1.getId());
  }

}