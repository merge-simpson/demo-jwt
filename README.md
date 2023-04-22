# Features(한/Eng)

**JWT 인증**에만 집중한 프로젝트입니다.

- **회원가입 미포함**(기본 데이터는 DB 구축 시 SQL에 포함. 예제 소스 이해에 영향 없도록 소스 코드와 분리시킴.).
- **인증**: `AuthenticationManager`를 사용한 인증(일반적 수단).
- JWT 인증 토큰 Provider: **JWT 액세스 토큰 발행**.
- **Java 17**에서 사용할 수 있는 **신택스** 활용(JDK 17)

Focus on **JWT authentication**.

- **No Sign Up**(Initial data is inserted when we construct database. So ***source code will not confuse you***.)
- **Authentication**: using <u>***Authentication Manager***</u>(General use).
- JWT Authentication Token Provider: Generate <u>**JWT Access Token**</u>.
- **Syntax Level**: <u>**JDK 17**</u>(Java 17)

# Uses

- Java 17
- Spring Boot 3
- JPA, Spring Security, JJWT

---

# Run

1. 요구 사항: 자바 17 이상(JDK 17 이상)
2. 데이터베이스를 준비해야 합니다. 편하게 도커 컴포즈를 준비했습니다.
   - 잘 모르겠다면, `Docker Desktop`을 설치하면 도커 컴포즈가 함께 설치됩니다.
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

프로젝트 루트에서 실행하기 위해서는 `cd`(change directory) 명령어로 프로젝트 루트로 이동하시면 됩니다.

예를 들어 윈도우에서 이 프로젝트를 받은 경로가 `D:\workspace-group\intellij-workspace\demo-jwt`라면,

`D:` 명령으로 D 드라이브로 전환한 상태에서, `cd D:\workspace-group\intellij-workspace\demo-jwt` 명령으로 프로젝트 루트로 이동할 수 있습니다.

그 경로에서 위 명령어(`docker-compose up -d --build`)를 실행하면 됩니다.

이렇게 하면 실제 데이터베이스를 설치하지 않았더라도 데이터베이스가 설치되었다 생각해도 됩니다. 그래서 앞서 DB 스펙은 소개하지 않았습니다. 설치하러 갈까 봐요.

(기존에 열려 있던 터미널이 새로 설치한 프로그램들을 인식하지 못하는 경우도 있기 때문에 새 터미널 창을 권하였고, 잘 찾으면 그냥 진행합니다.)

또는 데이터베이스를 직접 설치한 후 프로젝트 루트 하위에 있는 db 폴더에서 DDL 파일의 내용을 DB에 직접 실행해도 됩니다(SQL 파일).

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

- encode: 비밀번호를 생성할 때 사용하며, 이번 프로젝트에서는 회원가입/비밀번호 변경 등 기능을 뺐으니 사용하지 않습니다.
- match
  - 비밀번호가 일치하는지 대조하는 기능이며, 로그인 등에 사용하지만 이번 프로젝트에서는 `Authentication Manager`가 이 기능을 대신합니다.

# Password Encoder Factory(생략 가능)

- 여러 유형의 비밀번호 인코더를 선택하여 사용할 수 있도록 제공합니다.
- 특히 기존 데이터를 이관하였을 때, Authentication Manager가 지원하지 않는 비밀번호 인코더로 변환 후 저장되어 있는 비밀번호는
  우리가 직접 비밀번호 인코더를 만들어서 암호를 비교해야 할 수도 있습니다.
- 이 프로젝트에서는 비밀번호 인코더 유형을 편의상 BCrypt, SCrypt, PBKDF2만 사용하였지만, 그 외 유형의 인코더를 만들어 선택할 때 의미가 있습니다.

# Authentication Manager

- 인증을 수행하는 메서드를 제공하며, 접두사에 `{bcrypt}`, `{pbkdf2}`, `{scrypt}` 등이 있으면 그 인코더 양식으로 비밀번호를 해석해서 알아서
  비교해 줍니다.
- 기본 제공되는 객체를 받아서 빈(Bean) 객체로 등록하여 편하게 사용합니다.

# AuthenticationMemberMappingService

`implements UserDetailsService`

- Authentication Manager 작성만으로는 어떤 DB 테이블과 컬럼과 대조하여 인증을 수행할지 알지 못합니다.
- 그때 어떤 데이터들을 통해 인증을 수행할지 작성하는 역할입니다.
- 이 서비스를 작성할 때는, DB에서 조회한 정보를 Authentication Manager에게 넘길 수 있는 형태로 매핑해 줍니다.
- `UserDetailsService`를 구현받고 `@Service` 애노테이션으로 서비스 빈(Bean)으로 등록하면 `Authentication Manager`가 알아서
  이 서비스에 제공된 데이터를 통해 인증을 진행합니다.

# JWT Token Provider

- JWT 인증 토큰 공급자.
- `Authenticaion Manager`가 인증을 마친 다음에 이 `JWT Token Provider`가 토큰을 발행합니다.
- 인증 여부와 관계 없이 토큰을 발행하는 것이 가능할 만큼 독립적입니다. 다만, 인증 후 실행되도록 상위 로직에서 제어합니다.
- Secret Key를 정해서 사용해야 합니다.

## Secret Key 생성 예시

임의로 무엇이든 생성해 두고 사용하면 됩니다. 가급적 주기적으로 교체해 주는 것이 보안에 도움이 됩니다.
어느 시점에 어떤 근거로 생성했는지 알기 어려울수록 예측하기 어려워서 안전합니다.

다음은 생성 예시 중 하나이며, JWT 기본 공부용이면 다음 정도면 충분합니다.

```shell
openssl rand -base64 64
```

이 프로젝트에서는 이미 만들어진 JWT Secret이 있습니다.

# JWT Parser

아직 안 쓰지만 빈으로 등록해 두었습니다. JWT를 해석할 때 사용합니다.