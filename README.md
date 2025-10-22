# Developer Orchestration Project

A comprehensive microservices demonstration project showcasing modern Java development, containerization, Kubernetes/OpenShift deployment, and CI/CD practices using Azure DevOps.

## 🎯 Project Overview

This project demonstrates a complete microservices architecture built with **Java Spring Boot 3**, containerized with **Docker**, deployed on **Kubernetes/OpenShift**, and automated with **Azure DevOps CI/CD pipelines**.

## 🏗️ Technologies Used

### Core Technologies

- **Java 21** - Modern Java features (records, var, streams, Optional)
- **Spring Boot 3.5.6** - Microservices framework
- **Spring Data JPA** - Database abstraction
- **H2 Database** - In-memory database (dev/test)
- **PostgreSQL** - Production database support

### DevOps & Infrastructure

- **Docker** - Containerization
- **Docker Compose** - Multi-container orchestration
- **Kubernetes/OpenShift** - Container orchestration
- **Azure DevOps** - CI/CD pipeline
- **Maven** - Build automation

### Monitoring & Observability

- **Spring Boot Actuator** - Application metrics
- **Micrometer** - Metrics facade
- **Prometheus** - Metrics collection
- **Grafana** - Metrics visualization

## 📁 Project Structure

```doc
.
├── order_service/          # Order management microservice
├── inventory_service/      # Inventory management microservice
├── customer_service/       # Customer management microservice
├── k8s/                    # Kubernetes manifests
├── scripts/                # Helper scripts
├── docker-compose.yml      # Docker Compose configuration
├── azure-pipelines.yml     # Azure DevOps pipeline
└── README.md              # This file
```

## 🚀 Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.9+
- Docker & Docker Compose

### Local Development

1. **Build the services**

   ```bash
   cd order_service && ./mvnw clean package
   cd ../inventory_service && ./mvnw clean package
   cd ../customer_service && ./mvnw clean package
   ```

2. **Run with Docker Compose**

   ```bash
   docker-compose up --build
   ```

3. **Access the services**
   - Order Service: <http://localhost:8081/actuator/health>
   - Inventory Service: <http://localhost:8082/actuator/health>
   - Customer Service: <http://localhost:8083/actuator/health>

## 📈 Modern Java Features Demonstrated

- **Records** - Immutable data carriers for DTOs
- **var** - Local variable type inference
- **Streams API** - Functional-style operations
- **Optional** - Null-safe API design

## 🎯 Best Practices

- ✅ SOLID principles
- ✅ Microservices architecture
- ✅ Container best practices
- ✅ Infrastructure as Code
- ✅ CI/CD automation

## 👤 Author

**Mitchell**

- GitHub: [@mitchell985](https://github.com/mitchell985)

---
