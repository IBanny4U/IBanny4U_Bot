# Use official Java image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy source files
COPY . .

# Compile Java files
RUN javac -cp "lib/*" com/ibnybot/CodingAssistant.java

# Run the bot
CMD ["java", "-cp", "lib/*:.", "com.ibnybot.CodingAssistant"]