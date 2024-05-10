#include <stdio.h>
#include <stdlib.h>

typedef struct {
	int codigo;
	char nome[20], curso[10];
	float nota;
} Aluno;

int main() {
	FILE *arq, *aprovados, *reprovados;
	Aluno aluno;

	arq = fopen("arquivo.bin", "rb");
	if(arq == NULL) {
		printf("Impossível ler arquivo 'arquivo.bin'!");
		return -1;
	}
	aprovados = fopen("aprovados.bin", "wb");
	if(arq == NULL) {
		printf("Impossível abrir arquivo 'aprovados.bin'!");
		return -1;
	}
	reprovados = fopen("reprovados.bin", "wb");
	if(arq == NULL) {
		printf("Impossível abrir arquivo 'reprovados.bin'!");
		return -1;
	}

	printf("Alunos aprovados:\n");
	while(!feof(arq)) {
		// leitura do arquivo texto
		fread(&aluno, sizeof(aluno), 1, arq);
		if(feof(arq))
			break;
		
		if(aluno.nota >= 7.0)
			fwrite(&aluno, sizeof(aluno), 1, aprovados);
		else
			fwrite(&aluno, sizeof(aluno), 1, reprovados);
	}
	
	fclose(arq);
	fclose(aprovados);
	fclose(reprovados);

	return 0;
}
