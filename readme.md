# Java 9 workshop

## General

In lack of a working build tool the root of this project contains a few tasks
that can perform common build tasks.

### ./clean
Will delete contents in ./mods and ./mlib directories.

### ./build
Will compile all '*.java' files within ./src to modules in ./mods

### ./package
Will bundle the compiled code in ./mods into jars

### ./run
Will execute 'com.greetings' module.

### ./clean && ./build && ./package && ./run
Will run all build steps

## Jigsaw

Modules in java, without any 3rd party tools. 

### com.socket
Module with a service definition (API) but no implementation. Will use a ServiceLoader to find implementations at run-time.

### org.fastsocket
Module with implementation of service defined in com.socket

### org.astro
Module with a single class that is exported.

### com.greetings
Module that uses com.socket, and runtime supplied org.fastsocket

Ref: [Jigsaw quick-start](http://openjdk.java.net/projects/jigsaw/quick-start)

# Workshop
##Jigsaw

### Tips for setup
#### Suggested directory structure
- mods
  - x # compiled classes for x
  - y # compiled classes for y
- mlib
  - x.jar
  - y.jar
- src
  - x
    - x
      - Main.java
  - y
    - y
      - TextHolder.java      

#### Build
```sh
javac -d mods --module-source-path src $(find src -name "*.java")

jar\
 -c\
 -f mlib/y@1.0.jar\
 --module-version=1.0\
 -C mods/y .

jar\
 -c\
 -f mlib/x.jar\
 -e x.Main\
 -C mods/x .
```

#### Run 
```sh
java -p mlib -m x
```

### Get started 

- Define a module **x** with module definition and single class with main method
```java
module x {
  
}
```
```java
package x;

public class Main { 
  public static void main() {
    System.out.println("Hello from module x");
  } 
} 
```
- Define a module **y** with module definition single class and static method that returns some string
```java
module y {
  
}
```
```java
package y;

public interface TextHolder { 
  static String text() {
    return "Hello from module y";
  } 
} 
```
- Make module **y** export its only package
```java
module y {
  export y; 
}
```
- Make module **x** require module **y**
```java
module x {
  require y; 
}
```
- Use module **y**’s string method from module **x**
```java
System.out.println(TextHolder.text());
```
- Remove export and see that build fails
  - Add it back
- Remove require and see that build fails
  - Add it back
- Produce module jars

## HttpClient

Use the new http client to consume a web service with no 3rd party libraries.

Suggestion: Create command line application that consume brings API for looking up postal numbers. Ie. user type postal code application outputs location name.
**https://api.bring.com/shippingguide/api/postalCode.json?clientUrl=INSERT_SOME_URL&pnr=0001**



           

