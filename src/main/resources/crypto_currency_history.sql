create table crypto_currency_history (
	id int(11) NOT NULL AUTO_INCREMENT,
	crypto_currency_id int(11) NOT NULL, 
	price decimal(20,2) NOT NULL,
	created_at datetime default CURRENT_TIMESTAMP,
	PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;