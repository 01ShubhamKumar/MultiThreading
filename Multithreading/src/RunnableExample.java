
public class RunnableExample {
public static void main(String[] args) {
//	ThreadOne r = new ThreadOne();
	Thread t = new Thread(new ThreadOne());
	t.start();
	
}
}
class ThreadOne implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0;i < 5;i++) {
		System.out.println("Thread 1 "+ i);
	}
}
}