#include<stdio.h>

int main(){
    int n, x, y;
    int i, j;

    scanf("%d", &n);

    for (i = 0; i < n ; i++){
        scanf("%d", &x);
        y = 1;

        for(j = 2 ; j < x ; j++){
            if((x%j) == 0){
                y = 0;
            }
        }

        if(y == 1){
            printf("%d eh primo\n", x);
        } else {
            printf("%d nao eh primo\n", x);
        }
    }
    
    return 0;
}