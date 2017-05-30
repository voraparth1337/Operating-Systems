// Written by Parth V

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;


public class producer_consumer {
	public static Queue<Integer> q;
	public static int queue_size;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Specify queue size");
		queue_size = sc.nextInt();
		q = new ArrayBlockingQueue<Integer>(queue_size);
		Semaphore s = new Semaphore(6);
		Semaphore s1 = new Semaphore(6);
		new Consumer(s, s1);
		new Producer(s, s1);
	}
}

class Producer implements Runnable {
	Thread t;
	Semaphore s;
	Semaphore s1;

	Producer(Semaphore s, Semaphore s1) {
		this.t = new Thread(this);
		this.s = s;
		this.s1 = s1;
		t.start();
	}

	public void run() {
		try {

			for (int i = 1; i < 25; i++) {
				s.acquire();
				if (producer_consumer.q.offer(i))
					System.out.println("Produced item " + i + " and added to queue");
				else {
					System.out.println("item couldnt be added queue is full");
				}
				s1.release();
			}

		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}

class Consumer implements Runnable {
	Thread t;
	Semaphore s;
	Semaphore s1;

	Consumer(Semaphore s, Semaphore s1) {
		this.t = new Thread(this);
		this.s = s;
		this.s1 = s1;
		t.start();
	}

	public void run() {
		try {

			for (int i = 1; i < 25; i++) {
				s1.acquire();
				if (pr/oducer_consumer.q.isEmpty()) {

					System.out.println("Queue is empty....waiting for producer");
					
				} else {
					System.out.println("Consumed item " + producer_consumer.q.remove() + " from queue");
					
				}
				s.release();
				Thread.sleep(2500);
			}

		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}


/*Output
Specify queue size
5
Queue is empty....waiting for producer
Produced item 1 and added to queue
Produced item 2 and added to queue
Produced item 3 and added to queue
Produced item 4 and added to queue
Produced item 5 and added to queue
item couldnt be added queue is full
item couldnt be added queue is full
Consumed item 1 from queue
Produced item 8 and added to queue
Consumed item 2 from queue
Produced item 9 and added to queue
Consumed item 3 from queue
Produced item 10 and added to queue
Consumed item 4 from queue
Produced item 11 and added to queue
Consumed item 5 from queue
Produced item 12 and added to queue
Consumed item 8 from queue
Produced item 13 and added to queue
Consumed item 9 from queue
Produced item 14 and added to queue
Consumed item 10 from queue
Produced item 15 and added to queue
Consumed item 11 from queue
Produced item 16 and added to queue
Consumed item 12 from queue
Produced item 17 and added to queue
Consumed item 13 from queue
Produced item 18 and added to queue
Consumed item 14 from queue
Produced item 19 and added to queue
Consumed item 15 from queue
Produced item 20 and added to queue
Consumed item 16 from queue
Produced item 21 and added to queue
Consumed item 17 from queue
Produced item 22 and added to queue
Consumed item 18 from queue
Produced item 23 and added to queue
Consumed item 19 from queue
Produced item 24 and added to queue
Consumed item 20 from queue
Consumed item 21 from queue
Consumed item 22 from queue
Consumed item 23 from queue
Consumed item 24 from queue
Queue is empty....waiting for producer
*/
