#!/bin/bash
# Build script for all microservices

set -e

echo "Building microservices..."

# Build order-service
echo "Building order-service..."
cd order_service
docker build -t order-service:latest .
cd ..

# Build inventory-service
echo "Building inventory-service..."
cd inventory_service
docker build -t inventory-service:latest .
cd ..

# Build customer-service
echo "Building customer-service..."
cd customer_service
docker build -t customer-service:latest .
cd ..

echo "All services built successfully!"
