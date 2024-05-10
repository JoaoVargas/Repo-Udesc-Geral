#include <stdio.h>
#include <stdlib.h>

typedef struct {
	int codigo;
	char nome[20], curso[10];
	float nota;
} Aluno;

int main() {
	FILE *arq, *arq2;
	Aluno aluno;

	arq = fopen("arquivo.txt", "rt");
	if(arq == NULL) {
		printf("Impossível ler arquivo 'arquivo.txt'!");
		return -1;
	}
	arq2 = fopen("arquivo.bin", "wb");
	if(arq == NULL) {
		printf("Impossível criar arquivo 'arquivo.bin'!");
		return -2;
	}

	while(!feof(arq)) {
		// leitura do arquivo texto
		fscanf(arq, "%d", &aluno.codigo);
		if(feof(arq))
			break;
		fscanf(arq, "%s", aluno.nome);
		fscanf(arq, "%s", aluno.curso);
		fscanf(arq, "%f", &aluno.nota);
		
		// gravação do arquivo binário
		fwrite(&aluno, sizeof(aluno), 1, arq2);
	}
	
	fclose(arq);
	fclose(arq2);

	return 0;
}
