#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_LENGTH 100 // Maximum length of input string and integer array

int main() {
    char str[MAX_LENGTH]; // Input string
    int arr[MAX_LENGTH], count = 0; // Integer array and element count variable

    printf("Enter a string of numbers separated by spaces: ");
    fgets(str, MAX_LENGTH, stdin); // Read input string from user

    char *token = strtok(str, " "); // Tokenize input string using space delimiter

    while (token != NULL) { // Loop through all tokens in input string
        arr[count++] = atoi(token); // Convert token to integer and store in array
        token = strtok(NULL, " "); // Get next token
    }

    printf("Array elements are: ");

    for (int i = 0; i < count; i++) { // Loop through all elements in array
        printf("%d ", arr[i]); // Print each element
    }

    return 0;
}