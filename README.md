## Configuration Service

Retrieve: https://localhost/configuration-service/default/dev

Refresh: curl --insecure --location --request POST 'https://localhost/configuration-service/monitor' --header 'X-Github-Event: push' --header 'Content-Type: application/json' --data-raw '{"commits": [{"modified": ["myconfig-client-app.properties"] }]}'

## Configuration Sidecar

Request: https://localhost/myconfig-client-app/config

Refresh: curl --insecure -H "Content-Type: application/json" -d {} https://localhost/myconfig-client-app/actuator/refresh

Use loopback network interface for sidecar i.e. 'localhost' as communication mechanism between consuming microservice.
