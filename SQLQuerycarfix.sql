-- Tạo database
USE Carproject;
use master
drop database Carproject
--Tạo bảnh Roles
CREATE TABLE Roles (
  role_id INT PRIMARY KEY,
  title NVARCHAR(50)
);

-- Tạo bảng Staffs
CREATE TABLE Staffs (
  staff_id INT PRIMARY KEY,
  role_id int,
  username NVARCHAR(30),
  password NVARCHAR(30),
  full_name NVARCHAR(50),
  email NVARCHAR(50),
  phone NVARCHAR(10)
  FOREIGN KEY (role_id) REFERENCES Roles(role_id),
);

-- Tạo bảng Customers
CREATE TABLE Customers (
  customer_id INT PRIMARY KEY,
  full_name NVARCHAR(50),
  email NVARCHAR(50),
  phone NVARCHAR(10),
  driver_license_number NVARCHAR(20),
  username NVARCHAR(30),
  password NVARCHAR(30),
);

-- Tạo bảng Locations
CREATE TABLE Locations (
  location_id INT PRIMARY KEY,
  address NVARCHAR(100)
);

-- Tạo bảng Categories
CREATE TABLE Categories (
  category_id int primary key,
  title nvarchar(max)
);

-- Tạo bảng Cars
CREATE TABLE Cars (
  car_id INT IDENTITY(1,1) PRIMARY KEY NOT NULL ,
  name NVARCHAR(50) NULL,
  category_id INT NULL,
  detail NVARCHAR(MAX) NULL,
  registration_number NVARCHAR(20) NULL,
  location_id INT NULL,
  image NVARCHAR(20) NULL,
  price_per_day INT NULL,
  status NVARCHAR(20) NULL,
  FOREIGN KEY (location_id) REFERENCES Locations(location_id),
  FOREIGN KEY (category_id) REFERENCES Categories(category_id)
);

-- Tạo bảng Rentals
CREATE TABLE Rentals (
  rental_id INT PRIMARY KEY,
  customer_id INT,
  rental_start_date DATE,
  rental_end_date DATE,
  location_id INT,
  total_cost INT,
  rental_status NVARCHAR(20),
  FOREIGN KEY (location_id) REFERENCES Locations(location_id),
  FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
);

