#include <stdio.h>
#include <string.h>

int main()
{
    int n;
    int i, j, k;
    int y[100][100];
    char x[100][100];
    char temp;
    size_t tam;
    
    scanf("%d", &n);
    
    for(i = 0 ; i < n ; i++){
        scanf(" %[^\n]", x[i]);
    }
    
    //Transfere Array de String pro Array 2D DE INT e move 3 casas para direita
    for(i = 0 ; i < n ; i++){
        tam = strlen(x[i]);
        for(j = 0 ; j < tam ; j++){
            if ((x[i][j] >= 65 && x[i][j] <= 90) || (x[i][j] >= 97 && x[i][j] <= 122)){
                y[i][j] = x[i][j] + 3;
            } else {
                y[i][j] = x[i][j];
            }
            printf("%d\n", y[i][j]);
        }
        printf("\n");
    }
    
    //Transfere Array 2D DE INT pra String ao contrario
    for(i = 0 ; i < n ; i++){
        tam = strlen(x[i]);
        for(j = 0 ; j < tam ; j++){
            k = (tam - j - 1);
            printf("%d ", k);
            x[i][j] = y[i][k];
            printf("%c\n", x[i][j]);
        }
        printf("\n");
    }
    
    //Transfere Array de String pro Array 2D DE INT
    for(i = 0 ; i < n ; i++){
        tam = strlen(x[i]);
        for(j = 0 ; j < tam ; j++){
            y[i][j] = x[i][j];
            printf("%d\n", y[i][j]);
        }
        printf("\n");
    }
    
    //Segunda metade do Array 2d uma casa para esquerda
    for(i=0 ; i < n ; i++){
        tam = strlen(x[i]);
        k = ((tam - 1) / 2);
        for(j = k ; j < tam ; j++){
            if ((x[i][j] >= 65 && x[i][j] <= 93) || (x[i][j] >= 97 && x[i][j] <= 125)){
                y[i][j] = x[i][j] - 1;
            } else {
                y[i][j] = x[i][j];
            }
            printf("%d\n", y[i][j]);
        }
        printf("\n");
    }
    
    //Transfere Array 2D DE INT pra Array de String
    for(i = 0 ; i < n ; i++){
        tam = strlen(x[i]);
        for(j = 0 ; j < tam ; j++){
            x[i][j] = y[i][j];
            printf("%c\n", x[i][j]);
        }
        printf("\n");
    }
    
    for(i = 0 ; i < n ; i++){
        printf("%s\n", x[i]);
    }
    
    
    
    return 0;
}
