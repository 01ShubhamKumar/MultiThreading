
public class WaitAndNotifyDemo {

	private static final Object LOCK = new Object();
	
	public static void main(String[] args) {
		Thread one = new Thread(()->{
			try {
				one();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			
		});
		Thread two = new Thread(()->{
			try {
				two();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
				// TODO: handle exception
			}
		});
		one.start();
		two.start();
		
	}
	private static void one() throws InterruptedException{
		synchronized(LOCK){
			System.out.println("from Method one");
			LOCK.wait();
			System.out.println("After wait");
		}
		
	}
	private static void two() throws InterruptedException{
		synchronized(LOCK) {
			System.out.println("FROM Method two");
			LOCK.notify();
			System.out.println("After notify");
		}
	}
}
