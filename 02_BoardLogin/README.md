# Spring 심화 주차 기본 과제   
  
* 로그인  
<img src="https://user-images.githubusercontent.com/44867889/151365586-de1e8752-e1ac-42b3-aa44-285b4245661b.png" width="70%" height="70%">      
   
## 1. Requirement
1. 회원 가입 페이지
2. 로그인 페이지
3. 로그인 검사
4. 소셜 로그인 기능 만들기
5. 게시글 조회 페이지 > 댓글 목록 조회
6. 게시글 조회 페이지 > 댓글 작성
7. 게시글 조회 페이지 > 댓글 수정
8. 게시글 조회 페이지 > 댓글 삭제
9. 회원가입 테스트 코드 작성
    
## 2. API 설계
### 로그인
|기능|Method|url|
|---|-----|---|
|로그인 페이지 이동|GET| /user/login|
|회원가입 페이지 이동|GET| /user/signup|
|회원가입 요청 처리|POST|/user/signup|
|카카오 로그인|GET|/user/kakao/callback|

### 게시물
|기능|Method|url|
|-------|-----|---|
|게시글 생성|POST|/posting|
|게시글 조회|GET|/posts|
|상세 게시글 조회|GET|/posts/detail/{id}|
|게시글 수정|PUT|/posting/detail/{id}|
|게시글 삭제|DELETE|/posting/detail/{id}|
|로그인 사용자 정보 조회|GET|/post/current|  

### 댓글
|기능|Method|url|
|-------|-----|---|
|댓글 생성|POST|/write|
|게시물별 댓글 조회|GET|/comment/{postId}|
|댓글 수정|GET|/commenting/{id}|
|댓글 삭제|DELETE|/commenting/{id}|

### 페이지 전환
|기능|Method|url|
|-------|-----|---|
|상세 페이지 유저 정보 받아오기|GET|/posts/detail|
|글작성 페이지로 전환|GET|/posting|
|메인 페이지로 전환|GET|/|
|접근금지 페이지로 전환|GET|/forbidden|

  
## 4. 사용 프레임워크   
* Spring Data JPA
* Spring Web
* Mysql
* Lombok
* H2 database
  
## 5. 완성페이지
* 글쓰기  
<img src="https://user-images.githubusercontent.com/44867889/151365586-de1e8752-e1ac-42b3-aa44-285b4245661b.png" width="70%" height="70%">              
    
  
* 상세
  
<img src="https://user-images.githubusercontent.com/44867889/151365495-b9f83708-add9-4157-aa3c-6e7e759d60f1.png"  width="70%" height="70%">    

## 6. 내가 해결한 문제 
* 페이지 넘기기 기능이 제대로 작동하지 않음 
  해결방안 : @RestController 어노테이션을 사용하면 Json으로 반환하기 때문에 생긴 오류. @Controller 로 수정함. 
  
* 로그인한 사용자 정보 받아오기 
  해결방안 : 컨트롤러를 추가해서 사용자 정보를 받아옴. 그러나, 로그인을 하지 않았을 시에는 사용자 정보를 받아오기 어려운 경우가 있어 Model.AddAttribute를 이용해 
  정보를 받아오고 타임리프를 사용해 로그인 아이디를 프론트에 전달해 주는 방식을 사용함. 사용자 정보를 프론트에 넘기는 것은 추가로 공부를 해 보아야 할것임. 
  
* 작성일자, 수정일자가 반영되지 않음 
  해결방안 : @EnableJpaAuditing 어노테이션을 Applicaiton 파일에 추가하지 않아서 생긴 오류. 어노테이션을 추가하니 제대로 작동함. 

* 디테일 페이지 정보를 받아오지 못함. 
  해결방안 : HTML ajax 수정, 백틱을 제대로 사용하지 않아서 생긴 오류로 코드 수정을 통해 해결함. 
  
