package cz.inventi.kpj.kpjtesting;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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

@SpringBootTest
class KpjTestingApplicationTests {

  @Autowired
  KpjTesting kpjTesting;
  @MockBean
  HelloWorldService helloWorldService;


@Test
  void testHelloWorld() {
    // given
    // Setup mock
  when(helloWorldService.helloWorld()).thenReturn("Hello World");
    // when
    kpjTesting.printHelloWorld();

    // then
    // Verify the HelloWorldService#helloWorld() was called
  verify(helloWorldService, times(1)).helloWorld();
}
@Test
  void testPrintEcho() {
    // given
    // setup mock
  when(helloWorldService.echo(any())).thenReturn("KPJ echo");

    // when
    String result = kpjTesting.printEcho("KPJ echo");

    // then
    // Add assert the result is equals to "KPJ echo"
  assertEquals("KPJ echo", result);
  }

}
