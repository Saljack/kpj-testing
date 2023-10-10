package cz.inventi.kpj.kpjtesting;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

/**
 * 1. Create Spy on the {@link KpjTesting#helloWorldService} field <br/>
 * 2. Add JUnit annotations <br/>
 * 3. Verify the {@link HelloWorldServiceImpl#helloWorld()} is called <br/>
 * 4. Change behavior of {@link HelloWorldServiceImpl#echo(String)} to always
 * returns "KPJ Rulez" <br/>
 */
@SpringBootTest
@MockitoSettings(strictness = Strictness.LENIENT)
public class KpjTestingTest {
  @Spy
  HelloWorldService helloWorldService = new HelloWorldServiceImpl();

  @InjectMocks
  KpjTesting kpjTesting;

  @BeforeEach
  void setUp() {
    // Change behavior of HelloWorldServiceImpl#echo(String)
    // to always return "KPJ Rulez"
    doReturn("KPJ Rulez").when(helloWorldService.echo("Print ECHO"));
  }
@Test
  void testPrintHelloWorld() {
    // no given
    // when
    kpjTesting.printHelloWorld();

    // then
    // verify the HelloWorldServiceImpl#helloWorld() is called
  verify(helloWorldService.helloWorld(), times(1));
  }
@Test
  void testPrintEcho() {
    // no given
    // when
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