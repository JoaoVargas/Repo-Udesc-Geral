#include "CalendarioFuncs.h"

int main()
{
	int i = 0;
	Pessoa *lista;
	lista = malloc(sizeof(Pessoa) * (i+1));
	
    int menu = 1;
	while (!menu == 0){
		printf("---Menu do Calendario---\n");
		printf("Pessoas cadastradas: %d\n", i);
		printf("Digite a opçao:\n");
		printf("0 - Sair\n");
		printf("1 - Inserir pessoa\n");
		printf("2 - Deletar pessoa\n");
		printf("3 - Mostrar pessoas cadastradas\n");
		printf("4 - Buscar pessoas pelo nome\n");
		printf("5 - Mostrar aniversariantes do mes\n");
		printf("6 - Mostrar aniversariantes do dia\n");
		scanf("%d", &menu );
		printf("\n");

		switch(menu){
		
			case 1:
				lista[i] = inserirPessoa(lista[i]);
				i++;
				lista = realloc(lista, (i+1) * sizeof(Pessoa));
			break;	

			case 2:
				lista = deletarPessoa(lista, i);
				i--;
				lista = realloc(lista, (i+1) * sizeof(Pessoa));
			break;		

			case 3:
				mostrarPessoas(lista, i);
			break;

			case 4:
				buscarPessoasNomes(lista, i);
			break;

			case 5:
				aniversariantesDoMes(lista, i);
			break;

			case 6:
				aniversariantesDoDia(lista, i);
			break;
				
		}
		printf("\n\n");
		
		lista = ordenarAlfabeticamente(lista, i);
	}
	printf("Adeus...\n");

    return 0;
}
