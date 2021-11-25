INSERT INTO category (id,name,description)
VALUES (1, 'Аппаратная косметология', 'Аппаратная косметология'),
        (2, 'Инъекционная косметология', 'Инъекционная косметология'),
        (3, 'Клеточные технологии', 'клеточные технологии'),
        (4, 'Эстетическая косметология', 'эстетическая косметология'),
        (5, 'Коррекция фигуры', 'Коррекция фигуры'),
        (6, 'Эпиляция', 'Эпиляция');

INSERT INTO `procedure`(category_id, name, description, elapsed_time)
VALUES (1, 'Лазерное омоложение', '\tС возрастом коллагеновые структуры,
отвечающие за упругость и естественный блеск нашей кожи, становятся рыхлыми и утрачивают
способность обеспечивать коже ее прежние свойства. В стремлении
вернуть красоту многие решаются на пластическую операцию.
Стремительный прогресс в наши дни дает возможность получить то же
самое, но меньшими жертвами. А именно, лазерное омоложение нынче
является альтернативой пластической хирургии. С каждым днем процедура
«лазерное омоложение лица» в Минске приобретает все большую популярность.
Это легко объяснить – ее цена несравненно ниже, в отличие от
хирургического вмешательства.', 60),
       (2, 'Озонотерапия', '\tУ любой женщины, да и у мужчин тоже есть мечта избавиться
от «лишних» килограммов, сантиметров и «апельсиновых корочек» и вернуться к
стройным очертаниям тела. И сделать все это хочется быстро, без
изнуряющих диет и нагрузок в спортзале. Самым идеальным способом
похудеть и при этом «сильно не напрягаясь» и является озонотерапия
тела. Озонотерапия тела – это самый безопасный метод эстетической
косметологии для избавления от лишних жировых отложений.',30),
       (3, 'Клеточное омоложение', '\tЭто улучшенная лабораторией Regen Lab (Швейцария),
методика внедрения в ткани обогащенной тромбоцитами плазмы крови
для запуска естественного процесса омоложения. Заметный результат
наступает почти сразу после инъекции и со временем только усиливается.
Эффект от процедуры сохраняется на протяжение года: морщины заметно
разглаживаются, кожа глубоко увлажняется и выглядит сияющей,
подтягивается овал лица и восстанавливается плотность тканей. Полученный
препарат получают из собственной крови пациента, что значительно
сокращает проявления побочных эффектов, аллергических реакций и
отторжений.', 60),
       (4, 'Чистка лица', '\tЧистка лица – комплекс мероприятий, способствующий
исчезновению воспалительных процессов. Высококлассные специалисты
Академии красоты «Butterfly» подберут для Вас индивидуальную и
эффективную методику очистки в соответствии с типом Вашей кожи.
Чистка лица в Академии красоты «Butterfly» – это качественные
оригинальные косметические средства, новейшее качественное оборудование
и превосходный результат без вреда для здоровья.', 60),
       (5, 'Ручной массаж', '\tВ нашем салоне Вы окунетесь в уютную атмосферу и шикарный
интерьер, в руках квалифицированных специалистов, имеющих специальное
медицинское образование и большой опыт работы, почувствуете расслабление,
прилив сил и всю лечебную пользу ручного массажа! Наши специалисты
индивидуально подберут для Вас технику массажа. После процедуры Вам
предложат отдохнуть и выпить чашечку ароматного чая, кофе или
минеральной воды Серноводская с целебными свойствами горных источников.
Лучше всего проводить массаж курсами от 7-10 сеансов, это зависит от
поставленных целей.', 90),
       (6, 'Шугаринг', '\tШугаринг (от англ. «sugar» – «сахар») – популярный способ
депиляции, эффективность которого доказали еще красавицы Древнего
Востока. Персидские женщины издавна удаляли волосы на своем теле с
помощью пасты из меда, воска и целебных трав. И хотя в основе
современных косметических смесей лежит карамелизированный сахар,
этот вид депиляции не случайно называют «персидской».', 60);

INSERT INTO user (id, login, password, name, phone, role)
values (1,'administrator','admin','admin','+375291111111','admin'),
       (2,'client','clientuser','client','+375332222222','client'),
       (3,'employee1','employee1','employee1','+375443333333','employee'),
       (4,'employee2','employee2','employee2','+375254444444','employee');

INSERT INTO procedure_employee (employee_id, procedure_id, price, rating)
VALUES (3, 1, 20, 0),
       (3, 2, 20, 0),
       (3, 3, 20, 0),
       (3, 4, 20, 0),
       (3, 5, 20, 0),
       (3, 6, 20, 0),
       (4, 1, 20, 0),
       (4, 2, 20, 0),
       (4, 3, 20, 0),
       (4, 4, 20, 0),
       (4, 5, 20, 0),
       (4, 6, 20, 0);

INSERT INTO graphic (employee_id, date)
VALUES (3, '2021-12-01 09:00:00'),
       (3, '2021-12-01 10:00:00'),
       (3, '2021-12-01 11:00:00'),
       (3, '2021-12-01 12:00:00'),
       (3, '2021-12-01 13:00:00'),
       (3, '2021-12-01 14:00:00'),
       (3, '2021-12-01 15:00:00'),
       (3, '2021-12-01 16:00:00'),
       (3, '2021-12-01 17:00:00'),
       (4, '2021-12-01 09:00:00'),
       (4, '2021-12-01 10:00:00'),
       (4, '2021-12-01 11:00:00'),
       (4, '2021-12-01 12:00:00'),
       (4, '2021-12-01 13:00:00'),
       (4, '2021-12-01 14:00:00'),
       (4, '2021-12-01 15:00:00'),
       (4, '2021-12-01 16:00:00'),
       (4, '2021-12-01 17:00:00');