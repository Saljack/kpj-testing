package cz.inventi.kpj.kpjtesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;



/**
 * 1. Create Spy on the {@link KpjTesting# helloWorldService} field <br/>
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

    kpjTesting = new KpjTesting(helloWorldService);
    doReturn("Hello World").when(helloWorldService).helloWorld();
    doReturn("KPJ Rulez").when(helloWorldService).echo(anyString());

  }
  @Test
  void testPrintHelloWorld() {

    kpjTesting.printHelloWorld();
    verify(helloWorldService).helloWorld();

  }
  @Test
  void testPrintEcho() {

    String result = kpjTesting.printEcho("Print ECHO");
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