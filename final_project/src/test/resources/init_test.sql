DELETE FROM test.score WHERE id>0;
DELETE FROM test.graphic WHERE id>0;
DELETE FROM test.appointment WHERE id>0;
DELETE FROM test.procedure_employee WHERE id>0;
DELETE FROM test.user WHERE id>0;
DELETE FROM test.`procedure` WHERE id>0;
DELETE FROM test.category WHERE id>0;

INSERT INTO test.category (id,name,description)
VALUES (1, 'Аппаратная косметология', 'Аппаратная косметология'),
       (2, 'Инъекционная косметология', 'Инъекционная косметология'),
       (3, 'Клеточные технологии', 'клеточные технологии'),
       (4, 'Эстетическая косметология', 'эстетическая косметология'),
       (5, 'Коррекция фигуры', 'Коррекция фигуры'),
       (6, 'Эпиляция', 'Эпиляция');

INSERT INTO test.`procedure`(id, category_id, name, description, elapsed_time)
VALUES (1, 1, 'Лазерное омоложение', 'С возрастом коллагеновые структуры,
отвечающие за упругость и естественный блеск нашей кожи, становятся рыхлыми и утрачивают
способность обеспечивать коже ее прежние свойства. В стремлении
вернуть красоту многие решаются на пластическую операцию.
Стремительный прогресс в наши дни дает возможность получить то же
самое, но меньшими жертвами. А именно, лазерное омоложение нынче
является альтернативой пластической хирургии. С каждым днем процедура
«лазерное омоложение лица» в Минске приобретает все большую популярность.
Это легко объяснить – ее цена несравненно ниже, в отличие от
хирургического вмешательства.', 60),
       (2, 2, 'Озонотерапия', 'У любой женщины, да и у мужчин тоже есть мечта избавиться
от «лишних» килограммов, сантиметров и «апельсиновых корочек» и вернуться к
стройным очертаниям тела. И сделать все это хочется быстро, без
изнуряющих диет и нагрузок в спортзале. Самым идеальным способом
похудеть и при этом «сильно не напрягаясь» и является озонотерапия
тела. Озонотерапия тела – это самый безопасный метод эстетической
косметологии для избавления от лишних жировых отложений.',30),
       (3, 3, 'Клеточное омоложение', 'Это улучшенная лабораторией Regen Lab (Швейцария),
методика внедрения в ткани обогащенной тромбоцитами плазмы крови
для запуска естественного процесса омоложения. Заметный результат
наступает почти сразу после инъекции и со временем только усиливается.
Эффект от процедуры сохраняется на протяжение года: морщины заметно
разглаживаются, кожа глубоко увлажняется и выглядит сияющей,
подтягивается овал лица и восстанавливается плотность тканей. Полученный
препарат получают из собственной крови пациента, что значительно
сокращает проявления побочных эффектов, аллергических реакций и
отторжений.', 60),
       (4, 4, 'Чистка лица', 'Чистка лица – комплекс мероприятий, способствующий
исчезновению воспалительных процессов. Высококлассные специалисты
Академии красоты «Butterfly» подберут для Вас индивидуальную и
эффективную методику очистки в соответствии с типом Вашей кожи.
Чистка лица в Академии красоты «Butterfly» – это качественные
оригинальные косметические средства, новейшее качественное оборудование
и превосходный результат без вреда для здоровья.', 60),
       (5, 5, 'Ручной массаж', 'В нашем салоне Вы окунетесь в уютную атмосферу и шикарный
интерьер, в руках квалифицированных специалистов, имеющих специальное
медицинское образование и большой опыт работы, почувствуете расслабление,
прилив сил и всю лечебную пользу ручного массажа! Наши специалисты
индивидуально подберут для Вас технику массажа. После процедуры Вам
предложат отдохнуть и выпить чашечку ароматного чая, кофе или
минеральной воды Серноводская с целебными свойствами горных источников.
Лучше всего проводить массаж курсами от 7-10 сеансов, это зависит от
поставленных целей.', 90),
       (6, 6, 'Шугаринг', 'Шугаринг (от англ. «sugar» – «сахар») – популярный способ
депиляции, эффективность которого доказали еще красавицы Древнего
Востока. Персидские женщины издавна удаляли волосы на своем теле с
помощью пасты из меда, воска и целебных трав. И хотя в основе
современных косметических смесей лежит карамелизированный сахар,
этот вид депиляции не случайно называют «персидской».', 60);

INSERT INTO test.user(id, login, password, name, phone, role)
VALUES (1,'firstuser', 'bc45d36ca604cfbaf323636e79cf720e524bd8b5a34094763cd7e0c70a1d08a8',
        'firstuser','+375291111111', 'admin'),
        (2,'seconduser', 'a30ad9f78e746614c4448268ab86b9bbe8c709f17ec3789b7b01cd1fd3c2ce83',
         'seconduser','+375292222222', 'employee'),
        (3,'thirduser', 'b816140000cb14150c1fdb98c83edadc3fc8fe4dc43647312559be8b202fb1be',
         'thirduser','+375293333333', 'client');

INSERT INTO test.procedure_employee(id, employee_id, procedure_id, price, rating)
VALUES (1, 2, 1, 30, 0),
        (2, 2, 3, 50, 0);

INSERT INTO test.appointment(id, user_id, procedure_employee_id, date, status, price)
VALUES (1, 3, 2, '2021-12-01 09:00:00', 1, 50),
        (2, 3, 1, '2021-12-01 11:00:00', 1, 30),
        (3, 3, 2, '2021-12-01 12:00:00', 1, 50);

INSERT INTO test.score(id, user_id, value, appointment_id, comment, date)
VALUES (1,3, 5, 1, 'it is nice work!','2021-12-02 10:30:00'),
        (2, 3, 4, 2, 'good work', '2021-12-02 10:30:00'),
        (3, 3, 3, 3, 'normal', '2021-12-02 10:30:00');

INSERT INTO test.graphic(id, employee_id, date)
VALUES (1, 2, '2021-12-01 10:00:00'),
       (2, 2, '2021-12-01 13:00:00'),
       (3, 2, '2021-12-01 14:00:00');