/* Written by Parth V
 * This code simulates round robin process scheduling algorithm
 * You can use the members of process class to prepare gantt chart and display any kind of additional 
 * information
 */

import java.util.*;

class Process {
	int id, arrivalTime, finishTime, burst, tat, wt, bt;
	//bt represents the burst time of the process
	//burst is the burst time that will be used by the program to calculate the remaining cpu burst
	Process(int id, int a, int b) {
		this.id = id;
		this.arrivalTime = a;
		this.burst = this.bt = b;
	}

	void calc() {
		this.tat = this.finishTime - this.arrivalTime;
		this.wt = this.tat - this.bt;
	}
}

public class round_robin {
	static int total_burst = 0;
	static int total_wt = 0;
	static int total_tat = 0;
	static float avg_tat = 0;
	static float avg_wt = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of processes");
		int no_of_processes = sc.nextInt();
		System.out.println("Enter time quant");
		int quant = sc.nextInt();

		Process p[] = new Process[no_of_processes];

		for (int i = 0; i < no_of_processes; i++) { // getting process info
			System.out.println("Enter arrival time and cpu burst");
			int temp_arrival = sc.nextInt();
			int temp_burst = sc.nextInt();
			p[i] = new Process(i, temp_arrival, temp_burst);
		}
		// calculating the total burst time
		for (int i = 0; i < no_of_processes; i++)  
			total_burst += p[i].burst;
		

		int time = 0;
		int index = 0;
		while (time < total_burst) {
			if (p[index].burst < quant && p[index].burst > 0) {
				p[index].finishTime = time + p[index].burst;
				time += p[index].burst;
				p[index].burst = 0;
			} else if (p[index].burst >= quant && p[index].burst > 0) {
				p[index].burst -= quant;
				time += quant;

				if (p[index].burst == 0)
					p[index].finishTime = time;
			}
			// cycling through processes
			index = (index + 1)%no_of_processes;

		}
		for (int i = 0; i < no_of_processes; i++) 
			p[i].calc();
		
		for (int i = 0; i < no_of_processes; i++) {
			total_tat += p[i].tat;
			total_wt += p[i].wt;
		}

		//calculation of average turn around time and waiting time
		avg_tat = (float) (total_tat / (float) no_of_processes);
		avg_wt = (float) (total_wt / (float) no_of_processes);

		System.out.println("Average Turn Around Time is " + avg_tat + " ");
		System.out.println("Average Waiting Time is " + avg_wt + " ");
	}

}
