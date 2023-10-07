# backend

# Steps

- Create Databases.

```sql
CREATE DATABASE IF NOT EXISTS reactsb_authdb;
CREATE DATABASE IF NOT EXISTS reactsb_tododb;
```

- Run both Auth-service and Todo-service to automatically create Tables

```java
// Add VM Arguments while running in IntelliJ IDEA
// -Dspring.profiles.active=local,debug,mysql
```

- Insert Data into Tables

```sql
USE reactsb_authdb;
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
```
