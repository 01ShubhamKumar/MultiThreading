package Collections;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//ex- tourist and tourguide 
public class CyclicBarrierDemo {

	private static final int NUM_TOURIST= 5;
	private static final int  NUM_STAGES=3;
	private static final CyclicBarrier barrier = new CyclicBarrier(NUM_TOURIST,()->{
		System.out.println("tOUR GUIDE STARTS SPEAKING");
	});
	public static void main(String[] args) {
		for(int i = 0;i<NUM_TOURIST;i++) {
			Thread touristThread = new Thread(new Tourist(i));
			touristThread.start();
		}
	}
	
	static class Tourist implements Runnable{
private final int touristId;

public Tourist(int touristId) {
	this.touristId=touristId;
}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i = 0;i<NUM_STAGES;i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO: handle exception
					throw new RuntimeException(e);
				}
				System.out.println("Tourist" + touristId +"arrives at stages"+(i+1));
				
				try {
					barrier.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					// TODO Auto-generated catch block
				throw new RuntimeException(e);
//				} catch (BrokenBarrierException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} 
			}
			}
		}
	}
}

		
	
	

