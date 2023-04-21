# 데이터베이스 준비

Docker Compose를 사용할 줄 안다면 프로젝트 루트에 있는 `docker-compose.yml`을 실행하면 된다.

```shell
docker-compose up -d --build
```

그러나 사용할 줄 모른다면 프로젝트 루트 하위에 있는 db 폴더에서
DDL 파일의 내용을 DB에 직접 실행해도 됨(SQL 파일).

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

아직 안 씀. JWT를 해석할 때 사용.