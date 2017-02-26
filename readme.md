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
