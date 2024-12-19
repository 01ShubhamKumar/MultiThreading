package Locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockDemo {
	
private final Lock lockA = new ReentrantLock(true);
private final Lock lockB = new ReentrantLock(true);

public void workerOne () {
	lockA.lock();
	System.out.println("Worker One acquired lockA");
	
	try {
		Thread.sleep(200);
		
	} catch (InterruptedException e) {
		// TODO: handle exception
		throw new RuntimeException(e);
	}
	lockB.lock();
	System.out.println("Worker One acquired lockB");
	lockA.unlock();
	lockB.unlock();
}

public void workerTwo() {
lockB.lock();
System.out.println("Worker Two acquired lockB");
try {
	Thread.sleep(200);
} catch (InterruptedException e) {
	// TODO: handle exception
	throw new RuntimeException(e);
}
lockA.lock();
System.out.println("Worker two acquired lockA");
lockA.unlock();
lockB.unlock();
}

public static void main(String[] args) {
	
DeadLockDemo demo = new DeadLockDemo();
new Thread(demo::workerOne, "Worker One").start();
new Thread(demo::workerTwo,"Worker Two").start();
}
}
