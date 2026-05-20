run:
	mvn spring-boot:run -e

build:
	mvn package

test:
	mvn test

start: build run
