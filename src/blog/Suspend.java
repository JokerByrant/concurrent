package blog;

public class Suspend extends Thread {
	@Override
	public void run() {
		synchronized (this) {
			System.out.println("start");
			this.suspend();
			System.out.println("end");
		}
	}

	public static void main(String[] args) {
		Thread thread = new Suspend();
		thread.start();

		try {
			sleep(1000);
		} catch (InterruptedException e) {
		}


		// thread线程仍然被占用着，因此main无法获取到该线程
//		synchronized (thread) {
//			System.out.println("got the lock");
//			thread.resume();
//		}
		// 因为thread对象的锁未被释放，因此我们不需要重新拿到thread对象的锁，可以直接调用resume()方法来释放这个锁
		thread.resume();

	}
}

