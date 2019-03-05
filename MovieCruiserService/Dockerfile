FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=movieDb
ENV MYSQL_USER=poc
ENV MYSQL_PASSWORD=poc
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/movieDb
ADD ./target/MovieCruiserService-0.0.1-SNAPSHOT.jar /usr/src/MovieCruiserService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","MovieCruiserService-0.0.1-SNAPSHOT.jar"]