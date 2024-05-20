#include <stdio.h>
#include <string.h>
#include <stdio_ext.h>

typedef struct
{
    char nome[20], email[20], telefone[20];
} Registro;

void lista_registros(FILE *);
void inclui_registro(FILE *);
void exclui_registro(FILE *);
void alterar_registro(FILE *);

int main(int argc, char *argv[])
{
    int opcao;

    FILE *f = fopen("06-06-22.txt", "r+b");
    if (f == NULL)
    {
        printf("Erro no arquivo!\n");
        return -1;
    }

    do
    {
        printf("Escolha uma opcao:\n");
        printf(" 1 - lista registros\n");
        printf(" 2 - inclui registro\n");
        printf(" 3 - alterar registro\n");
        printf(" 4 - exclui registro\n");
        printf(" 0 - sair\n");

        scanf("%d", &opcao);
        __fpurge(stdin);

        switch (opcao)
        {
        case 1:
            lista_registros(f);
            break;
        case 2:
            inclui_registro(f);
            break;
        case 3:
            alterar_registro(f);
            break;
        case 4:
            exclui_registro(f);
            break;
        }

    } while (opcao);

    fclose(f);
    return 0;
}

void lista_registros(FILE *f)
{
    Registro r;

    fseek(f, 0, SEEK_SET);

    fread(&r, sizeof(r), 1, f);
    do
    {
        printf("Nome.: %s", r.nome);
        printf("Email: %s", r.email);
        printf("Fone.: %s\n", r.telefone);
        fread(&r, sizeof(r), 1, f);
    } while (!feof(f));
}

void inclui_registro(FILE *f)
{
    Registro r;
    printf("Entrs com os dados da pessoa:\nNome: ");
    fgets(r.nome, 20, stdin);
    printf("Email: ");
    fgets(r.email, 20, stdin);
    printf("Fone: ");
    fgets(r.telefone, 20, stdin);

    fseek(f, 0, SEEK_END);
    fwrite(&r, sizeof(Registro), 1, f);
}

void alterar_registro(FILE *f)
{
    Registro r;
    char nome[20];

    printf("Nome a alterar: ");
    fgets(nome, 20, stdin);

    // busca por nome
    fseek(f, 0, SEEK_SET);
    fread(&r, sizeof(r), 1, f);
    do
    {
        if (!strcmp(nome, r.nome))
        {
            // le os novos dados a salvar
            printf("Novo nome: ");
            fgets(r.nome, 20, stdin);
            printf("Novo Email: ");
            fgets(r.email, 20, stdin);
            printf("Novo Fone: ");
            fgets(r.telefone, 20, stdin);

            // salva novos valores do registro
            fseek(f, -sizeof(r), SEEK_CUR);
            fwrite(&r, sizeof(Registro), 1, f);
            return;
        }
    } while (!feof(f));
}

void exclui_registro(FILE *f)
{
}
