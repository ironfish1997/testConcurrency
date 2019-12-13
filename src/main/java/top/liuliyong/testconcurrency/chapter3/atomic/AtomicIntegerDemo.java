package top.liuliyong.testconcurrency.chapter3.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Liyong.liu
 * @Date: 2019/11/12 上午10:56
 */
public class AtomicIntegerDemo {
    static AtomicInteger i = new AtomicInteger();

    public static class AddThread implements Runnable {

        @Override
        public void run() {
            for (int k = 0; k < 10000; k++) {
                i.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] ts = new Thread[10];
        for (int k = 0; k < 10; k++) {
            ts[k] = new Thread(new AddThread());
        }
        for (Thread k : ts) {
            k.start();
        }
        for (Thread k : ts) {
            k.join();
        }
        System.out.println(i.get());
    }
}
