#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>


int main()
{
    int i;
    size_t N;
    bool *F;
    
    scanf(" %ld", N);
    
    F = malloc( sizeof(F[0]) * N );
    
    for (i = 0; i < N; i++){
        scanf("%d", F[i]);
    }
    
    for (i = 0; i < N; i++){
        printf(" %d", F[i]);
    }

    return 0;
}
