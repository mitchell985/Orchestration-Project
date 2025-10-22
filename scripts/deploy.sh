#!/bin/bash
# Deploy script for Kubernetes/OpenShift

set -e

echo "Deploying microservices to Kubernetes..."

# Apply ConfigMaps
echo "Creating ConfigMaps..."
kubectl apply -f k8s/configmaps/

# Apply Secrets
echo "Creating Secrets..."
kubectl apply -f k8s/secrets/

# Apply Services
echo "Creating Services..."
kubectl apply -f k8s/services/

# Apply Deployments
echo "Creating Deployments..."
kubectl apply -f k8s/deployments/

echo "Waiting for deployments to be ready..."
kubectl wait --for=condition=available --timeout=300s deployment/order-service
kubectl wait --for=condition=available --timeout=300s deployment/inventory-service
kubectl wait --for=condition=available --timeout=300s deployment/customer-service

echo "All services deployed successfully!"
echo "Getting pod status..."
kubectl get pods -l tier=backend
