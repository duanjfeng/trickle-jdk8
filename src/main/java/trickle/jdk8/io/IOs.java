package trickle.jdk8.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * <p>
 * Title: IOs
 * <p>
 * Description: IO enhancements demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class IOs {

    private static final Logger GLOBAL = Logger.getGlobal();

    /**
     * key.1. io增强
     * <p>
     * Files.lines/BufferedReader.lines/Files.list/Files.walk
     */
    private static void io() {
        String filePath = "./src/main/java/trickle/jdk8/io/IOs.java";

        // Files.lines
        // 读取文件流的行
        try (Stream<String> lines = Files.lines(Paths.get(filePath)).onClose(() -> GLOBAL.info("stream closing"))) {
            long count = lines.count();
            GLOBAL.info(() -> "lines count: " + count);
        }
        catch (IOException e) {
            GLOBAL.info(e.getMessage());
        }

        // BufferedReader.lines
        // 读取非文件流的行
        try (InputStream is = new FileInputStream(filePath)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            Stream<String> lines = reader.lines().onClose(() -> GLOBAL.info("stream closing"));
            long count = lines.count();
            GLOBAL.info(() -> "lines count: " + count);
            lines.close();
        }
        catch (IOException e) {
            GLOBAL.info(e.getMessage());
        }

        String dirPath = "./src/main/java/trickle/jdk8/";

        // Files.list
        // 遍历子目录
        try (Stream<Path> paths = Files.list(Paths.get(dirPath)).onClose(() -> GLOBAL.info("stream closing"))) {
            long count = paths.peek(x -> GLOBAL.info(x.toString())).count();
            GLOBAL.info(() -> "paths count: " + count);
        }
        catch (IOException e) {
            GLOBAL.info(e.getMessage());
        }

        // Files.walk
        // 递归遍历子目录
        try (Stream<Path> paths = Files.walk(Paths.get(dirPath)).onClose(() -> GLOBAL.info("stream closing"))) {
            long count = paths.count();
            GLOBAL.info(() -> "paths count 2: " + count);
        }
        catch (IOException e) {
            GLOBAL.info(e.getMessage());
        }
    }

    public static void main(String[] args) {
        io();
    }

}
