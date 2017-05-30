/* Written by Parth V
This code simulates SSTF disk scheduling algorithm 
You can use a seperate class for track object and to show additional information, however I have tried to keep the code as simple as possible
*/
import java.util.*;

public class SSTF {
	static ArrayList<Integer> al = new ArrayList<Integer>();
	static ArrayList<Integer> done = new ArrayList<Integer>();
	/*Done arraylist keeps tracks of all the tracks that are done*/
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter head positions");
		int head = sc.nextInt();
		System.out.println("Enter no of tracks to be scanned");
		int n = sc.nextInt() ;
		for(int i = 0 ; i < n ; i++){
			System.out.println("Enter disk");
			int temp = sc.nextInt();
			al.add(temp);
		}
		int curr = head;
		int seektime = 0;
		for(int i = 0 ; i < 9 ; i++){
			int newhead = al.get(next(curr));
			int dist = Math.abs(curr-newhead);
			System.out.println(newhead +" Distance " + dist);
			done.add(newhead);
			seektime = seektime + dist;
			curr = newhead;
		}

		float avg = (float)seektime/(float)n;
		System.out.println("Averagge seek time is " + avg);
	}
	/*
	This function returns the index of the next eligible track by going through all the tracks and finding the one with the minimal travel distance 
	and even considering whether the track is scanned or not
	 */
	public static int next(int curr){
		int min = 9999;
		int index = -1;
		for(int i = 0 ; i < al.size() ; i++){
			int temp = Math.abs(curr-al.get(i));
			if(temp<min && !done.contains(al.get(i))){
				min = temp;
				index = i;
			}
		}
		return index;
	}


}