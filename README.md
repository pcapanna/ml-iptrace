# IP Trace Application

![Build Status](https://travis-ci.org/codecentric/springboot-sample-app.svg?branch=master)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

[Spring Boot](http://projects.spring.io/spring-boot/) app.

## Requirements

For building and running the application you need:

- [JDK 11](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.ml.iptrace.IpTraceApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Build docker Image

Run the following script:
```shell
./mvnw spring-boot:build-image
```

## Run docker Image

Run the following script
```shell
docker run -it -p8080:8080 iptrace:0.0.1-SNAPSHOT
```

## Interact with live deploy on Heroku

[LIVE](https://ip-trace.herokuapp.com/)

## Documentation

[WEB DOCUMENTATION](https://ip-trace-docs.herokuapp.com/)

## Additional information (Spanish)


Proyecto hecho en lenguaje java y spring boot framework. 

Diseño con arquitectura hexagonal.
La aplicación no persiste información entre reinicios o deploys distintos.

Debido a la arquitectura es posible, con cierta facilidad, modificar las implementaciones de los repositorios utilizados para que usen otras estrategias de persistencia.
Una mejora clara sería implementar Persistencia con REDIS

Se utilizaron las Rutas de Api Rest externas recomendadas por la especificación.

Tener en cuenta que particularmente la ruta para obtención de cotización de monedas tiene un límite máximo de
1000 invocaciones mensuales. Debido a esto, la última cotización de una moneda no se obtiene más de 1 vez por 1 dia de unidad de tiempo.

## Copyright

Released under the Apache License 2.0. See the [LICENSE](https://github.com/codecentric/springboot-sample-app/blob/master/LICENSE) file.
