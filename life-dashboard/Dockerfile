FROM maven:3.9.6 AS build
WORKDIR /home/app

COPY ./pom.xml /home/app/pom.xml
COPY ./src/main/java/gr/ariskatsarakis/lifedashboard/LifeDashboardApplication.java /home/app/src/main/java/gr/ariskatsarakis/lifedashboard/LifeDashboardApplication.java


COPY . /home/app

RUN mvn clean install

FROM openjdk:19
COPY --from=build /home/app/target/*.jar /usr/local/lib/demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]