-- Tạo bảng RentalItems
CREATE TABLE RentalItems (
  rental_item_id INT PRIMARY KEY,
  rental_id INT,
  car_id INT,
  FOREIGN KEY (car_id) REFERENCES Cars(car_id),
  FOREIGN KEY (rental_id) REFERENCES Rentals(rental_id)
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
INSERT INTO Staffs (staff_id, role_id, username, password, full_name, email, phone)
VALUES 
 (1, 1, 'admin', 'admin', N'Nguyễn Văn A', 'nguyenvana@example.com', '1234567890'),
 (2, 2, 'manager', 'manager', N'Hoàng Thị F', 'hoangthif@example.com', '9012345678'),
 (3, 3, 'staff1', 'staff1', N'Võ Văn G', 'vovang@example.com', '3456789012'),
 (4, 3, 'staff2', 'staff2', N'Đặng Thị H', 'dangthih@example.com', '6789012345'),
 (5, 3, 'staff3', 'staff3', N'Trương Văn I', 'truongvani@example.com', '0123456789'),
 (6, 3, 'staff4', 'staff4', N'Nguyễn Thị K', 'nguyenthik@example.com', '8901234567')

-- Nhập dữ liệu Categories
INSERT INTO Categories (category_id, title)
VALUES 
	(1, N'4 chỗ (mini)'), 
	(2, N'4 chỗ (sedan)'),
	(3, N'5 chỗ (gầm cao)'), 
	(4, N'7 chỗ (gầm cao)'),
	(5, N'Bán tải');

-- Nhập dữ liệu Locations
INSERT INTO Locations (location_id, address)
VALUES
  (1, N'Quận Thanh Xuân, Hà Nội'),
  (2, N'Quận 1, Hồ Chí Minh'),
  (3, N'Quận Ngũ Hành Sơn, Đà Nẵng'),
  (4, N'Quận Sơn Trà, Đà Nẵng'),
  (5, N'Quận Thanh Khê, Đà nẵng');

-- Nhập dữ liệu Cars
SET IDENTITY_INSERT [dbo].[Cars] ON
INSERT INTO Cars (car_id, name, category_id, detail, registration_number, location_id, image, status, price_per_day)
VALUES
  (1, N'FORD RANGER RAPTOR 2019', 1, N'Bán tải Ford Raptor máy dầu số tự động . Đăng ký 1/2019. Xe full option gia đình sử dụng. Full chức năng hỗ trợ lái xe an toàn. Rửa xe miễn phí cho khách. Dạng nắp thùng cuộn', 'ABC123', 1, 'car1.jpg', 'Available', 200),
  (2, N'HYUNDAI ACCENT 2022', 1, N'Vinfast LUX A 2.0, sản xuất 2020. Xe mới sạch-mạnh-rộng rãi-xe trang bị', 'DEF456', 2, 'car2.jpg', 'Available', 150),
  (3, N'MAZDA 3', 1, N'MAZDA 3 tự động, sản xuất 6/2018. Cruise Control, phanh tay điện tử,...Xe gia đình không mùi', 'GHI789', 3, 'car3.jpg', 'Available', 180),
  (4, N'VINFAST FADIL 2020', 2, N'VinfastFadil, san xuat 2020. Xe gia dinh', 'JKL012', 4, 'car4.jpg', 'Available', 160),
  (5, N'VINFAST LUX A 2.0', 1, N'Vinfast LUX A 2.0, san xuat 2020. Xe mới sạch-manh-rộng rãi-xe trang bị', 'MNO345', 5, 'car5.jpg', 'Available', 250),
  (6, N'KIA K5 2022', 3, N'KIA K5 Trùm phân khúc sedan hạng D. Xe đăng ký mới 2022 KIA K5 là mẫu mới dành cho anh/chị em muốn thuê trải nghiệm, mang phiên bản Prenium màu đỏ công nghệ 4.0 hiện đại chạy rất sướng trên mọi cung đường. Xe có trang bị thêm bộ mâm VLT giúp xe chạy nhẹ, êm hơn bản zin và đẹp xe hơn.', 'PQR678', 1, 'car6.jpg', 'Available', 220),
  (7, N'KIA MORNING 2017', 3, N'Xe rất Tiết Kiệm Xăng . bảo dưỡng tốt. Có cảm biến mùi, bảo hiểm 2 chiều, Anh Chị an tâm sử dụng. Xe Được khử khuẩn Nano sau mỗi lần sử dụng', 'STU901', 2, 'car7.jpg', 'Available', 100),
  (8, N'MORRIS GARAGES MG5 STANDARD 2022', 2, N'Xe mới tháng 11/2022', 'VWX234', 3, 'car8.jpg', 'Available', 190),
  (9, N'VINFAST FADIL 2019', 3, N'Xe Fadil bản Full, gia đình đi nên sạch sẽ thơm tho. Nội thất đẹp, ghế da sang trọng. Siêu tiết kiệm xăng.', 'YZA567', 4, 'car9.jpg', 'Available', 170),
  (10, N'VINFAST FADIL 2022', 3, N'Xe sạch sẽ, thơm tho, có bảo hiểm', 'BCD890', 5, 'car10.jpg', 'Available', 210),
  (11, N'CHEVROLET CAPTIVA 2016', 4, N'Xe Captiva đã được đầu tư trang bị tận răng mọi tiện nghi của xe, bản cao cấp nhất của Chevrolet, trang bị thêm phi thuyền phù hợp các chuyến đi cần để hành lí nhiều.', 'EFG123', 1, 'car11.jpg', 'Available', 280),
  (12, N'HYUNDAI SANTA FE 2018', 4, N'Huynhdai santafe số tự động đăng ký thá 4/2018. Xe gia đình mới đep . Nội thất nguyen bản, sạch sẽ , bảo dưỡng thường xuyen, rửa xe miễn phi cho khách.Xe rộng  rãi,an toàn, tiện nghi phù hợp cho gia đình đi du lich . Xe được trang bị hệ thống đầy đủ và hiện đại', 'HIJ456', 2, 'car12.jpg', 'Available', 300),
  (13, N'MITSUBISHI XPANDER 2019', 5, N'Xpander 2019, màu bạc, 7 chỗ, xe mới. Xe gia đình nên được giữ gìn cẩn thận, bảo dưỡng định kỳ.', 'KLM789', 3, 'car13.jpg', 'Available', 230),
  (14, N'MITSUBISHI XPANDER 2020', 5, N'Xe gia đình hiệu Xpander màu đỏ, số tự động. 7 chỗ đa dụng, rộng rãi. Động cơ 1.5đủ dùng đi trong phố hoặc tham quan các điểm du lịch. Xe sản xuất và nhập tháng 10/2019. Được trang bị màn hình giải trí, dẫn đường, ra lệnh giọng nói, camera hành trình. Xe luôn được bảo dưỡng định kì, rửa xe sạch sẽ mỗi lần sử dụng', 'PQR012', 4, 'car14.jpg', 'Available', 240),
  (15, N'SUZUKI XL7 2021', 5, N'Suzuki XL7 stđ đời 2021 chạy rất tiết kiệm nhiên liệu.', 'STU345', 5, 'car15.jpg', 'Available', 270);
SET IDENTITY_INSERT [dbo].[Cars] OFF
--=========================================================================================================================
--=========================================================================================================================
--=========================================================================================================================
ALTER TABLE Cars
ADD [car_id] [int] IDENTITY(16,1) NOT NULL


	SELECT Cars.name AS car_name, Categories.title AS category_title, Locations.address, Cars.detail, Cars.registration_number, Cars.image, Cars.price_per_day, Cars.status
	FROM Cars 
	JOIN Categories ON Cars.category_id = Categories.category_id 
	INNER JOIN Locations ON Cars.location_id = Locations.location_id
	WHERE Categories.category_id=1  and Cars.car_id = 2


SELECT Rentals.rental_id, Customers.full_name AS customer_name, Rentals.rental_start_date, Rentals.rental_end_date
FROM Rentals
JOIN Customers ON Rentals.customer_id = Customers.customer_id;

select * from Customers
select * from Cars

/*UPDATE Cars and Categories 
SET Cars.name ='abc',Categories.title='abc',Cars.detail='mat xe',Cars.registration_number='12345', Cars.image='54321', Cars.price_per_day=500, Cars.status='non-availible'
FROM Cars
JOIN Categories ON Cars.category_id = Categories.category_id 
JOIN Locations ON Cars.location_id = Locations.location_id 
where Categories.category_id=1
*/

INSERT [dbo].[Cars]
([name] ,[category_id],[location_id] ,[registration_number],[image],[price_per_day],[status],[detail])
VALUES('abc',2,2,'abcd','car1.jpg',200,'none','none')