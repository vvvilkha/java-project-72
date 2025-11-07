package hexlet.code;

import hexlet.code.model.Url;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlRepository;
import io.javalin.Javalin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

import hexlet.code.util.NamedRoutes;
import io.javalin.testtools.JavalinTest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;


class TestApp {
    Javalin app;
    public static MockWebServer mockWebServer;

    public static String readFixture(String fileName) throws IOException {
        Path filePath = Paths.get("src/test/resources", fileName);
        return new String(Files.readAllBytes(filePath));
    }

    @BeforeEach
    public final void setUp() throws IOException, SQLException {
        mockWebServer = new MockWebServer();
        MockResponse mockedResponse = new MockResponse()
                .setBody(readFixture("index.html"));
        mockWebServer.enqueue(mockedResponse);
        mockWebServer.start();

        app = App.getApp();

        UrlRepository.clear();
    }

    @AfterAll
    public static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Анализатор страниц");
        });
    }

    @Test
    public void testUrlPage() throws SQLException {
        var url = new Url("https://rutube.ru");
        UrlRepository.save(url);
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls/" + url.getId());
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    public void testCreateUrl() {
        JavalinTest.test(app, (server, client) -> {
            var requestedBody = "url=https://rutube.ru";
            var response = client.post("/urls", requestedBody);
            var url = UrlRepository.findByName("https://rutube.ru");
            assertThat(url.get().getName()).isEqualTo("https://rutube.ru");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("https://rutube.ru");
        });
    }

    @Test
    public void testCheck() throws Exception {
        String mockUrl = mockWebServer.url("/").toString();

        Url url = new Url(mockUrl);
        UrlRepository.save(url);

        Long savedUrlId = UrlRepository.findByName(mockUrl)
                .orElseThrow()
                .getId();

        JavalinTest.test(app, (server, client) -> {
            var postResp = client.post(NamedRoutes.checksPath(savedUrlId));
            assertThat(postResp.code()).isEqualTo(200);

            var getResp = client.get(NamedRoutes.urlPath(savedUrlId));
            assertThat(getResp.code()).isEqualTo(200);

            var html = getResp.body().string();
            assertThat(html).contains("Example Domain");

            var lastCheckOpt = UrlCheckRepository.getLastUrlCheck(savedUrlId);
            assertThat(lastCheckOpt).isPresent();

            var lastCheck = lastCheckOpt.get();
            assertThat(lastCheck.getStatusCode()).isEqualTo(200);
            assertThat(lastCheck.getTitle()).isEqualTo("Example Domain");
            assertThat(lastCheck.getH1()).isEqualTo("Example Domain");
            assertThat(lastCheck.getDescription()).isNotBlank();
            assertThat(lastCheck.getDescription()).contains("");
        });
    }

    @Test
    public void testAllUrls() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.urlsPath());
            assertThat(response.code()).isEqualTo(200);
        });
    }
}
