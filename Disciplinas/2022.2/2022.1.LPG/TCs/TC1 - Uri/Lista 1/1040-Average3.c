#include <stdio.h>

int main()
{
    float N1, N2, N3, N4, NR, Media;
    
    scanf(" %f", &N1);
    scanf(" %f", &N2);
    scanf(" %f", &N3);
    scanf(" %f", &N4);
    
    Media = ((N1*2)+(N2*3)+(N3*4)+(N4*1))/10;
    
    printf("Media: %.1f\n", Media);
    
    if (Media >= 7.0){
        printf("Aluno aprovado.\n");
    }
    else if (Media < 7.0 && Media >= 5){
        scanf(" %f", &NR);
        printf("Aluno em exame.\n");
        printf("Nota do exame: %.1f\n", NR);
        
        Media = (Media + NR) / 2;
        
        if(Media >= 5){
            printf("Aluno aprovado.\n");
        }
        else if (Media < 5){
            printf("Aluno reprovado.\n");
        }
        
        printf("Media final: %.1f\n", Media);
    }
    else if (Media < 5){
        printf("Aluno reprovado.\n");
    }
    
    
    return 0;
}