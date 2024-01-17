FROM java:8

COPY demo.jar .

CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/demo.jar"]
