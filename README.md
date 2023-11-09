# PEOPLE API

Backend (Java + Spring)
 
 # O que é o sistema:

Esta é uma aplicação CRUD relacionada a entidade pessoas e conta com diversos recursos como:<br>

 - Testes de Unidade e Integração
 - Documentação no Swagger
 - Envio de E-mail
 - Hateaos
 - Migrations
 - Tratamento de Exceptions
 - Conexão com o Postgresql
 - Conexão com o H2
 - Logs

 # Funcionalidades:

 - Buscar uma lista de pessoas no sistema.
 - Buscar uma pessoa por id no sistema.
 - Salvar uma pessoa no sistema.
 - Remover uma pessoa no sistema.
 - Atualizar uma pessoa no sistema.
 - Buscar uma lista de logs no sistema.
 - Backup de dados do sistema (apenas para ADM).

 # Dependências:

  - Data JPA - > Utilizando o JPA para fazer a ponte entre o banco de dados e a aplicação -> ORM

  - Validation -> Utilizado para proibir requisições com dados inválidos ou com um formato inválido.

  - Web -> É uma aplicação WEB, Utilizado para receber requisições, devolver uma resposta ...

  - H2 - > Utilizado esse banco em MEMÓRIA para fazer testes, ambiente de teste.

  - PostgreSQL -> Utilizado esse BANCO no ambiente de desenvolvimento e produção.

  - Test -> Foram feitos testes na aplicação com JUNIT ( Testes de integração e Unidade).

  - OpenAPI -> Utilizado para documentar a API (Swagger).

  - LomBok -> Utilizado para evitar muitas linhas de código através de annotations.

  - Flyway -> Ferramente utilizada para realizarmos migrations no sistema.

  - Hateoas -> Utilizado para adicionar hypermedia no sistema.
  
  - Email -> Utilizado para disparar e-mails para as pessoas quando cadastradas no sistema.

# SGBD utilizados:
- H2 -> Ambiente de Teste
- Postgresql -> Ambiente de desenvolvimento

# Versionamento:

 - Versão do Java: 17

 - Versão do Spring Boot: 3.1.4

# Informações adicionais:

 - Testes realizados: 22
 - Link index do Swagger: /api/swagger-ui/index.html
 
