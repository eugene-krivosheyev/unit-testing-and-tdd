Тренинг «Unit Testing & TDD»
============================
24 ак. часа, 18 астр. часов

Цели тренинга
============
После тренинга участники смогут:
-------------------------------
- [x] Разрабатывать тесты как «спецификации на примерах» в роли документации
- [ ] Анализировать тестовое покрытие для принятия решений по тест-дизайну
- [ ] Подменять сложные компоненты системы на время тестирования
- [ ] Разрабатывать поддерживаемые тесты и их наборы 
- [ ] Обеспечивать поддерживаемый дизайн системы при помощи TDD
- [ ] Объяснить себе и менеджменту, где им нужны тесты, а где нет

В итоге бизнес получает:
------------------------
- [ ] Контролируемый cycle time задач
- [ ] Контролируемое качество системы

Программа
=========
Зачем мы собрались? (1 часа всего / _из них_ 0.5 часа практики)
---------------------------------------------------------------
- [x] Обзор тренинга
- [x] О тренере
- [x] Разбивка по парам и знакомство-представление друг друга
- [x] Приоритезация целей тренинга и сбор проблем

### Fork and then clone codebase for further development
```
git clone --depth 1 -b <YYYY-MM-project> https://github.com/eugene-krivosheyev/unit-testing-and-tdd
```

Что такое автотест? (1.5/0.5)
-----------------------------
- [x] Каковы цели и задачи _авто_ тестов?
- [x] В чем отличие от отладки?
- [x] Определение модуля и возможные виды модулей

