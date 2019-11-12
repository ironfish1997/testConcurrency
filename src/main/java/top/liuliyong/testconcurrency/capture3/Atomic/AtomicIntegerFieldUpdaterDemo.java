package top.liuliyong.testconcurrency.capture3.Atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Author: Liyong.liu
 * @Date: 2019/11/12 下午3:42
 */
public class AtomicIntegerFieldUpdaterDemo {
    public static class Candidate {
        volatile int score;
    }

    private final static AtomicIntegerFieldUpdater<Candidate> scoreUpdater = AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");
    //检查Updater是否正常工作
    private static AtomicInteger allScore = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        final Candidate stu = new Candidate();
        Thread[] t = new Thread[10000];
        for (int i = 0; i < 10000; i++) {
            t[i] = new Thread(() -> {
                if (Math.random() > 0.4) {
                    scoreUpdater.incrementAndGet(stu);
                    allScore.incrementAndGet();
                }
            });
            t[i].start();
        }
        for (int i = 0; i < 10000; i++) {
            t[i].join();
        }
        System.out.println("score=" + stu.score);
        System.out.println("allScore=" + allScore);
    }
}
