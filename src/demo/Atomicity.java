package demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 确定i++不是原子性的代码
 */
public class Atomicity {

	private static volatile int nonAtomicCounter = 0;
	private static volatile AtomicInteger atomicCounter = new AtomicInteger(0);
	private static int times = 0;

	public static void caculate() {
		times++;
		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					nonAtomicCounter++;
					atomicCounter.incrementAndGet();
				}
			}).start();
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
	}

	public static void main(String[] args) {
		caculate();
		while (nonAtomicCounter == 1000) {
			nonAtomicCounter = 0;
			atomicCounter.set(0);
			caculate();
		}

		System.out.println("Non-atomic counter: " + times + ":"
				+ nonAtomicCounter);
		System.out.println("Atomic counter: " + times + ":" + atomicCounter);
	}
}