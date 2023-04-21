# 데이터베이스

Docker Compose를 사용할 줄 안다면 프로젝트 루트에 있는 `docker-compose.yml`을 실행하면 된다.

```shell
docker-compose up -d --build
```

그러나 사용할 줄 모른다면 프로젝트 루트 하위에 있는 db 폴더에서 DDL 파일의 내용을 DB에 직접 실행(SQL 파일).

| Column   | Type         | Description |
|----------|--------------|-------------|
| id       | uuid         | Primary Key |
| email    | varchar(255) |             |
| password | varchar(255) |             |
| nickanme | varchar(255) |             |

---
