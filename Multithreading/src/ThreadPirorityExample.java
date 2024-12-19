
public class ThreadPirorityExample {
public static void main(String[] args) {
	System.out.println(Thread.currentThread().getName()+ "hi");
	
	Thread t = new Thread(()->{
		System.out.println("Thread one says Hi");
	});
	t.setPriority(Thread.MAX_PRIORITY);
	t.start();
}
}
