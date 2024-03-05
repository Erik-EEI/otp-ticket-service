# CI/CD Pipeline

## Overview

The CI/CD pipeline implemented using GitHub Actions automates the process of building and testing the application. After every successful tests, the docker image is automatically uploaded to Docker Hub.
This pipeline ensures that changes made to the codebase are automatically validated, reducing manual effort and improving efficiency.
> ##  Build & Test job
## Pipeline Logic

The pipeline consists of the following main steps:

1. **Checkout Repository**: This step checks out the codebase from the repository to the runner machine.

2. **Set up JDK 17**: Configures the Java Development Kit (JDK) version 17, necessary for building and testing the application.

3. **Build and Test Partner Module**: Builds and tests the Partner module of the application.

4. **Configure Application Properties**: Configures the `application.properties` file for the Ticket Service module, specifying database connection details and other environment-specific configurations.

5. **Build and Test TicketService Module**: Builds and tests the TicketService module of the application.

## Process Details

- **Branch Filtering**: The pipeline is triggered on every push to the `main` branch and for pull requests targeting the `main` branch. This ensures that changes are validated before being merged into the main branch.

- **Service Container**: The pipeline spins up a PostgreSQL database container (`db`) for running integration tests. This container is used to create a temporary database for testing purposes.

- **Environment Variables**: Environment variables such as database credentials are set within the pipeline configuration.

- **Dependency Caching**: The pipeline utilizes caching to speed up build times by storing dependencies between pipeline runs. This optimization reduces the time required for building and testing the application.

> ##  Build & Publish job
As part of the continuous integration and deployment process,I have integrated Docker Hub into the pipeline to automatically upload Docker images of the application components.

1. **Building Docker Images**: After successfully building and testing the application modules, it proceeds to build Docker images for these modules. This step ensures that the application is containerized and ready for deployment.

2. **Tagging Images**: Once the Docker images are built, we tag them appropriately to indicate their version or other relevant information. Tagging ensures that we can identify and manage different versions of our application images effectively.

3. **Pushing Images to Docker Hub**: Using GitHub Actions, push the tagged Docker images to the Docker Hub repository. This step automatically uploads the images, making them available for deployment.

4. **Automation and Integration**: The Docker image upload process is seamlessly integrated into the existing CI/CD pipeline, ensuring that it runs automatically whenever changes are made to the codebase.

>**PLEASE NOTE**: TicketService module requires external PostgresDB to function properly when not started with the provided `docker-compose.yml` file. 