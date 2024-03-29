package demo;

public class InterruptWait extends Thread {
	public static Object lock = new Object();

	@Override
	public void run() {
		System.out.println("start");
		synchronized (lock) {
			try {
				lock.wait();
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().isInterrupted());
				Thread.currentThread().interrupt(); // set interrupt flag again
				System.out.println(Thread.currentThread().isInterrupted());
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Thread thread = new InterruptWait();
		thread.start();
		try {
			sleep(2000);
		} catch (InterruptedException e) {
		}
		thread.interrupt();
		lock.notify();
	}
}
