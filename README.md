<h1>원티드 프리온보딩 백엔드 인턴십 선발과제</h1>

<h2>주제 설명</h2>
본 서비스는 기업의 채용을 위한 웹 서비스 입니다.<br>
회사는 채용공고를 생성하고, 이에 사용자는 지원합니다.

<h2>요구 사항</h2>
<h3>1. 회사 대상 서비스</h3><br>

<details>
   <summary>
      (1) 채용공고 생성
   </summary>
   <img src="https://github.com/Kim-Sang-Hun/wanted-pre-onboarding-backend/assets/119822465/7136fa28-1e9f-4ead-ba61-9f0ae42e21b1">
   회사에게 입력받은 값(포지션, 보상, 설명, 기술스택, 만료일자)과 companyId로 같은 회사가 작성한 동일한 채용공고가 이미 있는지 검사하고 없다면 저장합니다. 

</details>
<details>
   <summary>
      (2) 채용공고 수정
   </summary>
   <img src="https://github.com/Kim-Sang-Hun/wanted-pre-onboarding-backend/assets/119822465/57905ff6-ccc5-4e8e-a447-df65c535feaa">
   회사에게 입력받은 값(포지션, 보상, 설명, 기술스택, 만료일자)과 companyId, 그리고 recruitmentId로 수정하려는 채용공고가 요청한 회사의 채용공고인지 확인 후 수정합니다.

</details>
<details>
   <summary>
      (3) 채용공고 삭제
   </summary>
   <img src="https://github.com/Kim-Sang-Hun/wanted-pre-onboarding-backend/assets/119822465/fcada026-acfb-4054-bce9-08d733190ae5">
   companyId, recruitemntId로 삭제하려는 채용공고가 요청한 회사의 채용공고인지 확인 후 삭제합니다.

</details>
<br>
<h3>2. 사용자 대상 서비스</h3><br>
<details>
   <summary>
      (1) 채용공고 조회
   </summary>
   <img src="https://github.com/Kim-Sang-Hun/wanted-pre-onboarding-backend/assets/119822465/089741d9-6e7a-46fc-a992-d75a343f228a">
   전체 채용공고 중 아직 만료되지 않은 채용공고만을 가져옵니다. 가져오면서 만료일자가 가장 가까운 채용공고 순서대로 가져옵니다.
   설명을 제거하기 위해 DTO를 사용했습니다. DTO 내부에는 (채용공고 ID, 회사 이름, 회사가 있는 나라, 회사의 지역, 찾는 포지션, 보상, 기술스택, 만료일자)가 들어있습니다.

</details>
<details>
   <summary>
      (2) 채용 상세 페이지 조회
   </summary>
   <img src="https://github.com/Kim-Sang-Hun/wanted-pre-onboarding-backend/assets/119822465/41066ded-b413-495b-9ae5-14cda87d6231">
   @Query를 통해 그 회사의 다른 채용공고를 만료일자가 가장 가까운 채용공고 순서대로 가져옵니다.
   이 때 (1)의 채용공고 정보에 채용설명과 다른 채용공고 List를 추가하여 가져옵니다.
</details>

<details>
   <summary>
      (3) 채용공고 지원
   </summary>
   <img src="https://github.com/Kim-Sang-Hun/wanted-pre-onboarding-backend/assets/119822465/f2b6901a-7b80-43d6-a657-811e085363ef">
   이미 지원한 채용공고인지 확인한 후에 저장합니다.
   
</details>
<details>
   <summary>
      (4) 지원한 채용공고 확인
   </summary>
   <img src="https://github.com/Kim-Sang-Hun/wanted-pre-onboarding-backend/assets/119822465/205ee83e-daf7-4f4f-a962-25c18ce1e90c">
   유저가 지원한 채용공고들을 가져옵니다. 이 때 (1)의 채용공고 정보에 지원일자를 추가하며, 가져올 때 유저가 지원한 일자가 가까운 순서대로 가져옵니다.

</details>


<h2>사용 기술 스택</h2>
    <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white">
    <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
    <img src="https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=mariadb&logoColor=white">
    <img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white">

