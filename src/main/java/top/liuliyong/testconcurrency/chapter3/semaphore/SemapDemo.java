package top.liuliyong.testconcurrency.chapter3.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author: Liyong.liu
 * @Date: 2019/12/12
 */
public class SemapDemo implements Runnable {
    final Semaphore semaphore = new Semaphore(5);

    @Override

    public void run() {
        try {
            semaphore.acquire();
            //模拟耗时操作
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getId() + ":done!");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(20);
        final SemapDemo demo = new SemapDemo();
        for (int i = 0; i < 20; i++) {
            exec.submit(demo);
        }
    }
}
