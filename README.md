# Features(한국어/English)

JWT 인증에만 집중한 프로젝트입니다.

- 회원가입 미포함(기본 데이터는 DB 구축 시 SQL에 포함. 예제 소스 이해에 혼란 없도록 소스 코드와 분리시킴.).
- 인증: `AuthenticationManager`를 사용한 인증(범용 인증 방식).
- JWT 인증 토큰 Provider: JWT 액세스 토큰 발행.
- Java 17에서 사용할 수 있는 신택스 활용(JDK 17)

Focus on JWT authentication.

- No Sign Up(Initial data is inserted when we construct database. So source code will not confuse you.)
- Authentication: using Authentication Manager(General use).
- JWT Authentication Token Provider: Generate JWT Access Token.
- Syntax Level: JDK 17(Java 17)

# Uses

- Java 17
- Spring Boot 3
- JPA, Spring Security, JJWT

---

# Run

1. 요구 사항: 자바 17 이상(JDK 17 이상)
2. 데이터베이스를 준비해야 합니다. 편하게 도커 컴포즈를 준비했습니다.
   - 잘 모르겠다면 `Docker Desktop`을 설치해 두시면 도커 컴포즈가 함께 설치됩니다.
   - 이후 자세한 내용은 아래 [데이터베이스 준비](#데이터베이스-준비)를 참고하시기 바랍니다.
   - 또한 기본 데이터를 삽입하는 쿼리도 함께 실행되도록 하였습니다.
3. 이제 프로젝트를 실행합니다.
4. (Request) 포스트맨 등에서 요청을 보내 봅니다.
   - POST http://localhost:8080/sign-up
   - Body (raw > json 선택하시고)
   
     ```json
     {
       "email": "abc123@abc.com",
       "password": "abc123!@"
     }
     ```
5. (Response) 응답을 확인합니다.
   - 올바른 인증 정보를 전달한 경우 응답 바디 예시
     ```json
     {
       "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYmMxMjNAYWJjLmNvbSIsInJvbGVzIjpbIlVTRVIiXSwiaWF0IjoxNjgyMTc5ODA4LCJleHAiOjE2ODIxODE2MDh9.V2vnH3P94JUTk1Cfv-GL9dG_pB2IjESUNXKKsh5vSR4"
     }
     ```
   - 잘못된 인증 정보를 전달한 경우: 403 Forbidden
   - 전달 양식이 틀린 경우: 400 Bad Request
   - 인증 정보는 일치하지만 2차 인증 등이 필요한 경우(이번에 구현하지 않음.) 응답 바디
     ```json
     {
       "requiresMfa": true
     }
     ```

# 데이터베이스 준비

Docker Compose를 사용할 줄 안다면 프로젝트 루트에 있는 `docker-compose.yml`을 실행하면 됩니다.

```shell
docker-compose up -d --build
```

도커 컴포즈를 사용할 줄 모른다면, Docker Desktop을 설치한 후 새 터미널 윈도우를 열고 프로젝트 루트에서 위 명령어를 실행하면 됩니다.

(새로 설치한 프로그램은 기존에 열려 있던 터미널이 인식하지 못하는 경우도 있기 때문에 새 터미널 창을 권하였으며, 잘 찾으면 그냥 진행.)

또는 데이터베이스를 직접 설치한 후 프로젝트 루트 하위에 있는 db 폴더에서 DDL 파일의 내용을 DB에 직접 실행합니다(SQL 파일).

DB 인스턴스의 이름은 `jwtdemo_postgres`와 일치하여야 합니다.

신택스는 PostgreSQL 14버전에 맞추어져 있습니다.

## DB 접속 정보 참고

| Item     | Content          |
|----------|------------------|
| DB Name  | jwtdemo_postgres |
| Username | root             |
| Password | root             |
| Port     | 5432             |

## 테이블 참고

| Column   | Type         | Description |
|----------|--------------|-------------|
| id       | uuid         | Primary Key |
| email    | varchar(255) |             |
| password | varchar(255) |             |
| nickanme | varchar(255) |             |

이 테이블이 만들어진 상태로 진행(Postgresql).

---

# Password Encoder

- encode: 회원가입, 비밀번호 변경 등 비밀번호를 생성할 때 사용.
- match
  - 비밀번호가 일치하는지 대조하는 기능이며, 로그인 등에 사용 가능.
  - 그러나 이번 프로젝트에서는 Authentication Manager가 이 기능을 대신함.

# Authentication Manager

- 인증을 수행하기 위한 메서드를 제공.
- 기본 제공되는 객체를 빈(Bean) 객체로 등록하여 사용.

# AuthenticationMemberMappingService
`implements UserDetailsService`

- 인증 정보를 어떤 것끼리 대조할지 작성함.
- DB에서 조회한 정보를 Authentication Manager에게 넘길 수 있는 형태로 매핑해 줌.

# JWT Token Provider

- JWT 인증 토큰 공급자.
- Secret Key를 정해서 사용해야 한다.

## Secret Key 생성 예시

임의로 생성해 두고 사용하면 된다. 주기적으로 교체해 주는 것이 보안에 도움이 된다.
어느 시점에 어떤 근거로 생성했는지 알기 어려울수록 안전하다.

다음은 생성 예시 중 하나이며, JWT 기본 공부용이면 다음 정도면 충분하다.

```shell
openssl rand -base64 64
```

# JWT Parser

아직 안 씀. 만들어만 놓음. JWT를 해석할 때 사용.