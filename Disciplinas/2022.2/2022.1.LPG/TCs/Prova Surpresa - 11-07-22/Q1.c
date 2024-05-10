#include <stdio.h>

int somaImpar(int);

int main()
{
    int num, final;

    printf("Digite o numero: ");
    scanf("%d", &num);

    final = somaImpar(num);

    printf("somaImpar = %d\n", final);

    return 0;
}

int somaImpar(int n)
{
    if (n == 0)
        return n;
    else if (n % 2 != 0)
        return n + somaImpar(n - 1);
    else
        return somaImpar(n - 1);
}
