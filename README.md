# Projeto Integrador

## Requisitos Obrigatórios: 📝
### 1 - Inserir um lote de produtos no armazém de distribuição PARA registrar a existência de estoque;

### 2 - Adicionar produtos ao carrinho de compras doMarketplace PARA comprá-los, se desejar;

### 3 - Consultar um produto em stock no armazem para saber a sua localização num setor e os diferentes lotes onde se encontra;

### 4 - Consultar um produto em todos os armazens para saber o estoque em cada armazem do referido produto;

### 5 - Consultar os produtos em estoque estão prestes a expirar no almoxarifado, a fim de aplicar alguma ação comercial com eles;

## Requisitos Individuais: 📝

### 1- requisito: SalesProductReport - Ana Paula

### endpoint 1: SalesProductReport/periodo

### endpoint 2: SalesProductReport/IdUser/periodo

#### Nesse requisito foram criados dois endpoints:

Um para consultar o relatório de vendas dos produtos por período, e outro para consultar por período/usuário, dessa forma é retornado  uma lista com os produtos e quantidade que foram vendidos.
Sendo o foco desse relatório um gerenciamento do fluxo de vendas.

2-

3-

4-

5-

6-

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



