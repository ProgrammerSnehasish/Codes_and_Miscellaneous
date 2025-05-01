//First Come First Serve Algorithm.
#include<stdio.h>
#define max_size 100
int burst_time[max_size],waiting_time[max_size];
void getBurstTimes(int [],int);
void firstComeFirstServe(int [],int);
void displayProcessChartAndGaunttChart(int [],int [],int);
void getBurstTimes(int burst_time[max_size],int no_of_process){
        int i;
        for(i=0;i<no_of_process;i++){
                printf("Enter burst time for process %d: ",i+1);
                scanf("%d",&burst_time[i]);
 }
}
void displayProcessChartAndGaunttChart(int burst_time[max_size],int waiting_time[max_size],int no_of_process) {
        int i;
        printf("Process: ");
        for(i=0;i<no_of_process;i++){
                printf("P%d ",i+1);
        }
        printf("\n");
        printf("Brust Time: ");
        for(i=0;i<no_of_process;i++){
                printf("%d ",burst_time[i]);
        }
        printf("\n");
        printf("Waiting Time: ");
        for(i=0;i<no_of_process;i++){
                printf("%d ",waiting_time[i]);
        }
        printf("\n");
        printf("Displaying Gauntt Chart...\n");
        printf("0 ");
        for(i=0;i<no_of_process;i++){
                waiting_time[i] = waiting_time[i]+burst_time[i];
                waiting_time[i+1] = waiting_time[i];
                printf("P%d %d ",i+1,waiting_time[i]);
        }
}
void firstComeFirstServe(int burst_time[max_size],int no_of_process){
        int i,total_waiting_time=0;
        waiting_time[0]=0;
        float average_waiting_time = 0;
        for(i=0;i<no_of_process-1;i++){
                waiting_time[i+1] = burst_time[i];
        }
        for(i=0;i<no_of_process;i++){
                total_waiting_time += waiting_time[i];
        }
        printf("The total waiting time is: %d\n",total_waiting_time);
        average_waiting_time = total_waiting_time/no_of_process;
        printf("The average waiting time is: %f\n",average_waiting_time);
        printf("Displaying the Process Chart and Gauntt Chart...\n");
        displayProcessChartAndGaunttChart(burst_time,waiting_time,no_of_process);
}
int main(){
        int no_of_process;
        printf("___FCFS Process while arrival time of all processes is 0___\n");
        printf("Enter total number of processes: ");
        scanf("%d",&no_of_process);
        printf("Enter Burst Time for each processes:\n");
        getBurstTimes(burst_time,no_of_process);
        firstComeFirstServe(burst_time,no_of_process);
}
