package Locks;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
 *In a bus reservation system, a Read-Write Lock can be applied to handle concurrent access to seat availability and booking operations. The system typically has frequent read operations (checking seat availability) and less frequent write operations (booking or canceling seats). Using a Read-Write Lock ensures thread safety while optimizing performance.

Scenario in a Bus Reservation System
Read Operations (Frequent):

Passengers check seat availability for specific buses or routes.
These operations don't modify the data, so multiple threads can perform read operations concurrently.
Write Operations (Infrequent):

Booking or canceling a seat involves modifying the seat status.
During a write operation, no other read or write operations should be allowed.
*/

public class SharedResource {

	private int counter = 0;
	
	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	public void increment() {
		lock.writeLock().lock();
		try {
			counter++;
			System.out.println(Thread.currentThread().getName()+"writes:"+counter);
		} finally {
			lock.writeLock().unlock();
		}
	}
	
	public void getValue() {
		lock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + "reads:" + counter);
		}
		finally {
			lock.readLock().unlock();
		}
	}
}
//class ReadWriteDemo{
//	
//	public static void main(String[] args) {
//		
//		SharedResource sharedResource = new SharedResource();
//		
//		for(int i = 0;i<2;i++) {
//			Thread readerThread = new Thread(()->{
//				for(int j = 0;j<3;j++) {
//					sharedResource.getValue();
//				}
//			});
//			readerThread.setName("Reader Thread" +(i+1) );
//			readerThread.start();
//		}
//		Thread writerThread = new Thread(()->{
//			for(int i=0;i<5;i++) {
//				sharedResource.increment();
//			}
//		});
//		writerThread.setName("Writer Thread");
//		writerThread.start();
//	}
//}
