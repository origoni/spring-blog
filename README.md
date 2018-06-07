# SpringBlog from Millky
### SpringBoot 2 base Open-source Blog

밀키(millky.com)에서 블로그 부분을 뽑아(?) 오픈소스 합니다.

```
SpringBoot2 + Spring Data JPA + Spring Security + ...
```

단지 코드만 공개한는 것이 아니라. 개발 과정을 같이 공개하려 합니다.

### LIVE DEMO
- http://blog.millky.com/post/list
- 제공하는 API를 확인해 보세요~ http://blog.millky.com/swagger-ui.html#!/tag-api/tagCloudUsingGET


### 개발된 기능
- 기본적인 블로그 뷰
- 페이스북, 구글 소셜 로그인(깃헙등도 간단하게 추가 가능)
- 글쓰기, 수정, 삭제. 페이징
- 댓글쓰기, 삭제
- 카테고리
- 태그
- RSS 뷰
- 간단한 검색(DB)
 
#### 추가 예정
- 추가적인 소셜 로그인 및 기본 로그인
- 댓글 수정
- 국제화(i18n 다국어 지원)
- 기타 DB 지원
- 간단한 캐싱 (EHCache ?)


### Quick Start
설치되어 있어야 할 것
- JDK 1.8 (or Java 10)
- Maven 3.5
- Git

```
git clone https://github.com/origoni/Spring-Blog
cd Spring-Blog
mvn spring-boot:run
```

- visit [http://localhost:8080/](http://localhost:8080/)


### Tested
- STS(Eclipse) 3.8.4
- IntelliJ IDEA 2018.1.4

```
//@formatter:off & //@formatter:on
eclipse : Preferences -> Java -> Code style -> Formatter -> Edit... (or New...) > Off/On Tags
intellij : Preferences -> Editor -> Code Style > Formatter Control > Enable formatter markers in comments
```


### 관련 링크
- http://millky.com/
- http://millky.com/@origoni
- http://millky.com/@origoni/folder/30/post/list

### Project Convention

#### Package Structure

```
com.millky.blog
    └── application
        └── aop
        └── configuration
        └── utility
    └── domain
        └── model
            └── command
            └── entity
            └── exception
        └── repository
        └── service
    └── infrastructure
        └── dao
    └── presentation
        └── controller
            └── rest
            └── web
        └── support
            └── result
```

#### Environment
- Java version: 8 Update 172 or 10.0.1
- Spring Boot version: 2.0.2
- Maven version: 3.5.2
- Lombok version: 1.18.0
- Default Encoding: UTF-8
- Default SCM : git

#### 프로젝트 설정
1. STS 설치 -> http://millky.com/@origoni/post/1100
2. Lombok 설치 -> https://projectlombok.org/ (STS에 설치 : http://millky.com/@origoni/post/1164)
3. GitHub 다운 -> http://millky.com/@origoni/post/1145 (OSX : http://millky.com/@origoni/post/1140)

