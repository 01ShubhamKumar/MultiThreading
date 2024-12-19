import java.util.ArrayList;
import java.util.List;



// The producer-consumer is a synchronization scenario where one or more producer threads generate data 
// and put it into a shared buffer, while one or more consumer threads retrieve and process the data concurrently.
public class ProducerConsumer {
    public static void main(String[] args) {
        Worker worker = new Worker(5, 0);

        Thread producer = new Thread(() -> {
            try {
                worker.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                worker.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producer.start();
        consumer.start();
    }
}

class Worker {
    private int sequence = 0; // Keeps track of the sequence of produced items.
    private final int top; // Maximum number of elements in the container.
    private final int bottom; // Minimum number of elements in the container (always 0 here).
    private final List<Integer> container; // Shared container for producer and consumer.
    private final Object lock = new Object(); // Lock for synchronization.

    public Worker(int top, int bottom) {
        this.top = top;
        this.bottom = bottom;
        this.container = new ArrayList<>();
    }

    public void produce() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (container.size() == top) {
                    System.out.println("Container full, waiting for items to be removed...");
                    lock.wait(); // Wait if the container is full.
                }

                System.out.println(sequence + " added to the container");
                container.add(sequence++); // Add item to the container.
                lock.notify(); // Notify consumer that an item is available.
            }
            Thread.sleep(500); // Simulate production delay.
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (container.size() == bottom) {
                    System.out.println("Container empty, waiting for items to be filled...");
                    lock.wait(); // Wait if the container is empty.
                }

                int item = container.remove(0); // Remove the first item from the container.
                System.out.println(item + " removed from the container");
                lock.notify(); // Notify producer that space is available.
            }
            Thread.sleep(500); // Simulate consumption delay.
        }
    }
}

