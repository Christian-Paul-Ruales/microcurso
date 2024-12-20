# Consumo de servidor

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
http://localhost:8085/actuator/refresh

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
vault kv put pathDeseadp/nombreArchivo.json @nombreArchivoLocal.json
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

# ESTAMOS TRABAJANDO CON CONFIGURACIONES EN EL BOOTSTRAP.PROPERTIES

# Hyxtrix

Implementar la dependencia de starter hystryx
```
	implementation 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-client'
```
