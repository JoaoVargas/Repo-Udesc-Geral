#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

// Define o tipo Data
typedef struct
{
    int dia;
    int mes;
    int ano;
} Data;

// define o tipo Hora
typedef struct
{
    int hora;
    int min;
} Hora;

// define Evento, que recebe os tipos Data e Hora
typedef struct
{
    Data data;
    Hora inicio;
    Hora fim;
    char local[200];
    char descricao[200];
} Evento;

//define LIstaEventos, recebe um vetor de Evento
typedef struct
{
    Evento *lista;
    int numEventos;
} ListaEventos;
