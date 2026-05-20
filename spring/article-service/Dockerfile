FROM eclipse-temurin:21-jdk-jammy as build
WORKDIR /workspace

# Expect the module to be built by CI; this stage kept for local multi-stage builds if desired

FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
