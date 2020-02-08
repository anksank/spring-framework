# Spring Framework

## What is Logging?

### History

- Logging was done using System.out.println(), System.err.println() or e.printStackTrace()
- Debug Logs -> System.out, Error Logs - System.err
- In production, System.out was discarded. System.err was sent to a log file.
- Problem -> Lack of configurability

### Slf4j - The Logging API

- 2 Aspects to a well configured Logging System: API & Implementation
- Logging API -> What will be used at the Application layer directly; what we code; what the developer interacts with
- Logging Implementation -> What the logging API will use internally and make calls to in order to get logging working
- Both the Logging API and implementation are required
- slf4j -> Example of a good Logging API

### Logging Implementation - Using logback

- Each logging implementation (called as binding) has their own way of configuring the log output, but your application remains agnostic and always use the same `org.slf4j.Logger` API.
- Logback is the successor of log4j project
- Highly configurable through external configuration files at runtime. It views logging process in terms of levels of priorities and offers mechanisms to direct logging information to a variety of destinations like database, file, email, console, etc.
- logback is built using maven
- logback has 3 main components ->
  - loggers -> responsible for capturing logging information
  - appenders -> publishes logging informationt to various destinations
  - layouts -> formatting logging information in different styles
- logback also has filters: based on ternary logic allowing them to be assembled or chained together to compose an artitrarily complex filtering policy. (inspired by linux iptables)

### Advantages of logback

- logging behavior can be set at runtime using a configuration file.
- logging levels -> TRACE, DEBUG, INFO, WARN, ERROR
- using layout interface, the format of log output can be changed
- target destination of the log output can be changed by switching implementation of appender interface
- logback reloads its configuration file upon modification
- logback supports xml and groovy configurations

## Using logging with logback

- Add dependency from https://mvnrepository.com/ 
- Sources and Documentation can be downloaded inside intelliJ to browse through the dependencies code
- To use slf4j API, a logger object has to be created first.

## Logback Configuration

- If not present, logback works with a minimal configuration

### Logback Initialization Steps

- Logback tries to find a file called logback-test.xml in the classpath
- If not found, it tries to find a file called logback.groovy in the classpath
- If not found, it checks for logback.xml in the classpath
- If not found, the service-provider loading facility (introduced in jdk 1.6) is used to resolve the implementation of a Configurator Service
- If none of the above work, logback tries configures itself automatically using the BasicConfigurator which will cause logging output to be directed to console.

### Logback Configuration Example

```xml
<configuration>
  <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
    <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
      <pattern>%date [%thread] [%-5level] %logger{50} - %message%n</pattern>
    </encoder>
  </appender>
  
  <logger name="com.ankit" level="DEBUG"/>
  
  <root level="INFO">
    <appender-ref ref="STDOUT"/>
  </root>
</configuration>
```

### Loggers

- Loggers are objects with names (case-sensitive). It follows the hierarchical naming rule (there can be parent and child relationships among loggers).
- Example - com.ankit is a parent of com.ankit.springdemo.
- root logger sits at top of hierarchy (it cannot be retrived by name like other loggers).
- root logger always exists by default in a log4j system

### Pattern Conversion

<img width="1160" alt="Screenshot 2020-02-08 at 2 34 00 PM" src="https://user-images.githubusercontent.com/10058009/74082465-401af800-4a80-11ea-9c17-30f6f8dd062e.png">

