#include <stdio.h>
#include <stdlib.h>

int main()
{
    int *arr;
    int i, j, n = 10;

    arr = malloc(sizeof(int) * 5);

    for ( i = 0; i < n; i++)
    {
        j = i;
        printf("%d\n", arr[i]);
        j = i + 1;
    }
    printf("\n");

    arr = realloc(arr, sizeof(int) * 1);

    for (i = 0; i < n; i++)
    {
        arr[i] = 1;
        j = 5;
        printf("%d\n", arr[i]);
    }

    free(arr);
    return 0;
}