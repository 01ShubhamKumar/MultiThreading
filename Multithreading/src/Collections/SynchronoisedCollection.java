package Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronoisedCollection {
	public static void main(String[] args) throws InterruptedException {
		
//	
//List<Integer> list = new ArrayList<>();
List<Integer> list = Collections.synchronizedList(new ArrayList<>());
Thread one = new Thread(()->{
	for(int i =0;i<1000;i++) {
		list.add(i);
	}
});

Thread two = new Thread(()->{
	for(int i = 0; i< 1000;i++) {
		list.add(i);
	}
});
one.start();
two.start();
one.join();
two.join();
System.out.println(list.size());
}
}

// Drawback of Collections.synchronized()
// 1. it uses single lock to synchronoized the list(Coarse gained locking)
//2 . Limited functionality
//3. Performance Overhead