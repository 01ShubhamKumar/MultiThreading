package ExecutorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorDemo {
public static void main(String[] args) {
	ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
	service.scheduleAtFixedRate(new ProbeTask(), 1000, 2000, TimeUnit.MILLISECONDS);
	
	try {
		if(!service.awaitTermination(10000, TimeUnit.MILLISECONDS)) {
		service.shutdown();
		}
	} catch (InterruptedException e) {
		// TODO: handle exception
		service.shutdownNow();
	}
}
}
class ProbeTask implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Probing end point for update");
	}
	
}