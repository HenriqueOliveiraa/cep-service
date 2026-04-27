# CEP Service

Microsserviço para consulta de endereços por CEP. As consultas são registradas em um banco PostgreSQL.

## Tecnologias

- Java 17 + Spring Boot
- PostgreSQL
- Docker / Docker Compose
- WireMock

## Como Executar

```bash
docker-compose up --build
```

A aplicação sobe em `http://localhost:8080`.

## Endpoint

```
GET /cep/{cep}
```

**Exemplo:**
```bash
curl http://localhost:8080/cep/01310100
```

**Resposta:**
```json
{
  "cep": "01310-100",
  "logradouro": "Avenida Paulista",
  "bairro": "Bela Vista",
  "cidade": "São Paulo",
  "uf": "SP"
}
```

| Status | Motivo |
|--------|--------|
| 400 | CEP inválido |
| 404 | CEP não encontrado |
