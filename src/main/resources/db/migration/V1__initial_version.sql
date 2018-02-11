CREATE TABLE users
(
  id                      BIGSERIAL PRIMARY KEY NOT NULL,
  created                 TIMESTAMP WITHOUT TIME ZONE,
  username                VARCHAR(255)          NOT NULL,
  password                VARCHAR(255)          NOT NULL,
  account_non_expired     BOOLEAN               NOT NULL,
  account_non_locked      BOOLEAN               NOT NULL,
  credentials_non_expired BOOLEAN               NOT NULL,
  enabled                 BOOLEAN               NOT NULL
);
CREATE UNIQUE INDEX users_id_uindex
  ON users (id);
CREATE UNIQUE INDEX users_username_uindex
  ON users (username);


CREATE TABLE roles
(
  id        BIGSERIAL PRIMARY KEY NOT NULL,
  authority VARCHAR(255)          NOT NULL
);
CREATE UNIQUE INDEX table_name_id_uindex
  ON roles (id);
CREATE UNIQUE INDEX table_name_authority_uindex
  ON roles (authority);

INSERT INTO roles (authority) VALUES ('ROLE_USER');

CREATE TABLE user_role
(
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  CONSTRAINT user_role_users_id_fk FOREIGN KEY (user_id) REFERENCES users (id),
  CONSTRAINT user_role_roles_id_fk FOREIGN KEY (role_id) REFERENCES roles (id)
);