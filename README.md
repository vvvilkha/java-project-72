### Hexlet tests and linter status:
[![Actions Status](https://github.com/vvvilkha/java-project-72/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/vvvilkha/java-project-72/actions)
[![Java CI](https://sonarcloud.io/api/project_badges/measure?project=vvvilkha_java-project-72&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=vvvilkha_java-project-72)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=vvvilkha_java-project-72&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=vvvilkha_java-project-72)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=vvvilkha_java-project-72&metric=coverage)](https://sonarcloud.io/summary/new_code?id=vvvilkha_java-project-72)


---


# Анализатор страниц

> Учебный сервис, который принимает URL, делает HTTP-запрос, парсит HTML и сохраняет результаты проверки.  
> На списке сайтов видно последний статус проверки каждого URL, а на детальной странице — история всех проверок.
> Ссылка на сайт - https://java-project-72-jri5.onrender.com

---



 ##  Возможности

- Добавление URL с базовой нормализацией
- Проверка страницы: `status code`, `<title>`, `<h1>`, `meta[name=description]`
- История проверок по каждому сайту
- Последняя проверка каждого сайта в общем списке
- Простая flash-валидация и сообщения пользователю

---


##  Быстрый старт (локально)

```bash
# Клонируем
git clone https://github.com/vvvilkha/java-project-72.git
cd java-project-72/app

# Запуск приложения
./gradlew run

# Тесты и отчёты покрытия
./gradlew clean test jacocoTestReport
