#include <stdio.h>
 
int main() {
    int i, j;
    float k, l;
    for (i = 0 ; i <= 20 ; i+= 2){
        k = i/10.0;
        if ((i%10) == 0){  
            for (j = 1 ; j <= 3 ; j++){
            l = j + k;
            printf("I=%.0f J=%.0f\n", k, l );
            } 
        }else {  
            for (j = 1 ; j <= 3 ; j++){
            l = j + k;
            printf("I=%.1f J=%.1f\n", k, l );
            } 
        } 
    }
 
    return 0;
}