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

- 5 means -> 5 characters to be printed. (-) minus is used to left justify the string
- 40 means -> 40 characters of logger name to be printed, if its more than that, it is abbreviated or truncated
- %n is for next line

### Logger Name length

<img width="1202" alt="Screenshot 2020-02-08 at 2 47 45 PM" src="https://user-images.githubusercontent.com/10058009/74082656-25e21980-4a82-11ea-97da-77e12584cbd0.png">

## UML (Unified Modeling Language)

- UML is a standard visual modeling language intended to be used for
  - modeling business & similar processes
  - analysis, design, & implementation of software-based systems
- Common language for business analysts, software architects & developers used to describe, specify, design, and document existing or new business processes, structure & behavior of artifacts of software systems
- UML can be applied to application domains like - banking, finance, internet, aerospace, healthcare, etc.
- UML visually describes class diagrams, use case diagrams & related items
- More info - https://www.uml-diagrams.org

## Project Setup

- Dependencies can be added in the parent pom and versioning can be maintained in the same place centrally. In that case, the child pom only needs to have an entry of the dependency used without the version information.
- dependencyManagement tag is used in parent pom (according to current understaning)

## Using a Spring Container

### IoC Container

- Spring IoC (Inversion of Control) container, or simply, Container is a component of the Spring framwork that contains the beans and manages their complete lifecycle.
- Bean - A simple Java object that is instantiated, managed and assembled by the IoC container. 
- IoC is also known as dependency injection (DI). 
  - A process whereby objects define their dependencies (that is, the other objects they work with) only through constructor arguments, arguments to a factory method, or properties that are set on the object instance after it is constructed or returned from a factory method. 
  - The container then injects those dependencies when it creates the bean. This process is fundamentally the inverse (hence the name, Inversion of Control) of the bean itself controlling the instantiation or location of its dependencies by using direct construction of classes.
- beans.xml -> can be thought of as a configuration for the container
- Parameterised Logging:
  - When we use place holders, ex: `log.info("Number: {}", x);`, & logging level is off, string is not concatenated, in case of using concatenation, `log.info("Number: " + x);`, concatenation still happens & application becomes slow.
- By default, spring container defines all beans as singleton which is shared across the whole application

## Constructor Based Dependency Injection

- sample piece of code in beans.xml 
  ```xml
  <bean id="numberGenerator" class="com.ankit.NumberGeneratorImpl" />
  <bean id="game" class="com.ankit.GameImpl">
    <constructor-arg ref="numberGenerator"/>
  </bean>
  ```
- sample piece of code in the class
  ```java
  // == constructors ==
  public GameImpl(NumberGenerator numberGenerator) {
    this.numberGenerator = numberGenerator;
  }
  ```

## Setter Based Dependency Injection

- beans.xml
  ```xml
  <bean id="numberGenerator" class="com.ankit.NumberGeneratorImpl" />
  <bean id="game" class="com.ankit.GameImpl">
    <property name="numberGenerator" ref="numberGenerator"/>
  </bean>
  ```
- piece of code in class
  ```java
  public void setNumberGenerator(NumberGenerator numberGenerator) {
    this.numberGenerator = numberGenerator;
  }
  ```
- property refers to the name of the parameter passed in the function in the setter class & ref refers to the name id of the bean defined above 'game' bean

## Setter or Constrcutor Based Dependency Injection

- Constrcutor based DI can be used for mandatory dependencies & setter methods for optional dependencies
- Spring team generally advocates constructor injection since it enables one to implement application components as immutable objects and to ensure that required dependencies are not null
- Constructore injected components are always returned to the client (calling code) in a fully initialized state.
- Large number of constructor arguments is bad practice (3 should be max) -> it implies that class has lot of responsibilities and should be refactored to better address proper separation of concerns
- In case of setter dependencies, not-null checks must be performed everywhere
- Benefit of setter injection
  - objects of this class become amenable (easily controlled) to reconfiguration or re-injection later
  
### Dependency Resolution Process

- Application context is created & initialized with all the configuration metadata that described all beans (using xml or annotations via java code)
- For each bean, its dependencies are expressed as properties or constructor arguments. These dependencies are provided to the bean when the bean is actually created. Each property or constrcutor argument is an actual bean definition or reference to another bean in the container
- By default, spring can convert a value supplied in string format to all built-in types, such as int, long, String, boolean, etc.
- Spring container validates the configuration of each bean as the container is created, however, the bean properties themselves are not set until the bean is created
- Creation of bean potentially causes a graph of beans to be created, when the bean's dependencies and their dependencies are created and assigned

