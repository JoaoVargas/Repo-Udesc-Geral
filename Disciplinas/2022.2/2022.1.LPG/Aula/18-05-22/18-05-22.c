struct Produto {int codigo;char descricao[20];float preco;};

void le_produto( struct Produto *p ){scanf("%d", &p->codigo);scanf("%s", p->descricao);scanf("%f", &p->preco);}

int main(){struct Produto k;le_produto( &k );mostra_produto( k );}
