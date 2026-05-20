run:
	mvn spring-boot:run -e

build:
	mvn package

test:
	mvn test

ci:
	mvn clean verify

start: build run
