CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(500) NOT NULL,
    enabled  BOOLEAN      NOT NULL
);
CREATE TABLE authorities
(
    id        SERIAL PRIMARY KEY,
    username  VARCHAR(50) NOT NULL UNIQUE,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
);

CREATE TABLE user_account
(
    id          SERIAL PRIMARY KEY,
    first_name  VARCHAR(254) NOT NULL,
    last_name   VARCHAR(254) NOT NULL,
    middle_name VARCHAR(254),
    email       VARCHAR(254) NOT NULL UNIQUE,
    pwd         VARCHAR(64)  NOT NULL,
    role        VARCHAR(50)  NOT NULL,
    is_active   BOOLEAN DEFAULT FALSE
);

COMMENT ON TABLE user_account is 'Данные пользователя';
COMMENT ON COLUMN user_account.id is 'Идентификатор записи';
COMMENT ON COLUMN user_account.first_name is 'Имя пользователя';
COMMENT ON COLUMN user_account.last_name is 'Фамилия пользователя';
COMMENT ON COLUMN user_account.middle_name is 'Отчество пользователя (опционально)';
COMMENT ON COLUMN user_account.email is 'Электронная почта';
COMMENT ON COLUMN user_account.pwd is 'Пароль';
COMMENT ON COLUMN user_account.role is 'Роль';
COMMENT ON COLUMN user_account.is_active is 'Статус активности записи';

drop TABLE IF EXISTS customer;


CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);

INSERT INTO users (username, password, enabled)
VALUES ('happy', '12345', TRUE);

INSERT INTO authorities (username, authority)
VALUES ('happy', 'write');

INSERT INTO user_account (first_name, last_name, middle_name, email, pwd, role, is_active)
VALUES ('Dmitry', 'Lahno', 'Alexandrovich', 'dima@test.kz', '54321', 'admin', TRUE);

SELECT *
FROM user_account;

ALTER TABLE user_account ALTER COLUMN pwd TYPE VARCHAR(64);