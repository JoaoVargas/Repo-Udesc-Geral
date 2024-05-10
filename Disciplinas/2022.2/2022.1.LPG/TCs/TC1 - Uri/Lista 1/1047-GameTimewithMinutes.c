#include <stdio.h>


int main()
{
    int h, m, hf, mf, hp, mt, mtf, mp;
    
    scanf("%d %d %d %d", &h, &m, &hf, &mf);
    
    mt = (h*60)+m;
    mtf = (hf*60)+mf;
    
    mp = mtf - mt;
    
    
    
    if (mp > 0){
        printf("O JOGO DUROU %d HORA(S) E %d MINUTO(S)\n", (mp/60), (mp%60));
    } 
    else if (mp == 0){
        printf("O JOGO DUROU %d HORA(S) E %d MINUTO(S)\n", 24, 00);
    }
    else {
        printf("O JOGO DUROU %d HORA(S) E %d MINUTO(S)\n", (((24*60) + mp)/60), (((24*60) + mp)%60));
    }
    
    
    return 0;
}