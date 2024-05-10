#include<stdio.h>

int main(){
    int n, x, y, temp = 0, final = 0;
    int i, j;

    scanf("%d", &n);

    for (i = 0; i < n ; i++){
        scanf("%d %d", &x, &y);
        if ((x % 2) == 0){
            temp = x + 1;
            for (j = 0 ; j < y*2 ; j += 2){
                final += (temp + j);
            }
        } else {
            temp = x;
            for (j = 0 ; j < y*2 ; j += 2){
                final += (temp + j);
            }
        }

        printf("%d\n", final);

        final = 0;
    }
        
    return 0;
}