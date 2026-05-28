# Simulador de servidores CLI
Simula o funcionamento de servidores, além de poder observar números de requisições, e estatísticas.

## Features do projeto
- **Servidores**: funções que simulam requisições de servidores em três etapas: Web, Authenticator, Database. Qualquer
  uma pode falhar. Os três simuladores rodam em paralelo utilizando threads, e quem decide a hora de parar, é o usuário.
- **Observabilidade**: toda requisição é salva em um banco de dados local SQLite, com: status, local, caso haja erro, e o horário
  em que foi solicitada a conexão.
- **Estatísticas**: são printadas no console caso o usuário deseje.

## Linguagens e tecnologias
- Maven
- Java 21.0.11
- SQlite
- DB Browser
