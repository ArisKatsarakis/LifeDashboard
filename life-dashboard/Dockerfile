FROM maven:latest AS mvn
WORKDIR /home/app

COPY ./pom.xml /home/app/pom.xml
COPY ./src/main/java/gr/ariskatsarakis/lifedashboard/LifeDashboardApplication.java /home/app/src/main/java/gr/ariskatsarakis/lifedashboard/LifeDashboardApplication.java

RUN mvn clean install

COPY . /home/app

RUN mvn clean install

CMD mvn spring-boot:run
