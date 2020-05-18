create table planet (
	id bigserial,
	name varchar(255) not null,
	angular_speed_in_degree_by_day numeric(19,6) not null,
	clockwise bool not null,
	distance_in_km numeric(19,6) not null,
	last_angle_in_rad numeric(19,6) not null,
	constraint planet_pk primary key (id)
);

create table galaxy (
	id serial,
	name varchar(255) not null,
	origin_date date not null,
	day bigint not null,
	planet_one_id bigint not null,
	planet_two_id bigint not null,
	planet_three_id bigint not null,
	constraint galaxy_pk primary key (id),
	constraint galaxy_planet_one_fk foreign key (planet_one_id) references planet(id),
	constraint galaxy_planet_two_fk foreign key (planet_two_id) references planet(id),
	constraint galaxy_planet_three_fk foreign key (planet_three_id) references planet(id)
);

create table weather (
	id bigserial,
	day bigint not null,
	forecast varchar(255) not null,
	galaxy_id integer not null,
	constraint weather_pk primary key (id),
	constraint weather_galaxy_fk foreign key (galaxy_id) references galaxy(id)
);

create unique index weather_galaxy_id_day_uk on weather(galaxy_id, day);

insert into planet(id, name, angular_speed_in_degree_by_day, clockwise, distance_in_km, last_angle_in_rad) values(1, 'Ferengi', 1, true, 500, 0);
insert into planet(id, name, angular_speed_in_degree_by_day, clockwise, distance_in_km, last_angle_in_rad) values(2, 'Betasoide', 3, true, 2000, 0);
insert into planet(id, name, angular_speed_in_degree_by_day, clockwise, distance_in_km, last_angle_in_rad) values(3, 'Vulcano', 5, false, 1000, 0);

insert into galaxy(id, name, origin_date, day, planet_one_id, planet_two_id, planet_three_id) values(1, 'Galaxy M', '2020-05-17', 0, 1, 2, 3);

select nextval('planet_id_seq');
select nextval('planet_id_seq');
select nextval('planet_id_seq');