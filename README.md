Fonte do aprendizado: https://cafe.algaworks.com/spring-rest-para-iniciantes-v3/

# O que foi utilizado?
- Java;
- Spring;
- REST;
- MySQL;
- Flyway;
- Hibernate/JPA;
- Maven;
- dentre outros.

# Conceitos
- Bean Validation;
- Bean Configuration;
- Query Methods;
- Exceptions Handler;
- ModelMapper;
- dentre outros.

# Subir backend

## Pr�-requisito
Ter servidor do MySQL em execu��o com as seguintes configura��es:
- Endere�o: localhost:3306
- Schema: osworks
- Usu�rio: root
- Senha: admin

### Por linha de comando
1. Navegue at� o diret�rio ra�z do projeto (diret�rio que cont�m o arquivo pom.xml).
2. Execute o comando: 
- No Windows `mvnw.cmd spring-boot:run`
- No Linux `mvnw spring-boot:run`

### Pela IDE
1. Fa�a import do projeto (Maven) na IDE.
2. Execute o m�todo principal da classe **OsworksApiApplication**.

**A api ficar� dispon�vel no endere�o `localhost:8080`**
