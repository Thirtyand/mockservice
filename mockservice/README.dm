*** Заглушка для эмуляции определенных ответов от эндпоинтов REST API ***

Java 23 |  Spring Boot 3.3.4 |  Maven

Репозиторий 


Для запуска сервиса можно выполнить run.bat в консоли или самостоятельно осуществить следующие шаги:
1. в корневой папке с проектом, где находится pom.xml файл запустить консоль
2. выполнить команду mvn clean install . результатом выполнения служит появление jar-файла с имененем проекта (mockservice-0.0.1-SNAPSHOT.jar) в папке target
3. выполнить команду cd target
4. выполнить команду java -jar {название jar-файла} . например, java -jar mockservice-0.0.1-SNAPSHOT.jar


Организовано три эндпоинта по адресу http://localhost:8181 (поменять значение порта можно в application.properties) :

1.	Эндпоинт: GET /api/users?count={число}
       Статус ответа: 200 OK
       Ответ: возвращает массив пользователей, где количество пользователей соответствует параметру count
       Примечание: значение count определяет количество юзеров в списке, которое необходимо вернуть в ответе
    Структура ответа:
	[
        {
            "id": 1,
            "email": "michael.lawson@reqres.in",
            "first_name": "Michael",
            "last_name": "Lawson"
        },
        {
            "id": 2,
            "email": "lindsay.ferguson@reqres.in",
            "first_name": "Lindsay",
            "last_name": "Ferguson"
        },
        {
            "id": 3,
            "email": "tobias.funke@reqres.in",
            "first_name": "Tobias",
            "last_name": "Funke"
        }
	]

2 . Эндпоинт: GET /api/user/{id}
    Статус ответа: 200 OK
    Ответ: возвращает данные о пользователе с указанным id
    Примечание: в теле ответа приходит тот id, который был послан в запросе, остальные данные - псевдослучайно сгенерированы
    Структура ответа:
	{
        "id": 2,
        "email": "janet.weaver@reqres.in",
        "first_name": "Janet",
        "last_name": "Weaver",
        "address": "28 Lenin Street, Moscow"
    }

3. Эндпоинт: POST /api/user
    Статус ответа: 201 Created
    Запрос: ожидается JSON с данными нового пользователя
    Примечание: в этом ответе данные "first_name", "last_name", "email", "address" совпадают с теми, которые
                пришли в запросе, id генерируется рандомно и в "createdAt" возвращается текущая дата
    Структура запроса:
	{
        "email": "janet.weaver@reqres.in",
        "first_name": "Janet",
        "last_name": "Weaver",
        "address": "28 Lenin Street, Moscow"
	}
    Структура ответа:
	{
        "id": 123,
	"first_name": "Janet",
        "last_name": "Weaver",
        "email": "janet.weaver@reqres.in",
        "address": "28 Lenin Street, Moscow",
	"createdAt": "2024-07-08T03:28:01.813Z"
	}

