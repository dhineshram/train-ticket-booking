insert into user_detail values
(1, 'Dhinesh', 'Ram', 'a@b.c'),
(2, 'Krishnan', null, 'd@e.f');

insert into train values
(123, 'London express', 'London', 'France'),
(456, 'France express', 'London', 'France');

insert into section values
(1, 'Section A', 123, 70),
(2, 'Section B', 123, 60),
(3, 'Section A', 456, 65);

insert into schedule values
(1, 123, '2024-03-31', '04:30:00', 450, 47),
(2, 456, '2024-03-31', '13:45:00', 680, 23);

insert into booking values
(1, 1, 2, 2, 37),
(2, 2, 1, 3, 12);