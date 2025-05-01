//Banker's Algorithm & Safe Algorithm & Request Resource Algorithm in Dead lock.
#include <stdio.h>
#define max_size 100

int process, resource;
int total[max_size], avail[max_size], max[max_size][max_size], alloc[max_size][max_size], need[max_size][max_size], finish[max_size];

void getTotalInstances(int []);
void getAvailableInstances(int []);
void getMaxDemandList(int [][max_size]);
void getAllocationList(int [][max_size]);
void calculateNeed();
void displayDetails();
void assignInitialValueToFinish(int []);
int ifSafe();
int ifSafeAfterRequest();
int requestResources(int, int []);

void getTotalInstances(int total[max_size]) {
    for (int i = 0; i < resource; i++) {
        printf("Total available instances of Resource %d: ", i + 1);
        scanf("%d", &total[i]);
    }
}

void getAvailableInstances(int avail[max_size]) {
    for (int i = 0; i < resource; i++) {
        avail[i] = total[i];
        for (int j = 0; j < process; j++) {
            avail[i] -= alloc[j][i];
        }
    }
}

void getMaxDemandList(int max[max_size][max_size]) {
    for (int i = 0; i < process; i++) {
        printf("Process %d:\n", i + 1);
        for (int j = 0; j < resource; j++) {
            printf("Resource %d: ", j + 1);
            scanf("%d", &max[i][j]);
        }
    }
}

void getAllocationList(int alloc[max_size][max_size]) {
    for (int i = 0; i < process; i++) {
        printf("Process %d:\n", i + 1);
        for (int j = 0; j < resource; j++) {
            printf("Resource %d: ", j + 1);
            scanf("%d", &alloc[i][j]);
        }
    }
}

void calculateNeed() {
    for (int i = 0; i < process; i++)
        for (int j = 0; j < resource; j++)
            need[i][j] = max[i][j] - alloc[i][j];
}

void assignInitialValueToFinish(int finish[max_size]) {
    for (int i = 0; i < process; i++)
        finish[i] = 0;
}

void displayDetails() {
    printf("\nSystem Details:\n");
    printf("\nAvailable Resources:\n");
    for (int i = 0; i < resource; i++) {
        printf("Resource %d: %d\n", i + 1, avail[i]);
    }
    printf("\nProcess\t\tAllocation\t\t\tMaximum\t\t\tNeed\n");
    printf("\t\t");
    for (int i = 0; i < resource; i++) {
        printf("R%d\t", i + 1);
    }
    printf("\t");
    for (int i = 0; i < resource; i++) {
        printf("R%d\t", i + 1);
    }
    printf("\t");
    for (int i = 0; i < resource; i++) {
        printf("R%d\t", i + 1);
    }
    printf("\n");
    for (int i = 0; i < process; i++) {
        printf("P%-2d\t\t", i);
        for (int j = 0; j < resource; j++) {
            printf("%d       ", alloc[i][j]);
        }
        printf("\t");
        for (int j = 0; j < resource; j++) {
            printf("%d       ", max[i][j]);
        }
        printf("\t");
        for (int j = 0; j < resource; j++) {
            printf("%d       ", need[i][j]);
        }
        printf("\n");
    }
}

int ifSafe() {
    int work[max_size], sequence[max_size];
    for (int i = 0; i < resource; i++)
        work[i] = avail[i];
    int count = 0;
    assignInitialValueToFinish(finish);
    
    while (count < process) {
        int found = 0;
        for (int p = 0; p < process; p++) {
            if (finish[p] == 0) {
                int flag = 1;
                for (int j = 0; j < resource; j++) {
                    if (need[p][j] > work[j]) {
                        flag = 0;
                        break;
                    }
                }
                if (flag) {
                    for (int k = 0; k < resource; k++)
                        work[k] += alloc[p][k];
                    finish[p] = 1;
                    sequence[count++] = p;
                    found = 1;
                }
            }
        }
        if (!found) return 0;
    }
    
    printf("\nThe system is in a safe state.\nSafe sequence: ");
    for (int i = 0; i < process; i++) {
        printf("P%d", sequence[i]);
        if (i != process - 1) {
            printf(" -> ");
        }
    }
    printf("\n");
    return 1;
}

int ifSafeAfterRequest() {
    int work_ar[max_size], sequence[max_size];
    for (int i = 0; i < resource; i++)
        work_ar[i] = avail[i];
    int count = 0;
    int finish[max_size];
    assignInitialValueToFinish(finish);
    
    while (count < process) {
        int found = 0;
        for (int p = 0; p < process; p++) {
            if (finish[p] == 0) {
                int flag = 1;
                for (int j = 0; j < resource; j++) {
                    if (need[p][j] > work_ar[j]) {
                        flag = 0;
                        break;
                    }
                }
                if (flag) {
                    for (int k = 0; k < resource; k++)
                        work_ar[k] += alloc[p][k];
                    finish[p] = 1;
                    sequence[count++] = p;
                    found = 1;
                }
            }
        }
        if (!found) return 0;
    }
    
    printf("\nThe system is in a safe state.\nSafe sequence: ");
    for (int i = 0; i < process; i++) {
        printf("P%d", sequence[i]);
        if (i != process - 1) {
            printf(" -> ");
        }
    }
    printf("\n");
    return 1;
}

int requestResources(int processNum, int request[]) {
    for (int j = 0; j < resource; j++) {
        if (request[j] > need[processNum][j]) {
            printf("Error: Process has exceeded its maximum claim.\n");
            return 0;
        }
    }   
    for (int j = 0; j < resource; j++) {
        if (request[j] > avail[j]) {
            printf("Process is waiting. Resources not available.\n");
            return 0;
        }
    }
    for (int j = 0; j < resource; j++) {
        avail[j] -= request[j];
        alloc[processNum][j] += request[j];
        need[processNum][j] -= request[j];
    }
    printf("Updated ");
    displayDetails();
    if (ifSafeAfterRequest()) {
        printf("Request can be granted.\n");
        return 1;
    } else {
        for (int j = 0; j < resource; j++) {
            avail[j] += request[j];
            alloc[processNum][j] -= request[j];
            need[processNum][j] += request[j];
        }
        printf("Request cannot be granted. System is not in a safe state.\n");
        return 0;
    }
}

int main() {
    printf("Enter the number of processes: ");
    scanf("%d", &process);
    printf("Enter the number of resources: ");
    scanf("%d", &resource);
    printf("Enter the Total available instances for each resource:\n");
    getTotalInstances(total);
    printf("Enter the maximum demand of each process:\n");
    getMaxDemandList(max);
    printf("Enter the allocated resources for each process:\n");
    getAllocationList(alloc);
    getAvailableInstances(avail);
    calculateNeed();
    displayDetails();

    if (!ifSafe()) {
        printf("The system is not in a safe state.\n");
    }
    
    int processNum;
    printf("\nEnter the process number to request resources (0 to %d): ", process - 1);
    scanf("%d", &processNum);
    int request[max_size];
    printf("Enter the request for resources:\n");
    for (int j = 0; j < resource; j++) {
        printf("Resource %d: ", j + 1);
        scanf("%d", &request[j]);
    }
    requestResources(processNum, request);
    
    return 0;
}

