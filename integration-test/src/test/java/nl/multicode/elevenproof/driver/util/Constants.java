package nl.multicode.elevenproof.driver.util;

import java.net.URI;
import java.net.URISyntaxException;

public class Constants {

    public static final URI ELEVENPROOF_REST_API_ENDPOINT;

    static {
        try {
            ELEVENPROOF_REST_API_ENDPOINT = new URI(
                    String.format("http://%s:8080", Env.INSTANCE.get("host", "localhost")));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private Constants() {

    }
}
