Golden Raspberry Awards API

API RESTful desenvolvida em Spring Boot para leitura e processamento dos indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards.

Pré-requisitos

Java 17+

Maven 3.8+

Como rodar a aplicação:

Na raiz do projeto (onde está o pom.xml), execute:

mvn clean install -U

Após o build, execute:

mvn spring-boot:run

Como rodar os testes de integração:

O teste está localizados em:
src/test/java

Para executar todos os testes de integração:
mvn test

Com a aplicação rodando, é possível acessar o console do H2 em:
http://localhost:8080/h2-console

Endpoint da API:

Para obter o produtor com maior e menor intervalo entre dois prêmios consecutivos, utilize:

GET http://localhost:8080/api/premios/intervalos

Exemplo de resposta:

{
  "min": [
    {
      "producer": "Producer 1",
      "interval": 1,
      "previousWin": 2008,
      "followingWin": 2009
    }
  ],
  "max": [
    {
      "producer": "Producer 2",
      "interval": 99,
      "previousWin": 1900,
      "followingWin": 1999
    }
  ]
}

