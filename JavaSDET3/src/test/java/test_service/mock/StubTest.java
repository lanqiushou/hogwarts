
package test_service.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class StubTest {

  private static WireMockServer wireMockServer;

  @BeforeAll
  static void beforeAll() {
    //No-args constructor will start on port 8080, no HTTPS //extensions(ExampleTransformer.class)
    wireMockServer = new WireMockServer(wireMockConfig().port(8080));
    wireMockServer.start();
//    configureFor("localhost", 8089);
//    wireMockServer = new WireMockServer(options().port(8089));

  }

  @Test
  void stub() throws InterruptedException {
    stubFor(get(urlEqualTo("/user/d"))
            .withHeader("Accept", equalTo("text/xml"))
            .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "text/xml")
                    .withBody("<response>d info</response>")));

    //todo: use
    Thread.sleep(100000);
  }

  @Test
  void mockOnStub() throws InterruptedException {
    stubFor(get(urlEqualTo("/user/d"))
            .withHeader("Accept", equalTo("text/xml"))
            .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "text/xml")
                    .withBody("<response>d info</response>")));

    //todo: use
    Thread.sleep(10000);

    reset();
    stubFor(get(urlEqualTo("/user/d"))
            .withHeader("Accept", equalTo("text/xml"))
            .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "text/xml")
                    .withBody("<response>exception</response>")));

    Thread.sleep(10000);
  }

  @Test
  void proxy() throws InterruptedException {
    stubFor(
            get(urlMatching(".*"))
                    .atPriority(10)
                    .willReturn(aResponse().proxiedFrom("https://ceshiren.com")));
    Thread.sleep(100000);
  }


  @Test
  void mockOnProxyLocal() throws InterruptedException, IOException {
    stubFor(
            get(urlMatching(".*"))
                    .atPriority(10)
                    .willReturn(aResponse().proxiedFrom("https://ceshiren.com")));

    stubFor(
            get(urlMatching("/categories_and_latest"))
                    .atPriority(1) //"/Users/seveniruby/projects/Java3/src/main/resources/mock_local.json"
                    .willReturn(aResponse().withBody(Files.readAllBytes(Paths.get("/Users/tengjb/git/JavaSDET3/src/main/resources/mock_local.json"))))
    );
    Thread.sleep(100000);
  }

  @Test
  void mockOnProxy() throws InterruptedException, IOException {
    stubFor(
            get(urlMatching(".*"))
                    .atPriority(10)
                    .willReturn(aResponse().proxiedFrom("https://ceshiren.com").withTransformers("ExampleTransformer")));

    stubFor(
            get(urlMatching("/categories_and_latest"))
                    .atPriority(1) //"/Users/seveniruby/projects/Java3/src/main/resources/mock_local.json"
                    .willReturn(aResponse().withBody(Files.readAllBytes(Paths.get("/Users/tengjb/git/JavaSDET3/src/main/resources/mock_local.json"))))
    );
    Thread.sleep(100000);
  }
}