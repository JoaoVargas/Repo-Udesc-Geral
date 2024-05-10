#include <stdio.h>

int main()
{
    float N1, N2, N3, Formula;
    
    scanf(" %f", &N1);
    scanf(" %f", &N2);
    scanf(" %f", &N3);
    
    if (N1 + N2 > N3 && N2 + N3 > N1 && N3 + N1 > N2){
        Formula = N1 + N2 + N3;
        printf("Perimetro = %.1f\n", Formula);
    } else {
        Formula = ((N1 + N2) * N3) / 2;
        printf("Area = %.1f\n", Formula);
    }
    
    return 0;
}
