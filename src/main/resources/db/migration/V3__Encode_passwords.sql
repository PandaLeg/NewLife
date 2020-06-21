-- Устанавливает расширение, если оно не установлено
alter user postgres with superuser;
create extension if not exists pgcrypto;
-- с помощью функции crypt берём старое значение пароля, генерируем соль.
-- salt - это дополнительное значение присоединяемое к паролю при шифровании
-- происходит подбор пароля с помощью радужных таблиц
-- bf - алгоритм шифрования
update clinic set password = crypt(password, gen_salt('bf', 8));
update doctor set password = crypt(password, gen_salt('bf', 8));
update patient set password = crypt(password, gen_salt('bf', 8));