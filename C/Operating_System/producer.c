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

    // Get user input for the number of items to produce
    printf("Enter the number of items to produce: ");
    scanf("%d", &range);

    // Create shared memory
    int shm_fd = shm_open("/shared_buffer", O_CREAT | O_RDWR, 0666);
    ftruncate(shm_fd, bufferSize * sizeof(int));
    int *buffer = (int *)mmap(0, bufferSize * sizeof(int), PROT_WRITE, MAP_SHARED, shm_fd, 0);

    // Initialize shared variables
    int *in = mmap(NULL, sizeof(int), PROT_WRITE, MAP_SHARED | MAP_ANONYMOUS, -1, 0);
    int *count = mmap(NULL, sizeof(int), PROT_WRITE, MAP_SHARED | MAP_ANONYMOUS, -1, 0);
    *in = 0;
    *count = 0;

    // Create semaphores
    sem_t *empty = sem_open("/empty", O_CREAT, 0666, bufferSize);
    sem_t *full = sem_open("/full", O_CREAT, 0666, 0);
    sem_t *mutex = sem_open("/mutex", O_CREAT, 0666, 1);

    // Produce items
    for (int i = 0; i < range; ++i) {
        int item = i + 1; // Produce an item

        // Wait if buffer is full
        sem_wait(empty);
        sem_wait(mutex);

        // Add the item to the buffer
        buffer[*in] = item;
        *in = (*in + 1) % bufferSize;
        (*count)++;
        printf("Produced: %d\n", item);

        // Signal that the buffer is not empty
        sem_post(mutex);
        sem_post(full);

        sleep(1); // Simulate production time
    }

    // Clean up
    sem_close(empty);
    sem_close(full);
    sem_close(mutex);

    return 0;
}

