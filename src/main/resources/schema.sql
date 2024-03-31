
create table if not exists user_detail (
	id numeric,
	first_name varchar not null,
	last_name varchar,
	email varchar not null,
	constraint user_details_id_pkey primary key (id),
	constraint user_email_unique unique(email)
);

create table if not exists train (
	number numeric,
	name varchar not null,
	from_station varchar not null,
	to_station varchar not null,
	constraint train_number_pkey primary key (number),
	constraint train_name_unique unique (name)
);

create table if not exists section (
	id numeric,
	name varchar,
	train_number numeric,
	total_seats numeric not null,
	constraint section_id_pkey primary key(id),
	constraint section_train_number_fkey foreign key (train_number) references train(number)
);

create table if not exists schedule (
	id numeric,
	train_number numeric,
	date date,
	start_time time,
	ticket_fare int check (ticket_fare > 50),
	available_seats int default 0,
	constraint schedule_id_pkey primary key (id),
	constraint schedule_train_number_fkey foreign key (train_number) references train(number)
);

create table if not exists booking (
	id numeric,
	user_id numeric,
	schedule_id numeric,
	section_id numeric,
	seat_number numeric,
	constraint booking_id_pkey primary key (id),
	constraint booking_user_id_fkey foreign key (user_id) references user_detail(id),
	constraint booking_schedule_id_fkey foreign key (schedule_id) references schedule(id),
	constraint booking_section_id_fkey foreign key (section_id) references section(id)
);

create or replace view booking_view as
	select b.id as booking_id, b.seat_number,
		t.number as train_number, t.from_station, t.to_station,
		s.name as section_name,
		e.date, e.start_time, e.ticket_fare,
		concat(u.first_name, ' ', u.last_name) as name, u.email
	from booking b
		join schedule e on b.schedule_id = e.id
		join user_detail u on b.user_id = u.id
		join section s on b.section_id = s.id
		join train t on s.train_number = t.number;