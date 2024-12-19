package Collections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
static final int QUEUE_CAPACITY=10;
static BlockingQueue<Integer> taskQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

public static void main(String[] args) {
	Thread producer = new Thread(()->{
		try {
			for(int i=1;i<20;i++) {
				taskQueue.put(i);
				System.out.println("Task produced:" + i);
				Thread.sleep(100);
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	});
	Thread counsumerone= new Thread(()->{
		try {
			while(true) {
				int task  = taskQueue.take();
				processTask(task,"ConsumerOne");
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	});
	Thread consumerTwo = new Thread(()->{
		try {
			while(true) {
				int task  = taskQueue.take();
				processTask(task,"ConsumerTwo");
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	});
	producer.start();
	counsumerone.start();
	 consumerTwo .start();
}
private static void processTask(int task,String consumerName) throws InterruptedException{
	System.out.println("Task being process by "+ consumerName+":" + task);
	Thread.sleep(1000);
	System.out.println("TAsk consumed by  " + consumerName + ":" + task);
}
}
