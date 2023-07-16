-- Tạo database
create database Final
USE Final;

-- Tạo bảng Roles
CREATE TABLE Roles (
	role_id INT PRIMARY KEY,
	title NVARCHAR(50)
);

-- Tạo bảng 
CREATE TABLE Personnel (
	personnel_id varchar(10) PRIMARY KEY,
	role_id INT,
	full_name NVARCHAR(50),
	email NVARCHAR(50),
	phone NVARCHAR(10),
	username NVARCHAR(32) unique,
	password NVARCHAR(32),
	FOREIGN KEY (role_id) REFERENCES Roles(role_id)
);

-- Tạo bảng Customers
CREATE TABLE Customers (
	customer_id varchar(10) PRIMARY KEY,
	full_name NVARCHAR(50),
	email NVARCHAR(50),
	phone NVARCHAR(10),
	driver_license_number NVARCHAR(20),
	driver_license_picture varchar(20),
	username NVARCHAR(30),
	password NVARCHAR(30),
	status int
);

-- Tạo bảng Locations
CREATE TABLE Locations (
	location_id INT PRIMARY KEY,
	address NVARCHAR(max)
);

-- Tạo bảng Categories
CREATE TABLE Categories (
	category_id INT PRIMARY KEY,
	title NVARCHAR(MAX),
);

-- Tạo bảng Moreinfo
Create Table Carinfo(
	carinfo_id INT PRIMARY KEY,
	Transmission NVARCHAR(30),
	Fuel NVARCHAR(10),
	consumption NVARCHAR(30)
);

-- Tạo bảng Cars
CREATE TABLE Cars (
	car_id INT IDENTITY(1,1) PRIMARY KEY,
	name NVARCHAR(50),
	category_id INT,
	carinfo_id INT,
	detail NVARCHAR(MAX),
	registration_number NVARCHAR(20),
	location_id INT,
	image NVARCHAR(20),
	price_per_day int,
	status nvarchar(20),
	FOREIGN KEY (location_id) REFERENCES Locations(location_id),
	FOREIGN KEY (category_id) REFERENCES Categories(category_id),
	FOREIGN KEY (carinfo_id) REFERENCES CarInfo(carinfo_id)
);

-- Tạo bảng Rentals
CREATE TABLE Rentals (
  rental_id INT identity(1,1) PRIMARY KEY,
  personnel_id varchar(10),
  customer_id varchar(10),
  car_id int,
  rental_start_date DATE,
  rental_end_date DATE,
  location_id INT,
  total_cost INT,
  rental_status NVARCHAR(20),
  FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
  FOREIGN KEY (personnel_id) REFERENCES Personnel(personnel_id),
  FOREIGN KEY (car_id) REFERENCES Cars(car_id)
);

--=========================================================================================================================
--=========================================================================================================================
--=========================================================================================================================

-- Nhập dữ liệu Roles
INSERT INTO Roles(role_id, title)
VALUES
	(1, N'Quản trị viên'),
	(2, N'Quản lý xe'),
	(3, N'Nhân viên')

-- Nhập dữ liệu Staffs
INSERT INTO Personnel(personnel_id, role_id, username, password, full_name, email, phone)
VALUES 
	('AD001', 1, 'admin', 'admin', N'Nguyễn Văn A', 'nguyenvana@example.com', '1234567890'),
	('MA001', 2, 'manager', 'manager', N'Hoàng Thị F', 'hoangthif@example.com', '9012345678'),
	('ST001', 3, 'staff1', 'staff1', N'Võ Văn G', 'vovang@example.com', '3456789012'),
	('ST002', 3, 'staff2', 'staff2', N'Đặng Thị H', 'dangthih@example.com', '6789012345'),
	('ST003', 3, 'staff3', 'staff3', N'Trương Văn I', 'truongvani@example.com', '0123456789'),
	('ST004', 3, 'staff4', 'staff4', N'Nguyễn Thị K', 'nguyenthik@example.com', '8901234567')

-- Nhập dữ liệu Categories
INSERT INTO Categories (category_id, title)
VALUES 
	(1, N'4 chỗ (mini)'), 
	(2, N'4 chỗ (sedan)'),
	(3, N'5 chỗ (gầm cao)'), 
	(4, N'7 chỗ (gầm cao)'),
	(5, N'Bán tải');

--NHập dữ liệu Moreinfo
INSERT INTO CarInfo
VALUES
     (1,N'Số tự động',N'Xăng',N'5lít/100km'),
	 (2,N'Số sàn',N'Dầu',N'8lít/100km'),
	 (3,N'Số tự động',N'Dầu',N'7lít/100km'),
	 (4,N'Số sàn',N'Xăng',N'9lít/100km'),
	 (5,N'Số tự động',N'Dầu',N'6lít/100km');

-- Nhập dữ liệu Locations
INSERT INTO Locations (location_id, address)
VALUES
	(1, N'Quận Thanh Xuân, Hà Nội'),
	(2, N'Quận 1, Hồ Chí Minh'),
	(3, N'Quận Ngũ Hành Sơn, Đà Nẵng'),
	(4, N'Quận Sơn Trà, Đà Nẵng'),
	(5, N'Quận Thanh Khê, Đà nẵng');

