package demo;

public class Wait extends Thread {
	@Override
	public void run() {
		System.out.println("start");
		// 1.取得当前对象的monitor（监视器/锁）
		synchronized (this) {
			try {
				// 2.阻塞当前线程，释放当前对象的monitor
				this.wait();
				System.out.println("start run");
			} catch (InterruptedException e) {
				e.printStackTrace(); // notify won't throw exception
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Wait();
		// 线程启动
		thread.start();
		try {
			sleep(2000);
		} catch (InterruptedException e) {
		}
		// 3.取得thread对象的monitor（监视器/锁）
		synchronized (thread) {
			System.out.println("Wait() will release the lock!");
			// 4.唤醒thread对象，使这个对象重新获得thread对象的monitor
			thread.notify();
		}
	}
}
