package ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class CacheThreadPoolDemo {
public static void main(String[] args) {
	ExecutorService service = Executors.newCachedThreadPool();
	for(int i = 0;i<1000;i++) {
		service.execute(new TaskOne(i));
	}
	service.shutdown();
}
}
class TaskOne implements Runnable{
	private final int taskid;
	public TaskOne(int taskid) {
		this.taskid = taskid;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Task:"+ taskid + Thread.currentThread().getName());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
}

