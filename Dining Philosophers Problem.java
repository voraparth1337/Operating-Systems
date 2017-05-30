/*
Written by Parth V
In computer science, the dining philosophers problem is an example problem often used in concurrent algorithm design to 
illustrate synchronization issues and techniques for resolving them.
 */
import java.util.*;
 
public class dining_philosopher {
	static int number;
	public static void main(String[] args) throws InterruptedException {
		try{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of philosophers ?");
		number = sc.nextInt();
		
		if(number<2){
			System.out.println("No of philosophers can not be less than 2");
			return;
		}
		Fork f[] = new Fork[number];
		Philosopher p[] = new Philosopher[number];
		Thread t[] = new Thread[number];
		
		for(int i = 0 ; i< number ; i++){
			p[i] = new Philosopher(i+1);
			f[i] = new Fork(i+1);
		}
	
		for(int i = 0 ; i<number ; i++){
			final int index = i;
			t[i] = new Thread(new Runnable(){
				public void run(){
					try {
						p[index].start(f[index], index-1 > 0? f[index-1] : f[number-1]);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
		t[i].start();	
		}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		}
	

}


class Fork {
	public int forkId;
	private boolean status;

	Fork(int forkId) {
		this.forkId = forkId;
		this.status = true;
	}

	synchronized void free() throws InterruptedException {
		this.status = true;
	}

	synchronized boolean pick(int philosopherId) throws InterruptedException {
		int counter = 0;
		int waitUntil = new Random().nextInt(10) + 5;

		while (!status) {
			Thread.sleep(new Random().nextInt(100) + 50);
			counter++;
			if (counter > waitUntil)
				return false;
		}

		status = false;
		return true;
	}

}

class Philosopher {
	private int philosopherId;
	private Fork left, right;

	Philosopher(int id) {
		this.philosopherId = id;
	}

	public void start(Fork left, Fork right) throws InterruptedException {
		this.left = left;
		this.right = right;

		while (true) {
			if (new Random().nextBoolean())
				eat();
			else
				think();
		}
	}

	public void eat() throws InterruptedException {
		boolean rightPick = false;
		boolean leftPick = false;

		System.out.println("Philosopher " + philosopherId + " is now hungry");
		System.out.println("Philosopher " + philosopherId + " is now picking up fork" + left.forkId);

		leftPick = left.pick(philosopherId);

		if (!leftPick) {
			System.out.println("Cannot pick up...");
			return;
		}
		
		System.out.println("Philosopher " + philosopherId + " is now picking up fork" + right.forkId);
		rightPick = right.pick(philosopherId);
		
		if(!rightPick){
			left.free();
			System.out.println("Cannot pick up right fork so leaving ...");
			return;
		}
		
		System.out.println("Philosopher is now eating.....");
		Thread.sleep(new Random().nextInt(1000)+500);
		System.out.println("Finished eating...giving up the forks");
		left.free();
		right.free();
		
		

	}

	public void think() throws InterruptedException {
		System.out.println("Philosopher " + philosopherId + " is now sleeping");
		Thread.sleep(new Random().nextInt(2000) + 500);
		System.out.println("Philosopher " + philosopherId + " has stopped thinking");
	}

}
