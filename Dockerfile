# Use a lightweight Java base image
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy source files from src/com/ into /app/com/
COPY src/com/ com/

# Copy all JAR dependencies into /app/lib/
COPY lib/ lib/

# Compile all Java source files
RUN javac -cp "lib/*" com/ibnybot/*.java com/gemini/*.java

# Run the bot using Main class
CMD ["java", "-cp", "lib/*:.", "com.ibnybot.Main"]