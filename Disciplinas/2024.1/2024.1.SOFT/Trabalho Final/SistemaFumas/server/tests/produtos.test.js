const request = require('supertest');
const app = require('../index');
const pool = require('../db');

// Mock para o pool.query e pool.end
jest.mock('../db', () => ({
  query: jest.fn(),
  end: jest.fn()
}));

describe('API /produtos', () => {
  afterAll(() => {
    pool.end(); // Certifique-se de que o pool de conexões seja fechado
  });

  // Teste para o endpoint de criação de fornecedor
  it('Deve criar um novo  e um produto para ele', async () => {
    const newFornecedor = {
      cnpj: '12345678901234',
      nome: 'Fornecedor Teste',
      cep: '12345678',
      numero: '123'
    };

    pool.query.mockResolvedValueOnce({ rows: [newFornecedor] });

    const response = await request(app)
      .post('/fornecedores')
      .send(newFornecedor);

    expect(response.statusCode).toBe(200);
    expect(response.body).toEqual(newFornecedor);
  });

  it('Deve criar um novo  e um produto para ele', async () => {
    const newProduto = {
      cnpj: '12345678901234',
      nome: 'Produto Teste',
      marca: 'Marca Teste',
      tipo: 'CX',
      quantidade: '12',
      preco: '12.3',
    };

    pool.query.mockResolvedValueOnce({ rows: [newProduto] });

    const response = await request(app)
      .post('/produtos')
      .send(newProduto);

    expect(response.statusCode).toBe(200);
    expect(response.body).toEqual(newProduto);
  });

  // Teste para obter todos os fornecedores
  it('Deve retornar todos os fornecedores', async () => {
    const fornecedores = [
      {
        cnpj: '12345678901234',
        nome: 'Fornecedor Teste 1',
        cep: '12345678',
        numero: '123'
      },
      {
        cnpj: '23456789012345',
        nome: 'Fornecedor Teste 2',
        cep: '87654321',
        numero: '456'
      }
    ];

    pool.query.mockResolvedValueOnce({ rows: fornecedores });

    const response = await request(app)
      .get('/fornecedores');

    expect(response.statusCode).toBe(200);
    expect(response.body).toEqual(fornecedores);
  });

  // Teste para obter um fornecedor pelo CNPJ
  it('Deve retornar um fornecedor pelo CNPJ', async () => {
    const fornecedor = {
      cnpj: '12345678901234',
      nome: 'Fornecedor Teste',
      cep: '12345678',
      numero: '123'
    };

    pool.query.mockResolvedValueOnce({ rows: [fornecedor] });

    const response = await request(app)
      .get('/fornecedores/12345678901234');

    expect(response.statusCode).toBe(200);
    expect(response.body).toEqual(fornecedor);
  });

  // Teste para atualizar um fornecedor
  it('Deve atualizar um fornecedor', async () => {
    const updatedFornecedor = {
      cnpj: '12345678901234',
      nome: 'Fornecedor Atualizado',
      cep: '87654321',
      numero: '321'
    };

    pool.query.mockResolvedValueOnce({ rows: [updatedFornecedor] });

    const response = await request(app)
      .put('/fornecedores/12345678901234')
      .send(updatedFornecedor);

    expect(response.statusCode).toBe(200);
    expect(response.body).toEqual(updatedFornecedor);
  });

  // Teste para deletar um fornecedor
  it('Deve deletar um fornecedor', async () => {
    const fornecedor = {
      cnpj: '12345678901234',
      nome: 'Fornecedor Teste',
      cep: '12345678',
      numero: '123'
    };

    pool.query.mockResolvedValueOnce({ rows: [fornecedor] });

    const response = await request(app)
      .delete('/fornecedores/12345678901234');

    expect(response.statusCode).toBe(200);
    expect(response.body).toEqual([fornecedor]);
  });
});