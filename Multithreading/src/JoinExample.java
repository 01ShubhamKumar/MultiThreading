
public class JoinExample {
public static void main(String[] args) throws InterruptedException {
	Thread t = new Thread(()->{
		for(int i = 0;i<5;i++) {
			System.out.println("Thread one:"+i);
		}
	});
	Thread t1 = new Thread(()->{
		for(int i =0;i<25;i++) {
			System.out.println("thread t1:"+i);
		}
	});
	t.start();
	t1.start();
	t.join();
	t1.join();
	System.out.println("End");
}
}
