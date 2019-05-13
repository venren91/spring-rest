create table ecommerce.user(id integer primary key AUTO_INCREMENT, user_name varchar(100) not null,
email varchar (150), constraint user_unique unique(user_name));