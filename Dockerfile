# Use a lightweight Java image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy source files and libraries
COPY com/ com/
COPY lib/ lib/

# Compile all Java files
RUN javac -cp "lib/*" com/ibnybot/*.java com/gemini/*.java

# Run the bot using Main class
CMD ["java", "-cp", "lib/*:.", "com.ibnybot.Main"]