#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void escreverRand(FILE *arq);

int main()
{
    int i, num, positivo = 0, negativo = 0, temp;

    FILE *arqEE = fopen("Entrada.txt", "wt"); //Abre para gerar valores
    if (arqEE == NULL)
    {
        printf("Erro abrindo arquivo de entrada!\n");
        return 0;
    }
    escreverRand(arqEE);// chama a funçao q gera valores
    fclose(arqEE);// fecha apos gerar os valores

    FILE *arqE = fopen("Entrada.txt", "rt"); //abre para leitura
    if (arqE == NULL)
    {
        printf("Erro abrindo arquivo de entrada!\n");
        return 0;
    }

    FILE *arqS = fopen("Saida.txt", "wt");//abre para escrita
    if (arqS == NULL)
    {
        printf("Erro salvando arquivo de saida!\n");
        return 0;
    }

    while (!feof(arqE)) // loop principal
    {
        fscanf(arqE, "%d", &num);
        if (num == 0) // verificaçao fim do arquivo
        {
            break;
        }
        else if(num <= 100000){ // verificaçao principal
            for (i = 0; i < num; i++)// loop q vai lendo e adicionando nas variaveis
            {
                fscanf(arqE, "%d", &temp);
                if (temp < 0)
                {
                    negativo++;
                } else
                {
                    positivo++;
                }
            }
            //verificaçoes para escrever no arquivo
            if ((negativo > 0)  && (positivo == 0))
            {
                fprintf(arqS, "%d negativos", negativo);
            }
            else if ((negativo == 0) && (positivo > 0))
            {
                fprintf(arqS, "%d positivos", positivo);
            }
            else if ((negativo > 0)  && (positivo > 0))
            {
                fprintf(arqS, "%d negativos, %d positivos", negativo, positivo);
            }
            fprintf(arqS, "\n");

        }
        else// verificaçao numero de casos exedente
        {
            fprintf(arqS, "quantidade inválida de casos\n");
            for (i = 0; i < num; i++)
            {
                fscanf(arqE, "%d", &temp);
            }
        }

        // zera as variaveis antes de recomeçar o loop
        num = 0;
        negativo = 0;
        positivo = 0;
    }
    

    fclose(arqE);//fecha leitura e escrita
    fclose(arqS);
    return 0;
}


void escreverRand(FILE *arq){// funçao q gera um arquivo randomicamente
    int n, temp, temp2, i, j, metade = RAND_MAX/2;
    time_t t;
    srand((unsigned) time(&t));

    printf("Digite a quantidade de casos:\n");
    scanf("%d", &n);

    for (i = 0; i < n; i++)
    {
        temp = rand() % 100000;

        fprintf(arq, "%d", temp);
        for (j = 0; j < temp; j++)
        {
            temp2 = rand() - metade; // gera um numero entre -1073741823 e 1073741823
            fprintf(arq, " %d", temp2);
        }
        fprintf(arq, "\n");
    }
    fprintf(arq, "0");
}
