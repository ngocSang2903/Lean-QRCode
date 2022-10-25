create database Duanmau
go
use Duanmau
go
create table CountryType(
	IdCountryType varchar(20) primary key,
	NameCountryType nvarchar(50) not null,
)
go
create table Country(
	IdCountry varchar(20) primary key,
	NameCountry nvarchar(50) not null,
	Price int not null,
	IdCountryType varchar(20),
	constraint pe_IDPT foreign key(IdCountryType) references CountryType(IdCountryType)
)
go
create table Employee(
	UsernameEmp varchar(50) primary key,
	[Password] varchar(20),
	EmployeeName nvarchar(100),
	Gender nvarchar(5),
	Birthday varchar(20),
	Phone varchar(20),
	Email varchar(50),
	[Address] nvarchar(max),
	Hinh varchar(max)
)
go
create table [Order](
	IDOrder varchar(20) primary key,
	DateOrder varchar(20),
	UsernameEmp varchar(50),
	Constraint pe_usE foreign key(UsernameEmp) references Employee(UsernameEmp)
)
go
create table OrderDetail(
	IDOrder varchar(20),
	IdCountry varchar(20),
	CustomerName nvarchar(100),
	Quantity int,
	primary key(IDOrder,IdCountry),
	Constraint pe_idod foreign key (IDOrder) references [Order](IDOrder),
	Constraint pe_idpro foreign key (IdCountry) references Country(IdCountry)
)
go
select * from CountryType
insert into CountryType(IdCountryType,NameCountryType) values ('SP01',N'Nước Ngọt')
insert into Country(IdCountry,NameCountry,Price,IdCountryType) values ('N01','Nước C2',10000,'SP01')
Insert into [Order] values('HD002',convert(varchar(20),getdate(),103),'sangnn')


create table Vipx(
hoc UNIQUEIDENTIFIER primary key DEFAULT NEWSEQUENTIALID(),
ten varchar(10)
)
insert into Vip(hoc,ten) values('sang')