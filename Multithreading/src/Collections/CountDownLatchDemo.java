package Collections;

import java.util.concurrent.CountDownLatch;

// It help in coordinating multiple thread or task to synchronoized,
//their work,it ensures all reach the certain point before proceding further


public class CountDownLatchDemo {
public static void main(String[] args) throws InterruptedException {
	int numberofChefs = 3;
	CountDownLatch latch = new CountDownLatch(numberofChefs);
	new Thread(new Chef("Chef A","PIXXA",latch)).start();
	new Thread(new Chef("Chef B","Pasta",latch)).start();
	new Thread(new Chef("Chef c","SALAD",latch)).start();
	
	latch.await();
	System.out.println("All the dises ready");
}
}

class Chef implements Runnable{

	private final String name;
	private final String dish;
	private final CountDownLatch latch;
	
	
	public Chef(String name, String dish, CountDownLatch latch) {
	
		this.name = name;
		this.dish = dish;
		this.latch = latch;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println(name + "is preparing "+ dish);
			Thread.sleep(2000);
			System.out.println(name+"has finished "+dish);
			latch.countDown();
		} catch (InterruptedException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
}