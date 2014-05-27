CREATE TABLE "person" (
  "person_id"    VARCHAR(32),
  "title"        VARCHAR(20),
  "first_name"   VARCHAR(20),
  "middle_names" VARCHAR(30),
  "surname"      VARCHAR(20),
  "maiden_name"  VARCHAR(20),
  "birth_date"   TIMESTAMP,
  "death_date"   TIMESTAMP,
  PRIMARY KEY ("person_id")
);

CREATE TABLE "config" (
  "name"  VARCHAR(50),
  "value" VARCHAR(100)
);

INSERT INTO "config" VALUES ('CONSUMER_KEY', 'QeIFue934HZPC9XfjyuQtncZfDf3vErD');
INSERT INTO "config" VALUES ('TOKEN', '3D0hurZzfW8TgKCRtTrl4OilEf3g0tqT');
INSERT INTO "config" VALUES ('CONSUMER_SECRET', 'iofQ0RFptpmzOU26bRPPQHJGGMYwLdljrng3KdmXF7bkYa2R');
INSERT INTO "config" VALUES ('TOKEN_SECRET', 'amdboCuAwilN6z45wJ9RD0kdfFopTx1i7z4w6mBzxf11bPDq');