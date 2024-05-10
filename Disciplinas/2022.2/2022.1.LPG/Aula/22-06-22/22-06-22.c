#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int **array, n;

void mostrarArray(void);
void movaTodosAparaB(void);
void movaAparaB(void);
void movaAsobreB(void);
void empilheAsobreB(void);
int acharCords(int, int[]);

int main()
{
    int i, j, menu = 1;
    size_t temp;

    printf("\n");
    printf("Digite o tamanho do vetor: ");
    scanf("%d", &n);

    array = malloc(sizeof(int *) * n); // Aloca dinamicamente o tamanho do array de ponteiros
    for (i = 0; i < n; i++)            // Aloca memoria para cada ponteiro
    {
        array[i] = malloc(sizeof(int) * (2));
        array[i][0] = 1;
        array[i][1] = i;
    }

    while (!menu == 0)
    {
        for (i = 0; i < n; i++)
        {
            for (j = 0; j < array[i][0] + 1; j++)
            {
                printf("%d ", array[i][j]);
            }
            printf("\n");
        }

        printf("Pilhas de Caixas:\n");
        mostrarArray();
        printf("--MENU--\n");
        printf("0 - Cancela\n");
        printf("1 - Mova o topo da posicao A para o topo da posicao B\n");
        printf("2 - Mova o bloco A sobre o bloco B\n");
        printf("3 - Empilhe o bloco A e os que tao em cima sobre B\n");
        printf("4 - Mova todos os blocos de A para cima de B\n");
        printf("Opcao: ");
        scanf("%d", &menu);
        printf("\n");

        switch (menu)
        {
        case 1:
            movaAparaB();
            break;

        case 2:
            movaAsobreB();
            break;

        case 3:
            empilheAsobreB();
            break;

        case 4:
            movaTodosAparaB();
            break;

        default:
            break;
        }
    }
    free(array);
    return 0;
}

int acharCords(int num, int cords[])
{
    int i, j;

    for (i = 0; i < n; i++)
    {
        for (j = 1; j < array[i][0] + 1; j++)
        {
            if (num == array[i][j])
            {
                cords[0] = i;
                cords[1] = j;
                return 1;
            }
        }
    }
    return 0;
}

void mostrarArray()
{
    int i, j;

    for (i = 0; i < n; i++)
    {
        printf("%d - ", i);
        for (j = 0; j < array[i][0]; j++)
        {
            printf("[%d]", array[i][j + 1]);
        }
        printf("\n");
    }
    printf("\n");
}

void movaTodosAparaB()
{
    int a, b, i, j, oldSizeA, oldSizeB, curSizeB;

    printf("Digite a posicao A: ");
    scanf("%d", &a);
    printf("Digite a posicao B: ");
    scanf("%d", &b);

    oldSizeA = array[a][0];
    oldSizeB = array[b][0];

    if (array[a][0])
    {
        array[b] = realloc(array[b], ((oldSizeA + oldSizeB) * sizeof(int)));
        array[b][0] += oldSizeA;

        for (i = 0; i < oldSizeA; i++)
        {
            array[b][oldSizeB + i + 1] = array[a][i + 1];
        }
        array[a] = realloc(array[a], 1);
        array[a][0] = 0;
    }
    else
    {
        printf("Impossível, %d nao possui nenhuma caixa\n", a);
    }
}

void movaAparaB()
{
    int a, b, i, j, oldSizeA, oldSizeB, curSizeB;

    printf("Digite a posicao A: ");
    scanf("%d", &a);
    printf("Digite a posicao B: ");
    scanf("%d", &b);

    oldSizeA = array[a][0];
    oldSizeB = array[b][0];

    if (array[a][0])
    {
        array[b][0]++;
        array[b] = realloc(array[b], ((oldSizeB + 1) * sizeof(int)));

        array[b][array[b][0]] = array[a][array[a][0]];

        array[a] = realloc(array[a], array[a][0] * sizeof(int));
        array[a][0]--;
    }
    else
    {
        printf("Impossível, %d nao possui nenhuma caixa\n", a);
    }
}

void movaAsobreB(void)
{
    int a, b, cordsA[2], cordsB[2], i;

    printf("Digite o bloco A: ");
    scanf("%d", &a);
    printf("Digite o bloco B: ");
    scanf("%d", &b);

    if (acharCords(a, cordsA) && acharCords(b, cordsB))
    {
        array[cordsB[0]][0]++;
        array[cordsB[0]] = realloc(array[cordsB[0]], (array[cordsB[0]][0] + 1) * sizeof(int));

        for (i = array[cordsB[0]][0]; i > cordsB[1]; i--)
        {
            array[cordsB[0]][i + 1] = array[cordsB[0]][i];
        }

        array[cordsB[0]][cordsB[1] + 1] = array[cordsA[0]][cordsA[1]];

        for (i = cordsA[1]; i < array[cordsA[0]][0]; i++)
        {
            array[cordsA[0]][i] = array[cordsA[0]][i + 1];
        }

        array[cordsA[0]] = realloc(array[cordsA[0]], array[cordsA[0]][0] * sizeof(int));
        array[cordsA[0]][0]--;
    }
    else if ((!acharCords(a, cordsA)) && (!acharCords(b, cordsB)))
    {
        printf("Impossível, nao existe a caixa A nem a B\n");
    }
    else if (!acharCords(a, cordsA))
    {
        printf("Impossível, nao existe a caixa A\n");
    }
    else
    {
        printf("Impossível, nao existe a caixa B\n");
    }
}

void empilheAsobreB(void)
{
    int a, b, cordsA[2], cordsB[2], i;

    printf("Digite o bloco A: ");
    scanf("%d", &a);
    printf("Digite o bloco B: ");
    scanf("%d", &b);

    if (acharCords(a, cordsA) && acharCords(b, cordsB))
    {
        array[cordsB[0]][0] += array[cordsA[0]][0] - cordsA[1] + 1;
        array[cordsB[0]] = realloc(array[cordsB[0]], (array[cordsB[0]][0] + 1) * sizeof(int));
        printf("%d %d\n%d %d\n", cordsA[0], cordsA[1], cordsB[0], cordsB[1]);
        for (i = array[cordsB[0]][0]; i > (cordsB[1] + array[cordsA[0]][0] - cordsA[1] + 1); i--)
        {
            array[cordsB[0]][i] = array[cordsB[0]][cordsB[1] + 1];
        }

        for (i = 0; i < array[cordsA[0]][0] - cordsA[1] + 1; i++)
        {
            array[cordsB[0]][i + 1 + cordsB[1]] = array[cordsA[0]][cordsA[1] + i];
        }

        array[cordsA[0]] = realloc(array[cordsA[0]], cordsA[1] * sizeof(int));
        array[cordsA[0]][0] = cordsA[1] - 1;
    }
    else if ((!acharCords(a, cordsA)) && (!acharCords(b, cordsB)))
    {
        printf("Impossível, nao existe a caixa A nem a B\n");
    }
    else if (!acharCords(a, cordsA))
    {
        printf("Impossível, nao existe a caixa A\n");
    }
    else
    {
        printf("Impossível, nao existe a caixa B\n");
    }
}
