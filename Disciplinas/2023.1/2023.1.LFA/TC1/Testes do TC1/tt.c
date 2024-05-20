#include <stdio.h>
#include <stdlib.h>

#define MAX_STATES 100
#define MAX_ALPHABET 100

int transition_table[MAX_STATES][MAX_ALPHABET];
int final_states[MAX_STATES];
int num_states, num_alphabet, num_final_states;

int main() {
    // leitura do AFD
    printf("Entre com o numero de estados: ");
    scanf("%d", &num_states);

    printf("Entre com o alfabeto de entrada (em uma unica linha, sem espacos): ");
    char alphabet[MAX_ALPHABET];
    scanf("%s", alphabet);
    num_alphabet = strlen(alphabet);

    printf("Entre com a funcao de transicao (uma linha por estado, com os valores separados por espacos): \n");
    for (int i = 0; i < num_states; i++) {
        for (int j = 0; j < num_alphabet; j++) {
            scanf("%d", &transition_table[i][j]);
        }
    }

    printf("Entre com o conjunto de estados finais (em uma unica linha, com os valores separados por espacos): ");
    for (int i = 0; i < num_states; i++) {
        final_states[i] = 0;
    }
    for (int i = 0; i < num_final_states; i++) {
        int state;
        scanf("%d", &state);
        final_states[state] = 1;
    }

    // leitura da palavra
    printf("Entre com a palavra a ser testada: ");
    char word[100];
    scanf("%s", word);

    // processamento da palavra
    int current_state = 0;
    for (int i = 0; i < strlen(word); i++) {
        char c = word[i];
        int index = strchr(alphabet, c) - alphabet;
        if (index < 0 || index >= num_alphabet) {
            printf("Palavra invalida.\n");
            return 0;
        }
        current_state = transition_table[current_state][index];
    }

    // checagem do estado final
    if (final_states[current_state]) {
        printf("Palavra aceita.\n");
    } else {
        printf("Palavra rejeitada.\n");
    }

    return 0;
}