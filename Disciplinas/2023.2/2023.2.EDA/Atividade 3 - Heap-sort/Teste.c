#include <stdio.h>
#include <time.h>

void heapify(int arr[], int n, int i) {
  int maior = i;
  int esq = 2 * i + 1;
  int dir = 2 * i + 2;

  if (esq < n && arr[esq] > arr[maior])
    maior = esq;

  if (dir < n && arr[dir] > arr[maior])
    maior = dir;

  if (maior != i) {
    int temp = arr[i];
    arr[i] = arr[maior];
    arr[maior] = temp;

    heapify(arr, n, maior);
  }
}

void iheapSort(int arr[], int n) {
  for (int i = n / 2 - 1; i >= 0; i--)
    heapify(arr, n, i);

  for (int i = n - 1; i > 0; i--) {
    int temp = arr[0];
    arr[0] = arr[i];
    arr[i] = temp;

    heapify(arr, i, 0);
  }
}

void imprimirArray(int arr[], int n) {
  for (int i = 0; i < n; ++i)
    printf("%d ", arr[i]);
  printf("\n");
}

int main() {
  clock_t clo;
  double time_taken;
  int arr[] = {64, 25, 12, 22, 11, 36, 48, 19, 50, 41};
  int n = sizeof(arr) / sizeof(arr[0]);

  printf("Array original:\n");
  imprimirArray(arr, n);

  clo = clock();

  heapSort(arr, n);

  clo = clock() - clo;
  time_taken = ((double)clo) / CLOCKS_PER_SEC;

  printf("Array ordenado:\n");
  imprimirArray(arr, n);
  printf("%f \n", time_taken);
  return 0;
}
