# SLF4J mock
[![Build Status](https://travis-ci.com/s4u/slf4j-mock.svg?branch=master)](https://travis-ci.com/s4u/slf4j-mock)
[![verify](https://github.com/s4u/slf4j-mock/workflows/verify/badge.svg)](https://github.com/s4u/slf4j-mock/actions?query=workflow%3Averify)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.simplify4u/slf4j-mock/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.simplify4u/slf4j-mock)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=org.simplify4u%3Aslf4j-mock&metric=alert_status)](https://sonarcloud.io/dashboard?id=org.simplify4u%3Aslf4j-mock)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=org.simplify4u%3Aslf4j-mock&metric=ncloc)](https://sonarcloud.io/dashboard?id=org.simplify4u%3Aslf4j-mock)

Yet another way to testing logging in application which use SLF4J.

## Features
 - this binding create `Mockito` mock for each `Logger`
 - all call to `Logger` method are delegated to instance of `SimpleLogger`,
   so you can create standard `simplelogger.properties` 
 - light transitive dependencies - only `slf4j-api` and `mockito-core`
 - ease use
 
## Example of usage

Add dependency to your project:

    <dependencies>
        <dependency>
            <groupId>org.simplify4u</groupId>
            <artifactId>slf4j-mock</artifactId>
            <version><!-- check relases page --><version>  
            <scope>test</scope>        
        </dependency>
    <dependencies>

Please remember, that you can only have one `SLF4J` binding on classpath,
in the most case you must replace `org.slf4j:slf4j-simple` by `org.simplify4u:slf4j-mock`.

Usually Logger are created once on start by static reference,
so we must clear invocation on created mocks before each test:
 
    @Before
    public void setup() {
        LoggerMock.clearInvocations();
    }

write test:

    @Test
    public void logInfoShouldBeLogged() {

        // when
        sut.methodWithLogInfo(INFO_TEST_MESSAGE);

        // then
        Logger logger = LoggerMock.getLoggerMock(Example.class);
        verify(logger).info(INFO_TEST_MESSAGE);
        verifyNoMoreInteractions(logger);
    }
