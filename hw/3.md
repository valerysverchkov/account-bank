Реализовать контроллер, отвечающий за работу с счетами пользователя.

BankBookDto{

Integer id;

Integer userId;

String number;

BigDecimal amount;

String currency;

}

Методы:

Возвращает список BankBookDto пользователя по BankBookDto.userId GET /bank-book/by-user-id/{userId} (userId - обязательный, если нет, то возвращать ErrorDto с информацией)

Возвращает BankBookDto по BankBookDto.id GET  /bank-book/{bankBookId} (bankBookId - обязательный, если нет, то возвращать ErrorDto с информацией; если в нашем хранилище нет объекта с bankBookId, то генерируем исключение и обрабатываем его с результатом ErrorDto.NOT_FOUND)

Возвращает созданный лицевой счет POST /bank-book BODY: BankBookDto (если у BankBookDto.userId уже есть лицевой счет и номера счетов (number) совпадают, то проверяем тип валюты (currency), если они разные, то добавляем счет, если одинаковые, то генерируем исключение - счет с данной валютой уже имеется в хранилище: BankBookDto.id и обрабатываем исключение перед ответом)

Возвращает обновленный лицевой счет PUT /bank-book BODY: BankBookDto (по запросу обновляем счет в хранилище, но обновлять можем только userId, amount и currency, если пытаемся изменить number, то генерируем исключение - номер менять нельзя и обрабатываем исключение перед ответом)

Возвращает результат удаления счета (HTTP код) DELETE /bank-book/{bankBookId}

Возвращает результат удаления счетов пользователя (HTTP код) DELETE /bank-book/by-user-id/{userId} (Удаляет все счета пользователя из хранилища по BankBookDto.userId)