# CryptoCurrencies
Данный REST-сервис осуществляет следующие функции:
- выводит всю доступную информцию из базы данных;
- выводит список доступных к работе криптовалют;
- каждую минуту сохраняет в базе данных обновленную информацию (берет информацию со стороннего API) о цене валюты (в долларах);
- выводит по запросу (необходимо добавить в гет запрос кодировку валюты) актуальную цену валюты из запроса;
В файле SQLscrypt предоставлен скрипт для создания базы данных для сохранения туда данных.
