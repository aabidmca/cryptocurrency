create table crypto_currency (
	id int(11) NOT NULL AUTO_INCREMENT,
	name varchar(50) NOT NULL,
	code varchar(50) NOT NULL,
	price decimal(20,2) NOT NULL,
	created_at datetime,
	updated_at datetime default CURRENT_TIMESTAMP,
	PRIMARY KEY(id),
	UNIQUE KEY `crypto_currency_name_unique` (`name`),
	UNIQUE KEY `crypto_currency_code_unique` (`code`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;