//Producer and Consumer.
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <stdbool.h>
#define MAX_BUFFER_SIZE 100
int *buffer;
int bufferSize;
int count = 0;
int in = 0;
int out = 0;
pthread_mutex_t mutex;
pthread_cond_t notEmpty;
pthread_cond_t notFull;
void *producer(void *arg) {
    int item = 0;
    while (true) { 
        item++; 
        pthread_mutex_lock(&mutex);
        while (count == bufferSize) {
            pthread_cond_wait(&notFull, &mutex);
        }        
        buffer[in] = item;
        in = (in + 1) % bufferSize;
        count++;
        printf("Produced: %d\n", item);        
        pthread_cond_signal(&notEmpty);
        pthread_mutex_unlock(&mutex);
        sleep(1); 
    }
    pthread_exit(NULL);
}
void *consumer(void *arg) {
    while (true) { 
        pthread_mutex_lock(&mutex);
        while (count == 0) {
            pthread_cond_wait(&notEmpty, &mutex);
        }
        int item = buffer[out];
        out = (out + 1) % bufferSize;
        count--;
        printf("Consumed: %d\n", item);
        pthread_cond_signal(&notFull); 
        pthread_mutex_unlock(&mutex);
        sleep(1); 
    }
    pthread_exit(NULL);
}
int main() {
    pthread_t producerThread, consumerThread;
    printf("Enter the buffer size: ");
    scanf("%d", &bufferSize);
    if (bufferSize <= 0 || bufferSize > MAX_BUFFER_SIZE) {
        printf("Invalid buffer size. Please enter a value between 1 and %d.\n", MAX_BUFFER_SIZE);
        return -1;
    }
    buffer = (int *)malloc(bufferSize * sizeof(int));
    pthread_mutex_init(&mutex, NULL);
    pthread_cond_init(&notEmpty, NULL);
    pthread_cond_init(&notFull, NULL);
    pthread_create(&producerThread, NULL, producer, NULL);
    pthread_create(&consumerThread, NULL, consumer, NULL);
    pthread_join(producerThread, NULL);
    pthread_join(consumerThread, NULL);
    free(buffer);
    pthread_mutex_destroy(&mutex);
    pthread_cond_destroy(&notEmpty);
    pthread_cond_destroy(&notFull);
    return 0;
}
