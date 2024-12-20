# API GATEWAY
Es importante tener las librerias de
* Eureka Discovery client
* Gateway

**Si hay error verificar que la libreria de gateway NO CONTENGA mvc al final**

1. CONFIGURAR EL NOMBRE
2. USAR EL @EnableDiscoveryClient pero lo aremos con una propiedad
```properties
spring.cloud.gateway.discovery.locator.enabled=true
```
3. Instanciar la id
```properties
eureka.instance.instance-id=${spring.application.name}:${random.uuid}
```
4. desactivar la estrategia clasica de balanceo de carga
```properties
spring.cloud.loadbalancer.ribbon.enabled=false
```

5. configuracion de rutas
```properties
# configuracion de rutas
spring.cloud.gateway.routes[0].id=microcurso
spring.cloud.gateway.routes[0].uri=lb://MICROCURSO #lb load balancing

# Predicados
spring.cloud.gateway.routes[0].predicates[0]=Path=/api/products/**, api/categories/**
```


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
2i0FSfB5pNIR4SfLZNSWJdefZFv3xkwR
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

5. en real settings podemos ver al deslizar hasta abajo en endpoints, OpenID Endpoint Configuration
   toda la informacion de la configuracion que hicimos

6. Instalamos las dependencias en el api-gateway de:
    * Spring Cloud Security
    * OAuth2 Client

7. copiamos el apartado que encontramos en el paso 5, en la propiedad issuer
para este caso
````
http://localhost:8060/realms/mycompany-microservice-realm
````
6. definimos el client id
Copiamos el client id creado en el keycloak
```properties
spring.security.oauth2.client.registration.spring-cloud-gateway-client.client-id=spring-cloud-gateway-client
```
asi mismo definimos la credencial generada en cliente credentials
```properties
spring.security.oauth2.client.registration.spring-cloud-gateway-client.client-secret=2i0FSfB5pNIR4SfLZNSWJdefZFv3xkwR
```
y la redirect-uri de validations redirect uri
```properties
spring.security.oauth2.client.registration.spring-cloud-gateway-client.redirect-uri=http://localhost:8080/login/oauth2/code/spring-cloud-gateway-client
```

el apartado de propiedades deberia verse algo asi
```properties
# Configuraciones para keyclock
spring.security.oauth2.client.provider.keyclook.issuer-uri=http://localhost:8060/realms/mycompany-microservice-realm
spring.security.oauth2.client.registration.spring-cloud-gateway-client.client-id=spring-cloud-gateway-client
spring.security.oauth2.client.registration.spring-cloud-gateway-client.client-secret=2i0FSfB5pNIR4SfLZNSWJdefZFv3xkwR
spring.security.oauth2.client.registration.spring-cloud-gateway-client.provider=keycloak
spring.security.oauth2.client.registration.spring-cloud-gateway-client.authorization-grant-type=authorization_code
spring.security.oauth2.client.registration.spring-cloud-gateway-client.redirect-uri=http://localhost:8080/login/oauth2/code/spring-cloud-gateway-client

```
7. ejemplos de configuracion de spring security
````
https://www.baeldung.com/spring-boot-api-key-secret
````

8. Configuramos el securityConfig en el path principal observar el archivo SecurityConfiguration

9. Agregamos la siguiente llave de configuracion justo despues de los [].paths
```properties
spring.cloud.gateway.default-filters=TokenRelay
```
