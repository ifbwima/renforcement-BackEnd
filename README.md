# renforcement-BackEnd

## Configuration de la base de données MongoDB
spring.data.mongodb.uri=mongodb://localhost:27017/student
spring.data.mongodb.database=studentdb

## Configuration de la base de données PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/schooldb
spring.datasource.username=
spring.datasource.password=

## Configuration du serveur school
server.port=8081

## Configuration du serveur student
server.port=8082

## Eureka Configuration
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/

## Configuration du serveur gateway
server.port=8080



## Exemple d'url pour un get sur Student (avec gateway)
http://localhost:8080/api/students/1
## (sans gateway)
http://localhost:8082/students/1

## Exemple de body pour creer un student
{
    "id":1,
    "firstName":"Enzo",
    "lastName":"pavaroti",
    "email":"tomp@fmail.com",
    "schoolId":"1"
}

## Exemple de body pour creer un school
{
    "schoolid":1,
    "schoolname":"Ecole de la vie"
}
