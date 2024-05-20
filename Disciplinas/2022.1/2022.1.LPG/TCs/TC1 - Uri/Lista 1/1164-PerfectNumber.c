#include<stdio.h>

int main(){
    int n, x, a, b;
    int i, j;

    scanf("%d", &n);

    for (i = 0; i < n ; i++){
        scanf("%d", &x);
        a = x/2;
        b = 0;

        for (j = 1; j <= a ; j++){
            if((x%j) == 0){
                b += j;
            }
        }

        if(b == x){
            printf("%d eh perfeito\n", x);
        } else {
            printf("%d nao eh perfeito\n", x);
        }
        
    }
    return 0;
}