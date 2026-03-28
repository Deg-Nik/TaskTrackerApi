# TaskTracker API

RESTful API для системы управления задачами с поддержкой пользователей и ролевой модели доступа.

## Описание проекта

TaskTracker API - это backend-приложение на Spring Boot, которое предоставляет функционал для управления задачами и пользователями. Проект использует современные технологии и подходы разработки, включая AOP для логирования и мониторинга производительности.

## Технологический стек

- Java 17+
- Spring Boot 3.x
- Spring Data JPA - для работы с базой данных
- Spring Web - для создания REST API
- Lombok - для уменьшения шаблонного кода
- Jakarta Persistence - для ORM
- Maven - система сборки

## Архитектура

Проект следует многослойной архитектуре:
├── controller/     # Слой контроллеров (REST эндпоинты)
├── service/       # Слой бизнес-логики
├── repository/    # Слой доступа к данным
├── entity/        # Сущности базы данных
├── enums/         # Перечисления
├── annotation/    # Кастомные аннотации
└── aspect/        # AOP аспекты

## Сущности

### Task
Задача с основными полями:
- id - уникальный идентификатор
- title - заголовок задачи
- description - описание задачи
- status - статус (TODO, IN_PROGRESS, DONE)
- priority - приоритет (LOW, MEDIUM, HIGH)
- createdAt - дата создания
- updatedAt - дата обновления
- user - связанный пользователь

### User
Пользователь системы:
- id - уникальный идентификатор
- username - уникальное имя пользователя
- email - уникальный email
- password - пароль
- firstName - имя
- lastName - фамилия
- role - роль (USER, ADMIN)
- active - статус активности
- createdAt - дата создания
- updatedAt - дата обновления

## API Эндпоинты

### Управление задачами (`/api/tasks`)

| Метод | Эндпоинт | Описание |
|-------|----------|----------|
| GET | /api/tasks | Получить все задачи |
| GET | /api/tasks/{id} | Получить задачу по ID |
| POST | /api/tasks | Создать новую задачу |
| PUT | /api/tasks/{id} | Обновить задачу |
| DELETE | /api/tasks/{id} | Удалить задачу |

### Управление пользователями (`/api/users`)

| Метод | Эндпоинт | Описание |
|-------|----------|----------|
| GET | /api/users | Получить всех пользователей |
| GET | /api/users/{id} | Получить пользователя по ID |
| GET | /api/users/username/{username} | Получить пользователя по имени |
| GET | /api/users/email/{email} | Получить пользователя по email |
| GET | /api/users/role/{role} | Получить пользователей по роли |
| GET | /api/users/active | Получить активных пользователей |
| POST | /api/users | Создать нового пользователя |
| PUT | /api/users/{id} | Обновить пользователя |
| DELETE | /api/users/{id} | Удалить пользователя |
| PUT | /api/users/{id}/activate | Активировать пользователя |
| PUT | /api/users/{id}/deactivate | Деактивировать пользователя |

## AOP Функциональность

Проект использует аспектно-ориентированное программирование для:

### Логирование (`LoggingAspect`)
- Автоматическое логирование вызовов методов
- Отслеживание входных параметров и результатов
- Обработка исключений

### Мониторинг производительности (`PerformanceAspect`)
- Замер времени выполнения методов
- Превышение пороговых значений
- Оптимизация производительности

### Кэширование (`Cashed`)
- Кэширование результатов методов
- Улучшение производительности при повторных вызовах

### Безопасность (`Secured`)
- Проверка прав доступа (заглушка для будущей реализации)
- Ролевая модель доступа

## Примеры запросов

### Создание пользователяcurl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "email": "john@example.com",
    "password": "securePassword123",
    "firstName": "John",
    "lastName": "Doe",
    "role": "USER"
  }'

### Создание задачиcurl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Новая задача",
    "description": "Описание новой задачи",
    "priority": "MEDIUM",
    "status": "TODO"
  }'

## Конфигурация

### База данных
Проект использует H2 в памяти по умолчанию. Для смены базы данных измените application.properties:
# PostgreSQL пример
spring.datasource.
url=jdbc:postgresql://localhost:5432/tasktracker
spring.datasource.username=postgres
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update

### Логирование
Уровень логирования можно настроить в application.properties:
logging.level.com.example.tasktrackerapi=DEBUG
logging.level.org.springframework.web=DEBUG

## Запуск проекта

1. Клонируйте репозиторий:git clone <repository-url>
cd TaskTrackerApi

2. Соберите проект:mvn clean install

3. Запустите приложение:mvn spring-boot:run

Или используйте IDE для запуска TaskTrackerApiApplication.java

## Тестирование

Для запуска тестов:mvn test

## Будущие улучшения

- [ ] Реализация Spring Security
- [ ] JWT аутентификация
- [ ] Валидация входных данных
- [ ] Swagger/OpenAPI документация
- [ ] Unit и интеграционные тесты
- [ ] Docker контейнеризация
- [ ] CI/CD pipeline
- [ ] Микросервисная архитектура

## Вклад в проект

1. Fork проекта
2. Создайте feature ветку
3. Внесите изменения
4. Отправьте pull request


## Контакты

Для вопросов и предложений обращaитесь к разработчику.
