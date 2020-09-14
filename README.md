# kakaopay
- 카카오페이 뿌리기 기능 구현

## 개발환경
Spring Boot - maven / mySQL - resource 하위에 DDL 포함

## 핵심 문제 해결 전략
1. TB_뿌리기요청기본 ( TB_SRPEAD_REQUEST_BAS )과 TB_뿌리기요청내역 ( TB_SPREAD_REQUEST_IZ )

: "뿌리기" 요청의 기본 정보를 담고 있는 TB_뿌리기요청기본, "분배 된 정보"를 담고 있는 TB_뿌리기요청내역으로 나누어 관리

2. 받기 기능의 시간 제약이 있다는 점을 코드 전반적으로 활용하기 위해 QUERY에 적용

: 받기 기능이 10분간 유효하다는 점을 이용하여, 받기 요청 시점보다 (token, roomID)쌍이 등록된지 10분 이상 경과되었다면 유효하지 않은 정보로 판단
  예제 > INSERT 시 RG_REQUEST_DATE(등록요청일자)는 등록하고자 하는 DATE
        SELECT & UPDAE 시 RG_REQUEST_DATE(등록요청일자)는 (받기 요청시간 - 10min)으로, DB적재되어 있는 RG_REQUEST_DATE(등록요청일자)과 비교하는 용도로 사용
        조회 기능에서는 (받기 요청시간 - 10min)이 아닌 (받기 요청시간 - 7day)으로 사용
