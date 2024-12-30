# Lost & Found Application

## Description
A RESTful application for managing lost items. Admins can upload items via PDF, and users can claim them.

This application helps in keeping track of lost and found items by allowing users to view and claim items. Admins can upload new items using PDF files and retrieve claims made by users.

## Features
- **Admin:** Upload lost items via PDF.
- **User:** View and claim lost items.
- **Admin:** Retrieve claims and user details.
- **User Authentication**: Basic login and registration functionality.

## Prerequisites
- Docker
- Java 17 or higher
- Gradle (for local build if not using Docker)

## Setup

### 1. Setting up PostgreSQL with Docker
First, we need to set up a PostgreSQL container to store the application's data.

Run the following command to start a PostgreSQL container:

```bash
docker run -p 5432:5432 -e POSTGRES_PASSWORD=password -e POSTGRES_USER=postgres -e POSTGRES_DB=mydatabase -d postgres
```
Run the Spring Boot Application:

```bash
./gradlew bootRun
```

Access the application at http://localhost:8080