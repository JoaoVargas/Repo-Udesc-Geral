#include <stdio.h>
#include <stdlib.h>

void mostra_blocos(int **, int *);
void mova_de(int **, int *, int, int);

int main()
{
    int i;
    int *posicoes[5], tamanhos[5];
    char comando[30];

    for (i = 0; i < 5; i++)
    {
        posicoes[i] = malloc(sizeof(int));
        if (posicoes[i] == NULL)
        {
            printf("Erro alocando bloco %d\n", i);
            return -1;
        }
        posicoes[i][0] = i;
        tamanhos[i] = 1;
    }

    // menu
    do
    {
        int a, b;

        // exibe posicoes atuais
        mostra_blocos(posicoes, tamanhos);

        printf("\ncomando: ");
        fgets(comando, 30, stdin);

        // "mova de a para b
        if ((comando[0] == 'm') && (comando[5] == 'd'))
        {
            sscanf(comando, "mova de %d para %d", &a, &b);
            mova_de(posicoes, tamanhos, a, b);

            // "mova a sobre b
        }
        else if ((comando[0] == 'm') && (comando[5] == 'd'))
        {
            sscanf(comando, "mova %d sobre %d", &a, &b);
            //			mova_sobre(posicoes, tamanhos, a, b);

            // empilhe a sobre b
        }
        else if (comando[0] == 'e')
        {
            sscanf(comando, "empilhe %d sobre %d", &a, &b);
            //			empilhe(posicoes, tamanhos, a, b);
        }

    } while ((comando[0] != '\n') && ((comando[0] != 's') && (comando[0] != 'S')));
}

void mostra_blocos(int **p, int *t)
{
    int i, j;

    for (i = 0; i < 5; i++)
    {
        printf("%d -> | ", i);
        for (j = 0; j < t[i]; j++)
            printf("%d |", p[i][j]);
        printf("\n");
    }
}

void mova_de(int **p, int *t, int a, int b)
{
    if ((a >= 0 && a < 5) && (b >= 0 && b < 5))
    {
        if (a != b)
        {
            if ((p[a] == NULL) || (t[a] == 0))
            {
                printf("posicao %d está vazia!\n", a);
            }
            else
            {
                // remove elemento de a
                int elemento = p[a][t[a] - 1];
                if (t[a] == 1)
                { // somente 1 elemento em a
                    t[a] = 0;
                    free(p[a]);
                }
                else
                {
                    t[a]--;
                    p[a] = realloc(p[a], t[a] * sizeof(int));
                }

                // insere elemento em b
                t[b]++;
                p[b] = realloc(p[b], t[b] * sizeof(int));
                p[b][t[b] - 1] = elemento;
            }
        }
        else
        {
            printf("valores iguais!\n");
        }
    }
    else
        printf("valores invalidos!\n");
}
