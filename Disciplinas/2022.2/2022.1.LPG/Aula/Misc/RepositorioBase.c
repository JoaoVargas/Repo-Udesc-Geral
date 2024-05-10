#include <stdio.h>

int main(void) {
	int menu = 1;
	
	while (!menu == 0){
		printf("Qual exercicio?\n0 - Cancela\n1 - Soma\n2- Media\n3 - Troca\n4 - Temperatura\n5 - Contar 100\n6 - Linhas\n7 - Menu Temperatura\n8 - Fatorial\n9 - Fibonacci\n10 - Contrario\n");
		scanf("%d", &menu );
	
		switch(menu){
		
			case 1:
				soma();
				break;
				
			case 2:
				media();
				break;
				
			case 3:
				troca();
				break;
			
			case 4:
				temperatura();
				break;
				
			case 5:
				cem();
				break;
				
			case 6:
				linhas();
				break;
				
			case 7:
				temperaturamenu();
				break;
				
			case 8:
				fatorialmenu();
				break;
				
			case 9:
				fibonaccimenu();
				break;
				
			case 10:
				contratio();
				break;
			case 11:
				compara();
				break;
		}
		printf("\n\n");
		
	}
	
	return 0;
}

void soma(void){
	int resultado, A, B;
	
  	printf("Digite o primeiro numero: ");
	scanf("%d", &A );

	printf("Digite o segundo numero: ");
	scanf("%d", &B );

	resultado = A + B;

	printf("%d + %d = %d", A, B, resultado);
	
	return;
}

void media(void){
	int A, B;
	float resultado;
	
  	printf("Digite o primeiro numero: ");
	scanf("%d", &A );

	printf("Digite o segundo numero: ");
	scanf("%d", &B );

	resultado = ((A*4) + (B*0.6))/2;

	printf("( (%d*4) + (%d*0.6) ) / 2 = %.2f", A, B, resultado);
	
	return;
}

void troca(void){
	int A, B, C;
	
  	printf("Digite o A: ");
	scanf("%d", &A );

	printf("Digite o B: ");
	scanf("%d", &B );
	
	printf("Antes da troca:\nA = %d\nB = %d\n", A, B);

	C = A;
	A = B;
	B = C;

	printf("Depois da troca\nA = %d\nB = %d", A, B);
	
	return;
}

void temperatura(void){
	float C, F;
	
  	printf("Digite os graus Celsius: ");
	scanf("%f", &C );

	F = (9*C+160)/5;
	
	printf("Graus em Celsius: %.2f\nGraus em Fahrenheit: %.2f", C, F);
	
	return;
}

void temperatura2(void){
	float C, F;
	
  	printf("Digite os graus Fahrenheit: ");
	scanf("%f", &C );

	F = (C * (9/5)) + 32;
	
	printf("Graus em Fahrenheit: %.2f\nGraus em Celsius: %.2f", C, F);
	
	return;
}

void temperaturamenu(void){
	char C[1];
	int A;
	
  	printf("Caso queira a conversao Celsius -> Fahrenheit digite C\nCaso queira a conversao Fahrenheit -> Celsius digite F\n");
	scanf("%s", C );
	
	A = C[0];
	
	switch (A){
		case 67:
			temperatura();
			break;
			
		case 70:
			temperatura2();
			break;
	}
	
	return;
}

void cem(void){
	int C;
	for (C = 1;C<101;C++){
		printf("%d\n", C);
	}
	
	return;
}

void linhas(void){
	int C, B, D;
	
	printf("Digite o numero de linhas:\n");
	scanf("%d", &C );
	
	for (B = 1;B<=C;B++){
		for (D = 1;D<=B;D++){
			printf("*");
		}
		printf("\n");
	}
	
	return;
}

void fatorialmenu(void){
	int C;
	
	printf("Digite o numero do fatorial\n");
	scanf("%d", &C );
	
	printf("%d! = %d", C, fatorial(C));
	
	return;
}

int fatorial(int n){
	
	if (n <= 1){
		return 1;
	} else {
		return n*fatorial(n-1);
	}
}

void fibonaccimenu(void){
	int D;
	int i = 1;
	
	
	
	printf("Digite o limite da sequencia\n");
	scanf("%d", &D );
	
	for (i;i<=D;i++){
		printf("%d ",fibonacci(i));
	}
	
	return;
}

int fibonacci(int n){
	
	if (n <= 0){
		return 0;
	} else if(n == 1){
		return 1;
	}else{
		return fibonacci(n-1)+fibonacci(n-2);
	}
}

void contratio(void){
	int i;
	char str[50];
	
	printf("Escreva a palavra: \n");
	getchar();
	fgets(str, 50, stdin);
	
	for (i=strlen(str); i>=0; i--){
		printf("%c",str[i]);
	}
	
	return;
}

void compara(void){
	int i=0, a, chk, j;
	char str[50];
	char str2[50];
	
	printf("Escreva a palavra: \n");
	fgets(str, 50, stdin);
	
	a = strlen(str);
	
	while(str[i]!='\0')
    {
        chk=0;
        if(str[i]==' ')
        {
            j=i;
            while(str[j-1]!='\0')
            {
                str[j] = str[j+1];
                j++;
            }
            chk = 1;
        }
        if(chk==0)
            i++;
    }
	
	for (i=0; i<=a; i++){
		str[i] = tolower(str[i]);
	}
	
	j = 0;
	for( i = a - 1; i >= 0; i--) {
		str2[ j ] = str[ i ];
		j++;
	}
	
	if (strcmp(str, str2) == 0){
		printf("Palindromo\n");
	} else {
		printf("Nao eh palindromo\n");
		printf("%s.\n", str);
		printf("%d.\n", strlen(str));
		printf("%s.\n", str2);
		printf("%d.\n", strlen(str2));
		
	}
	
	
	return;
}

