FROM maven:latest AS build
WORKDIR /home/app

COPY ./pom.xml /home/app/pom.xml
COPY ./src/main/java/gr/ariskatsarakis/lifedashboard/LifeDashboardApplication.java /home/app/src/main/java/gr/ariskatsarakis/lifedashboard/LifeDashboardApplication.java

RUN mvn clean install

COPY . /home/app

RUN mvn clean install

FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/*.jar /usr/local/lib/demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]