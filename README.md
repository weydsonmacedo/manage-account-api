# manage-account-api

Projeto Desafio Donus, Gerenciamento de contas Bancárias.

 Detalhes da Implementação
-------

* Tecnologias utilizadas na implementação:

* JAVA: 11,
* Spring boot:2.4.1 ,
* SPRING-DATA-JPA:2.4.1 ,
* DataBase:AWS - RDS: POSTGRES,
* Deploy: AWS - Elastic Beanstalk , 
* SWAGGER:springdoc-openapi-ui: 1.5.2,
* JUNIT: 5,
* pring-boot-starter-test:2.4.1 ,
* DataBase para teste: h2,
* Lombok.

Pre-requisitos para o Funcionamento:
-------

* JAVA 11,
* MAVEN,
* POSTMAN OU QUALQUER OUTRO DO RAMO,
* O projeto conta com 3 Bancos de dados:
* 1º - apontado para AWS, configurado para testes de desenvolvimento.
* 2º - apontado para AWS pelo Elastic Beanstalk, ambiente em deploy, para testes remotos.
* 3º - H2. configurado apenas para rodar as classes de Testes Unitários e Testes Integrados.

Instalação:
-------

* 1 - Instalar Eclipse compatível com java 11. (Version: 2020-09) ou superior.
* 2 - fazer o download do repositório
* 3 - importar como projeto MAVEN. APÓS, RODAR: MAVEN -> UPDATE  / MAVEN -> CLEAN / MAVEN -> INSTALL
* 4 - Dentro do projeto tem uma pasta chamada: POSTMAN_ENDPOINTS. Nela contem todas as requisições necessárias para acessar o projeto.
* 5 - A aplicação também encontra-se com deploy para teste. 

LINK DO SWAGGER:
----
http://manage-account-api-dev.us-east-1.elasticbeanstalk.com/manage-account-api/swagger-ui/index.html?configUrl=/manage-account-api/v3/api-docs/swagger-config

Observações Sobre o Sistema:
-------
LEMBRETE: VERSÃO 2.0, instalar o mapper.
