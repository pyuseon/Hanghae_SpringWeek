# Spring 입문 주차 기본 과제   
<img src="https://user-images.githubusercontent.com/44867889/151365270-c9f8bcec-3aeb-4463-906f-36394a36da44.png" width="400" height="400">

## 1. Requirement
1. 전체 게시글 목록 조회 페이지
    - 제목, 작성자명, 작성 날짜를 조회하기
    - 작성 날짜 기준으로 내림차순 정렬하기
    - 특정 게시글을 클릭할 경우 `게시글 조회 페이지`로 이동하기
2. 게시글 작성 페이지
    - 제목, 작성자명, 작성 내용을 입력하기
    - "글쓰기" 버튼을 클릭하면 `전체 게시글 목록 조회 페이지`로 이동하고, 최신 게시글이 최상단에 위치함을 확인하기
3. 게시글 조회 페이지
    - 제목, 작성자명, 작성 날짜, 작성 내용을 조회하기
    
    
## 2. AWS 배포
* RDS 연결
    - MySQL을 이용하기
* EC2 배포
    - Ubuntu EC2 를 구매한 뒤, 8080 포트와 80번 포트를 연결하여 포트 번호 없이도 서비스에 접속 가능하게 하기

## 3. API 설계
|기능|Method|url|반환
|---|-----|---|-----|
|게시물 생성|POST|/api/post|Post|
|전체 게시물 조회|GET|/api/posts|List<Post>|
|상세 게시물 조회|GET|/api/posts/{id}|Post|
|게시물 업데이트|PUT|/api/posts/{id}||
|게시물 삭제|DELETE|/api/posts/{id}|id|  
    
## 4. 사용 프레임워크   
* Spring Data JPA
* Spring Web
* Mysql
* Lombok
* H2 database
  
## 5. 기능구현

 
