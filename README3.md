# Spring Framework (Section 8 & 9)

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
- `@SpringBootApplication` annotation automatically enables scanning and auto-configuration allowing this class to be used as a configuration class, which means we can define bean methods inside it.

## Simple Spring Boot Application

- [Branch](https://github.com/anksank/spring-framework/tree/015-Spring-Boot) with all changes
- `logback-spring.xml` needs to be created which will contain the logback configuration like the previous spring projects. By renaming the file, we allow spring to completely control the log initialization. This cannot happen if file name is logback.xml since the standard configuration is loaded too early. Hence, this is a standard by spring-boot. Since we have given spring the control over log initialization, we can make this file shorter.
  - Remove the `appender` and `root` section of this xml.
  - Adding the following include tag will help us load the configuration from another file:  
    `<include resource="org/springframework/boot/logging/logback/base.xml"/>`
    - Content of base.xml:
      ```xml
      <?xml version="1.0" encoding="UTF-8"?>
      <included>
        <include resource="org/springframework/boot/logging/logback/defaults.xml" />
        <property name="LOG_FILE" value="${LOG_FILE:-${LOG_PATH:-${LOG_TEMP:-${java.io.tmpdir:-/tmp}}}/spring.log}"/>
        <include resource="org/springframework/boot/logging/logback/console-appender.xml" />
        <include resource="org/springframework/boot/logging/logback/file-appender.xml" />
        <root level="INFO">
          <appender-ref ref="CONSOLE" />
          <appender-ref ref="FILE" />
        </root>
      </included>
      ```
      Since the xml contains `<included>` it can be included in other configurations. Also, the file `console-appender.xml` uses `${CONSOLE_LOG_PATTERN}` which can be defined in application.properties file. But, we can also go ahead and use default configuration.
- It is recommended to leave the main application class in the root package, so that it is able to scan and find other configuration classes.
- After adding a simple controller class, we can run the application on `localhost:8080/demo` without giving the name of the application. This happens because spring takes care of shortening the URL and taking care of the fact that we dont need to add the name of application in URL.

### Configuring ANSI colors in the console

In application.properties, we add `spring.output.ansi.enabled = always`.

### Automatic configuration of the ViewResolver

This can also be done just by adding a configuration in the application.properties file:
```properties
spring.mvc.view.prefix=/WEB-INF/view
spring.mvc.view.suffix=.jsp
```
**LIMITATION:** However, there are limitations when jsp is run with embedded containers.

## Add Spring Boot to an existing project

- Changes can be seen [here](https://github.com/anksank/spring-framework/commit/ad21dd65ab655eb72f6f4c13af6cc62de0715d48?diff=split)
- properties cannot be used with parent tag

## Thymeleaf

Modern server-side Java template engine used in web and standalone environments.

- With Thymeleaf, HTML can be displayed correctly in browsers as a static prototype (we can preview HTML without processing a template, which happens in case of JSPs).
- Easy integration with Spring and Java EE
- Thymeleaf has templates, which are just HTML files with Thymeleaf attributes.
- These templates are written in Thymeleaf and looks like regular HTML.
- Supports processing of HTML, XML, JavaScript, CSS and plain text out of the box.
- Extensible template engine -> template engine framework that allows you to create and customize processing of your templates.
- Integrates with Spring 3, 4, 5 as well as Spring Boot.
- Supports fragments -> smaller templates that we can reuse across the application.
- Supports decoupling template logic -> we can separate thymeleaf tags from HTML and the template engine will process it.

## Adding thymeleaf dependency

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

Sample thymeleaf file:

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>Welcome to the Number Game</h1>
    <a th:href="play" th:text="Play" href="http://www.google.com">Click to Play</a>
</body>
</html>
```

## Spring Boot developer tools

One of the most useful feauture -> ability to sense when files on the classpath have changed, and then allow you to do a near automatic restart of the application.

Dependency:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
</dependency>
```

- Scope runtime means that spring will take care of adding this dependency and it will be done in the runtime.
  - Its disabled when running a full packaged application (released version)

## Thymeleaf Preprocessing

- Some expressions in thymeleaf can be processed twice.
- Preprocessing is an execution of expressions done before the normal expression execution.
- Gives ability to modify an expression that will be executed.
- These expressions are like normal expressions but are surrounded by underscores(**__**).
  Example: `${__${resultMessage}__}`
- Feature is used when constants are used in templates or for supporting multiple languages.
- T operator is used to specify an instance of a Java Class, so it can be used to invoke static methods or get the value of static variables
  Example: `${__${T(com.ankit.util.AttributeNames)}.RESULT_MESSAGE}`
- `@{}` -> special syntax for URLs

## Thymeleaf Fragments

- `th:fragment` can be used to define a fragment.
- To insert a fragment, we use fragment expression, `~{...}`.
- Fragment can be selected by name or by the CSS selector the syntax is `~{templatename::selector}`.  
  Example: `~{templatename:fragmentname}`
- Reduces code duplications.
- Parameters can be passed to fragments and can be displayed based on conditions.

Example use in a html file:
```html
<footer th:replace="~{fragments/footer::footFragment}">
  <p>Footer</p>
</footer>
```

Footer Fragment definition:
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<body>
    <footer th:fragment="footFragment">
        <p>Created by Ankit .</p>
    </footer>
</body>
</html>
```

## Thymeleaf Decoupled Template Logic

- Thymeleaf allows decoupling of template and logic -> Template logic can be defined in a separate file from the HTML file.
- **Logic** file is an additional file in the same folder with the same filename with an extension **.th.xml**.
- Example: home.html (template file) and home.th.xml (logic file).
- Thymeleaf processes both the files together and generates the final structure
- With Spring 2.0.3, this option is not automatically enabled, we need to enable it.

### Enabling decoupling of logic and template

- This property does not exist as part of the application.properties as of version 2.0.3 of spring boot, hence we create a class and use it to set this property.
- `SpringResourceTemplateResolver` is a class that finds templates in the template directory when controller returns the view name.
- Code for this exists [here](https://github.com/anksank/spring-framework/commit/e2218cc4dd5ceb7842e03a214d33a7faa13a31a8?diff=unified).

### Adding Decouling Logic: Example

- [code](https://github.com/anksank/spring-framework/pull/9/commits/6a763233ca83e5152cc5c191fe69e08a890d8ecf)
