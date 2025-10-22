# Orchestration-Project · order_service

A minimal Spring Boot 3 service that serves a simple landing page for an Order Service. This module is intended to be part of a larger orchestration system and currently exposes a single HTTP endpoint that renders a static HTML page.

## Tech stack

- Java 21
- Spring Boot 3.5.6
  - spring-boot-starter-web
  - spring-boot-starter-test (JUnit 5)
- Maven Wrapper (`mvnw`)

## Repository layout

```text
.
├─ README.md                # This file
├─ TODO.md
└─ order_service/
   ├─ HELP.md
   ├─ mvnw, mvnw.cmd        # Maven wrapper scripts
   ├─ pom.xml               # Spring Boot 3, Java 21
   ├─ src/
   │  ├─ main/
   │  │  ├─ java/com/orch/order_service/order_service/
   │  │  │  ├─ HomeController.java          # Maps "/" to index page
   │  │  │  └─ OrderServiceApplication.java # Bootstraps the app
   │  │  └─ resources/
   │  │     ├─ application.properties       # spring.application.name=order_service
   │  │     └─ static/
   │  │        └─ index.html                # Landing page
   │  └─ test/
   │     └─ java/com/orch/order_service/order_service/
   │        └─ OrderServiceApplicationTests.java # Context load test
  └─ target/               # Build outputs (generated)
```

## How to run (macOS · zsh)

Prerequisites:

- JDK 21 installed and selected as your active Java (Spring Boot 3.5 requires Java 17+; this project targets Java 21).

Run locally with the Maven wrapper:

```bash
cd order_service
./mvnw spring-boot:run
```

By default the app starts on [http://localhost:8080](http://localhost:8080).

## Build, test, and package

- Run tests:

  ```bash
  cd order_service
  ./mvnw test
  ```

- Build an executable JAR:

  ```bash
  cd order_service
  ./mvnw -DskipTests clean package
  ls -l target/order_service-0.0.1-SNAPSHOT.jar
  ```

- Run the packaged JAR:

  ```bash
  cd order_service
  java -jar target/order_service-0.0.1-SNAPSHOT.jar
  ```

## Configuration

Configuration lives in `order_service/src/main/resources/application.properties`.

Defaults:

- `spring.application.name=order_service`
- Server port: `8080` (Spring Boot default)

To change the port, set for example:

```properties
server.port=8081
```

## Endpoints

- GET `/` — Renders the static landing page located at `src/main/resources/static/index.html`.

Quick check:

```bash
curl -i http://localhost:8080/
```

Note: Static assets are served from `/static`. The `HomeController` maps `/` to `index.html`.

## Development notes

- Hot reload: You can re-run `./mvnw spring-boot:run` after changes; for instant reload you can add Spring DevTools later.
- No database or persistence layer is configured yet.
- No actuator/health endpoints are included; add `spring-boot-starter-actuator` if needed.

## Troubleshooting

- Port already in use: Change `server.port` or stop the conflicting process.
- Java version mismatch: Ensure `java -version` shows 21. If using SDKMAN or jenv, switch the active Java accordingly.
- Clean build artifacts:

  ```bash
  cd order_service
  ./mvnw clean
  ```

## Next steps (suggestions)

- Add domain endpoints for order management (CRUD REST API).
- Introduce persistence (Spring Data JPA + PostgreSQL) and Flyway migrations.
- Add actuator for health and readiness probes.
- Containerize with a Dockerfile and CI workflow.

---

Maintained by `mitchell985`. If you extend this module, keep code simple (KISS), avoid duplication (DRY), and follow OWASP best practices.
