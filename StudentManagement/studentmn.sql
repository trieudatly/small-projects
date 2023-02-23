CREATE DATABASE studentmn;

CREATE TABLE studentmn.student
(
	id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(30) not null,
    age int not null,
    gender int not null,
    major varchar(30) not null,
    score float not null
);

INSERT INTO studentmn.student()
VALUES(NULL,'LY TRIEU DAT','30','1','INFORMATION TECH','5'),
(NULL,'NGUYEN VAN A','32','1','MATHEMATICS ','6'),
(NULL,'LE VAN B','22','0','CHEMISTRY','8'),
(NULL,'TRAN VAN C','19','1','PHYSICS','7'),
(NULL,'LY NGOC TUAN','27','0','NANO MACHINERY','6');