# Тренинг «Unit Testing & TDD»
24 ак. часа, 18 астр. часов

# Цели тренинга
## После тренинга участники смогут:
1. Объяснить себе и менеджменту, где им нужны тесты, а где нет
1. Разрабатывать тесты как «спецификации на примерах» в роли документации
1. Разрабатывать поддерживаемые тесты и их наборы по модели 
1. Подменять сложные компоненты системы на время тестирования
1. Анализировать тестовое покрытие для принятия решений по тест-дизайну
1. Обеспечивать поддерживаемый дизайн системы при помощи TDD

## В итоге бизнес получает:
1. Контролируемый cycle time задач
1. Контролируемое качество системы

# Программа
## 1. Зачем мы собрались? (1 часа всего / _из них_ 0.5 часа практики)
1. Обзор тренинга
1. О тренере
1. Разбивка по парам и знакомство-представление друг друга
1. Приоритезация целей тренинга и сбор проблем

## 2. Что такое автотест? (1.5/0.5)
1. Каковы цели и задачи _авто_ тестов?
1. В чем отличие от отладки?
1. Определение модуля и возможные виды модулей

### Live Coding Demo на примере "общеизвестного класса"
1. [Подключение основного фреймворка](https://github.com/junit-team/junit4/wiki)
1. Понятие контракта по Б. Мейеру
1. Именование тест-кейса и теста
1. Понятие трасс выполнения (flows) и граничные условия
1. Подход AAA
1. Подход GWT из BDD
1. Роль фикстуры
1. [Забытый полуторный этап](https://github.com/junit-team/junit4/wiki/Assumptions-with-assume)
1. Тест = фиксированная трасса выполнения
1. Тестовый набор = спецификация компонента

### Coding Iteration #01
- Given legacy codebase with Client and SavingAccount domain types
- When developers add guard clauses for creating Client and SavingAccount
- And cover these components with maintainable autotests
- Then coverage for theses components should be ≥ 80%
- And public code review should state for maintainability

## 3. Как замерять тестовое покрытие? (0.5/0)
1. Понятие покрытия
1. Виды расчета покрытия
1. Инструменты расчета покрытия
1. Анализ текущего покрытия
1. Что покрывать в первую очередь в проектах?

## 4. Как ускорить разработку автотестов за счет готовых фреймворков и библиотек? (1/0.5)
1. Подключение вспомогательных фреймворков
1. Простые сравнения средствами основного фреймворка
1. [Типизированные сравнения средствами встроенного фреймворка](https://github.com/junit-team/junit4/wiki/Matchers-and-assertthat)
1. [Типизированные сравнения средствами отдельного фреймворка](https://github.com/alexruiz/fest-assert-2.x/wiki/One-minute-starting-guide)
1. [Таймауты](https://github.com/junit-team/junit4/wiki/Timeout-for-tests)
1. [Исключения](https://github.com/junit-team/junit4/wiki/Exception-testing)
1. [Параметризованные тесты](https://github.com/junit-team/junit4/wiki/Parameterized-tests)
1. Расширение поведения тестов с помощью [запускальщиков](https://github.com/junit-team/junit4/wiki/Test-runners) и [правил](https://github.com/junit-team/junit4/wiki/Rules)

### Coding Iteration #02
- Given legacy codebase with Client and SavingAccount domain types
- When developers add consistency rules for linking Client and SavingAccount
- And cover these components with maintainable autotests
- Then coverage for theses components should be ≥ 90%
- And public code review should state for maintainability

## 5. Как писать интеграционные и модульные тесты? (1/0.5)
1. В чем их специфика? Системные vs Интеграционные vs Модульные
1. Как по коду определить скоуп?
1. Виды тест-дублеров
1. State-based testing VS Interaction-based testing
1. [Фреймворк тест-дублеров уровня объектов](https://site.mockito.org)
1. [Фреймворк тест-дублеров уровня REST-сервисов](http://wiremock.org/docs/getting-started/)
1. Как среда сборки различает UT и IT

### Coding Iteration #03
- Given legacy codebase with Processing component
- When developers analyse and refactor production codebase for testability
- And cover this component with maintainable _unit_ autotests
- Then coverage for theses component should be ≥ 90%
- And public code review should state for maintainability

## 6. Реализация фикстуры для обеспечения поддерживаемости тестов (1.5/1)
1. Как максимально реюзать фикстуры?
1. Наследование тест-кейсов
1. [Методы фреймворка](https://github.com/junit-team/junit4/wiki/Test-fixtures)
1. Fixture Builders

### Coding Iteration #04
- Given test codebase
- When developers analyse and refactor test codebase for maintainability
- Then public code review should state for tests maintainability 

## 7. Как группировать тесты в наборы? (0.5/0)
1. Зачем нужны test suites?
1. Способы группировки "из коробки" фреймворка: [группы](https://github.com/junit-team/junit4/wiki/aggregating-tests-in-suites) и [категории](https://github.com/junit-team/junit4/wiki/Categories)
1. Способ группировки средствами среды сборки

## 8. Как поддерживать качество тестов и снижать дублирование? (1/0.5)
### Как обеспечить качество самих тестов?
1. Сначала поломанный тест
1. Анализ тестового покрытия
1. Ревью кода тестов
1. Mutation coverage

### Анти-паттерны разработки модульных тестов: "вредные советы"
1. Отношение к тестам не как к обычному коду
1. Большие расфокусированные тесты
1. Неговорящие имена
1. Дублирование фикстуры
1. Стопроцентное покрытие

### Coding Iteration #05
- Given test codebase
- When developers analyse and refactor test codebase for maintainability
- Then cross-team code review should state for tests maintainability

## 9. Как обеспечить тестопригодность дизайна legacy системы? (1.5/0.5)
1. Как оценить тестопригодность legacy code?
1. Метрика Coupling
1. Метрика Cohesion
1. Понятность/осознаваемость
1. Каков тестопригодный дизайн?

### Live Coding Demo для реализации компонента Reporting и нового CheckingAccount
1. Принципы проектирования SOLID
1. Шаблоны Factory и DI
1. Шаблоны Strategy/State
1. Ключевая диллема покрытия legacy code?

### Coding Iteration #06
- Given legacy codebase with Reporting component
- When developers implement polymorhic testable implementation for Reporting and CheckingAccount
- Then cross-team code review should state for its testability

## 10. Какую ценность дает практика TDD? (0.5/0)
1. Что такое TDD?
1. TDD как практика проектирования
1. Зачем нужен TDD?
1. Минимизация отладки
1. Снижение затрат на инкрементальную разработку
1. Быстрая обратная связь
1. Повышение поддерживаемости дизайна
1. Удобство API "из коробки"
1. Тесты как документация
1. Предсказуемость поставки
1. Чистый работающий код
1. Управление страхом

## 11. В каком ритме писать по TDD? (1.5/0.5)
1. Red – Green – Refactor
1. Скорость отработки тестового набора как предусловие практики TDD

### Live Coding Demo для компонента Branch
- Операция добавления в branch

### Coding Iteration #07
- Given legacy codebase with Branch domain type
- When developers implement full-functional Branch implementation
- And made it through TDD cycles
- Then coverage for this component should be 100%
- And public code review should state for maintainability

## 12. [Базовые шаблоны TDD](https://www.dropbox.com/s/iiip3qdny3wwuyd/00.jpg?dl=0) (1.5/1)
1. Test First
1. Isolated Tests
1. Assertion First
1. Test Data
1. Child Test
1. Test List
1. Mock Object
1. Crash Test Dummy

### Coding Iteration #08
- Given legacy codebase with Reporting service
- When developers implement reporting operation for branch
- And made it through TDD cycles
- Then coverage for this component should be 100%
- And coverage for this component should state this is _unit_ tests
- And public code review should state for maintainability

## 13. Шаблоны красной и зеленой полосы (1.5/1)
### Красной полосы
1. One-step Test
1. Starter Test
1. Another Test
1. Regression Test

### Зеленой полосы
1. Obvious Implementation
1. Triangulation
1. One to Many

### Coding Iteration #09: TDD Ping-pong
- Given legacy codebase with Reporting service
- When developers implement reporting operations
- And made it through TDD cycles in pair ping-pong rules
- Then coverage for this component should be 100%
- And coverage for this component should state this is _unit_ tests
- And public code review should state for maintainability

## 14. Как внедрить UT&TDD в процесс разработки? (0.5/0)
### Каковы затраты на UT&TDD?
1. Постановка экономической задачи

### Как внедрить?
1. Общение с менеджментом
1. Secure sandbox
1. Последовательное расширение scope

### Типовые ошибки
1. Code First
1. Too Many Obvious Implementation
1. Too Many Triangulations
1. Coverage Affinity
1. Implementation Testing but not Contract Testing

## 15. Финальная ретроспектива (1/0.5)
- План конкретных ближайших действий

## Буфер (2)
