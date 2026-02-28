Content Calendar API
A Kotlin + Spring Boot 3.5 REST API for managing content items.
Built with Gradle, packaged with Cloud Native Buildpacks, and deployable to platforms like Railway, Render, Fly.io, AWS, and more.

🚀 Features
- Spring Boot 3.5 (AOT‑ready)
- Kotlin 1.9
- Spring Data JDBC
- PostgreSQL support
- Docker‑ready via Buildpacks (bootBuildImage)
- Native image support (GraalVM)
- Local development with Docker Compose
- Production‑ready configuration

🧱 Tech Stack
- Language: Kotlin
- Framework: Spring Boot 3.5
- Build Tool: Gradle (Kotlin DSL)
- Database: PostgreSQL
- Containerization: Cloud Native Buildpacks
- Deployment: Railway (or any container host)

📦 Building the Application
1. Build an executable JAR
   Spring Boot’s Gradle plugin produces an executable Uber JAR:
   ./gradlew clean build


Run it locally:
java -jar build/libs/content-calendar-0.0.1-SNAPSHOT.jar



🐳 Running with Docker
Build a container image (no Dockerfile needed)
./gradlew bootBuildImage --imageName=content-calendar:latest


Run the container:
docker run -p8080:8080 content-calendar:latest



🗄️ Local Development with Docker Compose
A local Postgres instance is included:
docker-compose up -d


This starts:
- postgres_local on port 5432
- A persistent Docker volume for data
  Update your application.yaml to match:
  spring:
  datasource:
  url: jdbc:postgresql://localhost:5432/appdb
  username: postgres
  password: postgres



🌐 Deploying to Railway 
- Railway automatically injects Postgres environment variables.
- Use DATABASE_URL for your Spring Boot datasource:
- spring:
  - datasource:
    - username: ${PGUSER}
    - password: ${PGPASSWORD}
    - url: jdbc:postgresql://\${PGHOST}:\${PGPORT}/\${PGDATABASE}

Railway also provides:
- PGHOST
- PGPORT
- PGUSER
- PGPASSWORD
- PGDATABASE
-  Spring Boot can auto‑configure using these if you omit manual settings.
-  Deploy your container
- Build the image:
  - ./gradlew bootBuildImage --imageName=content-calendar:latest


- Push to a registry (GitHub Container Registry example):
  docker tag content-calendar:latest ghcr.io/<your-username>/content-calendar:latest
  docker push ghcr.io/<your-username>/content-calendar:latest


- Point Railway to the image.

📁 Project Structure
content-calendar/
├── src/main/kotlin/...     # Application source
├── src/main/resources/     # application.yaml, schema.sql, data.sql
├── build.gradle.kts        # Gradle build config
├── settings.gradle.kts
├── docker-compose.yml
├── gradlew / gradlew.bat   # Gradle wrapper
├── gradle/                 # Wrapper JAR + scripts
└── README.md



🧪 Running Tests
./gradlew test



📜 License
MIT, Apache 2.0, or your preferred license.
