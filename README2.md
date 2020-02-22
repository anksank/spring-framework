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

## Setup Maven war plugin

- war -> Web-Application Archive
- jar -> Java-Application Archive
- war files contains the resources necessary for developing web application -> [war plugin](http://maven.apache.org/plugins/maven-war-plugin/)
- When we enable the war package, war plugin is added to the project automatically
- Below picture shows the project structure

<img width="450" alt="Screenshot 2020-02-21 at 9 48 18 PM" src="https://user-images.githubusercontent.com/10058009/75051550-176d1680-54f4-11ea-8a96-1ca62780e958.png">

- web.xml is a deployment descriptor of the servlet based java web application. It declares which servlet exists and which URLs are handled. This declaration can also be achieved by Annotations. (Hence, web.xml is not needed. But without the xml the maven war plugin will fail)
- WEB-INF directory is not part of the public document tree of the application (cannot be served directly to the client by the container, although the contents are visible to the servlet)
- Default maven war plugin that gets added is 2.2.0
- Following configuration can be set if web.xml is not used in the project.
  ```xml
  <plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-war-plugin</artifactId>
    <version>3.2.3</version>
    <configuration>
      <failOnMissingWebXml>false</failOnMissingWebXml>
    </configuration>
  </plugin>
  ```

## Maven Cargo plugin and tomcat

- Using maven cargo plugin, we can manipulate war projects within apache tomcat servlet container using goals, which means tomcat can be run in embedded mode.
- Spring boot does the same thing, where again we use embedded tomcat
- Entry in the plugin section looks like below -
  ```xml
  <plugin>
    <groupId>org.codehaus.cargo</groupId>
    <artifactId>cargo-maven2-plugin</artifactId>
    <version>1.7.10</version>
    <configuration>
        <container>
            <containerId>tomcat9x</containerId>
            <type>embedded</type>
        </container>
    </configuration>
  </plugin>
  ```
- After making this change, under the plugin option of the project, you can see an option called `cargo`, under which there will be `cargo:run` which deploys the content to the container and runs the tomcat.
- `localhost:8080/todo-list/index.html`

## Setup Dispatcher Servlet

- Refer branch: (011-Web-Dispatcher-Servlet)[https://github.com/anksank/spring-framework/tree/011-Web-Dispatcher-Servlet]
- Spring MVC Dispatcher servlet is the front controller of spring MVC and is used to dispatch http requests to other controllers.
- 2 ways to configure dispatcher servlet in container:
  - web.xml
  - Annotations (using Java code) (since the release of version 3)
- Create a new configuration class, `WebConfig` and use `@EnableWebMvc` annotation, which enables the web mvc configuration. It registers the beans specific to spring mvc. (ex: ViewResolver, RequestMapper, etc)
- Next step would be to configure the web dispatcher servlet which uses the container and the WebConfig class, for which the servlet API dependency needs to be added in the pom.
  ```xml
  <dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>${servlet-api.version}</version>
    <scope>provided</scope>
  </dependency>
  ```
  - scope -> provided means that this dependency is provided by the servlet api, and is not packaged in the war. Saves time when uploading file in remote server. In current case, tomcat provides this dependency.
  - Next step is to intialize the context -> by implementing the `WebApplicationInitializer` interface. This implementation is automatically detected upon startup of the spring application. We need to create the WebApplicationContext (Spring Container) and also we need to create the `DispatcherServlet` using the servlet context.
    ```java
    // create the spring application context
    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    
    // registering the configuration class to the context
    context.register(WebConfig.class);
    ```
  - `ServletContext` uses a set of method that it uses to communicate with the servlet container (tomcat in our case)
  - DispatcherServlet is created next where in we need to pass the web application context, which was created above.
    ```java
    // create the dispatcher servlet
    DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
    ```
  - To register and configure the servlet, we use the ServletRegistration class to register the servlet dynamically. Before that, we need to name the servlet.
  - `servletContext` parameter that was received from the onStartup method is used to call the method `addServlet()` to do the registration
    ```java
    // register and configure the dispatcher servlet
    ServletRegistration.Dynamic registration = servletContext.addServlet(DISPATCHER_SERVLET_NAME, dispatcherServlet);
    
    // container instantiates and configures the servlet
    registration.setLoadOnStartup(1);

    // maps the dispatcher servlet to the url pattern specified in the parameter, which overrides the tomcat homepage
    registration.addMapping("/");
    ```
