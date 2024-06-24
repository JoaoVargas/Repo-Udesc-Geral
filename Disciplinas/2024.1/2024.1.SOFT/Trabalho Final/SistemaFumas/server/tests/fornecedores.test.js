const request = require('supertest');
const app = require('../index'); // Ajuste o caminho se necessário
const pool = require('../db');

describe("GET /fornecedores/", () => {
    it("Deve devolver todos os itens de Fornecedores", async () => {
        return request(app)
            .get("/fornecedores/")
            .expect('Content-Type', /json/)
            .expect(200)
            .then((res) => {
                expect(res.statusCode).toBe(200);
            })
    });
});