### Circular Dependencies

- In case of constructor injection, it is possible to create an unresolved circular dependency scenario
- Example - Class A requires an instance of class B, and class B requires an instance of class A through constrcutor injection. If you configure beans of A & B to be injected into each other , spring container detects this circular reference at runtime & throws ***BeanCurrentlyInCreationException***.
- Solution is to use Setter based injection for one of the classes, or alternatively, for both classes. Although this is not recommended. In this case, one of the beans will have to be injected into the other prior to being fully initialised itself (chicken/egg scenario)
- More on this -> https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-constructor-injection

## Using Bean lifecycle callbacks

- One way to create lifecycle methods like init & destroy is to add them in the beans.xml itself.
- Example:
  ```xml
  <bean id="game" class="com.ankit.GameImpl" init-method="reset">
    <property name="numberGenerator" ref="numberGenerator"/>
  </bean>
  ```
- To make sure this works, we need to maintain this configuration in xml all the time. This is difficult to manage. If we plan to use the same method name called `reset` as the init method for all beans, the following change can be done.
  ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://www.springframework.org/schema/beans
            https://www.springframework.org/schema/beans/spring-beans.xsd" default-init-method="reset">
        <bean id="numberGenerator" class="com.ankit.NumberGeneratorImpl" />
        <bean id="game" class="com.ankit.GameImpl">
            <property name="numberGenerator" ref="numberGenerator"/>
        </bean>
    </beans>
  ```
- A better way is to use jsr250 `PostConstruct` and `PreDestroy` Annotations. -> Best practice to work with lifecycle callbacks.
  - To use this bean, it has to be defined first (by just defining the class in a bean definition), which is done as follows
    ```xml
    <bean class="org.springframework.context.annotation.CommonAnnotationBeanPostProcessor"/>
    ```
  - Adding PostProcessor bean as an Annotation requires adding a dependency in pom first
    ```xml
    <dependency>
      <groupId>javax.annotation</groupId>
      <artifactId>javax.annotation-api</artifactId>
      <version>1.3.2</version>
    </dependency>
    ```
  
## Annotation vs. XML Configuration
  
### XML Based Configuration

#### Pros
  
- XML Configuration is outside the Java Classes -> Addresses the separation of concerns
- Whole Configuration is limited to a few files only. If it needs to be changed, then code does not need to go through the process of re-compilation, since its only XML
- It is more verbose and more understandable to beginners

#### Cons

- XML typing can be prone to errors and is difficult to debug. Error free manual typing is unavoidable even in tools like IntelliJ
- XML is not type safe. Java code via the compiler will validate the types while compiling, and throw errors if you try to assign wrong type to a variable. This also applies to method arguments, since you are passing explicit types to them
- XML configuration misses the features of Java Language, requiring all kinds of ugly constructs to do what can be done by simple Java code. (Injecting a bean as a dependency, but missing to define it as a bean -> In Java, exception would be thrown)

### Annotations Based Configuration

#### Pros

- Shorter and more concise configurations
- Some developers prefer to have their dependency wiring closer to the source & prefer annotations over xml
- Type Safety is available. It can also self document a class, so that you can quickly look in the class & see what is being injected by Spring

#### Cons

- Annotations reside in the code. All metadata is strewn all throughout the code base. Leads to less control over configurations
- Annotations clutter POJOs. (Once annotations are added, POJOs are not POJOs anymore)
- Any change requires re-compilation
- Less intuitive in nature because of their brevity especially for new developers of Spring

### Combining best of both worlds

- Using `@Configuration` annotation is recommended. Using this we can build Java based configuration classes where we can centralize most of our annotation configuration. Also provides the type safety

## Autowiring Beans

Changes to make use of Autowiring -
- Add a namespace `context` in the beans.xml and use it to define a bean below in the xml like shown in branch 002-Annotation-Based-Config branch of this repo.
  ```xml
  <context:annotation-config/>
  ```
  This defines a bean called `org.springframework.context.annotation.internalCommonAnnotationProcessor`
- Remove `CommonAnnotationBeanPostProcessor` from the beans definition and also remove the setter based injection from game bean.
- Add Autowired annotation for the declaration of `NumberGenerator` object
