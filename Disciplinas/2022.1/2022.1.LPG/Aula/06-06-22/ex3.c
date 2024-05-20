#include <stdio.h>
#include <stdlib.h>

typedef struct {
	int codigo;
	char nome[20], curso[10];
	float nota;
} Aluno;

int main() {
	FILE *arq;
	Aluno aluno;

	arq = fopen("arquivo.bin", "rb");
	if(arq == NULL) {
		printf("Impossível ler arquivo 'arquivo.bin'!");
		return -1;
	}

	printf("Alunos aprovados:\n");
	while(!feof(arq)) {
		// leitura do arquivo texto
		fread(&aluno, sizeof(aluno), 1, arq);
		if(feof(arq))
			break;
		
		if(aluno.nota >= 7.0)
			printf("%s (%s)\n", aluno.nome, aluno.curso);
	}
	
	fclose(arq);

	return 0;
}
