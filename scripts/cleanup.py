#!/usr/bin/env python3
"""
Cleanup script for Kubernetes resources
Demonstrates Python scripting for DevOps tasks
"""

import subprocess
import sys

def run_command(command):
    """Execute a shell command and return the result"""
    try:
        result = subprocess.run(
            command,
            shell=True,
            check=True,
            capture_output=True,
            text=True
        )
        return result.stdout
    except subprocess.CalledProcessError as e:
        print(f"Error executing command: {command}")
        print(f"Error message: {e.stderr}")
        return None

def cleanup_resources():
    """Clean up all Kubernetes resources"""
    print("Cleaning up Kubernetes resources...")
    
    # Delete deployments
    print("Deleting deployments...")
    run_command("kubectl delete -f k8s/deployments/ --ignore-not-found=true")
    
    # Delete services
    print("Deleting services...")
    run_command("kubectl delete -f k8s/services/ --ignore-not-found=true")
    
    # Delete configmaps
    print("Deleting configmaps...")
    run_command("kubectl delete -f k8s/configmaps/ --ignore-not-found=true")
    
    # Delete secrets
    print("Deleting secrets...")
    run_command("kubectl delete -f k8s/secrets/ --ignore-not-found=true")
    
    print("Cleanup completed successfully!")

def main():
    """Main function"""
    print("=== Kubernetes Cleanup Script ===")
    
    # Confirm cleanup
    response = input("Are you sure you want to delete all resources? (yes/no): ")
    if response.lower() != 'yes':
        print("Cleanup cancelled.")
        sys.exit(0)
    
    cleanup_resources()

if __name__ == "__main__":
    main()
