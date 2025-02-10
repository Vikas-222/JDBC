use db;

show tables;
show columns from product;

select * from product;
select * from customers;
select * from customer;
drop table customer;
truncate table product;
truncate table customer;

create table product(
	productid int primary key auto_increment,
    name varchar(20) not null unique,
    price float not null,
    quantity int not null);
    
create table customer(
	customerid int primary key auto_increment,
    name varchar(20) not null,
    email varchar(20) not null,
    phone varchar(13) not null);
    
create table cart(
	cartItem_id int primary key auto_increment,
    customerid int not null,
    productid int not null,
    quantity int not null,
    foreign key (customerid) references customer(customerid),
    foreign key (productid) references product(productid));
    