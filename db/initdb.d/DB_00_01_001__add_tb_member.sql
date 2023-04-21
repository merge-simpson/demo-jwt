-- Use email instead of username
CREATE TABLE IF NOT EXISTS member (
    id                      UUID                            DEFAULT uuid_generate_v4(),
    email                   VARCHAR(255),
    password                VARCHAR(255)                    NOT NULL,
    nickname                VARCHAR(255),
    CONSTRAINT pk_member PRIMARY KEY (email)
);