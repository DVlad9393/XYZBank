Инструкция для локального поднятия Selenium-grid.

1. Открыть терминал (в IntelliJ IDEA) и перейти в директорию selenium-grid, выполнив команду:
cd selenium-grid
2. Запустить Hub с использованием файла конфигурации, выполнив команду:
java -jar selenium-server-4.21.0.jar hub --config hubConfig.json
3. Запустить Selenium Node, открыв новый терминал и перейдя в директорию selenium-grid, выполнив команду:
cd selenium-grid
Выполнить команду для запуска Node:
java -jar selenium-server-4.21.0.jar node --config nodeConfig.json
