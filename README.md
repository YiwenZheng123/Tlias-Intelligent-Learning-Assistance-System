# Tlias Intelligent Learning Assistance System

Tlias Intelligent Learning Assistance System is a Spring Boot based backend service for teaching management. It provides APIs for departments, employees, classes, students, login authentication, operation logs, statistics reports, and file uploads. The project uses MyBatis with MySQL for persistence.

## Tech Stack

- Java 17
- Spring Boot 3.5.16
- Spring Web
- MyBatis Spring Boot Starter
- MySQL Connector/J
- PageHelper
- Lombok
- Aliyun OSS SDK
- JWT
- Maven

## Features

- Login and authentication
  - Employee login through `/login`
  - Generate JWT after successful login
  - JWT parsing utility in `JwtUtils`
  - Token validation implementations are available in `TokenFilter` and `TokenInterceptor`

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
  - Query all employees for selector/list usage
  - Operation log persistence when adding employees

- Class management
  - Paginated class query
  - Add class
  - Query class by ID
  - Update class
  - Delete class
  - Query all classes for selector/list usage

- Student management
  - Paginated student query
  - Add student
  - Query student by ID
  - Update student
  - Batch delete students
  - Update violation score

- Report APIs
  - Employee job distribution
  - Employee gender distribution
  - Student degree distribution
  - Student count by class

- Operation logs
  - Paginated employee operation log query

- File upload
  - Upload files through `/upload`
  - Store uploaded files in Aliyun OSS
  - Return uploaded file URL

## Project Structure

```text
src/main/java/com/itheima
  config/       Spring MVC configuration
  controller/   REST API controllers
  exception/    Global exception handling
  filter/       Servlet filters, including TokenFilter
  interceptor/  Spring MVC interceptors, including TokenInterceptor
  mapper/       MyBatis mapper interfaces
  pojo/         Entity, DTO, and response classes
  service/      Service interfaces
  service/impl/ Service implementations
  utils/        Aliyun OSS and JWT utilities

src/main/resources
  application.yml
  application-local.yml
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
| GET | `/emps/list` | List all employees |

### Class APIs

| Method | Path | Description |
| --- | --- | --- |
| GET | `/clazzs` | Paginated class query |
| POST | `/clazzs` | Add a class |
| GET | `/clazzs/{id}` | Get class by ID |
| PUT | `/clazzs` | Update class |
| DELETE | `/clazzs/{id}` | Delete class |
| GET | `/clazzs/list` | List all classes |

### Student APIs

| Method | Path | Description |
| --- | --- | --- |
| GET | `/students` | Paginated student query |
| POST | `/students` | Add a student |
| GET | `/students/{id}` | Get student by ID |
| PUT | `/students` | Update student |
| DELETE | `/students/{ids}` | Batch delete students |
| PUT | `/students/violation/{id}/{score}` | Update student violation score |

### Report APIs

| Method | Path | Description |
| --- | --- | --- |
| GET | `/report/empJobData` | Get employee job statistics |
| GET | `/report/empGenderData` | Get employee gender statistics |
| GET | `/report/studentDegreeData` | Get student degree statistics |
| GET | `/report/studentCountData` | Get student count statistics by class |

### Login API

| Method | Path | Description |
| --- | --- | --- |
| POST | `/login` | Login with employee username and password, and return login information with JWT |

### Log API

| Method | Path | Description |
| --- | --- | --- |
| GET | `/log/page` | Paginated employee operation log query |

### Upload API

| Method | Path | Description |
| --- | --- | --- |
| POST | `/upload` | Upload file to Aliyun OSS |

## Authentication

After successful login, `/login` returns a token in the login result. Requests that require authentication should send the token in the request header:

```text
token: your_jwt_token
```

The project contains two JWT validation implementations:

- `TokenFilter`: Servlet filter based token validation. It allows login requests and validates the `token` request header for other requests.
- `TokenInterceptor`: Spring MVC interceptor based token validation. It validates the `token` request header before controller methods are invoked.

At the moment, both implementations are kept in the codebase as optional approaches. Their annotations or registration code are commented out, so enable only the approach you want to use.

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

The application also configures:

- Multipart upload size: `10MB`
- MyBatis underscore-to-camel-case mapping
- JDBC transaction debug logging
- Aliyun OSS endpoint, bucket name, and region

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
- `TokenFilter` and `TokenInterceptor` are the current token validation implementations; demo filter/interceptor classes are only examples.
- The project is still intended for continued development.
