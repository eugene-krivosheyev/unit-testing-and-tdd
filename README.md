Тренинг «Unit Testing & TDD»
============================
24 ак. часа, 18 астр. часов

Цели тренинга
=============
После тренинга участники смогут:
--------------------------------
- [ ] Обеспечить переход к (A)TDD в команде
- [ ] Сохранять качества дизайна и архитектуры при использовании (A)TDD
- [ ] Развивать через (A)TDD data driven applications на Spring, использующие БД и очереди 
- [ ] Развивать через (A)TDD интеграционные приложения на Spring, использующие внешние SOAP/REST-сервисы 
- [ ] Оптимизировать выполнение тестов для Spring-приложений
- [ ] Повысить надежность тестов многопоточного кода
- [ ] Повысить надежность тестов date/time-зависимого кода

Ожидания от участников на входе
-------------------------------
- [ ] Понимание автотестирования и практики TDD в объеме тренинга [Unit Testing and TDD](https://github.com/eugene-krivosheyev/unit-testing-and-tdd)


Программа
=========
Зачем мы собрались? (0.5 часа всего / _из них_ 0 часов практики)
---------------------------------------------------------------
- [ ] Обзор тренинга
- [ ] О тренере
- [ ] Разбивка по парам и знакомство-представление друг друга
- [ ] Приоритезация целей тренинга и сбор проблем
- [ ] Склонирована кодовая база
```
git clone --depth 1 -b <YYYY-MM-project> https://github.com/eugene-krivosheyev/unit-testing-and-tdd
```

Выравнивание понимания (A)TDD (1/1)
-----------------------------
- [ ] Given pairs
- [ ] When pairs align answers:
1. What is TDD?
1. What is rhythm/mantra for TDD cycle?
1. What are Test-Driven Development Patterns?
1. What are Red Bar Patterns?
1. What are Testing Patterns?
1. What are Green Bar Patterns?
1. What are the most important patterns of TDD, as you think?
1. What is ATDD?
1. What is the architecture mapping between Acceptance and Unit tests? 
- [ ] Then group aligns answers

Что дают практики TDD и ATDD? (2/1)
-----------------------------
- [ ] Given pairs
- [ ] And Layered Decision Making Model
- [ ] And Process Metrics concept
- [ ] And Internal Quality Attributes concept
- [ ] And Trade-off concept
- [ ] When pairs align answers:
1. What are intentional values of TDD in terms of PM/QA vs trade-offs?
1. What are your real-life shown values of TDD in terms of PM/QA vs trade-offs?
1. What are intentional values of ATDD in terms of PM/QA vs trade-offs?
1. What are your real-life shown values of ATDD in terms of PM/QA vs trade-offs?
1. What roles do communicate around Acceptance/System tests?
1. What roles do communicate around Integration tests?
1. What roles do communicate around Unit tests?
- [ ] Then group aligns answers
- [ ] And training backlog updated with (A)TDD problems faced

(A)TDD through Spring-based DB-driven app (2/1.5)
-----------------------------------------
- [ ] Given pairs
- [ ] And legacy codebase
- [ ] And recap for test fixture reuse with Builders
- [ ] And recap for test-doublers: dummy, stub, fake
- [ ] And recap for Spring test support
- [ ] And recap for Spring test context management
- [ ] And new app requirements
- [ ] When pairs implement new features through orthodox ATDD and TDD
- [ ] Then public code and design review stands for internal Quality Attributes
- [ ] And branch coverage and mutation coverage increased
- [ ] And architecture issues faced at development solved

(A)TDD through Spring-based message-driven app (2/1.5)
----------------------------------------------
- [ ] Given pairs
- [ ] And legacy codebase
- [ ] And recap for test-doublers: mock, spy
- [ ] And recap for test suites
- [ ] And recap for Spring test context reuse
- [ ] And new app requirements
- [ ] When pairs implement new features through orthodox ATDD and TDD
- [ ] Then public code and design review stands for internal Quality Attributes
- [ ] And branch coverage and mutation coverage increased
- [ ] And Spring context usage optimized for test run performance
- [ ] And test suites used for faster feedback 
- [ ] And architecture issues faced at development solved

(A)TDD through Spring-based integration-driven app (2/1.5)
--------------------------------------------------
- [ ] Given pairs
- [ ] And legacy codebase
- [ ] And recap for external REST service test-doublers
- [ ] And new app requirements
- [ ] When pairs implement new features through orthodox ATDD and TDD
- [ ] Then public code and design review stands for internal Quality Attributes
- [ ] And branch coverage and mutation coverage increased
- [ ] And Spring context usage optimized for test run performance
- [ ] And test suites used for faster feedback 
- [ ] And architecture issues faced at development solved


(A)TDD through macro benchmark (3/1.5)
------------------------------
- [ ] Given pairs
- [ ] And legacy codebase
- [ ] And recap for benchmark scopes
- [ ] Add recap for parallel test run options: standalone tool, build tool, test framework, test code
- [ ] Add recap for concurrency issues to detect
- [ ] Add recap for app monitoring and loging
- [ ] And new app requirements
- [ ] When pairs implement new features through orthodox ATDD and TDD
- [ ] Then public code and design review stands for internal Quality Attributes
- [ ] And branch coverage and mutation coverage increased
- [ ] And Spring context usage optimized for test run performance
- [ ] And test suites used for faster feedback 
- [ ] And concurrency and architecture issues faced at development solved

TDD through mirco benchmark (3/1.5)
--------------------------- 
- [ ] Given pairs
- [ ] And legacy codebase
- [ ] And recap for JMH
- [ ] And new app requirements
- [ ] When pairs verify correctness of new features' implementation through orthodox ATDD and TDD
- [ ] And concurrency issues faced at development solved

(A)TDD through date/time dependent app* (1/0.5)
---------------------------------------
- [ ] Given pairs
- [ ] And legacy codebase
- [ ] And recap for date/time API
- [ ] When pairs implement new features through orthodox ATDD and TDD
- [ ] Then public code and design review stands for internal Quality Attributes
- [ ] And branch coverage and mutation coverage increased
- [ ] And Spring context usage optimized for test run performance
- [ ] And test suites used for faster feedback 
- [ ] And concurrency and architecture issues faced at development solved

Как внедрить TDD в процесс разработки? (1/1)
--------------------------------------
- [ ] Given pairs
- [ ] When pairs align answers:
1. Most blocking factors for (A)TDD adoption
1. Solving steps for top-5 blockers
- [ ] Then group aligns answers

Финальная ретроспектива (0.5/0.5)
---------------------------------
- [ ] План конкретных ближайших действий на производстве
