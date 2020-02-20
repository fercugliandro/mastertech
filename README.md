# Exercício MasterTech - Ponto Eletronico

Esse exercício consiste em desenvolver um projeto que disponibiliza algumas APIs para o registro do usuário e para registrar o ponto do usuário.

Nesse exercício foi utilizado as seguintes tecnologias:
* Java 1.8
* Spring Boot
* Spring Data
* ySql 8

# Bando de Dados

Foi utilizado o banco de dados MySQL 8. Para a criação das tabelas, abaixo os scripts.

## Tabela de Usuários
```sql
CREATE TABLE `tb_usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cpf` varchar(255) DEFAULT NULL,
  `dt_cadastro` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome_completo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```

## Tabela Ponto Eletrônico
```sql
CREATE TABLE `tb_ponto_eletronico` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_usuario` bigint DEFAULT NULL,
  `dt_marcacao_ponto` datetime DEFAULT NULL,
  `tp_batida_ponto` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK60nxdvtq7wpt42am82t8ogvyv` (`id_usuario`),
  CONSTRAINT `FK60nxdvtq7wpt42am82t8ogvyv` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

```

# API de Usuário

## GET /mastertech/usuario/find
Listagem de todos os usuários cadastrados.

O request body, para essa api é vazio.

Exemplo de response:

```json
[
    {
        "id": 1,
        "cpf": "96336833003",
        "nomeCompleto": "Tony Stark",
        "email": "tony.stark@avengers.com",
        "dataCadastro": "2020-02-19T03:00:00.000+0000"
    },
    {
        "id": 2,
        "cpf": "76041409049",
        "nomeCompleto": "Natasha Romanoff",
        "email": "natasha.romanoffk@avengers.com",
        "dataCadastro": "2020-02-19T03:00:00.000+0000"
    },
    {
        "id": 3,
        "cpf": "56641101045",
        "nomeCompleto": "Bucky Barnes",
        "email": "buck.baners@hydra.com",
        "dataCadastro": "2020-02-19T03:00:00.000+0000"
    },
    {
        "id": 4,
        "cpf": "13577281073",
        "nomeCompleto": "Steve Rogers",
        "email": "steve.rogers@avengers.com",
        "dataCadastro": "2020-02-20T05:29:24.174+0000"
    }
]

```
## GET /mastertech/usuario/find/{idUsuario}
Listagem somente de um usuário específico.

Exemplo de chamada:
```url
http://localhost:8080/mastertech/usuarios/find/1
```

Exemplo de response:

```json
{
    "id": 1,
    "cpf": "96336833003",
    "nomeCompleto": "Tony Stark",
    "email": "tony.stark@avengers.com",
    "dataCadastro": "2020-02-19T03:00:00.000+0000"
}
```

## POST /mastertech/usuario/save
API que registra o usuário no banco de dados.

Exemplo de request:
```json
{
	"cpf": "77440824008",
	"nomeCompleto": "Samuel Wilson",
	"email": "samuel.wilson@avengers.com",
    "dataCadastro": "2020-02-19T03:00:00.000+0000"
}
```

Exemplo de response:

```json
{
    "id": 10,
	"cpf": "77440824008",
	"nomeCompleto": "Samuel Wilson",
	"email": "samuel.wilson@avengers.com",
    "dataCadastro": "2020-02-19T03:00:00.000+0000"
}
```

## PUT /mastertech/usuario/save
API que realiza alterações no usuário cadastrado no banco de dados.

Exemplo de request:
```json
{
    "id": 10,
	"cpf": "77440824008",
	"nomeCompleto": "Samuel Wilson - Falcon",
	"email": "samuel.wilson@avengers.com",
    "dataCadastro": "2020-02-19T03:00:00.000+0000"
}
```

Exemplo de response:

```json
{
    "id": 10,
	"cpf": "77440824008",
	"nomeCompleto": "Samuel Wilson - Falcon",
	"email": "samuel.wilson@avengers.com",
    "dataCadastro": "2020-02-19T03:00:00.000+0000"
}
```

# API - Ponto Eletronico

## GET /mastertech/pontoeletronico/find/{idUsuario}
Listagem dos registros de ponto de um usuário específico.

Exemplo de chamada:
```url
http://localhost:8080/mastertech/pontoeletronico/find/1
```

Exemplo de response:

```json


```
## GET /mastertech/usuario/find/{idUsuario}
Listagem somente de um usuário específico.

Exemplo de chamada:
```url
http://localhost:8080/mastertech/usuarios/find/1
```

Exemplo de response:

```json
[
    {
        "usuario": {
            "id": 1,
            "cpf": "96336833003",
            "nomeCompleto": "Tony Stark",
            "email": "tony.stark@avengers.com",
            "dataCadastro": "2020-02-19T03:00:00.000+0000"
        },
        "pontoEletronico": [
            {
                "dataMarcacao": "20/02/2020",
                "marcacoes": [
                    {
                        "marcacao": "08:00:00",
                        "tipoPonto": "ENTRADA"
                    },
                    {
                        "marcacao": "12:00:00",
                        "tipoPonto": "SAIDA"
                    },
                    {
                        "marcacao": "13:00:00",
                        "tipoPonto": "ENTRADA"
                    },
                    {
                        "marcacao": "18:00:00",
                        "tipoPonto": "SAIDA"
                    }
                ],
                "horasTrabalhadas": "09:00:00"
            },
            {
                "dataMarcacao": "21/02/2020",
                "marcacoes": [
                    {
                        "marcacao": "08:30:00",
                        "tipoPonto": "ENTRADA"
                    },
                    {
                        "marcacao": "12:30:00",
                        "tipoPonto": "SAIDA"
                    },
                    {
                        "marcacao": "13:30:00",
                        "tipoPonto": "ENTRADA"
                    },
                    {
                        "marcacao": "17:30:00",
                        "tipoPonto": "SAIDA"
                    }
                ],
                "horasTrabalhadas": "08:00:00"
            }
        ]
    }
]
```

## POST /mastertech/ponto/save
API que registra o ponto do usuário

Exemplo de request:
```json
{
    "usuario": {
        "id": "3"
    },
    "dataHoraPonto":"2020-02-20T07:30:00.000Z", 
    "tipoBatidaPonto": "ENTRADA"
}
```

Exemplo de response:

```json
{
    "id": 13,
    "usuario": {
        "id": 3,
        "cpf": null,
        "nomeCompleto": null,
        "email": null,
        "dataCadastro": null
    },
    "dataHoraPonto": "2020-02-20T07:30:00.000+0000",
    "tipoBatidaPonto": "ENTRADA"
}
```