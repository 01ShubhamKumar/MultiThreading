package Locks;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SharedResourceDemo {

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
class ReadWriteDemo{
	
	public static void main(String[] args) {
		
		SharedResourceDemo sharedResource = new SharedResourceDemo();
		
		for(int i = 0;i<2;i++) {
			Thread readerThread = new Thread(()->{
				for(int j = 0;j<3;j++) {
					sharedResource.getValue();
				}
			});
			readerThread.setName("Reader Thread" +(i+1) );
			readerThread.start();
		}
		Thread writerThread = new Thread(()->{
			for(int i=0;i<5;i++) {
				sharedResource.increment();
			}
		});
		writerThread.setName("Writer Thread");
		writerThread.start();
	}
}


