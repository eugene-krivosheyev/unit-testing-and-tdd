Тренинг «Unit Testing & TDD»
============================
24 ак. часа, 18 астр. часов

Записи
======
- [11.05](https://us02web.zoom.us/rec/share/_nV4q0yROE5bqef9MOtCogp2A3XcNIR7eke1f9Q5l59R82OXOv1n3PYJZgrpFX-X.fHtbHb-sAS9ghCTZ)

Цели тренинга
============
После тренинга участники смогут:
-------------------------------
- [ ] Объяснить себе и менеджменту, где им нужны тесты, а где нет
- [ ] Разрабатывать тесты как «спецификации на примерах» в роли документации
- [ ] Разрабатывать поддерживаемые тесты и их наборы по модели 
- [ ] Подменять сложные компоненты системы на время тестирования
- [ ] Анализировать тестовое покрытие для принятия решений по тест-дизайну
- [ ] Обеспечивать поддерживаемый дизайн системы при помощи TDD

В итоге бизнес получает:
------------------------
- [ ] Контролируемый cycle time задач
- [ ] Контролируемое качество системы

Программа
=========
Зачем мы собрались? (1 часа всего / _из них_ 0.5 часа практики)
---------------------------------------------------------------
- [ ] Обзор тренинга
- [ ] О тренере
- [ ] Разбивка по парам и знакомство-представление друг друга
- [ ] Приоритезация целей тренинга и сбор проблем

### Fork and then clone codebase for further development
```
git clone --depth 1 -b <YYYY-MM-project> https://github.com/eugene-krivosheyev/unit-testing-and-tdd
```

Что такое автотест? (1.5/0.5)
-----------------------------
- [ ] Каковы цели и задачи _авто_ тестов?
- [ ] В чем отличие от отладки?
- [ ] Определение модуля и возможные виды модулей

### Live Coding Demo на примере "общеизвестного класса"
- [ ] [Подключение основного фреймворка](https://junit.org/junit5/docs/current/user-guide/#running-tests-build-maven)
- [ ] Понятие контракта по Б. Мейеру
- [ ] Именование [тест-кейса/тест-класса и теста/тест-метода](https://junit.org/junit5/docs/current/user-guide/#writing-tests-classes-and-methods)
- [ ] Понятие трасс выполнения (flows) и граничные условия
- [ ] Подходы AAA и GWT из BDD
- [ ] Роль фикстуры
- [ ] [Забытый полуторный этап](https://junit.org/junit5/docs/current/user-guide/#writing-tests-assumptions)
- [ ] Тест = фиксированная трасса выполнения
- [ ] Тестовый набор = спецификация компонента

### Coding Iteration #01
- [ ] Given legacy codebase with Client and SavingAccount domain types
- [ ] When developers add guard clauses for creating Client and SavingAccount
- [ ] And cover these components with maintainable autotests
- [ ] Then coverage for these components should be ≥ 80%
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
- [ ] Подключение вспомогательных фреймворков: build tool
- [ ] [Условное выполнение тестов](https://junit.org/junit5/docs/current/user-guide/#writing-tests-conditional-execution)
- [ ] [Типизированные сравнения средствами основного фреймворка](https://junit.org/junit5/docs/current/user-guide/#writing-tests-assertions) средствами основного фреймворка
- [ ] [Типизированные сравнения средствами отдельного фреймворка](https://hamcrest.org/JavaHamcrest/tutorial)
- [ ] [Типизированные сравнения средствами отдельного фреймворка](https://assertj.github.io/doc/)
- [ ] [Исключения](https://junit.org/junit5/docs/current/user-guide/#writing-tests-assertions)
- [ ] [Таймауты](https://junit.org/junit5/docs/current/user-guide/#writing-tests-assertions)
- [ ] [Параметризованные тесты](https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests)
- [ ] [Расширения](https://junit.org/junit5/docs/current/user-guide/#extensions), в том числе [готовые](https://junit.org/junit5/docs/current/user-guide/#writing-tests-built-in-extensions)

### Coding Iteration #02
- [ ] Given legacy codebase with Client and SavingAccount domain types
- [ ] When developers add consistency rules for linking Client and SavingAccount
- [ ] And cover these components with maintainable autotests
- [ ] Then coverage for these components should be ≥ 90%
- [ ] And public code review should state for maintainability

Как писать интеграционные и модульные тесты? (1/0.5)
-----------------------------------------------------
- [ ] В чем их специфика? [Системные vs Интеграционные vs Модульные](http://www.plantuml.com/plantuml/svg/JP112y8m38Nl-HLXT_-2Z3d4iw8NwI5sGOPREzgeYEo_cuuoUobvVL_w7gsZK93dEC14za5tnceVl1DsNOLVGBmUo7K31wEpzUFa7Jl1Iy_lnLPWftZmWy7D5ZEinWmpa1KVy1pr8Xq9o310Hq8tYenjyuzkT5YJyrkwjMRa7gLSx1E7Ls_P860XadFaoSTUQtpIOYaNkssCzgmrKx_W1m00)
- [ ] Как по коду определить скоуп?
- [ ] Виды тест-дублеров: [dummy](http://www.plantuml.com/plantuml/svg/SoWkIImgAStDuGf9BIvHqBLJ20uDuG9Y8BD2mQKqDxSImWFAW5HmEQJcfG2L0m00), [stub](http://www.plantuml.com/plantuml/svg/SoWkIImgAStDuGf9BIvHqBLJ20uDuG9YCBEaD8aBH2Ze8YJ0R91YBeVKl1IWJW00), [fake](http://www.plantuml.com/plantuml/svg/SoWkIImgAStDuGf9BIvHqBLJ20uDuKf9B4bCIYnELN1Bp4xb0ee09I1i4A6W2rK6n60sI2kNGsfU2j0S0000), [mock](http://www.plantuml.com/plantuml/svg/SoWkIImgAStDuGf9BIvHqBLJ20uDuG9Y4DCtFpaRB0xdEAJcfG0L0m00), [spy](http://www.plantuml.com/plantuml/svg/SoWkIImgAStDuGf9BIvHqBLJ20uDuG9YCBEWaWk8GKoNr8BKl9JKl18k60ke65a9GYUO3J8DOYHKaGDG4z0Q0000)
- [ ] State-based testing VS Interaction-based testing
- [ ] [Фреймворк тест-дублеров уровня объектов](https://site.mockito.org) и [интеграция с основным тестовым фреймворком](https://www.baeldung.com/mockito-junit-5-extension)
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
- [ ] [Когда и сколько раз создается объект тестового класса?](https://junit.org/junit5/docs/current/user-guide/#writing-tests-test-instance-lifecycle) 
- [ ] Как максимально реюзать фикстуры?
- Наследование тест-кейсов
- [Методы жизненного цикла теста](https://junit.org/junit5/docs/current/user-guide/#writing-tests-annotations)
- Fixture Builders

### Coding Iteration #04
- [ ] Given test codebase
- [ ] When developers analyse and refactor test codebase for maintainability
- [ ] Then public code review should state for tests maintainability 

---

Как группировать тесты в наборы? (0.5/0)
----------------------------------------
- [ ] Зачем нужны test suites?
- [ ] [Вложенные тесты](https://junit.org/junit5/docs/current/user-guide/#writing-tests-nested)
- [ ] [Теги](https://junit.org/junit5/docs/current/user-guide/#writing-tests-tagging-and-filtering) и [запуска набора](https://www.baeldung.com/junit-filtering-tests)
- [ ] Способ фильтрации средствами среды сборки [модульных](https://maven.apache.org/surefire/maven-surefire-plugin/examples/inclusion-exclusion.html) и [интеграционных](https://maven.apache.org/surefire/maven-failsafe-plugin/examples/inclusion-exclusion.html) тестов

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

---

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

[Базовые шаблоны TDD](https://gist.github.com/jameshwang/5335032) (1.5/1)
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
