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

create table key (
  name varchar(50),
  value varchar(100)
);

insert into key values ('CONSUMER_KEY','QeIFue934HZPC9XfjyuQtncZfDf3vErD');
insert into key values ('TOKEN','3D0hurZzfW8TgKCRtTrl4OilEf3g0tqT');
insert into key values ('CONSUMER_SECRET','iofQ0RFptpmzOU26bRPPQHJGGMYwLdljrng3KdmXF7bkYa2R');
insert into key values ('TOKEN_SECRET','amdboCuAwilN6z45wJ9RD0kdfFopTx1i7z4w6mBzxf11bPDq');