package Locks;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * Atomic operations are essential in multi-threaded environments where multiple threads might read, write, or update the same variable. These variables ensure thread-safety without using synchronization, thus improving performance in certain scenarios.
 */
public class AtomicVariable {
	
	private static final AtomicInteger counter = new AtomicInteger(0);
private static int count = 0;

public static void main(String[] args) {
	
	Thread one  = new Thread(()->{
		for(int i = 0;i<10000;i++) {
			//count++;
			counter.incrementAndGet();
		}
	});
	
	Thread two = new Thread(()->{
		for(int i =0;i<10000;i++) {
			//count++;
			counter.incrementAndGet();
		}
	});
	
	one.start();
	two.start();
	
	try {
	one.join();
	two.join();
}catch(InterruptedException e) {
	throw new RuntimeException(e);
}
	System.out.println("count value is:"+counter);
}	
}
