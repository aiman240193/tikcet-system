FROM public.ecr.aws/docker/library/maven:3-amazoncorretto-17 AS build
WORKDIR /project
ADD pom.xml /project
RUN mvn verify --fail-never
COPY . .
RUN mvn package

# Add the jar to the final docker image
FROM public.ecr.aws/amazoncorretto/amazoncorretto:17
COPY --from=build /project/target/ticket-system-0.0.1-SNAPSHOT.jar ticket-system.jar
ENTRYPOINT ["java", "-jar", "ticket-system.jar"]