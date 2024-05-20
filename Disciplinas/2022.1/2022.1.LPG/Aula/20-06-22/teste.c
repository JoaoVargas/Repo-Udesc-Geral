#include <stdio.h>
#include <stdlib.h>

int main()
{
    int **arr;
    int i, j, n = 10;

    arr = malloc(sizeof(int*) * n);
    for (i = 0; i < n; i++)
    {
        arr[i] = malloc(sizeof(int) * 1);
        arr[0][i] = 1;
    }

    for (i = 0; i < n; i++)
    {
        printf("arr[%d] = %d\n", i, arr[i]);
    }
    printf("\n");

    for (i = 0; i < n; i++)
    {
        arr[i] = 10;
    }
    for (i = 0; i < n; i++)
    {
        printf("arr[%d] = %d\n", i, arr[i]);
    }

    printf("sizeof(arr) = %ld\n", sizeof(arr));
    printf("\n");

    free(arr);
    return 0;
}