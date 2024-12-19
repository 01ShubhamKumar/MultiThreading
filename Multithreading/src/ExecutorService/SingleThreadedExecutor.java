package ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SingleThreadedExecutor {
    public static void main(String[] args) {
    	  ExecutorService executor = Executors.newSingleThreadExecutor();
        	 for (int i = 0; i < 5; i++) {
                 executor.execute(new Task(i));
             }
        	 executor.shutdown();
    }
}

class Task implements Runnable { // Fixed class name
    private final int taskId;

    public Task(int taskId) { // Fixed constructor name
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println("Task with Id " + taskId + " being executed by Thread " + Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