-- Nhập dữ liệu Cars
SET IDENTITY_INSERT Cars ON;
INSERT INTO Cars (car_id, name, category_id, carinfo_id, detail, registration_number, location_id, image, price_per_day, status)
VALUES
	(1, N'FORD RANGER RAPTOR 2019', 1,1, N'Bán tải Ford Raptor máy dầu số tự động . Đăng ký 1/2019. Xe full option gia đình sử dụng. Full chức năng hỗ trợ lái xe an toàn. Rửa xe miễn phí cho khách. Dạng nắp thùng cuộn', 'ABC123', 1, 'car1.jpg', 200, 'Available'),
	(2, N'HYUNDAI ACCENT 2022', 1,3, N'Vinfast LUX A 2.0, sản xuất 2020. Xe mới sạch-mạnh-rộng rãi-xe trang bị', 'DEF456', 2, 'car2.jpg', 150, 'Available'),
	(3, N'MAZDA 3', 1,5, N'MAZDA 3 tự động, sản xuất 6/2018. Cruise Control, phanh tay điện tử,...Xe gia đình không mùi', 'GHI789', 3, 'car3.jpg', 180, 'Available'),
	(4, N'VINFAST FADIL 2020', 2,4, N'VinfastFadil, san xuat 2020. Xe gia dinh', 'JKL012', 4, 'car4.jpg', 130, 'Available'),
	(5, N'VINFAST LUX A 2.0', 1,2, N'Vinfast LUX A 2.0, san xuat 2020. Xe mới sạch-manh-rộng rãi-xe trang bị', 'MNO345', 5, 'car5.jpg', 160, 'Available'),
	(6, N'KIA K5 2022', 3,1, N'KIA K5 Trùm phân khúc sedan hạng D. Xe đăng ký mới 2022 KIA K5 là mẫu mới dành cho anh/chị em muốn thuê trải nghiệm, mang phiên bản Prenium màu đỏ công nghệ 4.0 hiện đại chạy rất sướng trên mọi cung đường. Xe có trang bị thêm bộ mâm VLT giúp xe chạy nhẹ, êm hơn bản zin và đẹp xe hơn.', 'PQR678', 1, 'car6.jpg', 190, 'Available'),
	(7, N'KIA MORNING 2017', 3,4, N'Xe rất Tiết Kiệm Xăng . bảo dưỡng tốt. Có cảm biến mùi, bảo hiểm 2 chiều, Anh Chị an tâm sử dụng. Xe Được khử khuẩn Nano sau mỗi lần sử dụng', 'STU901', 2, 'car7.jpg', 140, 'Available'),
	(8, N'MORRIS GARAGES MG5 STANDARD 2022', 2,2, N'Xe mới tháng 11/2022', 'VWX234', 3, 'car8.jpg', 170, 'Available'),
	(9, N'VINFAST FADIL 2019', 3,5, N'Xe Fadil bản Full, gia đình đi nên sạch sẽ thơm tho. Nội thất đẹp, ghế da sang trọng. Siêu tiết kiệm xăng.', 'YZA567', 4, 'car9.jpg', 150, 'Available'),
	(10, N'VINFAST FADIL 2022', 3,1, N'Xe sạch sẽ, thơm tho, có bảo hiểm', 'BCD890', 5, 'car10.jpg', 140, 'Available'),
	(11, N'CHEVROLET CAPTIVA 2016', 4,1, N'Xe Captiva đã được đầu tư trang bị tận răng mọi tiện nghi của xe, bản cao cấp nhất của Chevrolet, trang bị thêm phi thuyền phù hợp các chuyến đi cần để hành lí nhiều.', 'EFG123', 1, 'car11.jpg', 180, 'Available'),
	(12, N'HYUNDAI SANTA FE 2018', 4,3, N'Huynhdai santafe số tự động đăng ký thá 4/2018. Xe gia đình mới đep . Nội thất nguyen bản, sạch sẽ , bảo dưỡng thường xuyen, rửa xe miễn phi cho khách.Xe rộng  rãi,an toàn, tiện nghi phù hợp cho gia đình đi du lich . Xe được trang bị hệ thống đầy đủ và hiện đại', 'HIJ456', 2, 'car12.jpg', 200, 'Available'),
	(13, N'MITSUBISHI XPANDER 2019', 5,4, N'Xpander 2019, màu bạc, 7 chỗ, xe mới. Xe gia đình nên được giữ gìn cẩn thận, bảo dưỡng định kỳ.', 'KLM789', 3, 'car13.jpg', 170, 'Available'),
	(14, N'MITSUBISHI XPANDER 2020', 5,1, N'Xe gia đình hiệu Xpander màu đỏ, số tự động. 7 chỗ đa dụng, rộng rãi. Động cơ 1.5đủ dùng đi trong phố hoặc tham quan các điểm du lịch. Xe sản xuất và nhập tháng 10/2019. Được trang bị màn hình DVD, camera lùi, la răng đúc.', 'NOP012', 4, 'car14.jpg', 190, 'Available'),
	(15, N'FORD ECOSPORT 2021', 5,2, N'Ford EcoSport 2021, số tự động, màu xanh ngọc. Xe mới đẹp, đồng hồ đọc Km 4,000km. Điều hòa mát, ghế nỉ, màn hình camera lùi, gương chỉnh điện, đèn pha cảm biến anh sáng. Gương gập chỉnh điện, mâm đúc 17. Cảm biến trước sau', 'QRS345', 5, 'car15.jpg', 160, 'Available');
  
select * from Rentals

SELECT * FROM Rentals where rental_id =4
UPDATE Rentals SET rental_status = N'Chưa xác nhận', personnel_id = null WHERE rental_id = 4

SELECT IDENT_CURRENT('cars') + IDENT_INCR('cars')

SELECT COALESCE(MAX(car_id), 1) as id FROM Cars

SELECT COUNT(*) AS RecordCount FROM Cars

select * from Cars