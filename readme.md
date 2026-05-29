"""# TaskFlow API - Enterprise Task Management Backend

TaskFlow API is a production-ready backend for project and task management, built with **Spring Boot 3** and **Java 17**. This project is designed using modern industry standards, prioritizing high performance through **Redis Caching**, code reliability with **Unit Testing (Mockito)**, **CI/CD automation via GitHub Actions**, and container orchestration using **Docker Compose**.

---

## 🚀 Key Features & Architectural Highlights

1. **Robust Core API & Relational Database**
   - Implemented a **One-to-Many** relationship between `Project` and `Task` entities using JPA/Hibernate.
   - Safe mitigation of _Infinite Recursion_ (JSON looping) using `@JsonIgnore`.
   - Optimized relational data retrieval with **Eager Fetching** to prevent `LazyInitializationException` in distributed caching scenarios.

2. **High-Performance Caching (Redis)**
   - Layered caching system at the service layer (`@Cacheable`) to significantly reduce direct query load on PostgreSQL (anti-bottleneck).
   - Entity objects are fully **Serializable** compliant to ensure seamless stream-of-bytes data exchange with the Redis RAM cluster.

3. **Enterprise Testing Suite**
   - Isolated unit tests utilizing **JUnit 5** and **Mockito** to simulate both successful execution paths (`createTask_Success`) and edge-case exceptions (`createTask_ProjectNotFound_ThrowsException`) without requiring a physical database.

4. **Production DevOps & CI/CD Pipeline**
   - Automated code quality verification via **GitHub Actions** (`mvn clean test`) running on an Ubuntu cloud runner for every push to the main branch.
   - Full environment portability utilizing a **Multi-stage Build Dockerfile** to minimize the final production image size.

5. **Interactive API Documentation**
   - Fully automated, auto-generated documentation using **OpenAPI 3 / Swagger UI** for seamless integration with Frontend or Mobile teams.

6. **Advanced API Security (JWT)**
   - Implementasi autentikasi _Stateless_ menggunakan **JSON Web Tokens (JWT)** dan Spring Security.
   - Kata sandi dilindungi menggunakan enkripsi _hashing_ **BCrypt**.
   - Integrasi _Security Filter Chain_ kustom untuk memvalidasi _Bearer token_ pada setiap _request_ yang masuk.

---

## 🛠️ Tech Stack & Prerequisites

- **Language & Framework:** Java 17 (Eclipse Temurin), Spring Boot 3.x
- **Data Layer:** Spring Data JPA, Hibernate, PostgreSQL 15
- **Performance:** Spring Data Redis
- **Testing:** JUnit 5, Mockito
- **DevOps & Tools:** Docker & Docker Compose, GitHub Actions, Maven
- **Documentation:** Springdoc OpenAPI UI (Swagger)

---

## 🏃 Getting Started (How to Run)

This project is fully containerized using Docker Compose, eliminating the need to manually install Java, PostgreSQL, or Redis on your local machine.

### 1. Prerequisites

Ensure you have **Docker Desktop** and **Git** installed on your system.

### 2. Clone the Repository

Code output
File README.md successfully created.

```bash
git clone [https://github.com/juanbenedhit/taskflow-api.git](https://github.com/juanbenedhit/taskflow-api.git)
cd taskflow-api
```

### 3. Launch via Docker Compose

Execute the following command in the root directory to build the images and spin up all containers (PostgreSQL, Redis, and the Spring Boot API) in detached mode:

```bash
docker-compose up -d --build
```

### 4. Verify Container Status

Ensure all three services are up and running:

```bash
docker-compose ps
```

## 📖 API Documentation & Endpoints

Once all containers are successfully running, you can access the interactive API documentation directly from your browser:

👉 **[http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)**

_(Note: The external port is mapped to 8082 to prevent conflicts with the default 8080 local port)._

### Core Endpoints Summary:

| HTTP Method | Endpoint                          | Description                                                      | Request Validation                     |
| :---------- | :-------------------------------- | :--------------------------------------------------------------- | :------------------------------------- |
| **POST**    | `/api/projects`                   | Create a new project                                             | Project name is required (`@NotBlank`) |
| **GET**     | `/api/projects`                   | Retrieve all projects with their nested tasks (Cached via Redis) | -                                      |
| **POST**    | `/api/projects/{projectId}/tasks` | Add a new task to a specific project                             | Task Title & Status are required       |

## 🧪 Running Tests

To run the entire Unit Test suite locally without needing Docker or the actual database, execute the following Maven command:

```bash
mvn clean test
```

Service Layer Testing Logic:

- Success Scenario: Verifies that task objects are successfully saved, IDs are generated, and project relationships are correctly bound.

- Exception Scenario (Error Handling): Ensures the system throws a RuntimeException with the precise message ("Project tidak ditemukan") when a user attempts to add a task to an invalid project ID.

## 🤖 CI/CD Pipeline (GitHub Actions)

The automation workflow is configured inside .github/workflows/ci-pipeline.yml. Upon every git push to the main branch, the CI bot will:

Provision an Ubuntu Latest virtual environment.

Set up Java 17 (Temurin) with automated Maven caching.

Execute mvn clean test to guarantee code integrity prior to release.
