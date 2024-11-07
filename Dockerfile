FROM maven:3.9.4-amazoncorretto-20-debian as build

WORKDIR /usr/src
COPY . .
RUN mvn clean install package 


FROM maven:3.9.4-amazoncorretto-20-debian 
EXPOSE 8080
COPY --from=build /usr/src/target/bolComWebhookReceiver.jar /usr/share/bolComWebhookReceiver.jar
ENTRYPOINT ["java"]
CMD ["-jar", "/usr/share/bolComWebhookReceiver.jar"]
