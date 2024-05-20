#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct
{
    int codigo;
    char nome[30];
    int vendas;
} Vendedor;

typedef struct
{
    int codigo;
    char produto[30];
    int vendas;
} Venda;

int lerVendedores(Vendedor **);
int lerVendas(Venda **);
int salvaVendedores(Vendedor *, int);

int main()
{
    Vendedor *vendedores = NULL;
    Venda *vendas = NULL;
    int num_vendedores, num_vendas, i;

    num_vendedores = lerVendedores(&vendedores);
    if (num_vendedores < 1)
        return -1;

    printf("Vendedores:\n");
    for (i = 0; i < num_vendedores; i++)
    {
        printf("%d, %s %d\n", vendedores[i].codigo, vendedores[i].nome, vendedores[i].vendas);
    }

    num_vendas = lerVendas(&vendas);
    if (vendas == NULL)
        return -2;

    printf("\nVendas:\n");
    for (i = 0; i < num_vendas; i++)
    {
        printf("%d %s %d\n", vendas[i].codigo, vendas[i].produto, vendas[i].vendas);
    }

    // atualizar quantidade de vendas por vendedor
    for (i = 0; i < num_vendedores; i++)
    {
        vendedores[i].vendas = 0; // zera quantidades de venda de todos
        for (int j = 0; j < num_vendas; j++)
        {
            if (vendas[j].codigo == vendedores[i].codigo)
                vendedores[i].vendas += vendas[j].vendas; // soma quantidades
        }
    }

    // exibe alterados / corrigidos
    printf("Vendedores [ATUALIZADO]:\n");
    for (i = 0; i < num_vendedores; i++)
    {
        printf("%d, %s %d\n", vendedores[i].codigo, vendedores[i].nome, vendedores[i].vendas);
    }

    // salva arquivo alterado/corrigido
    salvaVendedores(vendedores, num_vendedores);

    return 0;
}

// para aqualizar o ponteiro, utilizamos ponteiro de ponteiro
// por isso aqui vamos atualizar em (*vendedores)
int lerVendedores(Vendedor **vendedores)
{
    Vendedor vendedor;
    int n = 0;

    FILE *arq = fopen("vendedores.txt", "rt");
    if (arq == NULL)
    {
        printf("Erro abrindo arquivo de vendedores!\n");
        return 0;
    }

    while (!feof(arq))
    {
        fscanf(arq, "%d", &vendedor.codigo);
        if (feof(arq))
            break;
        n++;
        fscanf(arq, "%s %d", vendedor.nome, &vendedor.vendas);
        *vendedores = realloc(*vendedores, n * sizeof(Vendedor));

        (*vendedores)[n - 1].codigo = vendedor.codigo;
        strcpy((*vendedores)[n - 1].nome, vendedor.nome);
        (*vendedores)[n - 1].vendas = vendedor.vendas;
    }
    fclose(arq);

    return n;
}

int lerVendas(Venda **vendas)
{
    Venda venda;
    int n = 0;

    FILE *arq = fopen("vendas.txt", "rt");
    if (arq == NULL)
    {
        printf("Erro abrindo arquivo de vendas!\n");
        return 0;
    }

    while (!feof(arq))
    {
        fscanf(arq, "%d", &venda.codigo);
        if (feof(arq))
            break;
        n++;
        fscanf(arq, "%s %d", venda.produto, &venda.vendas);
        *vendas = realloc(*vendas, n * sizeof(Venda));

        (*vendas)[n - 1].codigo = venda.codigo;
        strcpy((*vendas)[n - 1].produto, venda.produto);
        (*vendas)[n - 1].vendas = venda.vendas;
    }
    fclose(arq);

    return n;
}

int salvaVendedores(Vendedor *vendedores, int n)
{
    int i;

    FILE *arq = fopen("vendedores.txt", "wt");
    if (arq == NULL)
    {
        printf("Erro salvando arquivo de vendedores!\n");
        return 0;
    }

    for (i = 0; i < n; i++)
    {
        fprintf(arq, "%d, %s %d\n", vendedores[i].codigo, vendedores[i].nome, vendedores[i].vendas);
    }
    fclose(arq);

    return n;
}
