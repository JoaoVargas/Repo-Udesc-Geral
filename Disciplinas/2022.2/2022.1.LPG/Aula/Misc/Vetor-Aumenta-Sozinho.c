#include <stdio.h>
#include <string.h>
#include <stdlib.h>


int main(void) {
	int* ptr;
    int n = 1, a = 0, i;
	char b = 0;
	
	ptr = malloc(sizeof(int) * n);
	
	printf("Digite os numeros um por um e ao fim digite !:\n");
	scanf(" %c", &b);
	
	for (i = 0 ; !(b == 33) ; i++){
	    a = b - '0';
		
		ptr[n-1] = a;
		
		n++;
		
		ptr = realloc(ptr, (n+1) * sizeof(int));
		
		scanf(" %c", &b);
	}
	
	for (i = 0; i < n - 1; ++i) {
        printf("%d, ", ptr[i]);
    }
	
	free(ptr);
	return 0;
}
