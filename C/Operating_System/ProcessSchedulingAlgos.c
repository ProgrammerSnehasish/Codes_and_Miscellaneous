#include<stdio.h>
#include<stdlib.h>
#define max_size 100
int waiting_time[max_size],burst_time[max_size];
void getBurstTime(int [],int);
void displayProcessChart(int,int []);
void firstComeFirstServe(int,int []);
void shortestJobFirst(int,int []);
void getBurstTime(int burst_time[max_size],int no_of_process){
	for(int i=0;i<no_of_process;i++){
		printf("Enter burst time for P%d: ",i+1);
		scanf("%d",&burst_time[i]);
	}
}
void displayProcessChart(int no_of_process,int burst_time[max_size]){
	printf("Process: ");
	for(int i=0;i<no_of_process;i++){
		printf("P%d ",i+1);
	}
	printf("\n");
	printf("Burst Time: ");
	for(int i=0;i<no_of_process;i++){
		printf("%d ",burst_time[i]);
	}
	printf("\n");
}
void firstComeFirstServe(int no_of_process,int burst_time[max_size]){
	int i,total_waiting_time=0;
	float average_waiting_time=0;
	printf("________FCFS Scheduling while arrival time of all process is 0(no preemption)________\n");
	if(no_of_process == 0){
		printf("There is no process to schedule.");
	}
	else{
		waiting_time[0]=0;
		for(i=0;i<no_of_process-1;i++){
			waiting_time[i+1]=burst_time[i];
		}
		for(i=0;i<no_of_process;i++){
			total_waiting_time += waiting_time[i];
		}
		average_waiting_time = total_waiting_time/no_of_process;
		printf("The total waiting time is: %d",total_waiting_time);
		printf("\nThe average waiting time is: %f",average_waiting_time);
		printf("\nDisplaying Gauntt Chart...\n");
		printf("0 ");
		for(i=0;i<no_of_process;i++){
			waiting_time[i]=waiting_time[i]+burst_time[i];
			waiting_time[i+1]=waiting_time[i];
			printf(" P%d %d",i+1,waiting_time[i]);
		}
	}
}
void shortestJobFirst(int no_of_process,int burst_time[max_size]){
	int i,total_waiting_time=0,sorted_burst_time[max_size],process_order[max_size];
	float average_waiting_time=0;
	printf("________SJF Scheduling while arrival time of all process is 0(no preemption)________\n");
	if(no_of_process == 0){
		printf("There is no process to schedule.");
	}
	else{
		waiting_time[0]=0;
		for(i=0;i<no_of_process;i++){
			sorted_burst_time[i]=burst_time[i];
			process_order[i] = i + 1;
		}
		for(i=0;i<no_of_process-1;i++){
			for(int j=i+1;j<no_of_process;j++){
				if(sorted_burst_time[i]>sorted_burst_time[j]){
					int temp = sorted_burst_time[i];
					sorted_burst_time[i]=sorted_burst_time[j];
					sorted_burst_time[j]=temp;
					
					temp = process_order[i];
                    process_order[i] = process_order[j];
                    process_order[j] = temp;
				}
			}
		}
		for(i=0;i<no_of_process-1;i++){
			waiting_time[i+1]=sorted_burst_time[i];
		}
		for(i=0;i<no_of_process;i++){
			total_waiting_time += waiting_time[i];
		}
		average_waiting_time = total_waiting_time/no_of_process;
		printf("The total waiting time is: %d",total_waiting_time);
		printf("\nThe average waiting time is: %f",average_waiting_time);
		printf("\nDisplaying Gantt Chart...\n");
        printf("0");
        int current_time = 0;
        for (i = 0; i < no_of_process; i++) {
            current_time += sorted_burst_time[i];
            printf(" --P%d--> %d", process_order[i], current_time);
        }
	}
}
void main(){
	int no_of_process,choice;
	printf("Enter total no of processes: ");
	scanf("%d",&no_of_process);
	printf("Enter burst times for the %d processes.\n",no_of_process);
	getBurstTime(burst_time,no_of_process);
	printf("Displaying Process Chart...\n");
	displayProcessChart(no_of_process,burst_time);
	printf("List of Process Scheduling Algorithms:\n1. First Come First Serve\n2. Shortest Job First\n");
	printf("Enter your choice: ");
	scanf("%d",&choice);
	switch(choice){
		case 1:
		      firstComeFirstServe(no_of_process,burst_time);
		      break;
		case 2:
		     shortestJobFirst(no_of_process,burst_time);
		     break;
		default:
			printf("Wrong Choice entered, please enter right choice.");
	}
}
