#include <stdio.h>
#include <stdlib.h>

typedef struct
{
    char nome[100];
    struct
    {
        int dia, mes, ano;
    } data_nascimento;
    float notas[5];
} Aluno;


int main()
{
    float x = 0;
    Aluno temp;

    FILE *in = fopen("Q2in.bin", "rb"); // Abre para ler valores
    if (in == NULL)
    {
        printf("Erro abrindo arquivo de entrada!\n");
        return 0;
    }

    FILE *out = fopen("Q2out.txt", "wt"); // abre para escrita
    if (out == NULL)
    {
        printf("Erro salvando arquivo de saida!\n");
        return 0;
    }

    while (!feof(in))
    // loop principal
    {
        printf("%d\n", !feof(in));
        fread(&temp, sizeof(Aluno), 1, in);

        x = (temp.notas[0] + temp.notas[1] + temp.notas[2] + temp.notas[3] + temp.notas[4]) / 5;

        if (x >= 7)
        {
            fprintf(out, "%s\n", temp.nome);
        }

    }

    fclose(in); // fecha leitura e escrita
    fclose(out);

    return 0;
}
