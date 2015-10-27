# SpringBlog from Millky
### Spring Boot base Open-source Blog

밀키(millky.com)에서 블로그 부분을 뽑아(?) 오픈소스 합니다.

```
SpringBoot + Spring Data JPA + Spring Social + ...
```

단지 코드만 공개한는 것이 아니라. 개발 과정을 같이 공개하려 합니다.

### LIVE DEMO
- http://blog.millky.com/post/list
- 해당코드 수정없이 사용합니다.
- 어떠한 정보도 기록하지 않습니다.
- 제공하는 API를 확인해 보세요~ http://blog.millky.com/swagger-ui.html#!/tag-api/tagCloudUsingGET

### Quick Start
- JDK 1.8 or later
- Maven 3.0+

```
git clone https://github.com/origoni/Spring-Blog
cd Spring-Blog
mvn spring-boot:run
```

- visit [http://localhost:8080/](http://localhost:8080/)

### 관련 링크
- http://millky.com/
- http://millky.com/@origoni
- http://millky.com/@origoni/folder/30/post/list

### Project Convention

#### Package Structure

```
com.millky.blog
    └── application
        └── aop(?)
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
        └── support
            └── result
```

#### Environment
- Java version: 8 Update 66
- Spring Boot version: 1.3.0.RC1
- Maven version: 3.3.3
- Lombok version: 1.16.6
- Default Encoding: UTF-8
- Default SCM : git

#### 프로젝트 설정
1. STS 설치 -> http://millky.com/@origoni/post/1100
2. Lombok 설치 -> https://projectlombok.org/ (설치 : http://millky.com/@origoni/post/1164)
3. GitHub 다운 -> http://millky.com/@origoni/post/1145 (OSX : http://millky.com/@origoni/post/1140)

