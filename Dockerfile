FROM adoptopenjdk/openjdk11:alpine-jre
#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} propostas.jar
ENTRYPOINT ["java","-Xmx512m","-Dserver.port=${SERVER_PORT}","-Dspring.datasource.url=${DATASOURCE_DB}","-Dspring.datasource.username="${DATASOURCE_USER}","-Dspring.datasource.password="${DATASOURCE_PASSWD}","-Dspring.datasource.driver-class-name="${DATASOURCE_DRIVER}","-jar","/proposta.jar"]