create table person (
  person_id varchar(32),
  title varchar(20),
  first_name varchar(20),
  middle_names varchar(30),
  surname varchar(20),
  maiden_name varchar(20),
  birth_date timestamp,
  death_date timestamp,
  primary key(person_id)
);

insert into person (person_id,title,first_name,surname,birth_date) values ('asfg87fsdfw22rd2df3','Mr','Shaun','Storey', '1977-07-13 00:00:00');