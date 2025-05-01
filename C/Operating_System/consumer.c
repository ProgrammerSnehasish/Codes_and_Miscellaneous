#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/mman.h>
#include <semaphore.h>

#define MAX_BUFFER_SIZE 100

int main() {
    int bufferSize, range;

    // Get user input for the buffer size
    printf("Enter the buffer size: ");
    scanf("%d", &bufferSize);

    // Validate buffer size
    if (bufferSize <= 0 || bufferSize > MAX_BUFFER_SIZE) {
        printf("Invalid buffer size. Please enter a value between 1 and %d.\n", MAX_BUFFER_SIZE);
        return -1;
    }

    // Get user input for the number of items to consume
    printf("Enter the number of items to consume: ");
    scanf("%d", &range);

    // Open shared memory
    int shm_fd = shm_open("/shared_buffer", O_RDONLY, 0666);
    int *buffer = (int *)mmap(0, bufferSize * sizeof(int), PROT_READ, MAP_SHARED, shm_fd, 0);

    // Access shared variables
    int *out = mmap(NULL, sizeof(int), PROT_WRITE, MAP_SHARED | MAP_ANONYMOUS, -1, 0);
    int *count = mmap(NULL, sizeof(int), PROT_WRITE, MAP_SHARED | MAP_ANONYMOUS, -1, 0);
    *out = 0;

    // Open semaphores
    sem_t *empty = sem_open("/empty", 0);
    sem_t *full = sem_open("/full", 0);
    sem_t *mutex = sem_open("/mutex", 0);

    // Consume items
    for (int i = 0; i < range; ++i) {
        // Wait if buffer is empty
        sem_wait(full);
        sem_wait(mutex);

        // Remove the item from the buffer
        int item = buffer[*out];
        *out = (*out + 1) % bufferSize;
        (*count)--;
        printf("Consumed: %d\n", item);

        // Signal that the buffer is not full
        sem_post(mutex);
        sem_post(empty);

        sleep(1); // Simulate consumption time
    }

    // Clean up
    sem_close(empty);
    sem_close(full);
    sem_close(mutex);

    return 0;
}

