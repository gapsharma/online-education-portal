FROM domblack/oracle-jdk8:latest
WORKDIR /var/www/java2
COPY . /var/www/java2
RUN javac HelloWorld.java
CMD ["java", "HelloWorld"]
