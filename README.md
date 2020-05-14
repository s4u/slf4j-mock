# SLF4J mock

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

Please remember, that you can only have one SLF4J binding on classpath.

Usually Logger are created once on start by static reference,
so we must clear invocation on created mocks before each test:
 
    @Before
    public void setup() {
        LoggerMock.clearInvocations();
    }

And write test:

    @Test
    public void logInfoShouldBeLogged() {

        // when
        sut.methodWithLogInfo(INFO_TEST_MESSAGE);

        // then
        Logger logger = LoggerMock.getLoggerMock(Example.class);
        verify(logger).info(INFO_TEST_MESSAGE);
        verifyNoMoreInteractions(logger);
    }
