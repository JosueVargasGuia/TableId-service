FROM openjdk:11
EXPOSE  8091
WORKDIR /app
ADD   ./target/*.jar /app/TableId-service.jar
ENTRYPOINT ["java","-jar","/app/TableId-service.jar"]