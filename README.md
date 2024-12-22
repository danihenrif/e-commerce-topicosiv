# Autores

Daniel Henriques Fernando

Roberland Ricardo

# Execução
Na pasta raiz do projeto, use o comando:

```
docker-compose up --build
```
para levantar os serviços.

# Requisição
Faça uma requisição POST para o endpoint http://localhost:8080/buy. O body da requisição deve ser do formato JSON e seguir o template abaixo :

```
{
	"id" : 100,
	"idUser": 2,
	"ft": true
}
```
Onde, 

**id** representa o id do produto a ser comprado;

**idUser** representa o id do usuário que está comprando;

**ft** representa se a tolerância a falhas está ativa ou inativa;

# Resposta

O sistema retornará um id único que **representa se a compra foi realizada com êxito**, como representado abaixo :

```
"84830cc8-3552-4eef-837a-8fd3c4302a02"
```

