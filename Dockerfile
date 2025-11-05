FROM gradle:8.14-jdk21
WORKDIR /app
COPY app/ ./
RUN chmod +x gradlew
RUN ./gradlew clean build --no-daemon
CMD ["./gradlew", "run", "--no-daemon"]