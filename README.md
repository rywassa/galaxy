# Desafio da Galáxia M

Em uma galáxia distante, existem três civilizações. Vulcanos, Ferengis e Betasoides. Cada a civilização vive em paz em seu respectivo planeta.

Eles dominam a previsão do clima usando um sistema computacional. Abaixo do diagrama do sistema solar.

![image](https://user-images.githubusercontent.com/2258146/82271646-c2a03700-994e-11ea-934f-ac3f47914072.png)

**Premissas**:
* O planeta Ferengi se move com uma velocidade angular de 1 graus / dia no **sentido horário**. Sua distância do sol é de 500 km.
* O planeta betóideide se move com uma velocidade angular de 3 graus / dia no **sentido horário**. Sua distância do sol é de 2000 km.
* O planeta Vulcano se move com uma velocidade angular de 5 graus / dia no **sentido anti-horário**, sua distância do sol é de 1000 km.
* Todas as órbitas são circulares.

Quando os três planetas estão alinhados um com o outro e por sua vez alinhados com o sol, o sistema solar passa por um período de seca.

![image](https://user-images.githubusercontent.com/2258146/82271877-625dc500-994f-11ea-90fb-870ab0e0a03f.png)

Quando os três planetas não estão alinhados, eles formam um triângulo um com o outro. Sabe-se que no momento em que o sol está dentro do triângulo, 
o sistema solar experimenta uma período de chuva, tendo este, um pico de intensidade quando o perímetro do triângulo está em seu máximo.

![image](https://user-images.githubusercontent.com/2258146/82272100-09426100-9950-11ea-87f4-1d6cfa7a6804.png)

As condições ideais de pressão e temperatura são dadas quando os três planetas estão alinhados um com o outro, mas não alinhados com o sol.

![image](https://user-images.githubusercontent.com/2258146/82272229-58889180-9950-11ea-91cc-6fb33768c935.png)

Execute um programa de computador para poder prever nos próximos 10 anos:
1. Quantos períodos de seca haverá?
2. Quantos períodos de chuva haverá e em que dia será o pico máximo de chuva?
3. Quantos períodos de condições ideais de pressão e temperatura haverá?

## Solução

![image](https://user-images.githubusercontent.com/2258146/82274159-d13e1c80-9955-11ea-935b-f0505118537f.png)

### Framework
* [Spring Boot 2](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [Spring Data](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference)
* [Flywaydb](https://flywaydb.org/documentation/)
* [Springfox-swagger2](https://springfox.github.io/springfox/docs/current/)
* [Quartz](http://www.quartz-scheduler.org/documentation/)
* [TestContainer](https://www.testcontainers.org/)

### Plataforma de execução

Google Cloud Plataform - [AppEngine](https://cloud.google.com/appengine/docs/standard/java11)

### Pré-requisitos
* Java 11
* Maven 3.6+
* Postgres 11
* Docker CE 17+

### Compilar o projeto
```
mvn clean install
```

### Como executar a aplicação

Existem 2 profiles spring
* **dev** - Ambiente de desenvolvimento local
* **app-engine** - Ambiente do AppEngine

#### Ambiente de desenvolvimento local
```
java -Dspring.profiles.active=dev -jar galaxy-0.0.1-SNAPSHOT.jar
```

### Deploy no GCP AppEngine
```
mvn clean package appengine:deploy
``` 

### Deploy do GCP AppEngine Cron
```
gcloud app deploy ./src/main/appengine/cron.yaml
```
