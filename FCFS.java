/* Written by Parth V
 * This code simulates FCFS process scheduling algorithm
 * You can use the members of process class to prepare gantt chart and display any kind of additional 
 * information
 */
import java.util.Scanner;

class Process {
	int id;
	int arrivalTime;
	int burst;
	int bt;
	int finishTime;
	int tat;
	int wt;
	int priority;
	/*bt represents the burst time of the process
	*burst is the burst time that will be used by the program to calculate the remaining cpu burst
	*/
	public Process(int id, int at, int burst) {
		this.id = id;
		this.arrivalTime = at;
		this.burst = this.bt = burst;
	}

	public void calc() {
		this.tat = this.finishTime - this.arrivalTime;
		this.wt = this.tat - this.bt;
	}
}

public class FCFS {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int total_burst = 0;
		int total_tat = 0;
		int total_wt = 0;
		float avg_tat = 0;
		float avg_wt = 0;
		System.out.pritnln("Enter the number of processes");
		int n = sc.nextInt();
		Process p[] = new Process[n];
		
		for (int i = 0; i < n; i++) {
			System.out.println("Enter details for process " + i);
			System.out.println("Enter arrival time");
			int temp = sc.nextInt();
			System.out.println("Enter cpu burst");
			int temp2 = sc.nextInt();
			p[i] = new Process(i, temp, temp2);
		}

		for (int i = 0; i < n; i++)
			total_burst += p[i].bt;

		int time = 0;
		while(time<total_burst){
			int min = 999;
			int index = 0;
			for(int i = 0 ; i< n ; i++){
				/*main part of code is here . just change prioriy/arrival/cpu burst at the last if condition to get non pre-emptive results..
				here lesser the integer value of priority higher will be the priority 
				eg - priority 0 is higher than priority 5
				this "for loop" selects the most appropriate process at a given instant of time
				*/
				if(p[i].arrivalTime<=time && p[i].burst>0 && p[i].arrivalTime<min ){ 
					/* first condition checks if the process has arrived at a particular instant of time 
					second condition checks if its already completed or not
					third condition is the main condition that checks for smallest arrival time among all the arrived and incomplete processes
					*/
					min = p[i].burst;
					index = i;
				}
			}
			time = time + p[index].burst;
			p[index].burst = 0;
			p[index].finishTime = time;
		}
		

		for (int i = 0; i < n; i++)
			p[i].calc();

		for (int i = 0; i < n; i++) {
			total_tat += p[i].tat;
			total_wt += p[i].wt;
		}

		avg_tat = (float) total_tat / (float) n;
		avg_wt = (float) total_wt / (float) n;

		System.out.println("Average tat is " + avg_tat + " and Average waiting time is " + avg_wt);
		
	}
}