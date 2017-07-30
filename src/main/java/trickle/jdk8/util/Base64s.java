package trickle.jdk8.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.logging.Logger;

/**
 * <p>
 * Title: Base64s
 * <p>
 * Description: java.util.Base64 demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class Base64s {

    private static final Logger GLOBAL = Logger.getGlobal();

    private static final String PLAIN_TXT = "http://docs.oracle.com/javase/8/docs/api/java/util/Base64.html";

    /**
     * key.1. 标准base64算法
     */
    private static void standardBase64() {

        // Instances of Encoder class are safe for use by multiple concurrent threads.
        Encoder encoder = Base64.getEncoder();
        String encoded = encoder.encodeToString(PLAIN_TXT.getBytes(StandardCharsets.UTF_8));
        Decoder decoder = Base64.getDecoder();
        String decoded = new String(decoder.decode(encoded), StandardCharsets.UTF_8);

        GLOBAL.info(String.join(":", "Encoded", encoded));
        GLOBAL.info(String.join(":", "Decoded", decoded.toString()));
    }

    /**
     * key.2. url base64
     */
    private static void urlBase64() {

        // Instances of Encoder class are safe for use by multiple concurrent threads.
        Encoder encoder = Base64.getUrlEncoder();
        String encoded = encoder.encodeToString(PLAIN_TXT.getBytes(StandardCharsets.UTF_8));
        Decoder decoder = Base64.getUrlDecoder();
        String decoded = new String(decoder.decode(encoded), StandardCharsets.UTF_8);

        GLOBAL.info(String.join(":", "Encoded", encoded));
        GLOBAL.info(String.join(":", "Decoded", decoded.toString()));
    }

    /**
     * key.3. mime base64
     */
    private static void mimeBase64() {

        // Instances of Encoder class are safe for use by multiple concurrent threads.
        Encoder encoder = Base64.getMimeEncoder();
        String encoded = encoder.encodeToString(PLAIN_TXT.getBytes(StandardCharsets.UTF_8));
        Decoder decoder = Base64.getMimeDecoder();
        String decoded = new String(decoder.decode(encoded), StandardCharsets.UTF_8);

        GLOBAL.info(String.join(":", "Encoded", encoded));
        GLOBAL.info(String.join(":", "Decoded", decoded.toString()));
    }

    public static void main(String[] args) {
        standardBase64();
        urlBase64();
        mimeBase64();
    }

}
