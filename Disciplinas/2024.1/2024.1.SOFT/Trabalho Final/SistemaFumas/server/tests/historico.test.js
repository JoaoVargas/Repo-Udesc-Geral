const request = require('supertest');
const app = require('../index');
const pool = require('../db');

beforeAll(async () => {
  // Executar migrações e seeds aqui, se necessário
});

afterAll(async () => {
  await pool.query('DELETE FROM historico_preco ');
  await pool.query('DELETE FROM historico_estoque');
  await pool.query('DELETE FROM estoque_produtos');
  await pool.query('ALTER SEQUENCE produtos_id_produto_seq RESTART WITH 1;'); 
  await pool.query('ALTER SEQUENCE historico_estoque_id_historico_seq RESTART WITH 1;'); 
  await pool.query('ALTER SEQUENCE historico_preco_id_historico_seq RESTART WITH 1;'); 
  await pool.query('DELETE FROM produtos'); 
  await pool.query('DELETE FROM fornecedores');
  await pool.end(); // Fecha o pool de conexões
});


describe('API /historicos ', () => {
  it('Criar novos fornecedores', async () => {
    const sendFornecedores = [
      {
        cnpj: '12345678909876',
        nome: 'Fornecedor Produtos 1',
        cep: '12345678',
        numero: '123'
      },
      {
        cnpj: '12345678900000',
        nome: 'Fornecedor Produtos 2',
        cep: '12345678',
        numero: '123'
      },
    ]
    const receiveFornecedores = [
      {
        cnpj_fornecedor: '12345678909876',
        nome_fornecedor: 'Fornecedor Produtos 1',
        cep_fornecedor: '12345678',
        numero_fornecedor: '123'
      },
      {
        cnpj_fornecedor: '12345678900000',
        nome_fornecedor: 'Fornecedor Produtos 2',
        cep_fornecedor: '12345678',
        numero_fornecedor: '123'
      },
    ];

    const response = await request(app)
      .post('/fornecedores')
      .send(sendFornecedores[0])
    expect(response.statusCode).toBe(200);
    expect(response.body).toMatchObject(receiveFornecedores[0]);

    const response2 = await request(app)
      .post('/fornecedores')
      .send(sendFornecedores[1])
    expect(response2.statusCode).toBe(200);
    expect(response2.body).toMatchObject(receiveFornecedores[1]);

    const response3 = await request(app)
      .get('/fornecedores')
    expect(response3.statusCode).toBe(200);
    expect(response3.body).toMatchObject(receiveFornecedores);
  });



  it('Criar novos produtos', async () => {
    const sendProdutos = [
      { 
        cnpjFornecedor: '12345678909876', 
        nome: 'Produto 1', 
        marca: 'Marca Produto 1', 
        tipoUnidade: 'CX',
        quantidade: 5,
        preco: 10.50 
      },
      { 
        cnpjFornecedor: '12345678909876', 
        nome: 'Produto 2', 
        marca: 'Marca Produto 2', 
        tipoUnidade: 'CX',
        quantidade: 5,
        preco: 10.50 
      },
      { 
        cnpjFornecedor: '12345678900000', 
        nome: 'Produto 3', 
        marca: 'Marca Produto 3', 
        tipoUnidade: 'CX',
        quantidade: 5,
        preco: 10.50 
      },
    ];
    const receiveProdutos = [
      {
        cnpj_fornecedor_produto: '12345678909876', 
        nome_produto: 'Produto 1', 
        marca_produto: 'Marca Produto 1', 
        tipo_unidade_produto: 'CX',
        quantidade_unidades_produto: 5,
        preco_produto: 10.5 
      },
      {
        cnpj_fornecedor_produto: '12345678909876', 
        nome_produto: 'Produto 2', 
        marca_produto: 'Marca Produto 2', 
        tipo_unidade_produto: 'CX',
        quantidade_unidades_produto: 5,
        preco_produto: 10.5 
      },
      {
        cnpj_fornecedor_produto: '12345678900000', 
        nome_produto: 'Produto 3', 
        marca_produto: 'Marca Produto 3', 
        tipo_unidade_produto: 'CX',
        quantidade_unidades_produto: 5,
        preco_produto: 10.5 
      },
    ];

    const response = await request(app)
      .post('/produtos')
      .send(sendProdutos[0])
    expect(response.statusCode).toBe(200);
    expect(response.body).toMatchObject(receiveProdutos[0]);

    const response2 = await request(app)
      .post('/produtos')
      .send(sendProdutos[1])
    expect(response2.statusCode).toBe(200);
    expect(response2.body).toMatchObject(receiveProdutos[1]);

    const response3 = await request(app)
      .post('/produtos')
      .send(sendProdutos[2])
    expect(response3.statusCode).toBe(200);
    expect(response3.body).toMatchObject(receiveProdutos[2]);
  });



  it('Obter historico de preços por id_produto_estoque', async () => {
    const response = await request(app)
      .get(`/historicos/${1}`)
    expect(response.statusCode).toBe(200);
    expect(response.body[0].id_produto_preco).toBe(1);
    expect(response.body[0].preco_produto_preco).toBe(10.50);
  });



  it('Obter historico de preços por id_produto_estoque, apos atualizar preço', async () => {
    const response = await request(app)
      .get(`/historicos/${1}`)
    expect(response.statusCode).toBe(200);
    expect(response.body[0].id_produto_preco).toBe(1);
    expect(response.body[0].preco_produto_preco).toBe(10.50);

    const updateProduto1 = { 
      cnpj_fornecedor_produto: '12345678909876', 
      nome_produto: 'Produto 1', 
      marca_produto: 'Marca Produto 1', 
      tipo_unidade_produto: 'CX',
      quantidade_unidades_produto: 5,
      preco_produto: 20 
    };

    const response2 = await request(app)
      .put(`/produtos/${1}`)
      .send(updateProduto1)
    expect(response2.statusCode).toBe(200);
    expect(response2.body).toMatchObject(updateProduto1);

    const response3 = await request(app)
      .get(`/historicos/${1}`)
    expect(response3.statusCode).toBe(200);
    expect(response3.body[0].id_produto_preco).toBe(1);
    expect(response3.body[0].preco_produto_preco).toBe(10.50);
    expect(response3.body[1].id_produto_preco).toBe(1);
    expect(response3.body[1].preco_produto_preco).toBe(20);
  });
});
