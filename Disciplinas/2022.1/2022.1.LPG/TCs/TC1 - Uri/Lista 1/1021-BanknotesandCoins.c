#include <stdio.h>
#include <string.h>
#define num 12

int main(void){
    float x;
	int i=0, j=0;
	int notas[num];
	char notasNomes[num][10] = { "R$ 100.00", "R$ 50.00", "R$ 20.00", "R$ 10.00", "R$ 5.00", "R$ 2.00", 
	                             "R$ 1.00", "R$ 0.50", "R$ 0.25", "R$ 0.10", "R$ 0.05", "R$ 0.01" };
	
	scanf("%f", &x);
	
	notas[0]  =  x / 100;
	notas[1]  = (x - (notas[0]*100)) / 50;
	notas[2]  = (x - ((notas[0]*100) + (notas[1]*50))) / 20;
	notas[3]  = (x - ((notas[0]*100) + (notas[1]*50) + (notas[2]*20))) / 10;
	notas[4]  = (x - ((notas[0]*100) + (notas[1]*50) + (notas[2]*20) + (notas[3]*10))) / 5;
	notas[5]  = (x - ((notas[0]*100) + (notas[1]*50) + (notas[2]*20) + (notas[3]*10) + (notas[4]*5))) / 2;
	notas[6]  = (x - ((notas[0]*100) + (notas[1]*50) + (notas[2]*20) + (notas[3]*10) + (notas[4]*5) + (notas[5]*2))) / 1;
	notas[7]  = (x - ((notas[0]*100) + (notas[1]*50) + (notas[2]*20) + (notas[3]*10) + (notas[4]*5) + (notas[5]*2) + (notas[6]*1))) / 0.50;
	notas[8]  = (x - ((notas[0]*100) + (notas[1]*50) + (notas[2]*20) + (notas[3]*10) + (notas[4]*5) + (notas[5]*2) + (notas[6]*1) + (notas[7]*0.50))) / 0.25;
	notas[9]  = (x - ((notas[0]*100) + (notas[1]*50) + (notas[2]*20) + (notas[3]*10) + (notas[4]*5) + (notas[5]*2) + (notas[6]*1) + (notas[7]*0.50) + (notas[8]*0.25))) / 0.10;
	notas[10] = (x - ((notas[0]*100) + (notas[1]*50) + (notas[2]*20) + (notas[3]*10) + (notas[4]*5) + (notas[5]*2) + (notas[6]*1) + (notas[7]*0.50) + (notas[8]*0.25) + (notas[9]*0.10))) / 0.05;
	notas[11] = (x - ((notas[0]*100) + (notas[1]*50) + (notas[2]*20) + (notas[3]*10) + (notas[4]*5) + (notas[5]*2) + (notas[6]*1) + (notas[7]*0.50) + (notas[8]*0.25) + (notas[9]*0.10) + (notas[10]*0.05))) / 0.01;
	
	printf("NOTAS:\n");
	
	for (i=0 ; i < 6 ; i++){
		printf("%d nota(s) de ",notas[i]);
		
		for (j=0 ; j < strlen(notasNomes[i]) ; j++){
			printf("%c",notasNomes[i][j]);
		}
		
		printf("\n");
	}
	
	printf("MOEDAS:\n");
	
	for (i=6 ; i < (num-1); i++){
		printf("%d moeda(s) de ",notas[i]);
		
		for (j=0 ; j < strlen(notasNomes[i]) ; j++){
			printf("%c",notasNomes[i][j]);
		}
		
		printf("\n");
	}
	
	printf("%.0f moeda(s) de R$ 0.01\n",(x - ((notas[0]*100) + (notas[1]*50) + (notas[2]*20) + (notas[3]*10) + (notas[4]*5) + (notas[5]*2) + (notas[6]*1) + (notas[7]*0.50) + (notas[8]*0.25) + (notas[9]*0.10) + (notas[10]*0.05))) / 0.01);
	
	return 0;
}