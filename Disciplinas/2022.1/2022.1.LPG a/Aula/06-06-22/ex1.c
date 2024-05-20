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

	arq = fopen("arquivo.txt", "rt");
	if(arq == NULL) {
		printf("Impossível ler arquivo 'arquivo.txt'!");
		return -1;
	}

	printf("Alunos aprovados:\n");
	while(!feof(arq)) {
		fscanf(arq, "%d", &aluno.codigo);
		if(feof(arq))
			break;
		fscanf(arq, "%s", aluno.nome);
		fscanf(arq, "%s", aluno.curso);
		fscanf(arq, "%f", &aluno.nota);
		if(aluno.nota >= 7.0)
			printf("%s (%s)\n", aluno.nome, aluno.curso);
	}
	
	fclose(arq);

	return 0;
}
