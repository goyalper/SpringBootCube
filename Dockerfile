FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

# Copy the Maven POM file and download dependencies
COPY pom.xml .
RUN apt-get update && apt-get install -y maven
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Set the entry point
ENTRYPOINT ["java", "-jar", "target/springbootcube-0.0.1-SNAPSHOT.jar"]

# Expose the port
EXPOSE 8080
