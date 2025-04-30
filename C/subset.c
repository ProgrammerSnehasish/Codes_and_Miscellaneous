//Subset creation, summation and on the basis max sum of subset diplaying the subset.

#include <stdio.h>
#include <stdlib.h>
#define max_size 100

int no_of_elements, arr[max_size];

void getValue(int *);
void getArray(int [],int);
void printSubset(int [],int);
void analyzeSubsets(int [],int);

void getValue(int *no_of_elements) {
    scanf("%d", no_of_elements);
}

void getArray(int arr[max_size], int no_of_elements) {
    printf("Enter %d integers (space-separated): ", no_of_elements);
    for (int i = 0; i < no_of_elements; i++) {
        scanf("%d", &arr[i]);
    }
}

void printSubset(int subset[], int size) {
    printf("[");
    for (int i = 0; i < size; i++) {
        printf("%d", subset[i]);
        if (i < size - 1) printf(", ");
    }
    printf("]");
}

void analyzeSubsets(int arr[], int no_of_elements) {
    int totalSubsets = 1 << no_of_elements;
    int maxSum = -2147483648;
    int maxSubsetCount = 0;
    int **maxSubsets = malloc(totalSubsets * sizeof(int *));
    int *subsetSizes = malloc(totalSubsets * sizeof(int));

    for (int mask = 0; mask < totalSubsets; mask++) {
        int subset[no_of_elements], subsetSize = 0, sum = 0;
        for (int j = 0; j < no_of_elements; j++) {
            if (mask & (1 << j)) {
                subset[subsetSize++] = arr[j];
                sum += arr[j];
            }
        }
        printSubset(subset, subsetSize);
        printf(" -> Sum: %d\n", sum);
        if (sum > maxSum) {
            maxSum = sum;
            maxSubsetCount = 0;
            maxSubsets[maxSubsetCount] = malloc(subsetSize * sizeof(int));
            for (int k = 0; k < subsetSize; k++)
                maxSubsets[maxSubsetCount][k] = subset[k];
            subsetSizes[maxSubsetCount++] = subsetSize;
        } else if (sum == maxSum) {
            maxSubsets[maxSubsetCount] = malloc(subsetSize * sizeof(int));
            for (int k = 0; k < subsetSize; k++)
                maxSubsets[maxSubsetCount][k] = subset[k];
            subsetSizes[maxSubsetCount++] = subsetSize;
        }
    }
    printf("\nSubset(s) with maximum sum (%d):\n", maxSum);
    for (int i = 0; i < maxSubsetCount; i++) {
        printSubset(maxSubsets[i], subsetSizes[i]);
        printf("\n");
        free(maxSubsets[i]);
    }
    free(maxSubsets);
    free(subsetSizes);
}

int main() {
    int n;
    printf("Enter the number of elements in the array: ");
    getValue(&no_of_elements);
    if (no_of_elements < 1 || no_of_elements > max_size) {
        printf("Invalid size. Must be between 1 and %d.\n", max_size);
        return 1;
    }
    getArray(arr,no_of_elements);
    printf("\nAll subsets and their sums:\n\n");
    analyzeSubsets(arr,no_of_elements);
    return 0;
}
