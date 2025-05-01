//Basic Level code using Multie Threads.
#include<stdio.h>
#include<unistd.h>
#include<pthread.h>
#include<sys/types.h>
void *print_odd(void *);
void *print_even(void *);
void *print_odd(void *range) {
	int *range_odd;
	range_odd = (int *) range;
	printf("Printing Odd Numbers upto %d...\n",range_odd);
	for(int i = 1;i<=range_odd;i++) {
		if(i%2 != 0){
			printf("%d ",i);
		}
	}
	printf("\n");
	return NULL;
} 
void *print_even(void *range) {
	int *range_even;
	range_even = (int *) range;
	printf("Printing Even Numbers upto %d...\n",range_even);
	for(int i = 1;i<=range_even;i++) {
		if(i%2 == 0){
			printf("%d ",i);
		}
	}
	printf("\n");
	return NULL;
} 
int main() {
	pthread_t thread1,thread2;
	int iret1,iret2,range;
	printf("Enter the range to print odd and even numbers: ");
	scanf("%d",&range);
	iret1 = pthread_create(&thread1,NULL,print_odd,(void*) range);
	iret2 = pthread_create(&thread2,NULL,print_even,(void*) range);
	pthread_join(thread1,NULL);
	pthread_join(thread2,NULL);
	printf("Thread 1 returns: %d\n",iret1);
	printf("Thread 2 returns: %d\n",iret2);
	exit(0);
}
