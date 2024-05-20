#include "AgendaAux.h"

int addEvento(ListaEventos *);
int faddEvento(ListaEventos *, Evento);
int delEvento(ListaEventos *);
void mostrarTodosEventos(ListaEventos *);
Evento cadastrarData(Evento);
Evento cadastrarHora(Evento);
Evento cadastrarEvento(Evento);
int compararEventos(const void *x, const void *y);
void prepDelEvento(ListaEventos *);
void passarParaTras(ListaEventos *, int);
void mostrarDataEventos(ListaEventos *);
void mostrarCincEventos(ListaEventos *, int, int, int);

int compararEventos(const void *x, const void *y)
{
    int priA = ((Evento *)x)->data.ano, segA = ((Evento *)y)->data.ano;
    int priM = ((Evento *)x)->data.mes, segM = ((Evento *)y)->data.mes;
    int priD = ((Evento *)x)->data.dia, segD = ((Evento *)y)->data.dia;
    int priH = ((Evento *)x)->inicio.hora, segH = ((Evento *)y)->inicio.hora;
    int priMi = ((Evento *)x)->inicio.min, segMi = ((Evento *)y)->inicio.min;

    int diff = priA - segA;

    if (diff == 0)
    {
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
            if (priD < segD)
            {
                diff = -1;
            }
            else if (priD > segD)
            {
                diff = +1;
            }
            else
            {
                if (priH < segH)
                {
                    diff = -1;
                }
                else if (priH > segH)
                {
                    diff = +1;
                }
                else
                {
                    if (priMi < segMi)
                    {
                        diff = -1;
                    }
                    else if (priMi > segMi)
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

Evento cadastrarData(Evento eventoData)
{
    char buffer2[3], buffer4[5];
    int dia, mes, ano, check = 0;

    while (check == 0)
    {
        printf("Digite o dia do Evento: ");
        fflush(stdin);
        if (fgets(buffer2, sizeof(buffer2), stdin))
        {
            if (1 == sscanf(buffer2, "%d", &dia))
            {
                if (dia <= 31 && dia >= 1)
                {
                    eventoData.data.dia = dia;
                    check = 1;
                }
                else
                {
                    printf("Erro lendo o dia, digite uma data valida.\n");
                }
            }
            else
            {
                printf("Erro lendo o dia, digite uma data valida.\n");
            }
        }
    }
    check = 0;

    while (check == 0)
    {
        printf("Digite o mes do Evento: ");
        fflush(stdin);
        if (fgets(buffer2, sizeof(buffer2), stdin))
        {
            if (1 == sscanf(buffer2, "%d", &mes))
            {
                if (mes <= 12 && mes >= 1)
                {
                    eventoData.data.mes = mes;

                    check = 1;
                }
                else
                {
                    printf("Erro lendo o mes, digite uma data valida.\n");
                }
            }
            else
            {
                printf("Erro lendo o mes, digite uma data valida.\n");
            }
        }
    }
    check = 0;

    while (check == 0)
    {
        printf("Digite o ano do Evento: ");
        fflush(stdin);
        if (fgets(buffer4, sizeof(buffer4), stdin))
        {
            if (1 == sscanf(buffer4, "%d", &ano))
            {
                if (ano <= 2100 && ano >= 2020)
                {
                    eventoData.data.ano = ano;
                    check = 1;
                }
                else
                {
                    printf("Erro lendo o ano, digite uma data valida.\n");
                }
            }
            else
            {
                printf("Erro lendo o ano, digite uma data valida.\n");
            }
        }
    }
    check = 0;

    return eventoData;
}

Evento cadastrarHora(Evento eventoHora)
{
    char buffer2[3];
    int horaIni, minutoIni, horaFim, minutoFim, check = 0;

    while (check == 0)
    {
        printf("Digite que horas (0 - 23) ira iniciar o Evento: ");
        fflush(stdin);
        if (fgets(buffer2, sizeof(buffer2), stdin))
        {
            if (1 == sscanf(buffer2, "%d", &horaIni))
            {
                if (horaIni <= 23 && horaIni >= 0)
                {
                    eventoHora.inicio.hora = horaIni;
                    check = 1;
                }
                else
                {
                    printf("Erro lendo a hora, digite uma hora valida. (Padrao militar 0 - 23)\n");
                }
            }
            else
            {
                printf("Erro lendo a hora, digite uma hora valida. (Padrao militar 0 - 23)\n");
            }
        }
    }
    check = 0;

    while (check == 0)
    {
        printf("Digite que minutos (0 - 59) ira iniciar o Evento: ");
        fflush(stdin);
        if (fgets(buffer2, sizeof(buffer2), stdin))
        {
            if (1 == sscanf(buffer2, "%d", &minutoIni))
            {
                if (minutoIni <= 59 && minutoIni >= 0)
                {
                    eventoHora.inicio.min = minutoIni;
                    check = 1;
                }
                else
                {
                    printf("Erro lendo o minuto, digite um minuto valido. (0 - 59)\n");
                }
            }
            else
            {
                printf("Erro lendo o minuto, digite um minuto valido. (0 - 59)\n");
            }
        }
    }
    check = 0;

    while (check == 0)
    {
        printf("Digite que horas (0 - 23) ira acabar o Evento: ");
        fflush(stdin);
        if (fgets(buffer2, sizeof(buffer2), stdin))
        {
            if (1 == sscanf(buffer2, "%d", &horaFim))
            {
                if (horaFim <= 23 && horaFim >= horaIni)
                {
                    eventoHora.fim.hora = horaFim;
                    check = 1;
                }
                else if (horaFim < horaIni)
                {
                    printf("Erro lendo a hora, digite uma hora final maior que a hora inicial.\n");
                }
                else
                {
                    printf("Erro lendo a hora, digite uma hora valida. (Padrao militar 0 - 23)\n");
                }
            }
            else
            {
                printf("Erro lendo a hora, digite uma hora valida. (Padrao militar 0 - 23)\n");
            }
        }
    }
    check = 0;

    while (check == 0)
    {
        printf("Digite que minutos (0 - 59) ira iniciar o Evento: ");
        fflush(stdin);
        if (fgets(buffer2, sizeof(buffer2), stdin))
        {
            if (1 == sscanf(buffer2, "%d", &minutoFim))
            {
                if (minutoFim <= 59 && minutoFim >= 0)
                {
                    if (!(horaIni == horaFim))
                    {
                        eventoHora.fim.min = minutoFim;
                        check = 1;
                    }
                    else if (horaIni == horaFim && minutoFim >= minutoIni)
                    {
                        eventoHora.fim.min = minutoFim;
                        check = 1;
                    }
                    else
                    {
                        printf("Erro lendo o minuto, digite um minuto final maior que o minuto inicial.\n");
                    }
                }
                else
                {
                    printf("Erro lendo o minuto, digite um minuto valido. (0 - 59)\n");
                }
            }
            else
            {
                printf("Erro lendo o minuto, digite um minuto valido. (0 - 59)\n");
            }
        }
    }
    check = 0;

    return eventoHora;
}

Evento cadastrarEvento(Evento evento)
{
    evento = cadastrarData(evento);
    evento = cadastrarHora(evento);
    printf("Digite o local do evento: ");
    scanf("%s", evento.local);
    printf("Digite a descricao do evento: ");
    scanf("%s", evento.descricao);

    return evento;
}

int addEvento(ListaEventos *LP)
{
    Evento buffer;
    int i;

    if (!LP->numEventos)
    {
        LP->lista[LP->numEventos] = cadastrarEvento(LP->lista[LP->numEventos]);
        return 1;
    }
    else
    {
        buffer = cadastrarEvento(buffer);
        for (i = 0; i < LP->numEventos + 1; i++)
        {
            if (LP->lista[i].data.ano == buffer.data.ano && LP->lista[i].data.mes == buffer.data.mes && LP->lista[i].data.dia == buffer.data.dia && LP->lista[i].fim.hora >= buffer.inicio.hora && (LP->lista[i].fim.min >= buffer.inicio.min || LP->lista[i].inicio.min <= buffer.fim.min))
            {
                printf("Erro, ja existe um evento nesse periodo.");
                return 0;
            }
            else
            {
                LP->lista[LP->numEventos] = buffer;
                return 1;
            }
        }
    }
    return 0;
}

int faddEvento(ListaEventos *LP, Evento file)
{
    int i;

    if (!LP->numEventos)
    {
        LP->lista[LP->numEventos] = file;
        LP->numEventos++;
        qsort(LP->lista, LP->numEventos, sizeof(Evento), compararEventos);
        LP->lista = realloc(LP->lista, (LP->numEventos + 1) * sizeof(Evento));
        return 1;
    }
    else
    {
        for (i = 0; i < LP->numEventos + 1; i++)
        {
            if (LP->lista[i].data.ano == file.data.ano && LP->lista[i].data.mes == file.data.mes && LP->lista[i].data.dia == file.data.dia && LP->lista[i].fim.hora >= file.inicio.hora && (LP->lista[i].fim.min >= file.inicio.min || LP->lista[i].inicio.min <= file.fim.min))
            {
                printf("Erro, ja existe um evento nesse periodo. Entrada File");
                return 0;
            }
            else
            {
                LP->lista[LP->numEventos] = file;
                LP->numEventos++;
                qsort(LP->lista, LP->numEventos, sizeof(Evento), compararEventos);
                LP->lista = realloc(LP->lista, (LP->numEventos + 1) * sizeof(Evento));
                return 1;
            }
        }
    }
    return 0;
}

void prepDelEvento(ListaEventos *LP)
{
    Evento buffer;
    char buffer2[3], buffer4[5];
    int dia, mes, ano, horaIni, minutoIni, check = 0, check2 = 0, save = -1, i;

    while (check2 == 0)
    {
        for (i = 0; i < LP->numEventos; i++)
        {
            printf("[%02d] %02d/%02d/%04d - ", i, LP->lista[i].data.dia, LP->lista[i].data.mes, LP->lista[i].data.ano);
            printf("{%02d:%02d - %02d:%02d}\n", LP->lista[i].inicio.hora, LP->lista[i].inicio.min, LP->lista[i].fim.hora, LP->lista[i].fim.min);
        }
        printf("\n");

        while (check == 0)
        {
            printf("Digite o dia do Evento que deseja deletar: ");
            fflush(stdin);
            if (fgets(buffer2, sizeof(buffer2), stdin))
            {
                if (1 == sscanf(buffer2, "%d", &dia))
                {
                    if (dia <= 31 && dia >= 1)
                    {
                        buffer.data.dia = dia;
                        check = 1;
                    }
                    else
                    {
                        printf("Erro lendo o dia, digite uma data valida.\n");
                    }
                }
                else
                {
                    printf("Erro lendo o dia, digite uma data valida.\n");
                }
            }
        }
        check = 0;

        while (check == 0)
        {
            printf("Digite o mes do Evento que deseja deletar: ");
            fflush(stdin);
            if (fgets(buffer2, sizeof(buffer2), stdin))
            {
                if (1 == sscanf(buffer2, "%d", &mes))
                {
                    if (mes <= 12 && mes >= 1)
                    {
                        buffer.data.mes = mes;
                        check = 1;
                    }
                    else
                    {
                        printf("Erro lendo o mes, digite uma data valida.\n");
                    }
                }
                else
                {
                    printf("Erro lendo o mes, digite uma data valida.\n");
                }
            }
        }
        check = 0;

        while (check == 0)
        {
            printf("Digite o ano do Evento que deseja deletar: ");
            fflush(stdin);
            if (fgets(buffer4, sizeof(buffer4), stdin))
            {
                if (1 == sscanf(buffer4, "%d", &ano))
                {
                    if (ano <= 2100 && ano >= 2020)
                    {
                        buffer.data.ano = ano;
                        check = 1;
                    }
                    else
                    {
                        printf("Erro lendo o ano, digite uma data valida.\n");
                    }
                }
                else
                {
                    printf("Erro lendo o ano, digite uma data valida.\n");
                }
            }
        }
        check = 0;

        while (check == 0)
        {
            printf("Digite que horas (0 - 23) do Evento que deseja deletar: ");
            fflush(stdin);
            if (fgets(buffer2, sizeof(buffer2), stdin))
            {
                if (1 == sscanf(buffer2, "%d", &horaIni))
                {
                    if (horaIni <= 23 && horaIni >= 0)
                    {
                        buffer.inicio.hora = horaIni;
                        check = 1;
                    }
                    else
                    {
                        printf("Erro lendo a hora, digite uma hora valida. (Padrao militar 0 - 23)\n");
                    }
                }
                else
                {
                    printf("Erro lendo a hora, digite uma hora valida. (Padrao militar 0 - 23)\n");
                }
            }
        }
        check = 0;

        while (check == 0)
        {
            printf("Digite que minutos (0 - 59) do Evento que deseja deletar: ");
            fflush(stdin);
            if (fgets(buffer2, sizeof(buffer2), stdin))
            {
                if (1 == sscanf(buffer2, "%d", &minutoIni))
                {
                    if (minutoIni <= 59 && minutoIni >= 0)
                    {

                        buffer.inicio.min = minutoIni;
                        check = 1;
                    }
                    else
                    {
                        printf("Erro lendo o minuto, digite um minuto valido. (0 - 59)\n");
                    }
                }
                else
                {
                    printf("Erro lendo o minuto, digite um minuto valido. (0 - 59)\n");
                }
            }
        }
        check = 0;

        for (i = 0; i < LP->numEventos; i++)
        {
            if (LP->lista[i].data.dia == buffer.data.dia && LP->lista[i].data.mes == buffer.data.mes && LP->lista[i].data.ano == buffer.data.ano && LP->lista[i].inicio.hora == buffer.inicio.hora && LP->lista[i].inicio.min == buffer.inicio.hora)
            {
                save = i;
            }
        }

        if (save == -1)
        {
            printf("Evento invalido inserido. Nenhum correspondente cadastrado.\n");
        }
        else
        {
            passarParaTras(LP, save);
            check2 = 1;
        }
    }
}

void passarParaTras(ListaEventos *LP, int x)
{
    Evento temp = LP->lista[x];
    int i;

    for (i = x; i < LP->numEventos + 1; i++)
    {
        LP->lista[i] = LP->lista[i + 1];
    }
    LP->lista[LP->numEventos] = temp;
}

int delEvento(ListaEventos *LP)
{
    if (!LP->numEventos)
    {
        printf("Erro, nenhum Evento disponivel para ser deletado.");
        return 0;
    }
    else
    {
        prepDelEvento(LP);
        return 1;
    }
}

void mostrarTodosEventos(ListaEventos *LP)
{
    int i;

    if (LP->numEventos)
    {
        for (i = 0; i < LP->numEventos; i++)
        {
            printf("%02d/%02d/%04d\n", LP->lista[i].data.dia, LP->lista[i].data.mes, LP->lista[i].data.ano);
            printf("%02d:%02d - %02d:%02d\n", LP->lista[i].inicio.hora, LP->lista[i].inicio.min, LP->lista[i].fim.hora, LP->lista[i].fim.min);
            printf("Local do Evento: %s\n", LP->lista[i].local);
            printf("Descricao do Evento: %s\n", LP->lista[i].descricao);
            if (!(i + 1 == LP->numEventos))
            {
                printf("---\n");
            }
        }
    }
    else
    {

        printf("Erro, nenhum Evento cadastrado.");
    }
}

void mostrarDataEventos(ListaEventos *LP)
{
    Evento buffer;
    char buffer2[3], buffer4[5];
    int dia, mes, ano, check = 0, i, save = 0;

    while (check == 0)
    {
        printf("Digite o dia que deseja pesquisar: ");
        fflush(stdin);
        if (fgets(buffer2, sizeof(buffer2), stdin))
        {
            if (1 == sscanf(buffer2, "%d", &dia))
            {
                if (dia <= 31 && dia >= 1)
                {
                    buffer.data.dia = dia;
                    check = 1;
                }
                else
                {
                    printf("Erro lendo o dia, digite uma data valida.\n");
                }
            }
            else
            {
                printf("Erro lendo o dia, digite uma data valida.\n");
            }
        }
    }
    check = 0;

    while (check == 0)
    {
        printf("Digite o mes que deseja pesquisar: ");
        fflush(stdin);
        if (fgets(buffer2, sizeof(buffer2), stdin))
        {
            if (1 == sscanf(buffer2, "%d", &mes))
            {
                if (mes <= 12 && mes >= 1)
                {
                    buffer.data.mes = mes;
                    check = 1;
                }
                else
                {
                    printf("Erro lendo o mes, digite uma data valida.\n");
                }
            }
            else
            {
                printf("Erro lendo o mes, digite uma data valida.\n");
            }
        }
    }
    check = 0;

    while (check == 0)
    {
        printf("Digite o ano que deseja pesquisar: ");
        fflush(stdin);
        if (fgets(buffer4, sizeof(buffer4), stdin))
        {
            if (1 == sscanf(buffer4, "%d", &ano))
            {
                if (ano <= 2100 && ano >= 2020)
                {
                    buffer.data.ano = ano;
                    check = 1;
                }
                else
                {
                    printf("Erro lendo o ano, digite uma data valida.\n");
                }
            }
            else
            {
                printf("Erro lendo o ano, digite uma data valida.\n");
            }
        }
    }
    check = 0;

    printf("\n");
    for (i = 0; i < LP->numEventos; i++)
    {
        if (LP->lista[i].data.dia == buffer.data.dia && LP->lista[i].data.mes == buffer.data.mes && LP->lista[i].data.ano == buffer.data.ano)
        {
            printf("%02d/%02d/%04d\n", LP->lista[i].data.dia, LP->lista[i].data.mes, LP->lista[i].data.ano);
            printf("%02d:%02d - %02d:%02d\n", LP->lista[i].inicio.hora, LP->lista[i].inicio.min, LP->lista[i].fim.hora, LP->lista[i].fim.min);
            printf("Local do Evento: %s\n", LP->lista[i].local);
            printf("Descricao do Evento: %s\n", LP->lista[i].descricao);
            if (!(i + 1 == LP->numEventos))
            {
                printf("---\n");
            }
            save++;
        }
    }
    if (save == 0)
    {
        printf("Nenhum Evento cadastrado nessa data\n");
    }
}

void mostrarCincEventos(ListaEventos *LP, int dia, int mes, int ano)
{
    int i, save = 0;
    Evento buffer;

    buffer.data.dia = dia;
    buffer.data.mes = mes;
    buffer.data.ano = ano;

    for (i = 0; i < LP->numEventos; i++)
    {

        if (LP->lista[i].data.ano > buffer.data.ano)
        {
            printf("%02d/%02d/%04d\n", LP->lista[i].data.dia, LP->lista[i].data.mes, LP->lista[i].data.ano);
            printf("%02d:%02d - %02d:%02d\n", LP->lista[i].inicio.hora, LP->lista[i].inicio.min, LP->lista[i].fim.hora, LP->lista[i].fim.min);
            printf("Local do Evento: %s\n", LP->lista[i].local);
            printf("Descricao do Evento: %s\n", LP->lista[i].descricao);
            if (!(i + 1 == LP->numEventos))
            {
                printf("---\n");
            }
            save++;
        }
        else if (LP->lista[i].data.ano == buffer.data.ano)
        {
            if (LP->lista[i].data.mes > buffer.data.mes)
            {
                printf("%02d/%02d/%04d\n", LP->lista[i].data.dia, LP->lista[i].data.mes, LP->lista[i].data.ano);
                printf("%02d:%02d - %02d:%02d\n", LP->lista[i].inicio.hora, LP->lista[i].inicio.min, LP->lista[i].fim.hora, LP->lista[i].fim.min);
                printf("Local do Evento: %s\n", LP->lista[i].local);
                printf("Descricao do Evento: %s\n", LP->lista[i].descricao);
                if (!(i + 1 == LP->numEventos))
                {
                    printf("---\n");
                }
                save++;
            }
            else if (LP->lista[i].data.mes == buffer.data.mes)
            {
                if (LP->lista[i].data.dia >= buffer.data.dia)
                {
                    printf("%02d/%02d/%04d\n", LP->lista[i].data.dia, LP->lista[i].data.mes, LP->lista[i].data.ano);
                    printf("%02d:%02d - %02d:%02d\n", LP->lista[i].inicio.hora, LP->lista[i].inicio.min, LP->lista[i].fim.hora, LP->lista[i].fim.min);
                    printf("Local do Evento: %s\n", LP->lista[i].local);
                    printf("Descricao do Evento: %s\n", LP->lista[i].descricao);
                    if (!((i + 1 == LP->numEventos) || save == 5))
                    {
                        printf("---\n");
                    }
                    save++;
                }
            }
        }

        if (save == 5)
        {
            i = LP->numEventos;
        }
    }

    if (save == 0)
    {
        printf("Nenhum Evento cadastrado a partir da data atual.\n");
    }
}