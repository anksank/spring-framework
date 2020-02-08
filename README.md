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
