Инструкция по запуску
1.Следует скопировать проект с github на ваш компьютер
2.Создать базу данных с помощью инструмента
    pgsql/bin/psql и файла rstylebase_dump.sql
    для этого в консоли psql нужно выполнить
    CREATE DATABASE rstylebase;
    ./psql rstylebase < rstylebase_dump.sql
3.Запустить сервер базы данных
4.Скопировать содержимое каталога
    Rstyle/target/Rstyle
    в каталог
    apache-tomcat-8.5.43/webapps/ROOT/
5.Запустить сервлет контейнер ApacheTomcat
    для этого перейти в каталог
    apache-tomcat-8.5.43/bin/
    и выполнить ./startup.sh
6.В браузере перейти по адресу
    localhost:8080
7.Приятного просмотра)


p.s. Подразумевается что
-установлен apache-tomcat
-установлен postgresql
-база данных запускается на том же компьютере, что и apache-tomcat