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


describe('API /fornecedores', () => {
  it('Criar um novo fornecedor', async () => {
    const sendFornecedor = {
      cnpj: '12345678901234',
      nome: 'Fornecedor Teste',
      cep: '12345678',
      numero: '123'
    };
    const receiveFornecedor = {
      cnpj_fornecedor: '12345678901234',
      nome_fornecedor: 'Fornecedor Teste',
      cep_fornecedor: '12345678',
      numero_fornecedor: '123'
    };

    const response = await request(app)
      .post('/fornecedores')
      .send(sendFornecedor)

    expect(response.statusCode).toBe(200);
    expect(response.body).toMatchObject(receiveFornecedor);
  });



  it('Criar um novo fornecedor, cnpj ja existente', async () => {
    const sendFornecedor = {
      cnpj: '12345678901234',
      nome: 'Fornecedor Teste',
      cep: '12345678',
      numero: '123'
    };

    const response = await request(app)
      .post('/fornecedores')
      .send(sendFornecedor)

    expect(response.statusCode).toBe(500);
    expect(response.body.message).toBe("CNPJ já cadastrado.");
  });



  it('Criar um novo fornecedor, cnpj nulo', async () => {
    const sendFornecedor = {
      cnpj: '',
      nome: 'Fornecedor Teste',
      cep: '12345678',
      numero: '123'
    };

    const response = await request(app)
      .post('/fornecedores')
      .send(sendFornecedor)

    expect(response.statusCode).toBe(500);
    expect(response.body.message).toBe("Valores não podem ser vazios");
  });



  it('Obter todos os fornecedores', async () => {
    const sendFornecedor2 = {
        cnpj: '23456789012345',
        nome: 'Fornecedor Teste 2',
        cep: '87654321',
        numero: '456'
      };
    const receiveFornecedores = [
      {
        cnpj_fornecedor: '12345678901234',
        nome_fornecedor: 'Fornecedor Teste',
        cep_fornecedor: '12345678',
        numero_fornecedor: '123' 
      },
      {
        cnpj_fornecedor: '23456789012345',
        nome_fornecedor: 'Fornecedor Teste 2',
        cep_fornecedor: '87654321',
        numero_fornecedor: '456' 
      }
    ];

    const response = await request(app)
      .post('/fornecedores')
      .send(sendFornecedor2)
    expect(response.statusCode).toBe(200);
    expect(response.body).toMatchObject(receiveFornecedores[1]);

    const response2 = await request(app)
      .get('/fornecedores')
    expect(response2.statusCode).toBe(200);
    expect(response2.body).toMatchObject(receiveFornecedores);
  });



  it('Obter um fornecedor pelo cnpj', async () => {
    const sendCnpj = '12345678901234';
    const receiveFornecedor = 
      {
        cnpj_fornecedor: '12345678901234',
        nome_fornecedor: 'Fornecedor Teste',
        cep_fornecedor: '12345678',
        numero_fornecedor: '123' 
      };

    const response = await request(app)
      .get(`/fornecedores/${sendCnpj}`)
    expect(response.statusCode).toBe(200);
    expect(response.body).toMatchObject(receiveFornecedor);
  });



  it('Atualizar um fornecedor pelo cnpj', async () => {
    const sendCnpj = '12345678901234';
    const sendUpdatedFornecedor = 
      {
        cnpj: '12345678901234',
        nome: 'Fornecedor Atualizado',
        cep: '87654321',
        numero: '456' 
      };
    const receiveUpdatedFornecedor = 
      {
        cnpj_fornecedor: '12345678901234',
        nome_fornecedor: 'Fornecedor Atualizado',
        cep_fornecedor: '87654321',
        numero_fornecedor: '456' 
      };

    const response = await request(app)
      .put(`/fornecedores/${sendCnpj}`)
      .send(sendUpdatedFornecedor)

    expect(response.statusCode).toBe(200);
    expect(response.body).toMatchObject(receiveUpdatedFornecedor);
  });



  it('Atualizar um fornecedor pelo cnpj, cnpj nulo', async () => {
    const sendCnpj = '12345678901234';
    const sendUpdatedFornecedor = 
      {
        cnpj: '',
        nome: 'Fornecedor Atualizado',
        cep: '87654321',
        numero: '456' 
      };

    const response = await request(app)
      .put(`/fornecedores/${sendCnpj}`)
      .send(sendUpdatedFornecedor)

    expect(response.statusCode).toBe(500);
    expect(response.body.message).toBe("Valores não podem ser vazios");
  });



  it('Deletar um fornecedor pelo cnpj', async () => {
    const sendCnpj = '12345678901234';
    const receiveFornecedor = 
      {
        cnpj_fornecedor: '12345678901234',
        nome_fornecedor: 'Fornecedor Atualizado',
        cep_fornecedor: '87654321',
        numero_fornecedor: '456' 
      };



    const response = await request(app)
      .get(`/fornecedores/${sendCnpj}`)
    expect(response.statusCode).toBe(200);
    expect(response.body).toMatchObject(receiveFornecedor);

    const response2 = await request(app)
      .delete(`/fornecedores/${sendCnpj}`)
    expect(response2.statusCode).toBe(200);
    expect(response2.body).toMatchObject([receiveFornecedor]);

    const response3= await request(app)
      .get(`/fornecedores/${sendCnpj}`)
    expect(response3.statusCode).toBe(200);
    expect(response3.body).toBe("");
  });
});
