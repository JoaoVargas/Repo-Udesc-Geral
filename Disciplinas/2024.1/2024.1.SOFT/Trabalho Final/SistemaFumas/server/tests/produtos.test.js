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


describe('API /produtos', () => {
  it('Criar novos fornecedores', async () => {
    const sendFornecedor = {
      cnpj: '12345678909876',
      nome: 'Fornecedor Produtos 1',
      cep: '12345678',
      numero: '123'
    };
    const receiveFornecedor = {
      cnpj_fornecedor: '12345678909876',
      nome_fornecedor: 'Fornecedor Produtos 1',
      cep_fornecedor: '12345678',
      numero_fornecedor: '123'
    };

    const response = await request(app)
      .post('/fornecedores')
      .send(sendFornecedor)
    expect(response.statusCode).toBe(200);
    expect(response.body).toMatchObject(receiveFornecedor);

    const sendFornecedor2 = {
      cnpj: '12345678900000',
      nome: 'Fornecedor Produtos 2',
      cep: '12345678',
      numero: '123'
    };
    const receiveFornecedor2 = {
      cnpj_fornecedor: '12345678900000',
      nome_fornecedor: 'Fornecedor Produtos 2',
      cep_fornecedor: '12345678',
      numero_fornecedor: '123'
    };

    const response2 = await request(app)
      .post('/fornecedores')
      .send(sendFornecedor2)
    expect(response2.statusCode).toBe(200);
    expect(response2.body).toMatchObject(receiveFornecedor2);
  });



  it('Criar um novo produto', async () => {
    const sendProduto = { 
      cnpjFornecedor: '12345678909876', 
      nome: 'Produto 1', 
      marca: 'Marca Produto 1', 
      tipoUnidade: 'CX',
      quantidade: 5,
      preco: 10.50 
      };
    const receiveProduto = {
      cnpj_fornecedor_produto: '12345678909876', 
      nome_produto: 'Produto 1', 
      marca_produto: 'Marca Produto 1', 
      tipo_unidade_produto: 'CX',
      quantidade_unidades_produto: 5,
      preco_produto: 10.5 
    };

    const response = await request(app)
      .post('/produtos')
      .send(sendProduto)
    expect(response.statusCode).toBe(200);
    expect(response.body).toMatchObject(receiveProduto);
  });



  it('Criar um novo produto, cnpj do fornecedor errado', async () => {
    const sendProduto = { 
      cnpjFornecedor: '00000000000000', 
      nome: 'Produto 1', 
      marca: 'Marca Produto 1', 
      tipoUnidade: 'CX',
      quantidade: 5,
      preco: 10.50 
      };

    const response = await request(app)
      .post('/produtos')
      .send(sendProduto)
    expect(response.statusCode).toBe(500);
    expect(response.body.message).toBe(`insert or update on table "produtos" violates foreign key constraint "cnpj_fornecedor_produto_fkey"`);
  });



  it('Criar um novo produto, cnpj do fornecedor nulo', async () => {
    const sendProduto = { 
      cnpjFornecedor: '', 
      nome: 'Produto 1', 
      marca: 'Marca Produto 1', 
      tipoUnidade: 'CX',
      quantidade: 5,
      preco: 10.50 
      };

    const response = await request(app)
      .post('/produtos')
      .send(sendProduto)
    expect(response.statusCode).toBe(500);
    expect(response.body.message).toBe(`insert or update on table "produtos" violates foreign key constraint "cnpj_fornecedor_produto_fkey"`);
  });



  it('Obter todos os produtos', async () => {
    const sendProduto2 = { 
      cnpjFornecedor: '12345678900000', 
      nome: 'Produto 2', 
      marca: 'Marca Produto 2', 
      tipoUnidade: 'CX',
      quantidade: 5,
      preco: 10.50 
      };
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
        cnpj_fornecedor_produto: '12345678900000', 
        nome_produto: 'Produto 2', 
        marca_produto: 'Marca Produto 2', 
        tipo_unidade_produto: 'CX',
        quantidade_unidades_produto: 5,
        preco_produto: 10.5 
      }
    ];

    const response = await request(app)
      .post('/produtos')
      .send(sendProduto2)
    expect(response.statusCode).toBe(200);
    expect(response.body).toMatchObject(receiveProdutos[1]);

    const response2 = await request(app)
      .get('/produtos')
    expect(response2.statusCode).toBe(200);
    expect(response2.body).toMatchObject(receiveProdutos);
  });



  it('Obter um produto, pelo id', async () => {
    const sendProduto3 = { 
      cnpjFornecedor: '12345678900000', 
      nome: 'Produto 3', 
      marca: 'Marca Produto 3', 
      tipoUnidade: 'CX',
      quantidade: 5,
      preco: 10.50 
    };
    const receiveProduto3 = { 
      cnpj_fornecedor_produto: '12345678900000', 
      nome_produto: 'Produto 3', 
      marca_produto: 'Marca Produto 3', 
      tipo_unidade_produto: 'CX',
      quantidade_unidades_produto: 5,
      preco_produto: 10.50 
    };

    const response = await request(app)
      .post('/produtos')
      .send(sendProduto3)
    expect(response.statusCode).toBe(200);
    expect(response.body).toMatchObject(receiveProduto3);

    const response2 = await request(app)
      .get(`/produtos/${response.body.id_produto}`)
    expect(response2.statusCode).toBe(200);
    expect(response2.body).toMatchObject(receiveProduto3);
  });



  it('Atualizar um produto pelo id', async () => {
    const sendProduto4 = { 
      cnpjFornecedor: '12345678900000', 
      nome: 'Produto 4', 
      marca: 'Marca Produto 4', 
      tipoUnidade: 'CX',
      quantidade: 5,
      preco: 10.50 
    };
    const receiveProduto4 = { 
      cnpj_fornecedor_produto: '12345678900000', 
      nome_produto: 'Produto 4', 
      marca_produto: 'Marca Produto 4', 
      tipo_unidade_produto: 'CX',
      quantidade_unidades_produto: 5,
      preco_produto: 10.50 
    };

    const response = await request(app)
      .post('/produtos')
      .send(sendProduto4)
    expect(response.statusCode).toBe(200);
    expect(response.body).toMatchObject(receiveProduto4);

    const produto4Updated = { 
      cnpj_fornecedor_produto: '12345678900000', 
      nome_produto: 'Produto 4 Atualizado', 
      marca_produto: 'Marca Produto 4 Atualizado', 
      tipo_unidade_produto: 'CX',
      quantidade_unidades_produto: 5,
      preco_produto: 10.50 
    };

    const response2 = await request(app)
      .put(`/produtos/${response.body.id_produto}`)
      .send(produto4Updated)
    expect(response2.statusCode).toBe(200);
    expect(response2.body).toMatchObject(produto4Updated);

    const response3 = await request(app)
      .get(`/produtos/${response.body.id_produto}`)
    expect(response3.statusCode).toBe(200);
    expect(response3.body).toMatchObject(produto4Updated);
  });



  it('Deletar um produto pelo id', async () => {
    const sendProduto5 = { 
      cnpjFornecedor: '12345678900000', 
      nome: 'Produto 4', 
      marca: 'Marca Produto 4', 
      tipoUnidade: 'CX',
      quantidade: 5,
      preco: 10.50 
    };
    const receiveProduto5 = { 
      cnpj_fornecedor_produto: '12345678900000', 
      nome_produto: 'Produto 4', 
      marca_produto: 'Marca Produto 4', 
      tipo_unidade_produto: 'CX',
      quantidade_unidades_produto: 5,
      preco_produto: 10.50 
    };

    const response = await request(app)
      .post('/produtos')
      .send(sendProduto5)
    expect(response.statusCode).toBe(200);
    expect(response.body).toMatchObject(receiveProduto5);


    const response2 = await request(app)
      .get(`/produtos/${response.body.id_produto}`)
    expect(response2.statusCode).toBe(200);
    expect(response2.body).toMatchObject(receiveProduto5);

    const idDelete = response.body.id_produto;

    const response3 = await request(app)
      .delete(`/produtos/${idDelete}`)
    expect(response3.statusCode).toBe(200);
    expect(response3.body).toMatchObject([receiveProduto5]);


    const response4 = await request(app)
      .get(`/produtos/${idDelete}`)
    expect(response4.statusCode).toBe(200);
    expect(response4.body).toBe("");
  });
});
