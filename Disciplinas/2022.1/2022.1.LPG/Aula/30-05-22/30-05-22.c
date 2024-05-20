#include <stdio.h>
#include <stdlib.h>


int main(){
    int v[10];
    int i, j, k, n = 10, aux;
    FILE *arq = fopen("30-05-22.txt", "r+");

    if(arq) {
        for(i = 0; i<10; i++){
            fscanf(arq, "%d", v+i);
        }
    }

    for (k = n - 1; k > 0; k--) {
        for (j = 0; j < k; j++) {
            if (v[j] > v[j + 1]) {
                aux      = v[j];
                v[j]     = v[j + 1];
                v[j + 1] = aux;
            }
        }
    }


    if(arq) {
        for(i = 0; i<10; i++){
            fprintf(arq, " %d", v[i]);
        }
    }

    fclose(arq);
}