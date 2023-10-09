package cz.inventi.kpj.kpjtesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 *
 * 1. Create Spring Boot Test (Integration test with the application context
 * loading) <br/>
 * 2. Provide mock instance of {@link HelloWorldService} <br/>
 * 2.1. {@link HelloWorldService#helloWorld()} return "Hello World"<br/>
 * 2.2. {@link HelloWorldService#echo(String) } returns "KPJ echo" for all
 * inputs<br/>
 * 3. Add asserts and JUnit annotations<br/>
 * 3.1. Verify the {@link HelloWorldService#helloWorld()} was called<br/>
 * 3.2. Assert the {@link KpjTesting#printEcho(String)} returns "KPJ echo"<br/>
 */

@SpringBootTest()
class KpjTestingApplicationTests {

  @MockBean
  HelloWorldService helloWorldService;

  @Autowired
  KpjTesting kpjTesting;

  @Autowired
  KpjTestingApplication kpjTestingApplication;

  @Test
  void testHelloWorld() {
    // given
    when(helloWorldService.helloWorld()).thenReturn("Hello World");
    // Setup mock

    // when
   kpjTesting.printHelloWorld();

    // then
    verify(helloWorldService, times(1)).helloWorld();
    // Verify the HelloWorldService#helloWorld() was called

  }

  @Test
  void testPrintEcho() {
    // given
    when(helloWorldService.echo(anyString())).thenReturn("KPJ echo");
    // setup mock

    // when
    String result = kpjTesting.printEcho("KPJ echo");

    // then
    assertEquals("KPJ echo", result);
    // Add assert the result is equals to "KPJ echo"

  }

}
