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

## Pré-requisito
Ter servidor do MySQL em execução com as seguintes configurações:
- Endereço: localhost:3306
- Schema: osworks
- Usuário: root
- Senha: admin

### Por linha de comando
1. Navegue até o diretório raíz do projeto (diretório que contém o arquivo pom.xml).
2. Execute o comando: 
- No Windows `mvnw.cmd spring-boot:run`
- No Linux `mvnw spring-boot:run`

### Pela IDE
1. Faça import do projeto (Maven) na IDE.
2. Execute o método principal da classe **OsworksApiApplication**.

**A api ficará disponível no endereço `localhost:8080`**
