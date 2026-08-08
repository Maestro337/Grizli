Бот-модератор для Discord

Задача бота это покрытие базовых команд модерации (кик, бан, мут, предупреждения)

## Реализовано на данный момент:
1. Хранение предупреждений в БД (PostgreSQL)
2. Проверка прав перед выполнением команд

## В процессе:
1. `/ban`, `/mute`, `/warn`
2. Хранение предупреждений в БД (PostgreSQL)
3. Проверка прав перед выполнением команд

## Стек:
![Java](https://img.shields.io/badge/-Java-007396?style=flat&logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/-Maven-C71A36?style=flat&logo=apachemaven&logoColor=white)
![JDA](https://img.shields.io/badge/-JDA-5865F2?style=flat&logo=discord&logoColor=white)

Java 21, JDA 6.4.2

## Запуск
Нужен токен бота из [Discord Developer Portal](https://discord.com/developers/applications), передаётся через переменную окружения `DISCORD_TOKEN`.

```bash
export DISCORD_TOKEN=your_token_here
```
Далее обычный Maven-запуск через IDE или `mvn exec:java`.
