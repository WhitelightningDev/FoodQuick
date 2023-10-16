# Use a Java base image
FROM openjdk:11

# Set the working directory
WORKDIR /app

# Copy your Java source files to the container
COPY src/ /app/src/

# Compile your Java application
RUN javac src/*.java

# Run your Java application
CMD ["java", "-cp", "src", "Main"]
