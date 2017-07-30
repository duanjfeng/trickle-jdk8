package trickle.jdk8.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

/**
 * <p>
 * Title: Formatters
 * <p>
 * Description: Time formatter demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class Formatters {

    private static final Logger GLOBAL = Logger.getGlobal();

    /**
     * key.1. 格式化
     */
    private static void format() {

        String formattedNow = DateTimeFormatter.ISO_INSTANT.format(Instant.now());
        GLOBAL.info(() -> "formattedNow: " + formattedNow.toString());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow2 = formatter.format(LocalDateTime.now());
        GLOBAL.info(() -> "formattedNow 2: " + formattedNow2.toString());

    }

    /**
     * key.2. 解析
     */
    private static void parse() {

        Instant parsed = DateTimeFormatter.ISO_INSTANT.parse("2007-07-15T15:11:37.260Z", Instant::from);
        GLOBAL.info(() -> "parsed: " + parsed.toString());

        LocalDateTime parsed2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").parse("2007-07-15 23:12:38", LocalDateTime::from);
        GLOBAL.info(() -> "parsed 2: " + parsed2.toString());

        LocalDateTime parsed3 = LocalDateTime.parse("2007-07-15 23:12:38", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        GLOBAL.info(() -> "parsed 3: " + parsed3.toString());
    }

    public static void main(String[] args) {
        format();
        parse();
    }

}
