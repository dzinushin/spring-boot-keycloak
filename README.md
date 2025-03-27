

## Sources 

### A Quick Guide to OAuth2 With Spring Boot And Keycloak

https://www.baeldung.com/spring-boot-keycloak
https://github.com/eugenp/tutorials/tree/master/spring-boot-modules/spring-boot-keycloak



https://docs.velociraptor.app/knowledge_base/tips/setup_keycloak/

```
docker run -p 443:443 -e KC_HOSTNAME=keycloak.local \
-e KC_BOOTSTRAP_ADMIN_USERNAME=admin -e KC_BOOTSTRAP_ADMIN_PASSWORD=admin \
-v /root/keycloak-server.crt.pem:/etc/x509/https/keycloak-server.crt.pem \
-v /root/keycloak-server.key.pem:/etc/x509/https/keycloak-server.key.pem \
-e KC_HTTPS_CERTIFICATE_FILE=/etc/x509/https/keycloak-server.crt.pem \
-e KC_HTTPS_CERTIFICATE_KEY_FILE=/etc/x509/https/keycloak-server.key.pem \
quay.io/keycloak/keycloak:latest start-dev --https-port=443
```

https://github.com/spring-projects/spring-security/issues/15491
https://github.com/dreamstar-enterprises/docs


### Spring Cloud Gateway + Keycloak: полноценный пример

https://habr.com/ru/articles/872856/
https://github.com/franticticktick/api-gateway



## Other implementations
oauth2-proxy
https://github.com/oauth2-proxy/oauth2-proxy

