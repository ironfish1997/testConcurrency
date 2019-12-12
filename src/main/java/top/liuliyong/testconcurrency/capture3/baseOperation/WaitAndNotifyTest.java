package top.liuliyong.testconcurrency.capture3.baseOperation;

/**
 * 测试wait和notify的使用方法
 *
 * @Author: Liyong.liu
 * @Date: 2019/12/11
 */
public class WaitAndNotifyTest {
    private static final Object lock = new Object();

    static class TestWaitThread implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                int i = 100;
                while (i-- >= 0) {
                    try {
                        System.out.println("TestLockThread start");
                        lock.wait();
                        System.out.println("TestLockThread end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class TestNotifyThread implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("TestNotifyThread start");
                lock.notify();
                System.out.println("TestNotifyThread end");
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new TestWaitThread()).start();
        new Thread(new TestNotifyThread()).start();
    }
}
