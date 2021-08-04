# PJT3. 예약 시스템

## 일정 관리

* 예제) 8.3 일정 상태 

![image-20210803133523574](README.assets/image-20210803133523574.png)

<a href="https://trello.com/invite/b/s7hPrEzk/ea484303360596b468773c23f722e6a2/pjt3" style="font-size:24px; font-weight:550; color: #2DB400;">프로젝트 트렐로 보기</a>





## FrontEnd

### 스택

* HMTL, CSS, JavaScript

### 툴

* IDE: vsCode, Eclipse

### 개발

vsCode에서 JavaScript(이하 JS) 파일을 작성하고 Eclipse에서 HTML, CSS을 작성하였습니다.

인덴트는 기본설정이 4라서 4를 따랐고, CSS와 JS는 분리하여 사용하였습니다.

기본적인 틀을 바탕으로 더미 데이터를 지우고 JS로 ajax 통신을 이용하여 데이터를 받아왔습니다.

요구사항 확인 후, 트렐로에 반영시켰고 트렐로 일정대로 진행하였습니다.

* 프로모션 부분
  * ajax통신을 이용하여 프로모션 이미지를 불러왔습니다.
  * 큐방식을 이용하여 캐러셀(무한 슬라이더)을 만들었습니다. 
* 카테고리 부분
  * UI 부분이 조금 튀어나가는 부분이 있어 CSS를 고쳤습니다.
  * 전체리스트 항목은 놔두고 다른 카테고리를 ajax를 통해 데이터를 불러왔습니다.
* 상품리스트 부분
  * 각 카테고리별 상품을 순서에 맞게 4개를 ajax로 불러왔습니다.
  * 상품 전시하는 부분에서 `%2` 연산을 통해 양 쪽 `ul`에 배치하였습니다.
* 더보기 부분
  * 더보기를 누르면 각 카테고리에 맞는 데이터가 양 쪽 `ul`에 2개씩 배치됩니다.
    * ex) 받아온 상품이 3개일때,
      * 순서에 맞게 왼쪽 `ul`태그부터 상품이 (2, 1) 형식으로 배치됩니다
    * ex) 받아온 상품이 2개일때,
      * 순서에 맞게 왼쪽 `ul`태그부터 상품이 (1, 1) 형식으로 배치됩니다.
  * 더 이상 불러올 데이터가 없다면 더보기는 사라집니다.
* TOP 부분
  * 링크를 통해 `#`이 붙는 경우가 top기능의 부차적인 에러라고 생각해서 js를 통해 주소창에 `#`이 안 붙어도 화면상단으로 올라가게 만들었습니다.
* Header 부분
  * 왼쪽 예약 로고에 대한 링크를 없앴습니다.
  * 예약확인시 비회원 로그인을 하면 1시간동안 쿠키가 지속되게 만들었습니다.
  * 쿠키가 지속되는 동안 예약확인 버튼 대신 현재 비회원의 이메일이 표시됩니다.





## BackEnd

### 스택

* Spring mvc, JDBC, Mysql

### 툴

* IDE: Eclipse
* Web Server: Apache Tomcat

### 개발

Eclipse를 통해 작성하였고, 루트 패키지 명은 com.ntscorp.intern으로 지었습니다.

기능별로 product 패키지와 reservation 패키지 그리고 config 패키지로 나누었습니다.

PJT 3의 경우reservation 패키지는 사용하지 않습니다.

각 패키지에서 레이어드 아키텍처에 따라 controller / model /  repository / service / type 패키지로 구분하였습니다.



##### controller 

`@RestController`를 통해 API 형식을 지원합니다.

클라이언트가 원하는 데이터를 여러 서비스에서 받아 `ResponseEntity`를 json형태로 반환합니다.



##### model 

데이터베이스의 테이블 모델입니다.

해당 모델을 통해 데이터베이스에서 받는 결과를 사용합니다.



##### repository 

해당 패키지에서 레파지토리가 사용할 쿼리를 저장합니다.

저장된 쿼리를 통해 데이터베이스와 연결하고 데이터를 받아옵니다.



##### service 

서비스는 레파지토리에서 받은 데이터를 가공하거나 기능 구현에 대한 여러 작업을 거쳐 컨트롤러에 전달합니다.



##### type

Enum 클래스를 여기에 저장합니다.

HttpStatus를 커스텀하여 저장하였습니다.



이 외에도 DB properties를 application.properties로 따로 사용하였고,

`@Transactional(readOnly=true)`를 통해 조회문을 최적화시켰으며,

컴포넌트 스캔을 필터링하여 필요한 컴포넌트만 가져올 수 있도록 하였습니다.





## Pages

### 메인화면

![메인](README.assets/메인.PNG)



### 비회원로그인

![비회원로그인](README.assets/비회원로그인.PNG)





# 2021 NTS 인턴쉽 Project

부스트코스 + 깃랩 + 코드리뷰를 통한 공통 프로젝트 8주간 진행

부스트코스 URL: https://www.boostcourse.org/web316



## 부스트 코스

* 웹프로그래밍 기초 ✔

* DB 연결 웹 앱 ✔

* 웹 앱 개발: 예약서비스 🚢



※ 🚢: 진행 중, ✔: 완료



## 깃 플로우

* 웹프로그래밍 기초 Branch
  * PJT.1_dev - 개발 브랜치
  * PJT.1_master - 마지막 MR 후, 태깅해서 제출

<br>

* DB 연결 웹 앱  Branch
  * PJT.2_dev - 개발 브랜치
  * PJT.2_master - 마지막 MR 후, 태깅해서 제출

<br>

* 웹 앱 개발: 예약서비스  Branch
  * PJT.3_dev, PJT.4_dev, PJT.5_dev, PJT.6_dev - 개발 브랜치
  * master - 마지막 MR 후, 제출



## 기타 사항

* 1일 1커밋
* 주 2회 미팅
* 코드리뷰 후,  WIKI 작성
