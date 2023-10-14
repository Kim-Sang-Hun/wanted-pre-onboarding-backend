package com.wanted.wantedbackend.repository;

import com.wanted.wantedbackend.recruitment.model.Recruitment;
import com.wanted.wantedbackend.recruitment.repository.RecruitmentRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class RecruitmentRepositoryTest {

  @Autowired
  private RecruitmentRepository recruitmentRepository;

  @Test
  @DisplayName("같은 정보를 가진 채용공고를 찾아내는지 확인")
  void findSameRecruitments() {
    //given
    Recruitment sameRecruitment = recruitmentRepository.save(Recruitment.builder()
        .companyId(1L)
        .position("백엔드")
        .reward(100L)
        .description("자바 개발자 구함")
        .techStack("자바")
        .build());
    Recruitment differentRecruitment = recruitmentRepository.save(Recruitment.builder()
        .companyId(1L)
        .position("프론트엔드")
        .reward(10L)
        .description("리액트 개발자 구함")
        .techStack("리액트")
        .build());
    //when
    Recruitment foundRecruitment = recruitmentRepository.findSameRecruitment(1L
            , "백엔드", 100L, "자바 개발자 구함", "자바")
        .orElse(null);
    //then
    Assertions.assertThat(foundRecruitment).isEqualTo(sameRecruitment);
  }

  @Test
  @DisplayName("만료되지 않은 채용공고만 가져오는지 확인")
  void findNotExpiredRecruitments() {
    //given
    Recruitment recruitment = recruitmentRepository.save(Recruitment.builder()
        .companyId(1L)
        .position("백엔드")
        .reward(100L)
        .description("자바 개발자 구함")
        .techStack("자바")
        .expireDate(LocalDateTime.of(9024, 1, 1, 1, 1))
        .build());
    Recruitment expiredRecruitment = recruitmentRepository.save(Recruitment.builder()
        .companyId(1L)
        .position("프론트엔드")
        .reward(10L)
        .description("리액트 개발자 구함")
        .techStack("리액트")
        .expireDate(LocalDateTime.of(2021, 1, 1, 1, 1))
        .build());
    //when
    List<Recruitment> recruitments = recruitmentRepository.findNotExpiredRecruitments();
    //then
    Assertions.assertThat(recruitments.get(0)).isEqualTo(recruitment);
    Assertions.assertThat(recruitments.size()).isEqualTo(1);
  }

  @Test
  @DisplayName("가져오면서 만료일자가 가까운 순서대로 가져오는지 확인")
  void findNotExpiredRecruitmentsOrdered() {
    //given
    Recruitment recruitment1 = recruitmentRepository.save(Recruitment.builder()
        .companyId(1L)
        .position("백엔드")
        .reward(100L)
        .description("자바 개발자 구함")
        .techStack("자바")
        .expireDate(LocalDateTime.of(9000, 1, 1, 1, 1))
        .build());
    Recruitment recruitment2 = recruitmentRepository.save(Recruitment.builder()
        .companyId(1L)
        .position("프론트엔드")
        .reward(10L)
        .description("리액트 개발자 구함")
        .techStack("리액트")
        .expireDate(LocalDateTime.of(5000, 1, 1, 1, 1))
        .build());
    //when
    List<Recruitment> recruitments = recruitmentRepository.findNotExpiredRecruitments();
    //then
    Assertions.assertThat(recruitments.get(0)).isEqualTo(recruitment2);
    Assertions.assertThat(recruitments.get(1)).isEqualTo(recruitment1);
  }

  @Test
  @DisplayName("회사의 다른 공고를 만료되지 않은 것만 가져오는지 확인")
  void findOtherNotExpiredRecruitmentsByCompanyId() {
    //given
    Recruitment recruitment = recruitmentRepository.save(Recruitment.builder()
        .companyId(1L)
        .position("백엔드")
        .reward(100L)
        .description("자바 개발자 구함")
        .techStack("자바")
        .expireDate(LocalDateTime.of(9024, 1, 1, 1, 1))
        .build());
    Recruitment otherRecruitmentOfSameCompany = recruitmentRepository.save(Recruitment.builder()
        .companyId(1L)
        .position("프론트엔드")
        .reward(10L)
        .description("리액트 개발자 구함")
        .techStack("리액트")
        .expireDate(LocalDateTime.of(9024, 1, 1, 1, 1))
        .build());
    Recruitment recruitment3 = recruitmentRepository.save(Recruitment.builder()
        .companyId(1L)
        .position("백엔드")
        .description("자바 개발자 구함")
        .reward(10L)
        .techStack("자바")
        .expireDate(LocalDateTime.of(2020, 1, 1, 1, 1))
        .build());
    Recruitment recruitmentOfOtherCompany = recruitmentRepository.save(Recruitment.builder()
        .companyId(2L)
        .position("백엔드")
        .description("루비 개발자 구함")
        .reward(1L)
        .techStack("루비")
        .expireDate(LocalDateTime.of(9024, 1, 1, 1, 1))
        .build());
    //when
    List<Recruitment> recruitments = recruitmentRepository
        .findOtherRecruitmentsByCompanyId(1L, recruitment.getId());
    //then
    Assertions.assertThat(recruitments.get(0)).isEqualTo(otherRecruitmentOfSameCompany);
    Assertions.assertThat(recruitments.size()).isEqualTo(1);
  }
}