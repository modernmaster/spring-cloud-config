## Configuration Service

Application name of consuming sidecars must be the same name as the document in mongodb.

Retrieve: https://localhost/configuration-service/{application-name}/{profile}/{label}}

Refresh: curl --insecure --location --request POST 'https://localhost/configuration-service/monitor' --header '
X-Github-Event: push' --header 'Content-Type: application/json' --data-raw '{"commits": [{"
modified": ["{application-name}.properties"] }]}'

## Configuration Sidecar

Request: https://localhost/{application-name}/config

Refresh: curl --insecure -H "Content-Type: application/json" -d {} https://localhost/{application-name}/actuator/refresh

Use loopback network interface for sidecar i.e. 'localhost' as communication mechanism between consuming microservice.

## Database

Platform - MongoDB Source - reference-data-service Collection - licensee Test Data

{
"_id": {
"$oid": "6144797850d00b76c7593d67"
},
"profile": "istanbul",
"label": "dev",
"source": {
"user": {
"max-connections": 1,
"timeout-ms": 3600 },
"prop1": "Configuration object from service"
} }

{
"_id": {
"$oid": "614476c050d00b76c7593d62"
},
"source": {
"user": {
"max-connections": 1,
"timeout-ms": 3600 },
"prop1": "Default configuration object from service"
} }