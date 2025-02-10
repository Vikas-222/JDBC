			-- Multi-Table Queries: Create JOIN queries to combine data from multiple tables.
Use hotel;

SELECT users.*
FROM Users
INNER JOIN Bookings
ON users.userid = bookings.userid;

CREATE TABLE employees (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    department_id INT
);

CREATE TABLE departments (
    department_id INT AUTO_INCREMENT PRIMARY KEY,
    department_name VARCHAR(100)
);

INSERT INTO employees (name, department_id) VALUES
('Alice', 1),
('Bob', 2),
('Charlie', 1),
('David', 3),
('Eve', NULL);

INSERT INTO employees (name, department_id) VALUES
('Ram', 3),
( 'Ramesh', 4),
('Sujit', 1),
('Suresh',4);

INSERT INTO departments (department_id, department_name) VALUES
(1, 'HR'),
(2, 'Engineering'),
(3, 'Marketing'),
(4, 'Finance');

				--		Joins		--
                
select departments.department_name,departments.department_id 
	from departments Left join employees 
		on employees.department_id = departments.department_id;
        
select employees.name,employees.department_id 
	from employees right join departments 
		on employees.department_id = departments.department_id;
        
select employees.name,employees.department_id 
	from employees Inner join departments 
		on employees.department_id = departments.department_id;
        
SELECT 
    employees.name, departments.department_name
FROM
    employees
        Left JOIN
    departments ON employees.department_id = departments.department_id;
    
select users.userid, users.username, bookings.roomNumber, bookings.totalCost
	from bookings right join users
    on users.userid = bookings.userid;
    
SELECT users.*, bookings.roomNumber, bookings.totalCost
FROM bookings JOIN users ON users.userid = bookings.userid;
    
SELECT * FROM bookings full JOIN users;

SELECT employees.name, departments.department_name
FROM employees
LEFT JOIN departments
ON employees.department_id = departments.department_id
UNION
SELECT employees.name, departments.department_name
FROM employees
RIGHT JOIN departments
ON employees.department_id = departments.department_id;

SELECT employees.name, departments.department_name
FROM employees
CROSS JOIN departments;

select * from employees;

select e.name as employee, m.name as manager
	from employees e, employees m where e.employee_id = m.manager_id;
    
select users.username, rooms.roomNumber,rooms.roomPrice, bookings.roomNumber, bookings.totalCost 
	from users inner join rooms inner join bookings 
		where users.userid = bookings.userid and rooms.roomNumber = bookings.roomNumber;
    
select sum(roomPrice) as totalCost  from rooms; 
select avg(totalcost) as avgCost  from bookings; 
select count(userid) as totalUser  from users; 
select users.username as User, bookings.roomNumber from bookings left join users on users.userid = bookings.userid; 

									--				view 			--
create view bookingUserInnerView as
	SELECT users.*, bookings.roomNumber, bookings.totalCost
FROM bookings JOIN users ON users.userid = bookings.userid;

select * from bookingUserInnerView;


			-- Aggregation: Write queries using GROUP BY, performing calculations on grouped data.
					--		group by and aggregation function		--
							
select e.*, e.department_id  from employees e group by department_id;

SELECT COUNT(e.employee_id), e.department_id
FROM employees e
GROUP BY department_id;

select count(b.booking_id) as NumberOfVisits, userid
	from bookings b group by userid;

select max(b.totalCost) as HighestCost, userid
	from bookings b group by userid;



			-- Indexing: Add a single-column index on a heavily queried column, measure performance difference on large-ish data sets if possible.
					--		indexing		--
create database db;
use db;

CREATE TABLE customers(
  id int not null, 
  customer_name varchar(50)
);


INSERT INTO customers (id, customer_name)
SELECT n, CONCAT('Customer', n)
FROM
(
select a.N + b.N * 10 + c.N * 100 + d.N * 1000 + e.N * 10000 + f.N * 100000 + 1 N

from (select 0 as N union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) a

      , (select 0 as N union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) b

      , (select 0 as N union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) c

      , (select 0 as N union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) d

      , (select 0 as N union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) e

      , (select 0 as N union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) f

order by n
) t;

select * from customers;
			--	 before indexing duration to fetch is 1.17 sec 	--
select * from customers where id = 989999;

create index id on customers(id);
desc customers;
SHOW INDEX FROM customers;
drop index id_index on customers;

			--	 after indexing duration to fetch is 0.015 sec 	--
select * from customers where id = 989999;

show columns from customers;


					-- Transactions: Perform a sequence of DML statements under a transaction, and rollback if needed. 
                    
Begin;

create table employees
( name varchar(20) not null,
  department_id int not null);
  
INSERT INTO employees (name, department_id) VALUES
('Lucky', 3),
('Vijay', 4);


Select * from employees;

delete from employees where name = "Lucky";

 -- rollback; 
commit;

				
					-- Composite Indexes Create a composite index for a table. Execute queries before and after adding an index.
                    
CREATE UNIQUE INDEX index_unique ON customers(id,customer_name);
select * from customers where id = 989999;

alter table customers modify customer_name varchar(50) unique;
select * from customers order by id asc;



					-- Add a UNIQUE constraint to a table and try inserting duplicate values.
					-- Add a CHECK constraint and attempt inserting invalid data.
                    
CREATE TABLE Persons (
    ID int NOT NULL,
    name varchar(25) NOT NULL,
    Age int,
    City varchar(255),
    CONSTRAINT CHK_Person CHECK (Age>=18 And City='surat')
);
select * from persons;
insert into persons values
	(1,"Vikas",24,"surat"),
	(2,"Vinay",24,"surat"),
	(3,"john",22,"surat"),
	(1,"doe",18,"surat");