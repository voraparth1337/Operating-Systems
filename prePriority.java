/* Written by Parth V
 * This code simulates Pre-emtpive priority based process scheduling algorithm
 * You can use the members of process class to prepare gantt chart and display any kind of additional 
 * information
 */
import java.util.Scanner;

class Process {
	int id, arrivalTime, finishTime, burst, tat, wt, bt; 
	//bt represents the burst time of the process
	//burst is the burst time that will be used by the program to calculate the remaining cpu burst
	Process(int id, int a, int b, int p) {
		this.id = id;
		this.arrivalTime = a;
		this.burst = this.bt = b;
		this.priority = p;
	}

	void calc() {
		this.tat = this.finishTime - this.arrivalTime;
		this.wt = this.tat - this.bt;
	}
}

public class prePriority {
	static int total_burst = 0;
	static int total_wt = 0;
	static int total_tat = 0;
	static float avg_tat = 0;
	static float avg_wt = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of processes");
		int no_of_processes = sc.nextInt();

		Process p[] = new Process[no_of_processes];

		for (int i = 0; i < no_of_processes; i++) { // getting process info
			System.out.println("Enter arrival time and cpu burst");
			int temp_arrival = sc.nextInt();
			int temp_burst = sc.nextInt();
			System.out.println("Enter priority");
			int temp = sc.nextInt();
			p[i] = new Process(i, temp_arrival, temp_burst, temp);
		}

		for (int i = 0; i < no_of_processes; i++) // calculating the total clock time
			total_burst += p[i].burst;
		

		for (int time = 0; time <= total_burst; time++) {
			int min = 9999, index = -1;

			for (int j = 0; j < p.length; j++) {
				/*main part of code is here . just change prioriy/arrival/cpu burst at the last if condition to get non pre-emptive results..
				here lesser the integer value of priority higher will be the priority 
				eg - priority 0 is higher than priority 5
				this "for loop" selects the most appropriate process at a given instant of time
				*/
				if (p[j].arrivalTime <= time && p[j].burst > 0 && p[j].priority < min) {
					/* first condition checks if the process has arrived at a particular instant of time 
					second condition checks if its already completed or not
					third condition is the main condition that checks for lowest priority among all the arrived and incomplete processes
					*/
					min = p[j].burst;
					index = j;
				}
			}
			p[index].burst -= 1;
			if (p[index].burst == 0) {
				p[index].finishTime = (time+1); // because time is the start time of last cpu burst in this case
			}
		}
		for (int i = 0; i < no_of_processes; i++) {
			p[i].calc();
		}
		for (int i = 0; i < no_of_processes; i++) {
			total_tat += p[i].tat;
			total_wt += p[i].wt;
		}
		//calculation of average turn around time and waiting time
		avg_tat = (float) (total_tat / (float)no_of_processes);
		avg_wt = (float) (total_wt / (float)no_of_processes);

		System.out.println("Average Turn Around Time is " + avg_tat + " ");
		System.out.println("Average Waiting Time is " + avg_wt + " ");

	}
gg
}
