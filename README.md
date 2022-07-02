# Movies Review

Api para avaliar e comentar sobre os filmes disponíveis pela API pública OMDb API

# Installing
$ git clone https://github.com/EllenCintra/MoviesReview

Tendo instalado o Java 11, selecione a IDE de sua preferência e import Existing Maven Project.
Por padrão, o projeto está rodando em http://localhost:8080/

Através deste link https://www.getpostman.com/collections/9f8ca4256c7bd8de0bd4 você poderá importar a collection do Postman, onde há uma documentação atualizada, além de auxiliar na visualização dos endpoints e testes. 

# Dependências
  * Java 11
  * Maven
  * Springs: Data Jpa, Security, Cache, Web, Validation, Cloud e Openfeign 
  * io.jsonwebtoken
  * H2 Database: acompanhe pelo console http://localhost:8080/h2-console 
  
  * Dependências estão configuradas nos arquivos pom.xml e application.properties
  
# Project Architecture

     |--client/omdb
     |--config
        |--security
        |--swagger
     |--controller
     |--exceptions
     |--model
       |--dto
       |--enums
       |--form
       |--mapper
     |--repository
     |--service


