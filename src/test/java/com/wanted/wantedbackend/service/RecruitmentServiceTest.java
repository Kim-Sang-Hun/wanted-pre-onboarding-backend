package com.wanted.wantedbackend.service;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.wanted.wantedbackend.recruitment.model.Recruitment;
import com.wanted.wantedbackend.recruitment.model.dto.RecruitmentDetailReadDto;
import com.wanted.wantedbackend.recruitment.model.dto.RecruitmentReadDto;
import com.wanted.wantedbackend.recruitment.model.dto.RecruitmentSubmitDto;
import com.wanted.wantedbackend.recruitment.repository.RecruitmentRepository;
import com.wanted.wantedbackend.recruitment.service.RecruitmentService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
public class RecruitmentServiceTest {

  @Mock
  private RecruitmentRepository recruitmentRepository;

  @InjectMocks
  private RecruitmentService recruitmentService;

  @Test
  @DisplayName("조회시 값을 제대로 가져오는지 확인")
  void read() {
    //given
    Recruitment recruitment1 = Recruitment.builder()
        .position("백엔드")
        .reward(100L)
        .description("자바 개발자 구함")
        .techStack("자바")
        .expireDate(LocalDateTime.of(9000, 1, 1, 1, 1))
        .build();
    Recruitment recruitment2 = Recruitment.builder()
        .position("프론트엔드")
        .reward(10L)
        .description("리액트 개발자 구함")
        .techStack("리액트")
        .expireDate(LocalDateTime.of(5000, 1, 1, 1, 1))
        .build();
    List<Recruitment> list = List.of(recruitment2, recruitment1);
    when(recruitmentRepository.findNotExpiredRecruitments()).thenReturn(list);
    //when
    List<RecruitmentReadDto> dtos = recruitmentService.getAllRecruitments();
    //then
    Assertions.assertThat(dtos.get(0).getPosition()).isEqualTo("프론트엔드");
  }

  @Test
  @DisplayName("세부정보 조회시 값을 제대로 가져오는지 확인")
  void readDetail() {
    //given
    Recruitment recruitment1 = Recruitment.builder()
        .id(1L)
        .companyId(1L)
        .position("백엔드")
        .reward(100L)
        .description("자바 개발자 구함")
        .techStack("자바")
        .expireDate(LocalDateTime.of(9000, 1, 1, 1, 1))
        .build();
    Recruitment recruitment2 = Recruitment.builder()
        .id(2L)
        .companyId(1L)
        .position("프론트엔드")
        .reward(10L)
        .description("리액트 개발자 구함")
        .techStack("리액트")
        .expireDate(LocalDateTime.of(5000, 1, 1, 1, 1))
        .build();
    Recruitment recruitment3 = Recruitment.builder()
        .id(3L)
        .companyId(1L)
        .position("백엔드")
        .reward(1L)
        .description("루비 개발자 구함")
        .techStack("루비")
        .expireDate(LocalDateTime.of(4500, 1, 1, 1, 1))
        .build();
    List<Recruitment> list = List.of(recruitment3, recruitment2);
    when(recruitmentRepository.findById(any())).thenReturn(Optional.of(recruitment1));
    when(recruitmentRepository.findOtherRecruitmentsByCompanyId(any(), any())).thenReturn(list);
    //when
    RecruitmentDetailReadDto recruitmentDetail = recruitmentService.getRecruitmentDetail(1L);
    List<Long> otherRecruitments = recruitmentDetail.getOtherRecruitments();
    //then
    Assertions.assertThat(recruitmentDetail.getDescription()).isEqualTo("자바 개발자 구함");
    Assertions.assertThat(otherRecruitments.get(0)).isEqualTo(3L);
    Assertions.assertThat(otherRecruitments.size()).isEqualTo(2);
  }

