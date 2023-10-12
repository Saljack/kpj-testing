package cz.inventi.kpj.kpjtesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * 1. Create Spy on the {@link KpjTesting#helloWorldService} field <br/>
 * 2. Add JUnit annotations <br/>
 * 3. Verify the {@link HelloWorldServiceImpl#helloWorld()} is called <br/>
 * 4. Change behavior of {@link HelloWorldServiceImpl#echo(String)} to always
 * returns "KPJ Rulez" <br/>
 */
@MockitoSettings(strictness = Strictness.LENIENT)
public class KpjTestingTest {

  @Spy
  HelloWorldService helloWorldService = new HelloWorldServiceImpl();


  KpjTesting kpjTesting;

  @BeforeEach
  void setUp() {
    // setup mocks for all tests
    // hint you have to use doReturn for Spy
    // Change behavior of HelloWorldServiceImpl#echo(String)
    // to always returns "KPJ Rulez"
    helloWorldService = Mockito.mock(HelloWorldService.class);
    kpjTesting = new KpjTesting(helloWorldService);

  }

  @Test
  void testPrintHelloWorld() {
    // no given
    // when
    when(helloWorldService.helloWorld()).thenReturn("Hello World");
    kpjTesting.printHelloWorld();

    // then
    // verify the HelloWorldServiceImpl#helloWorld() is called
    verify(helloWorldService, times(1)).helloWorld();

  }

  @Test
  void testPrintEcho() {
    // no given
    // when
    when(helloWorldService.echo("Print ECHO")).thenReturn("KPJ Rulez");
    String result = kpjTesting.printEcho("Print ECHO");

    // then
    // add assert the result equals "KPJ Rulez"
    assertEquals("KPJ Rulez", result);

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