package trickle.jdk8.concurrent;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * <p>
 * Title: Futures
 * <p>
 * Description: CompletableFuture demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class Futures {

    private static final Logger GLOBAL = Logger.getGlobal();

    /**
     * 使用普通Future类型的示例
     * <p>
     * 在执行接下来的操作之前要调用阻塞的get方法来获取当前的结果
     */
    private static void future() throws Exception {
        ExecutorService pool = Executors.newCachedThreadPool();
        Future<List<String>> f = pool.submit(() -> {
            return readLines();
        });
        // 一定要调用了get方法之后，才能将结果用于接下来的操作
        List<String> list = f.get();
        int maxLineLength = maxLineLength(list);
        final int length = maxLineLength;
        GLOBAL.info(() -> "future: " + String.valueOf(length));
        pool.shutdownNow();
    }

    /**
     * key.1. CompletableFuture可以接受回调函数作为参数，从而实现各个步骤逐步完成，前一步结果可用时后一步开始执行的效果
     */
    private static void completableFuture() throws Exception {
        ExecutorService pool = Executors.newCachedThreadPool();
        @SuppressWarnings("unused")
        CompletableFuture<Void> completable = CompletableFuture.supplyAsync(() -> readLines(), pool).thenApply(x -> maxLineLength(x))
            .thenApply(String::valueOf).thenAccept(GLOBAL::info);
        pool.shutdownNow();
        pool.awaitTermination(60, TimeUnit.SECONDS);
    }

    /**
     * 辅助函数：读取文件所有行
     */
    private static List<String> readLines() {
        List<String> lines = new ArrayList<>();
        try {
            Path path = Paths.get("./src/main/java/trickle/jdk8/concurrent/Futures.java");
            lines = Files.readAllLines(path);
        }
        catch (Exception e) {
            // 此处略去异常处理逻辑
            GLOBAL.severe(e.getMessage());
        }
        return lines;
    }

    /**
     * 辅助函数：求行长度最大值
     */
    private static int maxLineLength(List<String> list) {
        int maxLineLength = 0;
        OptionalInt max = list.stream().mapToInt(String::length).max();
        if (max.isPresent()) {
            maxLineLength = max.getAsInt();
        }
        return maxLineLength;
    }

    public static void main(String[] args) {
        try {
            future();
            completableFuture();
        }
        catch (Exception e) {
            // 此处略去异常处理逻辑
            GLOBAL.severe(e.getMessage());
        }
    }

}
