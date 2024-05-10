#include <stdio.h>
#include <string.h>

int main()
{
    int n, i, j;
    char x[100];
    char temp[100];
    size_t tam;
    
    scanf("%d", &n);
    
    for(i = 0 ; i < n ; i++){
        scanf("%s", &x);
        tam = strlen(x);

        printf("%ld", tam);

        for(j = 0 ; j < tam ; j++){
            if ((x[j] >= 65 && x[j] <= 90) || (x[j] >= 97 && x[j] <= 122)){
                x[j] += 3;
            }
        }
        
        for(j = 0 ; j < tam ; j++){
            temp[j] = x[j- tam - 1];
        }
        for(j = 0 ; j < tam ; j++){
            x[j] = temp[j];
        }
    
        for(j = tam/2 ; j < tam ; j++){
            if ((x[j] >= 65 && x[j] <= 93) || (x[j] >= 97 && x[j] <= 125)){
                x[j] -= 1;
            }
        }
        printf("%s", x);
    }
    
    
    return 0;
}
