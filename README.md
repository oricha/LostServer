# Lost & Found Application

## Description
A RESTful application for managing lost items. Admins can upload items via PDF, and users can claim them.

## Features
- Admin: Upload lost items via PDF
- User: View and claim lost items
- Admin: Retrieve claims and user details

## Prerequisites
- Docker
- Java 17

## Setup
1. Build the application:

Start PostgreSQL with Docker:
```
docker run -p 5432:5432 -e POSTGRES_PASSWORD=password -e POSTGRES_USER=postgres -e POSTGRES_DB=mydatabase -d postgres
```
Run the Spring Boot Application:
```
./gradlew bootRun
```
Access the application at http://localhost:8080