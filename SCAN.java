/* Written by Parth V
This code simulates FCFS disk scheduling algorithm
You can use a seperate class for track object and to show additional information, however I have tried to keep the code as simple as possible
FOR CSCAN, LOOK AND CLOOK
you can modify the same code and add one more loops according to the logic...
*/

import java.util.*;

public class SCAN {
	static ArrayList<Integer> al = new ArrayList<Integer>();
	static ArrayList<Integer> done = new ArrayList<Integer>();

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter head");
		int head = sc.nextInt();
		System.out.println("Enter number of tracks");
		int n = sc.nextInt();
		System.out.println("Enter end");
		int end = sc.nextInt();
		for (int i = 0; i < n; i++) {
			System.out.println("Enter disk");
			int temp = sc.nextInt();
			al.add(temp);
		}
		int curr = head;
		int seektime = 0;
		// lowest and highest functions are needed for LOOK and C-LOOK , i was too lazy to code them seperately so included here :P
		int lowest = lowest();
		int highest = highest();
		//this loop goes from head to the end
		for (int i = head; i < end; i++) {
			if (al.contains(i) && !done.contains(i)) {
				// this condition checks if this track is to be read
				// other condition checks if it is already read or not
				int newhead = i;
				int dist = Math.abs(curr - newhead);
				System.out.println(newhead + " distance " + dist);
				seektime = seektime + dist;
				done.add(i);
				curr = newhead;

			}
		}
		seektime = seektime + (end-highest); // this adds the distance from last track to the end of disk
		curr = end; // now the head has reached the end
		// this loop goes from end to the start and scanning all remaining tracks
		for (int i = end - 1; i > 0; i--) {
			if (al.contains(i) && !done.contains(i)) {
				int newhead = i;
				int dist = Math.abs(curr - newhead);
				System.out.println(newhead + " distance " + dist);
				seektime = seektime + dist;
				done.add(i);
				curr = newhead;
			}
		}
		float avg = (float) seektime / (float) n;
		System.out.println("Averagge seek time is " + avg);
	}

	public static int highest() {
		int highest = -1;
		for (int i = 0; i < al.size(); i++) {
			if (al.get(i) > highest)
				highest = al.get(i);
		}
		return highest;
	}

	public static int lowest() {
		int lowest = 9999;
		for (int i = 0; i < al.size(); i++) {
			if (al.get(i) > lowest)
				lowest = al.get(i);
		}
		return lowest;
	}

}