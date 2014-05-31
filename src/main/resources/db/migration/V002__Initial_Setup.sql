CREATE TABLE "person" (
  "person_id"    VARCHAR(32),
  "first_name"   VARCHAR(20),
  "middle_names" VARCHAR(50),
  "surname"      VARCHAR(20),
  "sex"          VARCHAR(1),
  "birth_place"  VARCHAR(200),
  "birth_year"   VARCHAR(4),
  "birth_month"  VARCHAR(2),
  "birth_day"    VARCHAR(2),
  "death_year"   VARCHAR(4),
  "death_month"  VARCHAR(2),
  "death_day"    VARCHAR(2),
  "occupation"   VARCHAR(200),
  "notes"        VARCHAR(2000),
  PRIMARY KEY ("person_id")
);

CREATE TABLE "media" (
  "media_id"  VARCHAR(32),
  "person_id" VARCHAR(32),
  "type"      VARCHAR(50),
  "caption"   VARCHAR(200),
  "url"       VARCHAR(100),
  PRIMARY KEY ("media_id"),
  FOREIGN KEY ("person_id") REFERENCES "person" ("person_id")
);

--insert into "media" values ('f1dea8651fbb427d8a3eefcd223e46ac','60860c6c14d74d2090d4d39e01ac3add','PHOTO_COVER','With Florence and May','https://copy.com/arU6FtyaBVuc');
--insert into "media" values ('7ad456cdc083421aaa6d1b8d8c5c225a','60860c6c14d74d2090d4d39e01ac3add','PHOTO','Top left','https://copy.com/cuVSocFG0nHF');
--insert into "media" values ('611be0bf2dd3475da3033d98105091cb','60860c6c14d74d2090d4d39e01ac3add','PHOTO','Top right','https://copy.com/qVNFxcebwymF');
--insert into "media" values ('814b8103e4e544df81de3a2f09d87528','60860c6c14d74d2090d4d39e01ac3add','PHOTO','2nd row 1st from left','https://copy.com/BcsRLItp8IUx');

CREATE TABLE "config" (
  "name"  VARCHAR(50),
  "value" VARCHAR(100)
);

INSERT INTO "config" VALUES ('CONSUMER_KEY', 'QeIFue934HZPC9XfjyuQtncZfDf3vErD');
INSERT INTO "config" VALUES ('TOKEN', '3D0hurZzfW8TgKCRtTrl4OilEf3g0tqT');
INSERT INTO "config" VALUES ('CONSUMER_SECRET', 'iofQ0RFptpmzOU26bRPPQHJGGMYwLdljrng3KdmXF7bkYa2R');
INSERT INTO "config" VALUES ('TOKEN_SECRET', 'amdboCuAwilN6z45wJ9RD0kdfFopTx1i7z4w6mBzxf11bPDq');