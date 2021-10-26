CREATE DATABASE PRJ301_MVC2
GO

USE PRJ301_MVC2
GO

CREATE TABLE Registration 
(
	username varchar(20) NOT NULL,
	password varchar(20) NOT NULL,
	FirstName nvarchar(20) NOT NULL,
	MiddleName nvarchar(20),
	LastName nvarchar(20) NOT NULL,
	isAdmin bit,
	CONSTRAINT PK_Registration PRIMARY KEY (username)
)
GO

CREATE TABLE Product
(
	SKU varchar(20) NOT NULL,
	Name nvarchar(50) NOT NULL,
	Price money NOT NULL,
	Description nvarchar(200) NOT NULL,
	Quantity int NOT NULL,
	CONSTRAINT PK_Product PRIMARY KEY (SKU)
)
GO

CREATE TABLE Orders
(
	OrderID int IDENTITY,
	Name nvarchar(50) NOT NULL,
	Address nvarchar(50) NOT NULL,
	[Date] datetime DEFAULT GetDate() NOT NULL,
	Total money NOT NULL,
	CONSTRAINT PK_Order PRIMARY KEY (OrderID)
)
GO

CREATE TABLE OrderDetails
(
	OrderID int NOT NULL,
	SKU varchar(20) NOT NULL,
	Name nvarchar(50) NOT NULL,
	Price money NOT NULL,
	Quantity int NOT NULL,
	Total money NOT NULL,
	CONSTRAINT PK_OrderDetails PRIMARY KEY (OrderID),

	CONSTRAINT FK_OrderDetails_Orders 
	FOREIGN KEY (OrderID) 
	REFERENCES Orders(OrderID),

	CONSTRAINT FK_OrderDetails_Product 
	FOREIGN KEY (SKU) 
	REFERENCES Product(SKU),
)
GO

INSERT INTO Registration VALUES ('tien', 'tien123', N'Tiên', N'Lê Thủy', N'Huỳnh', 1)
INSERT INTO Registration VALUES ('phuong', 'phuong123', N'Phượng', N'Lâm Thúy', N'Nguyễn', 0)
INSERT INTO Registration VALUES ('quan', 'quan123', N'Quân', N'Đào Đức', N'Nguyễn', 0)
INSERT INTO Registration VALUES ('dat', 'dat123', N'Đạt', N'Thành', N'Trần', 0)
INSERT INTO Registration VALUES ('trung', 'trung123', N'Trung', N'Duy Hiếu', N'Trần', 0)
INSERT INTO Registration VALUES ('linh', 'linh123', N'Linh', N'Anh', N'Đỗ', 0)
INSERT INTO Registration VALUES ('thang', 'thang123', N'Thắng', N'Ngọc', N'Thắng', 0)
INSERT INTO Registration VALUES ('minh', 'minh123', N'Minh', N'Đặng Gia', N'Lê', 0)
INSERT INTO Registration VALUES ('huy', 'huy123', N'Huy', N'Minh', N'Trần', 0)
INSERT INTO Registration VALUES ('tuan', 'tuan123', N'Tuấn', N'Vũ Anh', N'Lưu', 0)

INSERT INTO Product VALUES ('BOOK00001', 'Java', '100000', 'Java Fundamental Book', 100)
INSERT INTO Product VALUES ('BOOK00002', 'MVC2', '90000', 'MVC2 Fundamental Book', 90)
INSERT INTO Product VALUES ('BOOK00003', 'Tomcat', '80000', 'Tomcat Fundamental Book', 80)
INSERT INTO Product VALUES ('BOOK00004', 'JDK', '70000', 'JDK Fundamental Book', 70)
INSERT INTO Product VALUES ('BOOK00005', 'Servlet', '60000', 'Servlet Fundamental Book', 60)
INSERT INTO Product VALUES ('BOOK00006', 'JavaBeans', '50000', 'JavaBeans Fundamental Book', 50)
INSERT INTO Product VALUES ('BOOK00007', 'JSP', '40000', 'JSP Fundamental Book', 40)
INSERT INTO Product VALUES ('BOOK00008', 'JDBC', '30000', 'JDBC Fundamental Book', 30)
INSERT INTO Product VALUES ('BOOK00009', 'Scripting Elements', '20000', 'Scripting Elements Fundamental Book', 20)
INSERT INTO Product VALUES ('BOOK00010', 'EL', '10000', 'EL Fundamental Book', 10)
