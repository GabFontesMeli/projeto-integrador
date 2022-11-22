# Projeto Integrador

## Requisitos Obrigatórios: 📝

### 1 - Inserir um lote de produtos no armazém de distribuição PARA registrar a existência de estoque;

### 2 - Adicionar produtos ao carrinho de compras doMarketplace PARA comprá-los, se desejar;

### 3 - Consultar um produto em stock no armazem para saber a sua localização num setor e os diferentes lotes onde se encontra;

### 4 - Consultar um produto em todos os armazens para saber o estoque em cada armazem do referido produto;

### 5 - Consultar os produtos em estoque estão prestes a expirar no almoxarifado, a fim de aplicar alguma ação comercial com eles;

## Requisitos Individuais: 📝

## Features - Ana Paula - SalesProductReport 
### O foco desse relatório é ter um gerenciamento do fluxo de vendas.

### Nesse requisito foram criados dois endpoints:
- Endpoint 1: SalesProductReport/periodo
Consulta o relatório de vendas dos produtos por período

- Endpoint 2: SalesProductReport/IdUser/periodo
Consulta por período/usuário
dessa forma é retornado  uma lista com os produtos e quantidade que foram vendidos.

## Features - Iara
CRUD completo de usuário

- Endpoint para buscar todos os usuários cadastrados no banco de dados
- Endpoint para buscar um usuário especiífico cadastrado no banco de dados
- Endpoint para atualizar um usuário existente
- Endpoint para deletar um usuário

## Buscar todos os usuários
Retorna uma lista dos usuários cadastrados no banco de dados
##### `GET`
```sh
http://localhost:8080/api/v1/user
```
### Retorno
`200 - Ok`
```sh
[
    {
        "id": 1,
        "name": "name",
        "email": "email",
        "userType": "seller"
    }
]
```

## Buscar usuário por id
Retorna um usuário pelo id
##### `GET`
```sh
http://localhost:8080/api/v1/user/{userId}
```

### Retorno
`200 - Ok`
```sh
{
    "id": 1,
    "name": "name",
    "email": "email",
    "userType": "seller"
}
```

## Atualizar usuário
Atualiza um usuário
Apenas nome e e-mail podem ser alterados
##### `PUT`
```sh
http://localhost:8080/api/v1/user/{userId}
```
### Body
```sh
{
    "name": "name",
    "email": "email"
}
```
### Retorno
`202 - Accepted`
```sh
{
    "id": 1,
    "name": "name",
    "email": "email",
    "userType": "seller"
}
```

## Deletar usuário
Atualiza um usuário
Apenas nome e e-mail podem ser alterados
##### `DELETE`
```sh
http://localhost:8080/api/v1/user/{userId}
```

### Retorno
`204 - No Content`
```sh

```

## Features - Fontes

### Relatório Financeiro das vendas realizadas de um determinado período

- Endpoint que retorna informações das vendas realizadas dentro do período fornecido

Ao fornecer uma data de `início` e uma data `limite`, será feita uma requisição ao banco de dados em busca das vendas realizadas dentro deste período fornecido. E caso elas existam, a rota retornará uma lista contendo informações destas vendas, junto do valor total somado.

### Possíveis exceções da rota

- As datas fornecidas ao endpoint devem seguir estritamente o formato `aaaa-mm-dd`(ano-mês-dia), caso contrário, será retornado uma exceção do tipo `InvalidDateFormatException` junto ao status code `400 BAD_REQUEST`.

- Também é possível que não exista nenhuma venda realizada dentro do período passado. Caso isso aconteca, será retornado uma exceção do tipo `CartNotFoundException` junto ao status code `404 NOT_FOUND`("Cart" é a entidade que possui a data de todas as vendas realizadas).

### Exemplo de parâmetros que devem ser passados na URL

> .../finance-report-by-period/`2020-01-01`/`2024-01-01`

##### `GET`
```sh
http://localhost:8080/api/v1/fresh-products/orders/finance-report-by-period/2020-01-01/2024-01-01
```
### Retorno
`200 - Ok`
```sh
{
    "financeReportByPeriod": "Finance report between 2020-01-01 and 2024-01-01.",
    "totalSalesValue": 83.0,
    "salesInfo": [
        {
            "date": "2022-12-23",
            "value": 23.0,
            "userId": 1
        },
        {
            "date": "2023-04-30",
            "value": 60.0,
            "userId": 1
        }
    ]
}
```

