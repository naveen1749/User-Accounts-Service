FROM openjdk:18
ADD target/userAccounts.jar userAccounts.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/userAccounts.jar"]