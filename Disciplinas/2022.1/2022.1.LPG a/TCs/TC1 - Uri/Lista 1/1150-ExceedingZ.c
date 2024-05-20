#include<stdio.h>

int main(){
    int x, y;
    int i, a = 0, b = 0;

        scanf("%d",&x);
        while(1){
            scanf("%d",&y);
            if(y>x){
                break;
            }
        }

        for(i = x ; ; i++){
            a += i;
            b++;
            if(a>y){
                break;
            }
        }

        printf("%d\n", b);

    return 0;
}