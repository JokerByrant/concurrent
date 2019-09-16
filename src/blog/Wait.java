package blog;

/**
 * @author 一池春水倾半城
 * @date 2019/9/12
 */
public class Wait {
    final static Object person = new Object();
    static class T1 extends Thread{
        @Override
        public void run() {
            // 1.取得person对象的monitor（监视器/锁）
            synchronized (person){
                System.out.println("T1 start!");
                try {
                    System.out.println("T1 wait!");
                    // 2.阻塞当前线程，释放person对象的monitor
                    person.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1 end!");
            }
        }
    }

    static class T2 extends Thread{
        @Override
        public void run() {
            // 3.拿到person对象的monitor
            synchronized (person){
                System.out.println("T2 start!");
                // 4.唤醒person对象中被阻塞的线程，使其在当前线程的person对象锁被释放后，重新拿到person对象的monitor
                person.notify();
                System.out.println("T2 end!");
                try {
                    System.out.println("sleep 2 seconds..........");
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new T1();
        Thread t2 = new T2();
        t1.start();
        t2.start();
    }
}
