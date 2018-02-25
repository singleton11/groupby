CREATE TABLE users
(
  id                      BIGSERIAL PRIMARY KEY NOT NULL,
  created                 TIMESTAMP WITH TIME ZONE,
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

CREATE TYPE STOP_RULE AS ENUM ('manual', 'time', 'users', 'hybrid');

CREATE TABLE groups
(
  id             BIGSERIAL PRIMARY KEY    NOT NULL,
  created        TIMESTAMP WITH TIME ZONE NOT NULL,
  amount         BIGINT                   NOT NULL,
  time_to_finish TIMESTAMP WITH TIME ZONE,
  stop_rule      STOP_RULE                NOT NULL,
  owner          BIGINT,
  CONSTRAINT groups_users_id_fk FOREIGN KEY (owner) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE UNIQUE INDEX groups_id_uindex
  ON groups (id);

CREATE TABLE group_user
(
  group_id BIGINT NOT NULL,
  user_id  BIGINT NOT NULL,
  CONSTRAINT group_user_groups_id_fk FOREIGN KEY (group_id) REFERENCES groups (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT group_user_users_id_fk FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
);