# Developer Orchestration Project

To-do list for the creation of a project to fulfil the requirements for a developer orchestration role to show the skill set necessary for the position. This will be done by creating a Java boot spring application running multiple micro services, handled by boot spring and docker compose, then running openshift deployed on azure with ADO.

## Architecture

### 🏗️ Project Structure

```doc
/order-service
/inventory-service
/customer-service
/k8s/
  ├── deployments/
  ├── services/
  ├── configmaps/
  └── secrets/
/scripts/
  ├── build.sh
  ├── deploy.sh
  └── cleanup.py
azure-pipelines.yml
docker-compose.yml
TODO.md
README.md
```

### Services

- `order-service` – handles order creation and management
- `inventory-service` – manages stock and availability
- `customer-service` – stores and retrieves customer data

Each service:

- Written in **Java 17** using **Spring Boot**
- Owns its own **PostgreSQL** or **H2** database
- Exposes **REST** APIs
- Provides metrics through `/actuator/prometheus`

### Infrastructure

- **Docker** containers for each service
- **Kubernetes** (via Minikube/kind) for deployment and scaling
- **Azure DevOps** pipeline for build → test → containerize → deploy
- **Prometheus + Grafana** for monitoring

## Java and Spring Boot

- [x] Show modern Java features
  - [x] Use `var`, records, streams, Optionals
  - [x] Create `module-info.java` example
- [x] Build a small CLI demo (`film-catalog`)
- [ ] Generate new Spring Boot project via Spring Initializr
- [ ] Add dependencies: Web, Actuator, DevTools, Lombok
- [ ] Create REST endpoint `/health` returning JSON
- [ ] Implement service + repository layers
- [ ] Configure `application.properties` with multiple profiles
- [ ] Ask Copilot: _"generate REST controller with GET /api/hello"_

---

## Multi-Service

- [ ] Create three Spring Boot microservices: **order**, **inventory**, **customer**
- [ ] Give each its own DB (H2 or PostgreSQL)
- [ ] Implement CRUD endpoints
- [ ] Call between services with WebClient
- [ ] Add logging + error handling
- [ ] Ask Copilot: _"generate integration call from order-service to inventory-service"_

---

## Containerization

Use Openshift?

- [ ] Write multi-stage Dockerfile for each service
- [ ] Build + run containers locally
- [ ] Create `docker-compose.yml` for all services
- [ ] Verify inter-service communication
- [ ] Ask Copilot: _"generate Dockerfile for Spring Boot app using openjdk:17-jdk-slim"_
- [ ] Install Minikube or kind or Openshift
- [ ] Create Deployment + Service YAMLs
- [ ] Add ConfigMap + Secret YAMLs
- [ ] Apply manifests → verify pods
- [ ] Scale replicas + test rolling updates
- [ ] Ask Copilot: _"generate K8s deployment for dockerized Spring Boot service"_

---

## Azure DevOps CI/CD

- [ ] Create Azure DevOps account + project
- [ ] Link GitHub repo
- [ ] Ask Copilot: _"generate azure-pipelines.yml for Java + Docker project"_
- [ ] Define stages: Build → Test → Docker Build → Deploy
- [ ] Configure variables, triggers, build agent
- [ ] Add bash/Python helper scripts for deployment tasks

---

## Monitoring Setup

- [ ] Add Micrometer to each service
- [ ] Expose `/actuator/prometheus` metrics
- [ ] Deploy Prometheus + Grafana in K8s
- [ ] Ask Copilot: _"generate Prometheus scrape config for actuator endpoints"_
- [ ] Build Grafana dashboard (requests, latency, errors)
- [ ] Document dashboard setup

---

## Testing Automation

- [ ] Add JUnit 5 + Mockito
- [ ] Write unit + integration tests
- [ ] Ask Copilot: _"generate unit test for OrderService.createOrder()"_
- [ ] Add Cucumber (Gherkin) for behaviour tests
- [ ] Integrate all tests into Azure pipeline

---

## Agile

- [ ] Create product backlog (Azure Boards or Trello)
- [ ] Write user stories + acceptance criteria
- [ ] Plan two 1-week sprints
- [ ] After each, note completed vs pending
- [ ] Ask Copilot: _"summarize sprint results and write retrospective notes"_

---

## Last adjustments

- [ ] Write `README.md` with architecture diagram, pipeline flow, dashboards
- [ ] Record short demo GIF or video
- [ ] Push project to GitHub
- [ ] Ask Copilot: _"generate Mermaid diagram for microservice architecture"_
- [ ] Prepare STAR-format interview stories

---