### Live Coding Demo на примере "общеизвестного класса"
- [x] [Подключение основного фреймворка](https://github.com/junit-team/junit4/wiki)
- [x] Понятие контракта по Б. Мейеру
- [x] Именование тест-кейса и теста
- [x] Понятие трасс выполнения (flows) и граничные условия
- [x] Подход AAA
- [x] Подход GWT из BDD
- [x] Роль фикстуры
- [x] [Забытый полуторный этап](https://github.com/junit-team/junit4/wiki/Assumptions-with-assume)
- [x] Тест = фиксированная трасса выполнения
- [x] Тестовый набор = спецификация компонента

### Coding Iteration #01
- [x] Given legacy codebase with Client and SavingAccount domain types
- [x] When developers add guard clauses for creating Client and SavingAccount
- [x] And cover these components with maintainable autotests
- [x] Then coverage for these components should be ≥ 80%
- [ ] And public code review should state for maintainability

Как замерять тестовое покрытие? (0.5/0)
---------------------------------------
- [ ] Понятие покрытия
- [ ] Виды расчета покрытия
- [ ] Инструменты расчета покрытия
- [ ] Анализ текущего покрытия
- [ ] Что покрывать в первую очередь в проектах?

Как ускорить разработку автотестов за счет готовых фреймворков и библиотек? (1/0.5)
-----------------------------------------------------------------------------------
- [ ] Подключение вспомогательных фреймворков
- [ ] Простые сравнения средствами основного фреймворка
- [ ] [Типизированные сравнения средствами встроенного фреймворка](https://github.com/junit-team/junit4/wiki/Matchers-and-assertthat)
- [ ] [Типизированные сравнения средствами отдельного фреймворка](https://assertj.github.io/doc/)
- [ ] [Таймауты](https://github.com/junit-team/junit4/wiki/Timeout-for-tests)
- [ ] [Исключения](https://github.com/junit-team/junit4/wiki/Exception-testing)
- [ ] [Параметризованные тесты](https://github.com/junit-team/junit4/wiki/Parameterized-tests)
- [ ] Расширение поведения тестов с помощью [запускальщиков](https://github.com/junit-team/junit4/wiki/Test-runners) и [правил](https://github.com/junit-team/junit4/wiki/Rules)

### Coding Iteration #02
- [ ] Given legacy codebase with Client and SavingAccount domain types
- [ ] When developers add consistency rules for linking Client and SavingAccount
- [ ] And cover these components with maintainable autotests
- [ ] Then coverage for these components should be ≥ 90%
- [ ] And public code review should state for maintainability

Как писать интеграционные и модульные тесты? (1/0.5)
-----------------------------------------------------
- [ ] В чем их специфика? Системные vs Интеграционные vs Модульные
- [ ] Как по коду определить скоуп?
- [ ] Виды тест-дублеров
- [ ] State-based testing VS Interaction-based testing
- [ ] [Фреймворк тест-дублеров уровня объектов](https://site.mockito.org)
- [ ] [Фреймворк тест-дублеров уровня REST-сервисов](http://wiremock.org/docs/getting-started/)
- [ ] [Фреймворк тест-дублеров уровня REST-сервисов](https://www.mock-server.com)
- [ ] Как среда сборки различает UT и IT

### Coding Iteration #03
- [ ] Given legacy codebase with Processing component
- [ ] When developers analyse and refactor production codebase for testability
- [ ] And cover this component with maintainable _unit_ autotests
- [ ] Then coverage for these component should be ≥ 90%
- [ ] And public code review should state for maintainability

Реализация фикстуры для обеспечения поддерживаемости тестов (1.5/1)
-------------------------------------------------------------------
- [ ] Как максимально реюзать фикстуры?
- [ ] Наследование тест-кейсов
- [ ] [Методы фреймворка](https://github.com/junit-team/junit4/wiki/Test-fixtures)
- [ ] Fixture Builders

### Coding Iteration #04
- [ ] Given test codebase
- [ ] When developers analyse and refactor test codebase for maintainability
- [ ] Then public code review should state for tests maintainability 

---

Как группировать тесты в наборы? (0.5/0)
----------------------------------------
- [ ] Зачем нужны test suites?
- [ ] Способы группировки "из коробки" фреймворка: [группы](https://github.com/junit-team/junit4/wiki/aggregating-tests-in-suites) и [категории](https://github.com/junit-team/junit4/wiki/Categories)
- [ ] Способ группировки средствами среды сборки

Как поддерживать качество тестов и снижать дублирование? (1/0.5)
----------------------------------------------------------------
### Как обеспечить качество самих тестов?
- [ ] Сначала поломанный тест
- [ ] Анализ тестового покрытия
- [ ] Ревью кода тестов
- [ ] Mutation coverage

### Анти-паттерны разработки модульных тестов: "вредные советы"
- [ ] Отношение к тестам не как к обычному коду
- [ ] Большие расфокусированные тесты
- [ ] Неговорящие имена
- [ ] Дублирование фикстуры
- [ ] Стопроцентное покрытие

### Coding Iteration #05
- [ ] Given test codebase
- [ ] When developers analyse and refactor test codebase for maintainability
- [ ] Then cross-team code review should state for tests maintainability

Как обеспечить тестопригодность дизайна legacy системы? (1.5/0.5)
-----------------------------------------------------------------
- [ ] Как оценить тестопригодность legacy code?
- [ ] Метрика Coupling
- [ ] Метрика Cohesion
- [ ] Понятность/осознаваемость
- [ ] Каков тестопригодный дизайн?

### Live Coding Demo для реализации компонента Reporting и нового CheckingAccount
- [ ] Принципы проектирования SOLID
- [ ] Шаблоны Factory и DI
- [ ] Шаблоны Strategy/State
- [ ] Ключевая диллема покрытия legacy code?

### Coding Iteration #06
- [ ] Given legacy codebase with Reporting component
- [ ] When developers implement polymorphic testable implementation for Reporting and CheckingAccount
- [ ] Then cross-team code review should state for its testability

Какую ценность дает практика TDD? (0.5/0)
-----------------------------------------
- [ ] Что такое TDD?
- [ ] TDD как практика проектирования
- [ ] Зачем нужен TDD?
- [ ] Минимизация отладки
- [ ] Снижение затрат на инкрементальную разработку
- [ ] Быстрая обратная связь
- [ ] Повышение поддерживаемости дизайна
- [ ] Удобство API "из коробки"
- [ ] Тесты как документация
- [ ] Предсказуемость поставки
- [ ] Чистый работающий код
- [ ] Управление страхом

В каком ритме писать по TDD? (1.5/0.5)
--------------------------------------
- [ ] Red – Green – Refactor
- [ ] Скорость отработки тестового набора как предусловие практики TDD

### Live Coding Demo для компонента Branch
- [ ] Операция добавления в branch

### Coding Iteration #07
- [ ] Given legacy codebase with Branch domain type
- [ ] When developers implement full-functional Branch implementation
- [ ] And made it through TDD cycles
- [ ] Then coverage for this component should be 100%
- [ ] And public code review should state for maintainability

---

[Базовые шаблоны TDD](https://www.dropbox.com/s/iiip3qdny3wwuyd/00.jpg?dl=0) (1.5/1)
------------------------------------------------------------------------------------
- [ ] Test First
- [ ] Isolated Tests
- [ ] Assertion First
- [ ] Test Data
- [ ] Child Test
- [ ] Test List
- [ ] Mock Object
- [ ] Crash Test Dummy

### Coding Iteration #08
- [ ] Given legacy codebase with Reporting service
- [ ] When developers implement reporting operation for branch
- [ ] And made it through TDD cycles
- [ ] Then coverage for this component should be 100%
- [ ] And coverage for this component should state this is _unit_ tests
- [ ] And public code review should state for maintainability

Шаблоны красной и зеленой полосы (1.5/1)
----------------------------------------
### Красной полосы
- [ ] One-step Test
- [ ] Starter Test
- [ ] Another Test
- [ ] Regression Test

### Зеленой полосы
- [ ] Obvious Implementation
- [ ] Triangulation
- [ ] One to Many

### Coding Iteration #09: TDD Ping-pong
- [ ] Given legacy codebase with Reporting service
- [ ] When developers implement reporting operations
- [ ] And made it through TDD cycles in pair ping-pong rules
- [ ] Then coverage for this component should be 100%
- [ ] And coverage for this component should state this is _unit_ tests
- [ ] And public code review should state for maintainability

Как внедрить UT&TDD в процесс разработки? (0.5/0)
-------------------------------------------------
### Каковы затраты на UT&TDD?
- [ ] Постановка экономической задачи

### Типовые ошибки TDD
- [ ] Code First
- [ ] Too Many Obvious Implementation
- [ ] Too Many Triangulations
- [ ] Coverage Affinity
- [ ] Implementation Testing but not Contract Testing

### Как внедрить?
- [ ] Общение с менеджментом
- [ ] Secure sandbox
- [ ] Последовательное расширение scope

Финальная ретроспектива (1/0.5)
-------------------------------
- [ ] План конкретных ближайших действий

Буфер (2)
---------
- [ ] Auto-testing and DB: patterns
- [ ] Test data generation: [mockaroo](https://www.mockaroo.com), [generatedata](http://generatedata.com), [10](https://www.softwaretestinghelp.com/test-data-generation-tools/) and [15](https://www.guru99.com/test-data-generation-tools.html) tools, [Faker](https://github.com/joke2k/faker), [PG](https://pgcodekeeper.readthedocs.io/ru/latest/mock_data.html), [not only db](https://quality-lab.ru/blog/8_services_for_data_generation/)
- [ ] BDD frameworks, e.g. Cucumber
