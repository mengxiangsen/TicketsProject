create database trainTicket;


use trainTicket;

create table users(
loginName nvarchar(50) primary key not null,
password nvarchar(50) not null);

create table vehicle(
vehicleNum nvarchar(50) primary key not null,
startPlace nvarchar(50) not null,
endPlace nvarchar(50) not null,
startTime time(7) not null,
endTime time(7) not null,
type nvarchar(50) not null,
price nvarchar(30) not null,
date date not null);

create table orders (
orderNum int primary key IDENTITY(1,1) not null ,
name nvarchar(50) not null,
phone nvarchar(50) not null,
identityCard nvarchar(50) not null,
startPlace nvarchar(50) not null,
endPlace nvarchar(50) not null,
date date not null,
VehicleNum nvarchar(50) not null,
price nvarchar(50) not null);


insert into users values('17751777819','986916');


insert into vehicle values('K528','北京','上海','04:30:00','08:19:00','其他','100.5','2015-12-17');
insert into vehicle values('G7032','北京','上海','06:00:00','07:59:00','G/D/C','139.5','2015-12-17');
insert into vehicle values('D636','北京','上海','06:09:00','08:21:00','Z字头','95.5','2015-12-17');
insert into vehicle values('T526','北京','上海','04:36:00','08:32:00','T字头','200.5','2015-12-17');
insert into vehicle values('K526','北京','上海','04:36:00','08:32:00','其他','295.5','2015-12-17');

