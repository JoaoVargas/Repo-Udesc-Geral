/*
Um programa para gerenciar os saques de um caixa eletrônico deve possuir algum mecanismo para decidir o número de notas de cada valor que deve ser disponibilizado para o cliente que realizou o saque. 
Um possível critério seria o da "distribuição ótima" no sentido de que as notas de menor valor fossem distribuídas em número mínimo possível. Por exemplo, se a quantia solicitada for R$ 87,00, o programa deveria indicar uma nota de R$ 50,00, uma de R$ 20,00, uma de R$ 10,00, uma nota de R$ 5,00 e outra de R$ 2,00. Escreva um programa que receba o valor da quantia solicitada e retorne a distribuição das notas de acordo com o critério da distribuição ótima.
*/

#include <stdio.h>
#include <string.h>
#define num 6

int main(void){
	int x, i=0, j=0;
	int notas[num];
	char notasNomes[num][10] = { "R$ 50,00", "R$ 20,00", "R$ 10,00", "R$ 5,00", "R$ 2,00", "R$ 1,00" };
	
	scanf("%d", &x);
	
	notas[0] = x/50;
	notas[1] = (x%50)/20;
	notas[2] = ((x%50)%20)/10;
	notas[3] = (((x%50)%20)%10)/5;
	notas[4] = ((((x%50)%20)%10)%5)/2;
	notas[5] = (((((x%50)%20)%10)%5)%2)/1;
	
	
	for (i=0 ; i < num ; i++){
		if (!notas[i] == 0){
			if (notas[i] > 1){
				printf("%d notas de ",notas[i]);
				for (j=0 ; j < strlen(notasNomes[i]) ; j++){
					printf("%c",notasNomes[i][j]);
				}
				printf("\n");
			}else{
				printf("%d nota de ",notas[i]);
				for (j=0 ; j < strlen(notasNomes[i]) ; j++){
					printf("%c",notasNomes[i][j]);
				}
				printf("\n");
			}
		}
		
	}
	
	return 0;
}