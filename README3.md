# Spring Framework (Section 8)

## Spring Boot

Spring boot is a framework that helps developers build stand-alone, production grade Spring based applications that they can run easily and quicky.

- Dont have to write the same configuration again and again. Most applications need very little spring configuration, almost everything is auto configured.
- Main Features:
  - Spring boot starters
    Examples: Spring MVC, JPA - These are configured with most commonly used library dependencies.
  - Spring boot auto-configuration
    Spring boot configures many components automatically. No need to set up boilerplate configuration. For example, if you are using spring-webmvc dependency, spring boot assumes you are building spring mvc application, and it automatically registers `DispatcherServlet`.
  - Embedded container servlet support
  - Elegant configuration management
- Using spring boot, we can configure self contained JAR Application with an embedded servlet container instead of creating a WAR.

## Using Spring Initializr

Online Spring boot application generator used to create Spring boot applications from scratch.

- Goto [start.spring.io](https://start.spring.io) to quickly create and generate a spring boot application.

## Understanding Spring Boot Structure

- Extra folders that exist in spring boot application:
  - `.mvn`
    - `wrapper`
      - `maven-wrapper.jar`
      - `maven-wrapper.properties` -> These files help in running maven goals without installing maven, so that application can be run using console
  - `src`
    - `main`
      - `resources`
        - `static` -> for static content like html, css, js, etc. -> auto-detected by spring-boot
        - `templates` -> dynamic content like thymeleaf and groovy templates -> auto-detected by spring-boot
        - `application.properties` -> for things like database urls, service ports, and other application dependent properties.
  - `mvnw`
  - `mvnc.cmd` -> batch scripts available to run from the console without installing and configuring maven
