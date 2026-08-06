# Tlias Intelligent Learning Assistance System

Tlias Intelligent Learning Assistance System is a Spring Boot based backend service for managing departments, employees, employee statistics, and file uploads. The project is currently under development and uses MyBatis with MySQL for persistence.

## Tech Stack

- Java 17
- Spring Boot 3.5.16
- Spring Web
- MyBatis Spring Boot Starter
- MySQL Connector/J
- PageHelper
- Lombok
- Aliyun OSS SDK
- Maven

## Features

- Department management
  - List departments
  - Add department
  - Query department by ID
  - Update department
  - Delete department

- Employee management
  - Paginated employee query
  - Add employee
  - Query employee by ID
  - Update employee
  - Batch delete employees
  - Employee work experience persistence

- Report APIs
  - Employee job distribution
  - Employee gender distribution

- File upload
  - Upload files through `/upload`
  - Store uploaded files in Aliyun OSS
  - Return uploaded file URL

## Project Structure

```text
src/main/java/com/itheima
  controller/   REST API controllers
  exception/    Global exception handling
  mapper/       MyBatis mapper interfaces
  pojo/         Entity, DTO, and response classes
  service/      Service interfaces
  service/impl/ Service implementations
  utils/        Aliyun OSS utilities

src/main/resources
  application.yml
  logback.xml
  com/itheima/mapper/ MyBatis XML mapper files
  static/upload.html
```

## Main APIs

### Department APIs

| Method | Path | Description |
| --- | --- | --- |
| GET | `/depts` | List all departments |
| POST | `/depts` | Add a department |
| GET | `/depts/{id}` | Get department by ID |
| PUT | `/depts` | Update department |
| DELETE | `/depts?id={id}` | Delete department |

### Employee APIs

| Method | Path | Description |
| --- | --- | --- |
| GET | `/emps` | Paginated employee query |
| POST | `/emps` | Add an employee |
| GET | `/emps/{id}` | Get employee by ID |
| PUT | `/emps` | Update employee |
| DELETE | `/emps?ids=1,2,3` | Batch delete employees |

### Report APIs

| Method | Path | Description |
| --- | --- | --- |
| GET | `/report/empJobData` | Get employee job statistics |
| GET | `/report/empGenderData` | Get employee gender statistics |

### Upload API

| Method | Path | Description |
| --- | --- | --- |
| POST | `/upload` | Upload file to Aliyun OSS |

## Configuration

The application reads database configuration from `src/main/resources/application.yml`.

Set the database password through the `DB_PASSWORD` environment variable:

```powershell
$env:DB_PASSWORD="your_mysql_password"
```

Aliyun OSS credentials should be provided through environment variables required by the Aliyun SDK:

```powershell
$env:OSS_ACCESS_KEY_ID="your_access_key_id"
$env:OSS_ACCESS_KEY_SECRET="your_access_key_secret"
```

The default database connection is:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/tlias
    username: root
```

## Run Locally

Make sure Java 17 and Maven are available.

```powershell
mvn test
mvn spring-boot:run
```

Or package and run:

```powershell
mvn -Dmaven.test.skip=true package
java -jar target/tlias-web-management-0.0.1-SNAPSHOT.jar
```

The application starts on the default Spring Boot port:

```text
http://localhost:8080
```

## Notes

- `target/`, `logs/`, `.idea/`, and local environment files are ignored by Git.
- Do not commit real database passwords or cloud access keys.
- The project is still incomplete and intended for continued development.
