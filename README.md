# SLF4J mock
[![Build](https://github.com/s4u/slf4j-mock/workflows/Build/badge.svg)](https://github.com/s4u/slf4j-mock/actions?query=workflow%3ABuild)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.simplify4u/slf4j-mock/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.simplify4u/slf4j-mock)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=org.simplify4u%3Aslf4j-mock-parent&metric=alert_status)](https://sonarcloud.io/dashboard?id=org.simplify4u%3Aslf4j-mock-parent)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=org.simplify4u%3Aslf4j-mock-parent&metric=ncloc)](https://sonarcloud.io/dashboard?id=org.simplify4u%3Aslf4j-mock-parent)

Yet another way to testing logging in application which use SLF4J.

## Features
 - this binding support for easy create `Mockito` mock for `Logger`
 - call to `Logger` can be delegated to instance of `SimpleLogger`,
   so we can create standard `simplelogger.properties` 
 - support for testing and mocking `MDC`
 - light transitive dependencies - only `slf4j-api` and `mockito-core`
 - support testing in parallel in multi thread
 - all the Magic are done by `Mockito` plugins, so you don't need to directly use class from this library
 - ease use
 
## Example of usage

Add dependency to your project:

For `SLF4J 1.7.x` 

    <dependencies>
        <dependency>
            <groupId>org.simplify4u</groupId>
            <artifactId>slf4j-mock</artifactId>
            <version><!-- check relases page --></version>  
            <scope>test</scope>        
        </dependency>
    <dependencies>

For `SLF4J 2.x`

    <dependencies>
        <dependency>
            <groupId>org.simplify4u</groupId>
            <artifactId>slf4j2-mock</artifactId>
            <version><!-- check relases page --></version>  
            <scope>test</scope>        
        </dependency>
    <dependencies>

Please remember, that you can only have one `SLF4J` binding or provider on classpath,
in the most case you must replace `org.slf4j:slf4j-simple` by `org.simplify4u:slf4j-mock`.

Write test:

    class MyTest {

        @Mock
        Logger logger;
    
        @InjectMocks
        Example sut;

        @Test
        public void logInfoShouldBeLogged() {
    
            // when
            sut.methodWithLogInfo(INFO_TEST_MESSAGE);
    
            // then
            verify(logger).info(INFO_TEST_MESSAGE);
            verifyNoMoreInteractions(logger);
        }
    }

# Project homepage

More information and examples you can find on site:

https://www.simplify4u.org/slf4j-mock/
