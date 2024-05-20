#include <stdio.h>
#include <string.h>

typedef struct
{
    char nome[30];
    char email[20];
    char telefone[20];
    char removido;
} Registro;

void inclusao(FILE *arq)
{
    Registro r;

    printf("\nNome....: ");
    scanf("%s", r.nome);
    printf("Email...: ");
    scanf("%s", r.email);
    printf("Telefone: ");
    scanf("%s", r.telefone);

    // apontar para o final do arquivo
    fseek(arq, 0, SEEK_END);
    // gravar registro
    fwrite(&r, sizeof(r), 1, arq);
}

void listar(FILE *arq)
{
    Registro r;

    // vai para o inicio do arquivo
    fseek(arq, 0, SEEK_SET);

    // le cada registro e exibe
    fread(&r, sizeof(r), 1, arq);
    do
    {
        printf("Nome....: %s\n", r.nome);
        printf("Email...: %s\n", r.email);
        printf("Telefone: %s\n\n", r.telefone);
        fread(&r, sizeof(r), 1, arq);
    } while (!feof(arq));
}

int altera_registro(FILE *arq)
{
    Registro r;
    char nome[30];

    // vai para o inicio do arquivo
    fseek(arq, 0, SEEK_SET);

    printf("Nome a alterar: ");
    scanf("%s", nome);

    // busca nome
    fread(&r, sizeof(r), 1, arq);
    while ((strcmp(nome, r.nome)) && (!feof(arq)))
    {
        fread(&r, sizeof(r), 1, arq);
    }
    if (feof(arq))
        return -1;

    fseek(arq, -sizeof(r), SEEK_CUR);
    printf("\nNovos dados:\n");
    printf("Nome....: ");
    scanf("%s", r.nome);
    printf("Email...: ");
    scanf("%s", r.email);
    printf("Telefone: ");
    scanf("%s", r.telefone);
    fwrite(&r, sizeof(r), 1, arq);
    return 0;
}

int main()
{
    int opcao;

    FILE *f = fopen("06-06-22-2.bin", "r+b");
    if (f == NULL)
    {
        printf("Erro no arquivo!\n");
        return -1;
    }

    // Menu
    do
    {
        do
        {
            printf("Escolha uma opcao:\n");
            printf("  [1]: incluir registro\n");
            printf("  [2]: listar registros\n");
            printf("  [3]: alterar registro\n");
            printf("  [4]: remover registro\n");
            printf("  [0]: sair\n");
            scanf("%d", &opcao);
        } while ((opcao < 0) || (opcao > 4));

        switch (opcao)
        {
        case 1:
            inclusao(f);
            break;

        case 2:
            listar(f);
            break;

        case 3:
            altera_registro(f);
            break;

        case 4:
            break;
        }

    } while (opcao);

    fclose(f);

    return 0;
}
