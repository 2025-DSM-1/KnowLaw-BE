FROM openjdk:17-jdk-alpine
WORKDIR /app

# 빌드된 jar 파일 복사 (jar 파일명에 맞게 수정 필요)
COPY build/libs/_2025-dsm-hackaton-BE-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]