#include <stdio.h>
#include <stdlib.h>

int main()
{
    int i, num, positivo = 0, negativo = 0, temp;

    FILE *arqE = fopen("Entrada.txt", "rt");
    if (arqE == NULL)
    {
        printf("Erro abrindo arquivo de entrada!\n");
        return 0;
    }

    FILE *arqS = fopen("Saida.txt", "wt");
    if (arqS == NULL)
    {
        printf("Erro salvando arquivo de saida!\n");
        return 0;
    }

    while (!feof(arqE))
    {
        fscanf(arqE, "%d", &num);
        if (num == 0)
        {
            break;
        }
        else if(num <= 100000){
            for (i = 0; i < num; i++)
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
        else
        {
            fprintf(arqS, "quantidade inválida de casos\n");
            for (i = 0; i < num; i++)
            {
                fscanf(arqE, "%d", &temp);
            }
        }
        
        num = 0;
        negativo = 0;
        positivo = 0;
    }
    

    fclose(arqE);
    fclose(arqS);
    return 0;
}