=> 스프링 작동 방식에대한 깊은 이해 없이 작성한 부분이 있어서 

  
## 세부 요구사항 
1. 회원 가입 페이지
    - 회원가입 버튼을 클릭하기
    - 닉네임, 비밀번호, 비밀번호 확인을 입력하기
    - 닉네임은 `최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 구성하기
    - 비밀번호는 `최소 4자 이상이며, 닉네임과 같은 값이 포함된 경우 회원가입에 실패`로 만들기
    - 비밀번호 확인은 비밀번호와 정확하게 일치하기
    - 데이터베이스에 존재하는 닉네임을 입력한 채 회원가입 버튼을 누른 경우 "중복된 닉네임입니다." 라는 에러메세지를 프론트엔드에서 보여주기
    - 회원가입 버튼을 누르고 에러메세지가 발생하지 않는다면 `로그인 페이지`로 이동시키기
2. 로그인 페이지
    - 로그인, 회원가입 버튼을 만들기
    - 닉네임, 비밀번호 입력란 만들기
    - 로그인 버튼을 누른 경우 닉네임과 비밀번호가 데이터베이스에 등록됐는지 확인한 뒤, 하나라도 맞지 않는 정보가 있다면 "닉네임 또는 패스워드를 확인해주세요"라는 메세지를 프론트엔드에서 보여주기
    - 로그인 버튼을 눌러서 에러 메세지가 발생하지 않는다면 `전체 게시글 목록 조회 페이지`로 이동시키기
3. 로그인 검사
    - `아래 페이지를 제외하고` 모두 로그인 한 경우만 볼 수 있도록 하기
        - 회원가입 페이지
        - 로그인 페이지
        - 게시글 목록 조회 페이지
        - 게시글 조회 페이지
    - 로그인을 하지 않거나 올바르지 않은 경로로 접속한 사용자가 로그인이 필요한 경로에 접속한 경우 "로그인이 필요합니다." 라는 메세지를 프론트엔드에서 띄워주고 `로그인 페이지`로 이동시키기
    - 로그인 한 사용자가 로그인 페이지 또는 회원가입 페이지에 접속한 경우 "이미 로그인이 되어있습니다."라는 메세지를 띄우고 `전체 게시글 목록 조회 페이지`로 이동시키기
- api 막기 (Post)
4. 소셜 로그인 기능 만들기
    - 카카오 소셜 로그인이 가능하도록 하기
5. 게시글 조회 페이지 > 댓글 목록 조회
    - 로그인 하지 않은 사용자도 댓글 목록 조회가 가능하도록 하기
    - 현재 조회중인 게시글에 작성된 모든 댓글을 목록 형식으로 볼 수 있도록 하기
    - 댓글 목록 위에 댓글 작성란 만들기
        - 댓글 작성에 관한 기능은 5번 요구사항으로 연결하기
    - 댓글 목록 중, 내가 작성한 댓글인 경우 댓글 수정, 댓글 삭제 버튼 만들기
        - 댓글 수정 버튼을 누르면 6번 요구사항으로 연결하기
        - 댓글 삭제 버튼을 누르면 7번 요구사항으로 연결하기
    - 제일 최근 작성된 댓글을 맨 위에 띄우기
6. 게시글 조회 페이지 > 댓글 작성
    - 로그인 한 사용자만 댓글 작성이 가능하도록 하기
    - 게시글 조회 페이지 하단에 댓글 내용을 입력할 수 있는 댓글 작성 버튼 만들기
    - 로그인 하지 않은 사용자가 댓글 작성란을 누르면 "로그인이 필요한 기능입니다." 라는 메세지를 띄우고 `로그인 페이지`로 이동시키기
    - 댓글 내용란을 비워둔 채 댓글 작성 버튼을 누르면 "댓글 내용을 입력해주세요" 라는 메세지를 띄우기
    - 댓글 내용을 입력하고 댓글 작성 버튼을 누른 경우 작성한 댓글을 추가하기
7. 게시글 조회 페이지 > 댓글 수정
    - 내가 작성한 댓글만 수정 가능하도록 하기
    - 댓글 본문이 사라지고, 댓글 내용, 저장 버튼 생성하기
    - 댓글 내용에는 이전에 입력했던 댓글 내용을 기본 값으로 채우기
    - 수정할 댓글 내용은 비어 있지 않도록 하기
    - 저장 버튼을 누른 경우 기존 댓글의 내용을 새로 입력한 댓글 내용으로 바꾸기
8. 게시글 조회 페이지 > 댓글 삭제
    - 내가 작성한 댓글만 삭제 가능하도록 하기
    - "정말로 삭제하시겠습니까?" 메세지를 띄우고, 확인/취소 버튼 중 "확인" 버튼을 누른 경우 목록에서 해당 댓글을 삭제하기
    - 취소를 누른 경우 삭제되지 않고 그대로 유지하기
9. 회원가입 테스트 코드 작성
    - 회원가입시 구현한 아래 조건을 검사하는 테스트 코드를 작성하기
        - 닉네임은 `최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 이루어져 있어야 합니다.
        - 비밀번호는 `최소 4자 이상이며, 닉네임과 같은 값이 포함된 경우 회원가입에 실패`합니다.
        - 비밀번호 확인은 비밀번호와 정확하게 일치해야 합니다.
        - 데이터베이스에 존재하는 닉네임을 입력한 채 회원가입 버튼을 누른 경우 "중복된 닉네임입니다." 라는 에러메세지가 발생합니다.
    - 테스트 코드 실행 시 실제로는 데이터베이스에 연결되지 않도록 하기
    - 각 조건 별로 2개 이상의 테스트 케이스가 존재하도록 하기
     
