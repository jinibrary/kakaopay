# kakaopay
- 카카오페이 뿌리기 기능 구현

##개발환경
Spring Boot - maven
mySQL - resource 하위에 DDL 포함

##핵심 문제 해결 전략
1.TB_뿌리기요청기본 ( TB_SRPEAD_REQUEST_BAS )와 TB_뿌리기요청내역 ( TB_SPREAD_REQUEST_IZ )
"뿌리기" 요청의 기본 정보를 담고 있는 TB_뿌리기요청기본과 "분배 된 정보"를 담고 있는 TB_뿌리기요청내역으로 나누어 관리함으로써 데이터를 분산시킴
2.받기 기능 및 조회 기능이 시간 제약이 있다는 점을 QUERY에 적용
받기 기능이 10분간 유효하다는 점을 이용하여, 받기요청 시점보다 (token, roomID)쌍이 등록된지 10분 이상 경과되었다면 유효하지 않은 정보로 판단
