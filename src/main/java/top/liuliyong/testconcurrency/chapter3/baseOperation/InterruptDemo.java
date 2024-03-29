package top.liuliyong.testconcurrency.chapter3.baseOperation;

/**
 * @Author: Liyong.liu
 * @Date: 2019/11/15 下午8:39
 */
public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (isInterrupted()) {
                        System.out.println("Interrupted!");
                        break;
                    }
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted While Sleeping");
                        //设置中断状态,重点!!!!!
                        interrupt();
                    }
                    yield();
                }
            }
        };
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }
}