  @Test
  @DisplayName("채용공고 내의 회사정보와 수정하려는 회사가 다른 경우 오류 발생")
  void update1() {
    //given
    Recruitment recruitment1 = Recruitment.builder()
        .id(1L)
        .companyId(1L)
        .position("백엔드")
        .reward(100L)
        .description("자바 개발자 구함")
        .techStack("자바")
        .expireDate(LocalDateTime.of(9000, 1, 1, 1, 1))
        .build();
    RecruitmentSubmitDto request = RecruitmentSubmitDto.builder()
        .position("프론트엔드")
        .reward(1L)
        .description("리액트 개발자 구함")
        .techStack("리액트")
        .expireDate(LocalDateTime.of(9000, 1, 1, 1, 1))
        .build();
    when(recruitmentRepository.findById(any())).thenReturn(Optional.of(recruitment1));
    //when
    Throwable throwable = catchThrowable(() ->
        recruitmentService.updateRecruitment(123L, 1L, request));
    //then
    Assertions.assertThat(throwable).message().isEqualTo("다른 회사의 채용공고입니다.");
  }

  @Test
  @DisplayName("수정 잘 되는지 확인")
  void update2() {
    //given
    Recruitment recruitment1 = Recruitment.builder()
        .id(1L)
        .companyId(1L)
        .position("백엔드")
        .reward(100L)
        .description("자바 개발자 구함")
        .techStack("자바")
        .expireDate(LocalDateTime.of(9000, 1, 1, 1, 1))
        .build();
    RecruitmentSubmitDto request = RecruitmentSubmitDto.builder()
        .position("프론트엔드")
        .reward(1L)
        .description("리액트 개발자 구함")
        .techStack("리액트")
        .expireDate(LocalDateTime.of(4500, 1, 1, 1, 1))
        .build();
    when(recruitmentRepository.findById(any())).thenReturn(Optional.of(recruitment1));
    //when
    Recruitment updatedRecruitment = recruitmentService.updateRecruitment(1L, 1L, request);
    //then
    Assertions.assertThat(updatedRecruitment.getPosition()).isEqualTo("프론트엔드");
    Assertions.assertThat(updatedRecruitment.getReward()).isEqualTo(1L);
    Assertions.assertThat(updatedRecruitment.getDescription()).isEqualTo("리액트 개발자 구함");
    Assertions.assertThat(updatedRecruitment.getTechStack()).isEqualTo("리액트");
    Assertions.assertThat(updatedRecruitment.getExpireDate())
        .isEqualTo(LocalDateTime.of(4500, 1, 1, 1, 1));
  }

  @Test
  @DisplayName("채용공고 내의 회사정보와 수정하려는 회사가 다른 경우 오류 발생")
  void delete1() {
    //given
    Recruitment recruitment1 = Recruitment.builder()
        .id(1L)
        .companyId(1L)
        .position("백엔드")
        .reward(100L)
        .description("자바 개발자 구함")
        .techStack("자바")
        .expireDate(LocalDateTime.of(9000, 1, 1, 1, 1))
        .build();
    when(recruitmentRepository.findById(any())).thenReturn(Optional.of(recruitment1));
    //when
    Throwable throwable = catchThrowable(() ->
        recruitmentService.deleteRecruitment(123L, 1L));
    //then
    Assertions.assertThat(throwable).message().isEqualTo("다른 회사의 채용공고입니다.");
  }

  @Test
  @DisplayName("삭제 성공")
  void delete2() {
    //given
    Recruitment recruitment1 = Recruitment.builder()
        .id(1L)
        .companyId(1L)
        .position("백엔드")
        .reward(100L)
        .description("자바 개발자 구함")
        .techStack("자바")
        .expireDate(LocalDateTime.of(9000, 1, 1, 1, 1))
        .build();
    when(recruitmentRepository.findById(any())).thenReturn(Optional.of(recruitment1));
    //when
    Long id = recruitmentService.deleteRecruitment(1L, 1L);
    //then
    Assertions.assertThat(id).isNotNull();
  }
}