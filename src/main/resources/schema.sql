create table USERS(
      user_id   INTEGER NOT NULL AUTO_INCREMENT,
      username VARCHAR(20) NOT NULL ,
      password VARCHAR(255) NOT NULL ,
      enabled BOOLEAN NOT NULL ,
      email VARCHAR(50) NOT NULL ,
      full_name VARCHAR(50) NOT NULL ,
      PRIMARY KEY (user_id)
);

CREATE TABLE CONFIRMATION_TOKEN (
       token_id INTEGER NOT NULL,
       confirmation_token VARCHAR(255) NOT NULL,
       created_date DATE,
       user_id INTEGER,
       FOREIGN KEY(user_id) REFERENCES USERS(user_id),
       PRIMARY KEY (token_id)
);

CREATE TABLE AUTHORITIES (
        username VARCHAR(20) NOT NULL ,
        authority VARCHAR(20) NOT NULL,
        FOREIGN KEY(username) REFERENCES USERS(username),
        PRIMARY KEY (username)

);

CREATE TABLE TASKS (
         task_id INTEGER NOT NULL ,
         name VARCHAR(255) NOT NULL,
         user_id INTEGER NOT NULL ,
         FOREIGN KEY(user_id) REFERENCES USERS(user_id),
         PRIMARY KEY (task_id)
);

CREATE TABLE TASK_UNITS (
       task_unit_id INTEGER NOT NULL ,
       name VARCHAR(255) NOT NULL,
       description VARCHAR(500) NOT NULL ,
       created_date DATE,
       deadline DATE,
       status VARCHAR(20),
       pre_task_unit_id INTEGER,
       task_id INTEGER,

       FOREIGN KEY(task_id) REFERENCES TASKS(task_id),
       FOREIGN KEY(pre_task_unit_id) REFERENCES TASK_UNITS(task_unit_id),
       PRIMARY KEY (task_unit_id)
);