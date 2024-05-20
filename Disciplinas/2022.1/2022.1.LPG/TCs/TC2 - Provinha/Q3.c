/*
Escreva um programa em C que leia 10 strings (vetores de caracteres) e exiba ao final
os strings em ordem alfabética.
*/

#include<stdio.h>
#include<string.h>
#define N 10

int main(){
   int i,j;
   char str[100][100], temp[100];

   printf("Escreva as 10 strings:\n");
   for(i=0 ; i<N ; i++)
      scanf("%s", &str[i]);

    //Bouble sort nas strings com strcmp  
   for(i=0 ; i<N ; i++){
        for(j=i+1 ; j<N ; j++){
            if(strcmp(str[i],str[j])>0){
                strcpy(temp,str[i]);
                strcpy(str[i],str[j]);
                strcpy(str[j],temp);
            }
        }
    }

   printf("\n\n");
   for(i=0 ; i<=N ; i++){
        printf("%s\n", str[i]);
    }
   
   return 0;
}