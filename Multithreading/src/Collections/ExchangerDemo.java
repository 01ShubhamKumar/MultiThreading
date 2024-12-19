//Exchanger is a synchronoized point where two threads can exchange objects.

package Collections;
import java.util.concurrent.*;

public class ExchangerDemo {
	
public static void main(String[] args) {
	Exchanger<Integer> exchanger = new Exchanger<>();
	Thread one = new Thread(new FirstThread(exchanger));
	Thread two = new Thread(new secondThread(exchanger));
	
	one.start();
	two.start();
}
}

class FirstThread implements Runnable{
	
	//this line acts as a synchronoized point that help to exchange the data between the object
  private final Exchanger<Integer>exchanger;
	
	public FirstThread(Exchanger<Integer> exchanger) {
	this.exchanger = exchanger;
}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int dataToSend = 10;
		System.out.println("First thread is sending data " + dataToSend);
		
	try {
		Integer receivedData=	exchanger.exchange(dataToSend);
		System.out.println("First thread received" + receivedData);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		throw new RuntimeException(e);
	}
	}
	
}
class secondThread implements Runnable{

	private final Exchanger<Integer>exchanger;
	
	public secondThread(Exchanger<Integer> exchanger) {
	this.exchanger = exchanger;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(3000);
			int dataToSend = 20;
			System.out.println("Second thread is exchanging data " + dataToSend);
		Integer receivedData=	exchanger.exchange(dataToSend);
		System.out.println("Second thread received" + receivedData);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}