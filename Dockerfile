# Base image
FROM openjdk:17-jdk-alpine

# 작업 디렉터리 설정
WORKDIR /app

# 로컬의 jar 파일을 컨테이너로 복사
COPY build/libs/*.jar app.jar

# 포트 개방 (Spring 기본 포트)
EXPOSE 8080

# 실행 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]