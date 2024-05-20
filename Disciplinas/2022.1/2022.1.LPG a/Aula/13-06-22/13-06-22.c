#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void ex1(void);
void ex2(void);

int main()
{
    int menu = 1;
    while (!(menu == 0))
    {
        printf("----Menu----\n");
        printf("0) Exit\n");
        printf("1) Ex 1\n");
        printf("2) Ex 2\n");

        scanf("%d", &menu);

        printf("\n");

        switch (menu)
        {
        case 1:
            ex1();
            printf("\n\n");
            break;

        case 2:
            ex2();
            printf("\n\n");
            break;

        default:
            break;
        }
    }


    return 0;
}

void ex1(void)
{
    int x, y, *p;

    y = 0;
    printf("y = 0 -> y = %d\n", y);
    p = &y;
    printf("p = &y -> p = %ls\n", p);
    x = *p;
    printf("x = *p -> x = %d\n", x);
    x = 4;
    printf("x = 4 -> x = %d\n", x);
    (*p)++;
    printf("(*p)++ -> p = %ls, *p = %d\n", p, *p);
    --x;
    printf("--x -> x = %d\n", x);
    (*p) += x;
    printf("(*p) += x -> p = %ls, *p = %d\n", p, *p);

    return;
}

void ex2(void)
{
    int x, *p;
    x = 100;
    // p = x; ta errado pq ss
    p = &x;
    printf("Valor de p: %d.n", *p);

    printf("\n\n\n");

    int ia = 10, ja = 2;
    int *temp, *i = &ia, *j = &ja;
    *temp = *i;
    *i = *j;
    *j = *temp;
    printf("i = %d, j = %d", *i, *j);

    printf("\n\n\n");

    char *a, *b;
    a = "abacate";
    b = "uva";

    // if (a < b) cringe
    if (strcmp(&a, &b))
    {
        printf("%s vem antes de %s no dicionário", a, b);
    }
    else
    {
        printf("%s vem depois de %s no dicionário", a, b);
    }
}