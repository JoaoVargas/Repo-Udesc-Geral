#include <stdio.h>
#include <stdlib.h>

typedef struct
{
    int dia;
    int mes;
    int ano;
} Data;

typedef struct
{
    int hora;
    int min;
} Hora;

typedef struct
{
    Data data;
    Hora inicio;
    Hora fim;
    char local[200];
    char descricao[200];
} Evento;

typedef struct
{
    Evento *lista;
    int numEventos;
} ListaEventos;

int compararEventos (const void *x, const void *y) {
    int priA = ((Evento *)x)->data.ano, segA = ((Evento *)y)->data.ano;
    int priM = ((Evento *)x)->data.mes, segM = ((Evento *)y)->data.mes;
    int priD = ((Evento *)x)->data.dia, segD = ((Evento *)y)->data.dia;
    int priH = ((Evento *)x)->inicio.hora, segH = ((Evento *)y)->inicio.hora;
    int priM = ((Evento *)x)->inicio.min, segM = ((Evento *)y)->inicio.min;

    int diff = priA - segA;

    if (diff == 0) {
        if (priM < segM)
        {
            diff = -1;
        }
        else if (priM > segM)
        {    
            diff = +1;
        }
        else
        {
            if (priD < priD)
            {
                diff = -1;
            }
            else if (priD > priD)
            {    
                diff = +1;
            }
            else
            {
                if (priH < priH)
                {
                    diff = -1;
                }
                else if (priH > priH)
                {    
                    diff = +1;
                }
                else
                {
                    if (priM < priM)
                    {
                        diff = -1;
                    }
                    else if (priM > priM)
                    {    
                        diff = +1;
                    }
                    else
                    {
                        diff = 0;
                    }
                }
            }
        }
    }
    
    return diff;
}

int main()
{
    int i;

    FILE *escrita = fopen("Entrada.bin", "wb"); 
    // if (escrita == NULL)
    // {
    //     printf("Erro abrindo arquivo de entrada!\n");
    //     return 0;
    // }

    // FILE *leitura = fopen("Entrada.bin", "rb");
    // if (leitura == NULL)
    // {
    //     printf("Erro abrindo arquivo de entrada!\n");
    //     return 0;
    // }

    ListaEventos *LP;
    LP->numEventos = 0;
    LP->lista = malloc(sizeof(Evento) * (LP->numEventos + 1));

    LP->lista[LP->numEventos].data.dia = 2;
    LP->numEventos++;
    LP->lista = realloc(LP->lista, (LP->numEventos + 1) * sizeof(Evento));

    LP->lista[0].data.ano = 10;
    LP->lista[0].data.mes = 2;
    LP->lista[0].data.dia = 4;

    LP->numEventos++;
    LP->lista = realloc(LP->lista, (LP->numEventos + 1) * sizeof(Evento));

    LP->lista[1].data.ano = 15;
    LP->lista[1].data.mes = 2;
    LP->lista[1].data.dia = 3;

    LP->numEventos++;
    LP->lista = realloc(LP->lista, (LP->numEventos + 1) * sizeof(Evento));

    LP->lista[2].data.ano = 10;
    LP->lista[2].data.mes = 2;
    LP->lista[2].data.dia = 2;

    LP->numEventos++;
    LP->lista = realloc(LP->lista, (LP->numEventos + 1) * sizeof(Evento));

    LP->lista[3].data.ano = 15;
    LP->lista[3].data.mes = 1;
    LP->lista[3].data.dia = 1;

    printf("%d / %d / %d\n",LP->lista[0].data.dia, LP->lista[0].data.mes, LP->lista[0].data.ano);
    printf("%d / %d / %d\n",LP->lista[1].data.dia, LP->lista[1].data.mes, LP->lista[1].data.ano);
    printf("%d / %d / %d\n",LP->lista[2].data.dia, LP->lista[2].data.mes, LP->lista[2].data.ano);
    printf("%d / %d / %d\n",LP->lista[3].data.dia, LP->lista[3].data.mes, LP->lista[3].data.ano);

    printf("\n");

    qsort(LP->lista, LP->numEventos, sizeof(Evento), compararEventos);

    printf("%d / %d / %d\n",LP->lista[0].data.dia, LP->lista[0].data.mes, LP->lista[0].data.ano);
    printf("%d / %d / %d\n",LP->lista[1].data.dia, LP->lista[1].data.mes, LP->lista[1].data.ano);
    printf("%d / %d / %d\n",LP->lista[2].data.dia, LP->lista[2].data.mes, LP->lista[2].data.ano);
    printf("%d / %d / %d\n",LP->lista[3].data.dia, LP->lista[3].data.mes, LP->lista[3].data.ano);
    

    
    // char line[3], line2[3];
    // int i, j;
    // if(fgets(line, sizeof(line), stdin)){
    //     if (1 == sscanf(line, "%d", &i)) {
    //         printf("%d\n", i);
            
    //     } 
    //     else
    //     {
    //         printf("erro\n");
    //     }
    // }
    // fflush (stdin);
    // if(fgets(line2, sizeof(line2), stdin)){
    //     if (1 == sscanf(line2, "%d", &j)) {
    //         printf("%d\n", j);
            
    //     } 
    //     else
    //     {
    //         printf("erro\n");
    //     }
    // }




    return 0;
}