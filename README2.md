# Spring Framework (Section 6 & 7)

## Lombok Introduction

- Lombok is a java library that generates boilerplate code.
- In groovy and kotlin, getter and setter methods are generated automatically
- With lombok, we dont have to write **getters**, **setters**, **equals()**, **hashCode()** and **toString()**
- Helps in keeping code cleaner (especially with jpa entities)
- Lombok uses Annotation processor API
- Code is generated during compilation

### @Getter & @Setter

- Default getter returns the field, and is named getName if the field is name (isName if the field's type is boolean)
- Similar way, `@Setter` also works

### Other Annotations

- `@ToString` -> To generate boilerplate code for the toString method
- `@EqualsAndHashCode` -> Generates the equals and hashCode methods
- `@RequiredArgsConstructor` -> Generates a constructor with required arguments
- `@Slf4j` generates private static field for SLF4J logger
- There are other annotations too.

### @Data Annotation

- Generates code for POJO (Plain Old Java Object)
- It combines the @Getter, @Setter, @ToString, @EqualsAndHashCode and @RequiredArgsConstructor

### Example

#### Without Lombok

```java
public class Person {
  private String firstName;
  private String lastName;
  
  public String getFirstName() {
    return firstName;
  }
  
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  public String getLastName() {
    return lastName;
  }
  
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
```

#### With Lombok

```java
public class Person {
  
  @Getter
  @Setter
  private String firstName;
  
  @Getter
  @Setter
  private String lastName;
}
```

#### Another example with lombok

Even if another field is added, it becomes very simple:

```java
@Getter
@Setter
public class Person {

  private String firstName;
  private String lastName;
  private int age;

}
```

## Lombok Setup

- After installing the lombok plugin in intelliJ, it needs to be enabled through the **Annotation Processor** option in the preferences, since lombok is an annotation processor

<img width="1300" alt="Screenshot 2020-02-20 at 6 42 53 PM" src="https://user-images.githubusercontent.com/10058009/74936936-17e0b100-5411-11ea-85f1-711ee2fb7e23.png">

- If an external jar needs to be added as a dependency, create a lib folder in your project, and paste the jar inside it. In case of an external jar, pom.xml dependency looks like below
  ```xml
  <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <scope>system</scope>
      <systemPath>${basedir}/../lib/lombok-edge.jar</systemPath>
  </dependency>
  ```
  
## Spring MVC

- Spring Web MVC -> Original web framework built on the Servlet API
- Its shipped in the spring framework from the very beginning and is called Spring MVC
- Designed around the front-controller pattern (like other web frameworks)
- MVC (design pattern) represents 3 components ->
  - Model: managing application's data, business logic and business rules
  - View: output representation of information
  - Controller: responsible for invoking models to perform business logic and updating the view based on the model's output
- Spring MVC has a central servlet, called **Dispatcher Servlet**, which provides shared algorithm for request processing
- Actual work is performed by the configurable, delegated components (controllers that handle requests)
- `DispatcherServlet` expects a `WebApplicationContext` which is an extension of a plain `ApplicationContext`, for its own configuration
- DispatcherServet delegates to special beans to process requests and render appropriate responses
- With the WebApplicationContext, there are some beans that are registered automatically.
- Different **View** technologies to render web pages - Groovy Markup, Freemarker, & Thymeleaf
- Spring MVC can also integrate with other web frameworks
