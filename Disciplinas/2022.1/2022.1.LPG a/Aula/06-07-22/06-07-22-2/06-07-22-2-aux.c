#include <stdio.h>
#include <stdlib.h>

typedef struct
{
    float sepal_length, sepal_width, petal_length, petal_width;
    char class[20];
} Registro;

int main(int argc, char **argv)
{
    Registro r;

    if (argc < 3)
    {
        printf("Use: %s <arquivo entrada> <arquivo saida>\n\n", argv[0]);
        return 1;
    }

    FILE *entrada = fopen(argv[1], "rt");
    if (entrada == NULL)
    {
        printf("Erro abrindo arquivo de entrada\n");
        return -1;
    }

    FILE *saida = fopen(argv[2], "wb");
    if (saida == NULL)
    {
        printf("Erro criando arquivo de saida\n");
        return -2;
    }

    while (!feof(entrada))
    {
        fscanf(entrada, "%f", &r.sepal_length);
        if (feof(entrada))
            break;
        fscanf(entrada, ",%f,%f,%f,%s", &r.sepal_width, &r.petal_length, &r.petal_width, r.class);
        fwrite(&r, 1, sizeof(Registro), saida);
    }

    return 0;
}
