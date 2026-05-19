run:
	cd article-service && mvn spring-boot:run

build:
	cd article-service && mvn package

start: build run