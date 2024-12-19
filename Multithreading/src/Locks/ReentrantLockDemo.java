package Locks;

import java.util.concurrent.locks.ReentrantLock;

//A reentrant lock (or recursive lock) is a synchronization primitive used 
//in multithreading to allow a thread to repeatedly acquire 
//the same lock without causing a deadlock. 
//This is especially useful in situations where a 
//thread needs to call methods that require the same lock to maintain consistency
//from within a lock-protected section.
// It must release the lock the same number of times.ie counter == 0

public class ReentrantLockDemo {
	
private final ReentrantLock lock = new ReentrantLock();
private int sharedData = 0;

public void methodA() {
	lock.lock();
	try {
		sharedData++;
		System.out.println("Method A: sharedData=" + sharedData);
		methodB();
	}
	finally {
		lock.unlock();
	}
}

public void methodB() {
	lock.lock();
	try {
		sharedData--;
		System.out.println("Method B: sharedData=" +sharedData);
	}
	finally {
		lock.unlock();
	}
}

public static void main(String[] args) {
	ReentrantLockDemo example = new ReentrantLockDemo();
	for(int i = 0;i<5;i++) {
		new Thread(example::methodA).start();
	}
}
}
