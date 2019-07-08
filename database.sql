connect_root

create database ibmtest;
use ibmtest;

CREATE TABLE Customers (
customerId INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
names VARCHAR(30) NOT NULL,
lastnames VARCHAR(30) NOT NULL,
documentType VARCHAR(4) NOT NULL,
documentNumber VARCHAR(50) NOT NULL,
birthDate TIMESTAMP,
insertDate TIMESTAMP,
modifyDate TIMESTAMP
);

insert into Customers (names,lastnames,documentType,documentNumber,birthDate)
values ('Andres Jose','Cardona Tapia','CC','11230123832','1990-07-21');

insert into Customers (names,lastnames,documentType,documentNumber,birthDate)
values ('Luis Carlos','Yepes Cardona','CC','45012382','1972-01-30');

insert into Customers (names,lastnames,documentType,documentNumber,birthDate)
values ('Paola','Restrepo Diaz','CC','65903128','1994-09-01');

CREATE TABLE Cards (
cardId INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
accountNumber INT(15),
cardNumber VARCHAR(16) NOT NULL,
cardSerial VARCHAR(10) NOT NULL,
cvvCode  VARCHAR(4) NOT NULL,
cardState int(2),
stateDate TIMESTAMP,
expiredDate TIMESTAMP,
insertDate TIMESTAMP,
modifyDate TIMESTAMP
);

insert into cards (cardNumber,cardSerial,cvvCode,cardState,stateDate)
values ('3241890878903231','9083431551','987',1,CURRENT_TIMESTAMP);

insert into cards (cardNumber,cardSerial,cvvCode,cardState,stateDate)
values ('3241890878903232','9083431444','123',1,CURRENT_TIMESTAMP);

insert into cards (cardNumber,cardSerial,cvvCode,cardState,stateDate)
values ('3241890878903233','9083433332','943',1,CURRENT_TIMESTAMP);

insert into cards (cardNumber,cardSerial,cvvCode,cardState,stateDate)
values ('3241890878903234','9083432224','645',1,CURRENT_TIMESTAMP);

insert into cards (cardNumber,cardSerial,cvvCode,cardState,stateDate)
values ('3241890878903235','9083431110','787',1,CURRENT_TIMESTAMP);

CREATE TABLE Products (
productId INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
desProduct VARCHAR(30) NOT NULL,
productState VARCHAR(2) NOT NULL,
productInitials VARCHAR(6) NOT NULL,
insertDate TIMESTAMP,
modifyDate TIMESTAMP
);

insert into Products (desProduct,productState,productInitials) 
values ('Cuenta de ahorros',1,'CTAAHO');
insert into Products (desProduct,productState,productInitials) 
values ('Cuenta de corriente',1,'CTACTE');
insert into Products (desProduct,productState,productInitials) 
values ('Credito libre inversión',1,'CDTOLI');
insert into Products (desProduct,productState,productInitials) 
values ('Credito rotativo',1,'CDTORT');

CREATE TABLE Accounts (
accountId INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
customerId INT(15) NOT NULL,
productId INT(6) NOT NULL,
cardId INT(6),
creationDate TIMESTAMP,
accountState INT(2),
modifyDate TIMESTAMP
)


insert into Accounts (customerId,productId,cardId,creationDate,accountState)
values (1,1,1,TIMESTAMP('2019/06/15 16:23:30'),1);
insert into Accounts (customerId,productId,cardId,creationDate,accountState)
values (2,1,2,TIMESTAMP('2019/06/30 11:01:30'),1);
insert into Accounts (customerId,productId,cardId,creationDate,accountState)
values (2,2,3,TIMESTAMP('2019/07/01 09:22:02'),1);
insert into Accounts (customerId,productId,cardId,creationDate,accountState)
values (2,3,NULL,TIMESTAMP('2019/07/02 17:23:30'),1);
insert into Accounts (customerId,productId,cardId,creationDate,accountState)
values (3,1,NULL,TIMESTAMP('2019/07/02 11:23:30'),1);
insert into Accounts (customerId,productId,cardId,creationDate,accountState)
values (3,1,4,TIMESTAMP('2019/07/07 08:23:30'),1);


create view `salesReport` AS
SELECT 
	ac.accountId, ac.creationDate, c.customerId, c.names, c.lastnames, 
	c.documentNumber, p.desProduct, p.productId, ca.cardId, ca.cardNumber 
FROM accounts ac
LEFT JOIN cards ca ON ca.cardId = ac.cardId
INNER JOIN products p ON p.productId = ac.productId
INNER JOIN customers c ON c.customerId = ac.customerId;