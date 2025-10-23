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

## 📊 Monitoring & Observability

This project uses Prometheus and Grafana for monitoring. The services are configured to expose metrics in Prometheus format, and a Grafana dashboard is provided to visualize these metrics.

### Prometheus

Prometheus is configured to scrape metrics from the following endpoints:

- **Order Service:** `http://order-service:8081/actuator/prometheus`
- **Inventory Service:** `http://inventory-service:8082/actuator/prometheus`
- **Customer Service:** `http://customer-service:8083/actuator/prometheus`

### Grafana

A pre-configured Grafana dashboard is available to visualize the metrics from the services. You can access it at `http://localhost:3000`. The default login is `admin`/`admin`.

The dashboard provides a basic overview of the services, including:

- **Order Service Requests:** The rate of requests to the order service.

## � API Endpoints

The system consists of three Spring Boot microservices. Below are the HTTP APIs exposed by each service with their purpose, inputs, and outputs.

### Order Service ([http://localhost:8081](http://localhost:8081))

- POST /api/orders

  - Purpose: Create a new order
  - Body: { customerId: string, totalAmount: number }
  - Response: Order { id, customerId, totalAmount, status, createdAt }

- GET /api/orders/{id}

  - Purpose: Get a single order by ID
  - Response: Order { id, customerId, totalAmount, status, createdAt } or 404 if not found

- GET /api/orders

  - Purpose: List all orders (most recent first)
  - Response: Order[]

- GET /api/orders/status/{status}

  - Purpose: List orders by status
  - Path Param: status = PENDING | PROCESSING | COMPLETED | CANCELLED | REFUNDED
  - Response: Order[]

- GET /api/orders/customer/{customerId}

  - Purpose: List orders for a specific customer
  - Response: Order[]

- PATCH /api/orders/{id}/status

  - Purpose: Update an order's status
  - Body: { status: "PENDING" | "PROCESSING" | "COMPLETED" | "CANCELLED" | "REFUNDED" }
  - Response: Updated Order or 404 if not found

- GET /api/orders/statistics

  - Purpose: Retrieve aggregate statistics
  - Response: { totalOrders: number, completedOrders: number, averageOrderValue: number }

- GET /api/orders/revenue

  - Purpose: Total revenue across COMPLETED orders
  - Response: { totalRevenue: number }

- GET /health

  - Purpose: Lightweight custom health check
  - Response: { status, service, timestamp, version }

- Actuator (if enabled): /actuator/health, /actuator/metrics

Example

```bash
# Create an order
curl -s -X POST http://localhost:8081/api/orders \
   -H 'Content-Type: application/json' \
   -d '{"customerId":"cust-123","totalAmount": 99.99}'

   # List orders
curl -s -X GET http://localhost:8081/api/orders \
   -H 'Content-Type: application/json'
```

### Inventory Service ([http://localhost:8082](http://localhost:8082))

- POST /api/inventory

  - Purpose: Create a new inventory item
  - Body: { productId: string, productName: string, quantity: number }
  - Response: InventoryItem { id, productId, productName, quantity, reservedQuantity, availableQuantity }

- GET /api/inventory/{productId}

  - Purpose: Get a single inventory item by product ID
  - Response: InventoryItem or 404 if not found

- GET /api/inventory

  - Purpose: List all inventory items
  - Response: InventoryItem[]

- GET /api/inventory/{productId}/available?quantity=Q

  - Purpose: Check if requested quantity is available
  - Response: { productId: string, requestedQuantity: number, available: boolean }

- PATCH /api/inventory/{productId}/quantity

  - Purpose: Update quantity for a product
  - Body: { quantity: number }
  - Response: Updated InventoryItem or 404 if not found

- Actuator (if enabled): /actuator/health, /actuator/metrics

### Customer Service ([http://localhost:8083](http://localhost:8083))

- POST /api/customers

  - Purpose: Create a new customer
  - Body: { customerId: string, name: string, email: string, phone?: string }
  - Response: Customer { id, customerId, name, email, phone, createdAt }

- GET /api/customers/{customerId}

  - Purpose: Get a customer by their business ID
  - Response: Customer or 404 if not found

- GET /api/customers/email/{email}

  - Purpose: Look up a customer by email
  - Response: Customer or 404 if not found

- GET /api/customers

  - Purpose: List all customers
  - Response: Customer[]

- PUT /api/customers/{customerId}

  - Purpose: Update customer details
  - Body: { name: string, email: string, phone?: string }
  - Response: Updated Customer or 404 if not found

- DELETE /api/customers/{customerId}

  - Purpose: Delete a customer
  - Response: 204 No Content or 404 if not found

- GET /health

  - Purpose: Lightweight custom health check
  - Response: { status, service, timestamp, version }

- Actuator (if enabled): /actuator/health, /actuator/metrics

Notes

- All bodies and responses are JSON.
- Error responses follow a consistent pattern in order-service via a global exception handler: { status, message, timestamp } with appropriate HTTP status codes.
- In dev (Docker Compose), in-memory H2 databases are used; data resets on restart.

## 🎯 Best Practices

- ✅ SOLID principles
- ✅ Microservices architecture
- ✅ Container best practices
- ✅ Infrastructure as Code
- ✅ CI/CD automation

## 👤 Author

Mitchell and Copilot

- GitHub: [@mitchell985](https://github.com/mitchell985)

---
