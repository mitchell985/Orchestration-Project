# Developer Orchestration Project

To-do list for the creation of a project to fulfil the requirements for a developer orchestration role to show the skill set necessary for the position. This will be done by creating a Java boot spring application running multiple micro services, handled by boot spring and docker compose, then running openshift deployed on azure with ADO.

## Architecture

### 🏗️ Project Structure

```text
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

- Written in **Java** using **Spring Boot**
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
  - [x] Use `var`, `records`, `streams`, `Optionals`
  - [x] Create `module-info.java` example
- [x] Generate Spring Boot projects via Spring Initializr
- [x] Add dependencies: Web, Actuator, DevTools, Lombok
- [x] Create REST endpoint `/health` returning JSON
- [x] Implement service + repository layers
- [x] Configure `application.properties` with multiple profiles
- [x] Test the setup

---

## Multi-Service

- [x] Create three Spring Boot microservices: **order**, **inventory**, **customer**
- [x] Give each its own DB (H2 or PostgreSQL)
- [x] Implement CRUD endpoints
- [x] Call between services with WebClient
- [x] Add logging + error handling

---

## Containerization

- [x] Write multi-stage Dockerfile for each service
- [x] Build + run containers locally
- [x] Create `docker-compose.yml` for all services
- [x] Verify inter-service communication
- [ ] Install Openshift
- [x] Create Deployment + Service YAMLs
- [x] Add ConfigMap + Secret YAMLs
- [x] Apply manifests → verify pods
- [x] Scale replicas + test rolling updates

---

## Azure DevOps CI/CD

- [ ] Create Azure DevOps account + project
- [ ] Link GitHub repo
- [ ] Define stages: Build → Test → Docker Build → Deploy
- [ ] Configure variables, triggers, build agent
- [ ] Add bash/Python helper scripts for deployment tasks

---

## Monitoring Setup

- [x] Add Micrometer to each service
- [x] Expose `/actuator/prometheus` metrics
- [x] Deploy Prometheus + Grafana in K8s
- [x] Build Grafana dashboard (requests, latency, errors)
- [x] Document dashboard setup

---

## Testing Automation

- [x] Add JUnit 5 + Mockito
- [x] Write unit + integration tests
- [x] Add Cucumber (Gherkin) for behaviour tests
- [x] Integrate all tests into Azure pipeline

---

## Agile

- [x] Create product backlog (Azure Boards or Trello)
- [x] Write user stories + acceptance criteria
- [x] Plan two 1-week sprints
- [x] After each, note completed vs pending

---

## Last adjustments

- [x] Write `README.md` with architecture diagram, pipeline flow, dashboards

---

**ALL TASKS COMPLETED! ✅**
