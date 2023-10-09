package cz.inventi.kpj.kpjtesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.quality.Strictness.LENIENT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoSettings;

/**
 * 1. Create Spy on the {@link KpjTesting#helloWorldService} field <br/>
 * 2. Add JUnit annotations <br/>
 * 3. Verify the {@link HelloWorldServiceImpl#helloWorld()} is called <br/>
 * 4. Change behavior of {@link HelloWorldServiceImpl#echo(String)} to always
 * returns "KPJ Rulez" <br/>
 */
@MockitoSettings(strictness = LENIENT)
public class KpjTestingTest {

    @Spy
    HelloWorldService helloWorldService;

    @InjectMocks
    KpjTesting kpjTesting;

    @BeforeEach
    void setUp() {
        // setup mocks for all tests
      doReturn("Hello World").when(helloWorldService).helloWorld();
      doReturn("KPJ Rulez").when(helloWorldService).echo(anyString());
        // hint you have to use doReturn for Spy
        // Change behavior of HelloWorldServiceImpl#echo(String)
        // to always returns "KPJ Rulez"

    }

    @Test
    void testPrintHelloWorld() {
        // no given
        // when
        kpjTesting.printHelloWorld();

        // then
        verify(helloWorldService, times(1)).helloWorld();
        // verify the HelloWorldServiceImpl#helloWorld() is called

    }

    @Test
    void testPrintEcho() {
        // no given
        // when
        String result = kpjTesting.printEcho("Print ECHO");

        // then
        assertEquals("KPJ Rulez", result);
        // add assert the result equals "KPJ Rulez"

    }

    static class HelloWorldServiceImpl implements HelloWorldService {

        @Override
        public String helloWorld() {
            return "Hello world";
        }

        @Override
        public String echo(String echo) {
            return echo;
        }

    }

}