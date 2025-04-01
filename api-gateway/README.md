# API Gateway With Token Relay

[Documentation](https://spring.io/blog/2019/08/16/securing-services-with-spring-cloud-gateway)   


https://docs.spring.io/spring-session/reference/configuration/redis.html

Как запустить:

из корня проекта запустить docker compose:
```
docker compose up -d
```

для запуска приложений нужен установленный Java SDK 21 (например из sdkman)

Запускаем api-gateway:
```
cd api-gateway
./gradlew bootRun
```

Запускаем echo-kotlin:
```
cd echo-koltin
./gradlew bootRun
```

Запускаем test-app:
```
cd test-app
./gradlew bootRun
```

Доступ в админку Keycloak https://localhostL8180   admin/admin

Доступ в ручки gateway (proxy)
  http://localhost:8080/session
  http://localhost:8080/id-token
  http://localhost:8080/access-token
  http://localhost:8080/refresh-token

Доступ в мкс 
  https://localhost:8080/echo --> kotlin-echo
  https://localhost:8080/test --> test-app
  https://localhost:8080/test/jwt

Юзеры:
  alice/alice 
  jdoe/jdoe