# CURSO SPRING MICROSERVICES
*lo hice con mariadb por conocimientos*

uri: https://www.youtube.com/watch?v=-ksmE3KoX9U&list=PL145AyWAbMDhwUbBL74s1D2ZV9EqBaQ1t


## Plugins importantes intellij
- docker
- lombok

## PRODUCT MICROSERVICE

## Eureka client

Para cualquier cliente de eureka es necesario usar la siguiente anotacion en la clase principal
````bash
@EnableDiscoveryClient
````


## Actuator
se usa para exponer informacion operativa sobre la aplicacion en ejecucion

**Muy importante: **
En las propiedades usar 
```bash
management.endpoints.web.exposure.include=*
```
y en el controller agregar en la declaracion
```bash
@RefreshScope
```

Despues para refrescar hacer una peticion post a:

http://apphost:port/actuator/refresh

ejemplo: 
http://localhost:8084/actuator/refresh

## HashiCorp Vault para proteger datos sensibles

https://developer.hashicorp.com/vault/install?product_intent=vault

Arrancamos el vault con
```bash
vault server --dev --dev-root-token-id="00000000-0000-0000-0000-000000000000"
```
siendo el token, el token de inicio de sesion
## Implementar con spring vault
De manera de desarrollo, siempre los
valores se reiniciaran :(, es necesario volver a hacer
este procedimiento

Creamos un archivo json de configuraciones privadas

enviamos el archivo con el siguiente comando
```bash
vault kv put secret/nombreArchivo @nombreArchivoLocal.json
```
ejemplo:
```bash
vault kv put secret/booking @booking.json
```

## EN EL PROYECTO
Importante bajarte la dependencia de vault

starter-vault-config

y configurarla en el build.gradle o en el pom.xml

## Configuracion de vault en el archivo de pruebas
spring.cloud.vault.application-name=microcurso
spring.cloud.vault.host=localhost
spring.cloud.vault.port=8200
spring.cloud.vault.scheme=http
spring.cloud.vault.authentication=TOKEN
spring.cloud.vault.token=00000000-0000-0000-0000-000000000000


# RabbitMQ
es recomendable ir a la documentacion para instalar

https://www.rabbitmq.com/docs/download
```bash
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.13-management
```

## configuracion en el application.properties
```bash
# rabitmq
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
```
# ESTAMOS TRABAJANDO CON CONFIGURACIONES EN EL BOOTSTRAP.PROPERTIES
## Api Gateway
intercepta todas las llamadas y enruta al microservicio adecuado

## spring cloud gateway
VER EN EL PROYECTO API GATEWAY

## Keycloak

__Configuracion tambien descrita en el  proyecto de api-gateway__

Cambiamos el puerto por el tema del apigateway
```bash
docker run -p 8060:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:25.0.2 start-dev
```

creara un usuario con clave admin
admin/admin

1. Creamos un realm 
podemos crear grupos aislados de usuarios
desde el lado izquierdo en el combobox le damos en la opcion de abajo de crear realm

**en este caso el nombre sera**
```properties
mycompany-microservice-realm
```

2. Crear un cliente
En el apartado clients/create client
el id sera
```properties
spring-cloud-gateway-client
```
en capability config

```properties
Client authentication: on
```
y el nombre
```properties
api-gateway
```
en credentials se debio crear una credencial parecida a la siguiente

**esta es solo como ejemplo, copiar la propia**
```properties
uGqtSEWGQJfQ7uBsjjJZjYCMCsSAQ7qn
```
el acceso debe ser confidencial

configuramos como valid redirect urs la url para cuando nos autentiquemos correctamente
```
http://localhost:8080/login/oauth2/code/spring-cloud-gateway-client
```

3. En el apartado user creamos un usuario 
4. creamos una credencial no temporal, para este ejemplo fue

```properties
secret
```

5. en realm settings podemos ver al deslizar hasta abajo en endpoints, OpenID Endpoint Configuration
toda la informacion de la configuracion que hicimos

## PARA EL RESTO IR AL PROYECTO DE API GATEWAY...

## Una vez de vuelta aqui...
1. Buscar las siguientes dependencias
    * OAuth2 resource server
    * Oauth2 jose
    * spring security

## Configuracion spring security
Documentacion de security Configuration
```
https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
```

# Hystrix (circuit braker)