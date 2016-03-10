# --- !Ups
CREATE TABLE "user"("id" SERIAL PRIMARY KEY ,"name" varchar(200) , "email" varchar(200) , "mobile" varchar(200) ,"password" varchar(200));
INSERT INTO "user" values (1,'test', 'test@knoldus.com','9873215276','knoldus');
