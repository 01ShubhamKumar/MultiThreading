package ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolDemo {
public static void main(String[] args) {
	ExecutorService service = Executors.newFixedThreadPool(3);
	for(int i = 0;i<7;i++) {
		service.execute(new Work(i));
	}
	service.shutdown();
}
}

 class Work implements Runnable{
public final int workId;
public Work(int workId) {
	this.workId=workId;
}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	
	
}
