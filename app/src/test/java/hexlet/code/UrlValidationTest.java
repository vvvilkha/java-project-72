package hexlet.code;

import hexlet.code.controller.UrlCheckController;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class UrlValidationTest {
    @ParameterizedTest
    @ValueSource(strings = {
        "http://example.com",
        "https://example.com",
        "http://localhost:8080",
        "http://127.0.0.1"
    })
    void validUrls(String url) {
        assertThat(UrlCheckController.isValidUrl(url)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "example.com",
        "htp://broken",
        "://missing",
        ""
    })
    void invalidUrls(String url) {
        assertThat(UrlCheckController.isValidUrl(url)).isFalse();
    }
}
