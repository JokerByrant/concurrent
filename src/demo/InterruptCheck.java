package demo;

public class InterruptCheck extends Thread {

	@Override
	public void run() {
		System.out.println("start");
		int i = 1;
		while (true){
			System.out.println(i++);
			if (Thread.currentThread().isInterrupted())
				break;
		}
		System.out.println("while exit");
	}

	public static void main(String[] args) {
		Thread thread = new InterruptCheck();
		thread.start();
		try {
			sleep(500);
		} catch (InterruptedException e) {
		}
		thread.interrupt();
	}
}
