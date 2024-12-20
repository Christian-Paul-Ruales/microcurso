
HashiCorp Vault para proteger datos sensibles

https://developer.hashicorp.com/vault/install?product_intent=vault

Arrancamos el vault con
```bash
vault server --dev --dev-root-token-id="00000000-0000-0000-0000-000000000000"
```

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

