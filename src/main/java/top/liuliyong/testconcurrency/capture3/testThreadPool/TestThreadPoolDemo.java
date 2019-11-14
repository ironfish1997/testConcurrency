package top.liuliyong.testconcurrency.capture3.testThreadPool;

import java.util.concurrent.*;

/**
 * @Author: Liyong.liu
 * @Date: 2019/11/14 下午3:34
 */
public class TestThreadPoolDemo {
    public static void main(String[] args) {
        new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10)).execute(() -> {
            int s = 10 / 0;
        });
    }
}
