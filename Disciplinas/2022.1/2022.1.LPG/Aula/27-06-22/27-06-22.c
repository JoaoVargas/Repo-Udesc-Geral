#include <stdio.h>
#include <stdlib.h>

int main()
{
    float a, b, c, d;
    float *e;
    e = &d;
    int i;

    FILE *in = fopen("in.txt", "rt");
    if (in == NULL)
    {
        printf("Erro abrindo arquivo de In!\n");
        return 0;
    }

    FILE *out = fopen("out.txt", "wt");
    if (out == NULL)
    {
        printf("Erro salvando arquivo de Out!\n");
        return 0;
    }

    FILE *out_bin = fopen("out.bin", "wb");
    if (out_bin == NULL)
    {
        printf("Erro salvando arquivo de OutBin!\n");
        return 0;
    } 

    while (!feof(in))
    {
        fscanf(in, "%f", &a);
        printf("%f\n", a);
        fscanf(in, "%f", &b);
        printf("%f\n", b);
        fscanf(in, "%f", &c);
        printf("%f\n", c);

        if (feof(in))
        {
            break;
        }
        

        d = (a + b + c) / 3;
        printf("%f\n", d);
        fprintf(out, "%f\n", d);
        fwrite(e, sizeof(float), 1, out_bin);
        }

    fclose(in);
    fclose(out);
    return 0;
}
