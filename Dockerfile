# Using a Java base image
FROM openjdk:11

# Setting the working directory
WORKDIR /app

# Copying source files to the container
COPY src/ /app/src/

# Compile the Java application
RUN javac src/*.java

# Run the Java application
CMD ["java", "-cp", "src", "Main"]
