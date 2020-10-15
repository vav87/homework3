# Домашняя работа #3

## Задание
Используя Spring Boot доделать веб-приложение по работе с сущностями Person (люди) и Department (отдел).
Реализовать базовые CRUD операции над ними, дописать unit-тесты и задеплоить его на Heroku.
 
**Person**:
* `GET /` – получение подробной информации о всех людях и о том, какому они принадлежат отделу.
* `GET /{id}` – получение подробной информации о человеке. Если информация по id не найдена, то возвращать 404 ошибку.
* `POST /` – создание новой записи о человеке.
* `PATCH /{id}` – обновление информации о человеке. Если информация по id не найдена, то возвращать 404 ошибку.
* `DELETE /{id}` – удаление информации о человеке и удаление его из отдела. Если запись с таким id не найдена, ничего не делать.

**Department**:
* `GET /` – получение краткой информации о всех департаментах.
* `GET /{id}` – получение подробной информации о департаменте и краткой информации о людях в нем. Если информация по id не найдена, то возвращать 404 ошибку.
* `POST /` – создание нового департамента.
* `PATCH /{id}` – обновление данных о департаменте. Если информация по id не найдена, то возвращать 404 ошибку.
* `DELETE /{id}` – удаление всех людей из департамента и удаление самого департамента. Если запись с таким id не найдена, ничего не делать.
* `POST /{departmentId}/{personId}` – добавление нового человека в департамент. Если не найден человек или департамент, отдавать 404 ошибку.
Если департамент закрыт, то отдавать 409 ошибку.
* `DELETE /{departmentId}/{personId}` – удаление человека из департамента. Если департамент не найден, отдавать 404 ошибку, если не найден человек в департаменте, то ничего не делать.
* `POST /{id}/close` – удаление всех людей из департамента и установка отметки на департаменте, что он закрыт для добавления новых людей. Если информация по id не найдена, то возвращать 404 ошибку.

Сущность _Person_ имеет поля:
```postgresql
CREATE TABLE person
(
    id          INTEGER NOT NULL CONSTRAINT person_pkey PRIMARY KEY,
    first_name  VARCHAR(80) NOT NULL,
    last_name   VARCHAR(80) NOT NULL,
    middle_name VARCHAR(80),
    age         INTEGER
);
```

Сущность _Department_ имеет поля:
```postgresql
CREATE TABLE department
(
    id     INTEGER NOT NULL CONSTRAINT department_pkey PRIMARY KEY,
    name   VARCHAR(80) NOT NULL CONSTRAINT idx_department_name UNIQUE,
    closed BOOLEAN DEFAULT FALSE NOT NULL
);
```

Требуется добавить дополнительные поля к этим сущностям и отдавать их в API.

## Требования
1. Дописать бизнес-функционал и unit-тесты.
1. Для работы с базой данных использовать Hibernate (т.е. EntityManager). Использовать JPA нельзя.
1. Используя Jacoco (code coverage), сгенерировать отчет о покрытии и задеплоить его на [https://codecov.io/](https://codecov.io/).
1. Задеплоить приложение на Heroku.

## Сборка приложения 
```shell script
# запустить PostgreSQL в docker-контейнере
docker-compose up -d postgres

# загружает gradle wrapper 6.6
./gradlew wrapper

# сборка проекта, прогон unit-тестов, запуск приложения
./gradlew clean build bootRun 
```

##  Комментарии
1. [Установить Docker](https://docs.docker.com/engine/install/) и [Docker Compose](https://docs.docker.com/compose/install/).
Для Windows и Mac он уже идет в комплекте с Docker.
1. Для настройки Codecov нужно:
    1. авторизоваться на [https://codecov.io/](https://codecov.io/) через GitHub, выбрать нужный репозиторий;
    1. в GitHub -> Settings -> Secrets добавить новый ключ `CODECOV_TOKEN`;
    1. после прогона тестов, данные по покрытию отображаются странице проекта `https://codecov.io/gh/<github-user>/<repository-name>`.
1. По описанию методов генерируется OpenAPI спецификация, описание в json доступно по адресу http://localhost:8080/api-docs,
для просмотра в Swagger UI его нужно запустить через docker `docker run -p 8070:8080 -e API_URL=http://localhost:8080/api-docs swaggerapi/swagger-ui` и открыть `http://localhost:8070`
1. Описание работы Heroku [https://devcenter.heroku.com/articles/how-heroku-works](https://devcenter.heroku.com/articles/how-heroku-works).
1. Для деплоя на Heroku использовать [Deploy to Heroku](https://github.com/marketplace/actions/deploy-to-heroku). Требуется прописать `HEROKU_API_KEY` в Secrets.
1. Для подключения БД на Heroku заходите через Dashboard в раздел Resources и в блоке Add-ons ищете Heroku Postgres.
Для получения адреса, пользователя и пароля переходите в саму БД и выбираете раздел Settings -> Database Credentials.
Эти данные заносите в [application-heroku.properties](src/main/resources/application-heroku.properties).
1. Unit-тесты на Spring:
    * [Web Layer tests](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-testing-spring-boot-applications-testing-autoconfigured-mvc-tests)
    * [Service tests](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-testing-spring-boot-applications)
    * [Database tests](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-testing-spring-boot-applications-testing-autoconfigured-jpa-test)
1. После успешного деплоя на Heroku запускаются интеграционные тесты (репозиторий [java-education/homework3-testing](https://github.com/java-education/homework3-testing)).
1. Для запуска в [main.yml](.github/workflows/main.yml) прописать url сервиса на Heroku.

##  Как сдавать?
* Fork этого репозитория
* Merge request вашей реализации в этот репозиторий

## Дедлайн
