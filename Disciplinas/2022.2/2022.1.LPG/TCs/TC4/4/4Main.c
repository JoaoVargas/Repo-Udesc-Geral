#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main()
{
    int* linha; 
    linha = malloc(sizeof(int) * 1);
    int i, num, positivo = 0, negativo = 0;

    FILE *arqEE = fopen("Entrada.bin", "wb"); //Abre para gerar valores
    if (arqEE == NULL)
    {
        printf("Erro abrindo arquivo de entrada!\n");
        return 0;
    }
    escreverRand(arqEE);// chama a funçao q gera valores
    fclose(arqEE);// fecha apos gerar os valores

    FILE *arqE = fopen("Entrada.bin", "rb"); //abre para leitura
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
        fread(&num, sizeof(int), 1, arqE);
        
        if (num == 0) // verificaçao fim do arquivo
        {
            break;
        }
        else if(num <= 100000){ // verificaçao principal
            linha = realloc(linha, num * sizeof(int));
            for (i = 0; i < num; i++)// loop q vai lendo e adicionando nas variaveis
            {
                fread(&linha[i], sizeof(int), 1, arqE);
                if (linha[i] < 0)
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
                fread(&linha[i], sizeof(int), 1, arqE);
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
        fwrite(&temp, sizeof(int), 1, arq);

        for (j = 0; j < temp; j++)
        {
            temp2 = rand() - metade; // gera um numero entre -1073741823 e 1073741823
            fwrite(&temp2, sizeof(int), 1, arq);
        }
    }
    fwrite(0, sizeof(int), 1, arq);
}