# Desafio da Galáxia M

## Compilar o projeto em modo de desenvolvimento
```
mvn clean verify
```

## Compilar o projeto em modo de produção
```
mvn clean install
```

## Deploy no GCP AppEngine
```
mvn clean package appengine:deploy
``` 

## Deploy do GCP AppEngine Cron
```
gcloud app deploy ./src/main/appengine/cron.yaml
```