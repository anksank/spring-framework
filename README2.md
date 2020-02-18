# Spring Framework (Section 6)

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
