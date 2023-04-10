DROP DATABASE IF EXISTS Spring2023_Final_Project;

CREATE DATABASE IF NOT EXISTS Spring2023_Final_Project
CHARACTER SET utf8mb4
COLLATE utf8mb4_vietnamese_ci;

USE Spring2023_Final_Project;

CREATE TABLE Role(
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE Accounts(
	id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    is_delete BOOLEAN DEFAULT 0, -- 1 : true | 0 : false
    create_date DATETIME NOT NULL,
    update_date DATETIME NOT NULL
);

-- THÊM BẢNG
CREATE TABLE Authorities(
	id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT NOT NULL,
    role_id INT NOT NULL,
	CONSTRAINT fk_authorities_role FOREIGN KEY (role_id) REFERENCES Role(id),
    CONSTRAINT fk_authorities_accounts FOREIGN KEY (account_id) REFERENCES Accounts(id)
);

CREATE TABLE Profile(
	id INT AUTO_INCREMENT PRIMARY KEY,
    fullname VARCHAR(100),
    avatar VARCHAR(100),
    gender BOOLEAN,
    birthday DATE,
    email VARCHAR(255) UNIQUE,
    phone VARCHAR(15) UNIQUE,
    address VARCHAR(255),
    user_id INT,
    CONSTRAINT fk_profile_account FOREIGN KEY (user_id) REFERENCES Accounts(id)
);

CREATE TABLE Categories(
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    status BOOLEAN DEFAULT 0 -- 1 : true | 0 : false
);

CREATE TABLE Product_Material(
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    is_delete BOOLEAN DEFAULT 0 -- 1 : true | 0 : false
);

CREATE TABLE Discounts(
	id INT AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(255),
    scale FLOAT,
	create_date DATETIME NOT NULL,
    update_date DATETIME NOT NULL,
    staff_id INT,
    CONSTRAINT fk_Discounts_Accounts FOREIGN KEY (staff_id) REFERENCES Accounts(id)
);

CREATE TABLE Products(
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
    quantity INT,
    price DECIMAL(12, 1),
    image VARCHAR(100),
    description LONGTEXT,
    status BOOLEAN DEFAULT 0,
    is_delete BOOLEAN DEFAULT 0, -- 1 : true | 0 : false
	create_date DATETIME NOT NULL,
    update_date DATETIME NOT NULL,
    prod_material_id INT,
    categories_id INT NOT NULL,
    discount_id INT,
    CONSTRAINT fk_product_prod_material FOREIGN KEY (prod_material_id) REFERENCES Product_Material(id),
    CONSTRAINT fk_product_category FOREIGN KEY (categories_id) REFERENCES Categories(id),
	CONSTRAINT fk_product_discount FOREIGN KEY (discount_id) REFERENCES Discounts(id)
);

CREATE TABLE Wish_List(
	user_id INT,
	product_id INT,
	PRIMARY KEY(user_id, product_id),
    CONSTRAINT fk_WishList_Accounts FOREIGN KEY (user_id) REFERENCES Accounts(id),
    CONSTRAINT fk_WishList_Products FOREIGN KEY (product_id) REFERENCES Products(id)
);

CREATE TABLE Size(
	id VARCHAR(10) PRIMARY KEY -- S, M, L or ...
);

CREATE TABLE Product_Size(
	id INT AUTO_INCREMENT PRIMARY KEY,
	product_id INT,
    size_id VARCHAR(10),
    price DECIMAL(12, 1),
    quantity INT,
    CONSTRAINT fk_productSize_Products FOREIGN KEY (product_id) REFERENCES Products(id),
    CONSTRAINT fk_productSize_Size FOREIGN KEY (size_id) REFERENCES Size(id)
);

CREATE TABLE Order_Status(
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) -- Begin: Đơn đã đặt ---> Đã xác nhận thông tin ---> Chờ lấy hàng ---> Đang giao ---> Đánh giá End.
);

CREATE TABLE Order_Method_Payment(
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) -- Cash or Card Credit
);

CREATE TABLE Orders(
	id INT AUTO_INCREMENT PRIMARY KEY,
    phone VARCHAR(15),
    address VARCHAR(255),
    note VARCHAR(255),
    total DECIMAL(10, 2),
	create_date DATETIME NOT NULL,
    update_date DATETIME NOT NULL,
    orderstatus_id INT,
    ordermethod_id INT,
    profile_id INT,
    CONSTRAINT fk_Order_OrderStatus FOREIGN KEY (orderstatus_id) REFERENCES Order_Status(id),
    CONSTRAINT fk_Order_OrderMethodPayment FOREIGN KEY (ordermethod_id) REFERENCES Order_Method_Payment(id),
    CONSTRAINT fk_Order_Profile FOREIGN KEY (profile_id) REFERENCES Profile(id)
);

CREATE TABLE Order_Detail(
	id INT AUTO_INCREMENT PRIMARY KEY,
    price DECIMAL(12, 1),
    quantity INT,
    sale FLOAT,
    order_id INT,
    product_id INT,
    CONSTRAINT fk_OrderDetail_Orders FOREIGN KEY (order_id) REFERENCES Orders(id),
    CONSTRAINT fk_OrderDetail_Products FOREIGN KEY (product_id) REFERENCES Products(id)
);

CREATE TABLE Product_Rate(
	id INT AUTO_INCREMENT PRIMARY KEY,
    rate FLOAT,
    comment VARCHAR(255),
	create_date DATETIME NOT NULL,
    profile_id INT,
    product_id INT,
    CONSTRAINT fk_ProductRate_Profile FOREIGN KEY (profile_id) REFERENCES Profile(id),	
    CONSTRAINT fk_ProductRate_Products FOREIGN KEY (product_id) REFERENCES Products(id)
);

-- ========================================= THÊM BẢNG =======================================
CREATE TABLE Revenus(
	id INT AUTO_INCREMENT PRIMARY KEY,
    date DATETIME NOT NULL,
	total DECIMAL(10, 2),
	amount INT,
    category_id INT,
    product_id INT,
    CONSTRAINT fk_Revenus_category FOREIGN KEY (category_id) REFERENCES Categories(id),
    CONSTRAINT fk_Revenus_Products FOREIGN KEY (product_id) REFERENCES Products(id)
);

CREATE TABLE History_Payment(
	id INT AUTO_INCREMENT PRIMARY KEY,
    total DECIMAL(10, 2),
    create_date DATETIME NOT NULL
);

CREATE TABLE Coupon(
	id INT AUTO_INCREMENT PRIMARY KEY,
    code nvarchar(20) not null,
    value int not null,
    quantity int not null,
    create_date datetime,
    end_date datetime null
);
-- ========================================= END ==============================================

-- ==================================== THÊM DỮ LIỆU ==========================================
INSERT INTO Role(name) VALUES
	('Director'),
    ('Staff'),
	('Customer');
 
INSERT INTO Accounts(username, password, is_delete, create_date, update_date) VALUES
	('director1', 'director123', '0', '2022-1-11', '2022-1-12'),
    ('director2', 'director123', '0', '2022-2-12', '2022-2-22'),
	('staff1', 'staff123', '0', '2022-1-11', '2022-1-20'),
	('staff2', 'staff123', '0', '2022-1-11', '2022-2-5'),
    ('staff3', 'staff123', '0', '2022-1-11', '2022-8-12'),
	('cust1', 'cust123', '0', '2022-1-11', '2022-1-12'),
    ('cust2', 'cust123', '0', '2022-1-11', '2022-2-20'),
    ('cust3', 'cust123', '0', '2022-1-12', '2020-3-1'),
    ('cust4', 'cust123', '0', '2022-1-12', '2022-3-7'),
    ('cust5', 'cust123', '0', '2022-1-12', '2022-4-25'),
    ('cust6', 'cust123', '0', '2022-1-13', '2022-4-17'),
    ('cust7', 'cust123', '0', '2022-2-1', '2022-6-2'),
    ('cust8', 'cust123', '0', '2022-2-4', '2022-4-12'),
    ('cust9', 'cust123', '0', '2022-3-11', '2022-8-18'),
    ('cust10', 'cust123', '0', '2022-3-3', '2022-5-12'),
    ('cust11', 'cust123', '0', '2022-4-7', '2022-7-19'),
    ('cust12', 'cust123', '0', '2022-4-20', '2022-8-3'),
    ('cust13', 'cust123', '0', '2022-4-27', '2022-9-1'),
    ('cust14', 'cust123', '0', '2022-5-9', '2022-8-21'),
    ('cust15', 'cust123', '0', '2022-5-20', '2022-10-28'),
    ('cust16', 'cust123', '0', '2022-6-26', '2022-12-10'),
    ('cust17', 'cust123', '0', '2022-6-29', '2022-8-11'),
    ('cust18', 'cust123', '0', '2022-6-30', '2022-9-10'),
    ('cust19', 'cust123', '0', '2022-7-11', '2022-7-22'),
    ('cust20', 'cust123', '0', '2022-7-28', '2022-8-18'),
    ('cust21', 'cust123', '0', '2022-8-1', '2022-9-23'),
    ('cust22', 'cust123', '0', '2022-8-15', '2022-10-2'),
    ('cust23', 'cust123', '0', '2022-8-17', '2022-9-26'),
    ('cust24', 'cust123', '0', '2022-9-20', '2022-9-24'),
    ('cust25', 'cust123', '0', '2022-9-26', '2022-10-22'),
    ('cust26', 'cust123', '0', '2022-9-30', '2022-11-5'),
    ('cust27', 'cust123', '0', '2022-10-21', '2022-10-30'),
    ('cust28', 'cust123', '0', '2022-10-27', '2022-11-2'),
    ('cust29', 'cust123', '0', '2022-11-22', '2022-12-23'),
    ('cust30', 'cust123', '0', '2022-11-22', '2023-1-30'),
    ('cust31', 'cust123', '0', '2022-12-26', '2022-12-30'),
    ('cust32', 'cust123', '0', '2022-12-29', '2023-1-3'),
    ('cust33', 'cust123', '0', '2022-12-29', '2023-1-24'),
    ('cust34', 'cust123', '0', '2022-12-31', '2023-1-23'),
    ('cust35', 'cust123', '0', '2023-1-1', '2023-1-26'),
    ('cust36', 'cust123', '0', '2023-2-2', '2023-2-3'),
    ('cust37', 'cust123', '0', '2023-2-3', '2023-2-3'),
    ('cust38', 'cust123', '0', '2023-2-5', '2023-2-6'),
    ('cust39', 'cust123', '0', '2023-2-6', '2023-2-6'),
    ('cust40', 'cust123', '0', '2023-2-7', '2023-2-7');
    
INSERT INTO Authorities(account_id, role_id ) VALUES
	(1,1),
    (2,1),
    (3,2),
    (4,2),
	(5,2),
    (6,3),
	(7,3),
    (8,3),
	(9,3),
    (10,3),
    (11,3),
    (12,3),
    (13,3),
    (14,3),
    (15,3),
    (16,3),
    (17,3),
    (18,3),
    (19,3),
    (20,3),
    (21,3),
    (22,3),
    (24,3),
    (25,3),
    (26,3),
    (27,3),
    (28,3),
    (29,3),
    (30,3),
    (31,3),
    (32,3),
    (33,3),
    (34,3),
    (35,3),
    (36,3),
    (37,3),
    (38,3),
    (39,3),
    (40,3),
    (41,3),
    (42,3),
    (43,3),
    (44,3),
    (45,3);
    
INSERT INTO Profile(fullname, avatar, gender, birthday, email, phone, address, user_id) VALUES
    ('Đỗ Phi Hùng', 'admin1.png', 1, '2002-12-1', 'hungdpps19239@fpt.edu.vn', '0388829379', 'Quang Trung, Phường 11, Gò Vấp, Tp Hồ Chí Minh', 1),
	('Bùi Nhật Trường', 'admin2.png', 1, '1999-1-1', 'truongbnps19317@fpt.edu.vn', '09375863952', 'Lạc Long Quân, Phường 3, Quận 11, Tp Hồ Chí Minh', 2), 
	('Phan Nguyễn Trường Giang', 'admin3.png', 1, '1995-1-1', 'giangpntps19276@fpt.edu.vn', '0934859262', 'Lạc Long Quân, Phường 3, Quận 11, Tp Hồ Chí Minh', 3), 
	('Nguyễn Hoàng Quốc Anh', 'admin4.png', 1, '2002-1-1', 'anhnhqps17999@fpt.edu.vn', '03573924659', 'Quang Trung, Phường 11, Gò Vấp, Tp Hồ Chí Minh', 4), 
	('Cao Hoài Bảo Ngọc', 'admin5.png', 0, '2002-1-1', 'ngocchbps19105@fpt.edu.vn', '03327595625', 'Phạm Thế Hiển, Quận 8, Tp Hồ Chí Minh', 5), 
   
	('Đặng Hoàng Thái', 'cust1.png', 1, '1992-03-11', 'cust1@gmail.com', '0984265835', 'Lý Thường Kiệt, Phường 6, Quận Tân Bình, Tp Hồ Chí Minh', 6),
	('Liễu Hà Thu', 'cust2.png', 0, '1989-05-18', 'cust2@gmail.com', '0936584625', 'Đất Thánh, Phường 6, Quận Tân Bình, Tp Hồ Chí Minh', 7), 
	('Bùi Tuấn Dũng', 'cust3.png', 1, '1997-11-21', 'cust3@gmail.com', '0357553860', 'Lý Thường Kiệt, Phường 6, Quận Tân Bình, Tp Hồ Chí Minh', 8), 
	('Nguyễn Bảo Như An', 'cust4.png', 0, '1889-08-22', 'cust4.@gmail.com', '0893657846', '540 Cách Mạng Tháng Tám, Phường 11, Quận 3, Tp Hồ Chí Minh', 9), 
	('Trần Đặng Anh Thư', 'cust5.png', 0, '1984-09-15', 'cust5.@gmail.com', '0945285638', 'Phường 15, Quận 10, Tp Hồ Chí Minh', 10), 
    
    ('Lâm Anh Tuấn', 'cust6.png', 1, '1999-11-28', 'cust6.@gmail.com', '0345869306', '205 Trần Văn Đang, Phường 11, Quận 3, Tp Hồ Chí Minh', 11),
	('Trần Anh Quốc', 'cust7.png', 1, '1992-08-03', 'cust7.@gmail.com', '0956385934', '270 Phan Đình Phùng, Phường 1, Quận Phú Nhuận, Tp Hồ Chí Minh', 12), 
	('Nguyễn Thái Thiên Ân', 'cust8.png', 0, '2002-07-17', 'cust8.@gmail.com', '0956382547', '280 Nam Kỳ Khởi Nghĩa, Phường 8, Quận 3, Tp Hồ Chí Minh', 13), 
	('Trịnh Đình Trí', 'cust9.png', 1, '2001-09-30', 'cust9.@gmail.com', '0357839572', '216 Võ Thị Sáu, Quận 3, Tp Hồ Chí Minh', 14), 
	('Cao Thái Hà', 'cust10.png', 0, '1998-12-13', 'cust10.@gmail.com', '0945637956', 'Nguyễn Trung Trực, Phường Bến Nghé, Quận 1, Tp Hồ Chí Minh', 15), 
    
    ('Thái Lý Luận', 'cust11.png', 1, '1990-05-07', 'cust11@gmail.com', '0354239379', 'Phường 14, Quận 4, Tp Hồ Chí Minhg', 16),
	('Nguyễn Trần Thiên Bảo', 'cust12.png', 1, '1995-04-01', 'cust12@gmail.com', '0385626374', 'Nguyễn Tất Thành, Phường 13, Quận 4, Tp Hồ Chí Minh', 17), 
	('Đỗ Ánh Tuyết', 'cust13.png', 0, '1989-08-22', 'cust13@gmail.com', '0835467284', '8 Nguyễn Thiện Thành, Thủ Thiêm, Tp Thủ Đức', 18), 
	('Nguyễn Bảo Kha', 'cust14.png', 1, '2002-1-1', 'cust14@gmail.com', '0956354859', 'B3/B8 Lương Định Của, Phường Bình an, Quận 2, Tp Hồ Chí Minh', 19), 
	('Bùi Nhật Minh', 'cust15.png', 1, '1998-03-24', 'cust15@gmail.com', '0835462748', 'Bình Trưng Tây, Phường Bình Trưng Tây, Quận 2, Tp Hô Chí Minh', 20), 
    
    ('Nguyễn Hoàng Bảo Như', 'cust16.png', 0, '2001-10-05', 'cust16@gmail.com', '0987524635', 'Đường số 18, Tân Thuận Đong, Quận 7, Tp Hồ Chí Minh', 21),
	('Nguyễn Thị Nga', 'cust17.png', 0, '1994-12-24', 'cust17@gmail.com', '094675639', 'An Phú, Quận 2, Tp Hồ Chí Minh', 22), 
	('Phan Thái An', 'cust18.png', 1, '1995-12-1', 'cust18@gmail.com', '0986473859', 'Đường số 11, Phước Bình, Quận 9, Tp Hồ Chí Minh', 23), 
	('Nguyễn Trần Khánh Thi', 'cust19.png', 0, '2002-3-25', 'cust19@gmail.com', '0935478592', 'Tăng Nhơn Phú B, Quận 9, Tp Hồ Chí Minh', 24), 
	('Tôn Thái Hà', 'cust20.png', 0, '1983-12-30', 'cust20@gmail.com', '0985673859', 'Phước Bình, Quận 9, Tp Hồ Chí Minh', 25), 
    
    ('Đỗ Văn Quyết', 'cust21.png', 1, '2000-3-12', 'cust21@gmail.com', '0924356748', 'An Phú, Quận 2, Tp Hồ Chí Minh', 26),
	('Bùi Nhật Linh', 'cust22.png', 0, '1999-4-1', 'cust22@gmail.com', '0352648693', '21/5 Lê Hữu Kiều, Phường Bình Trưng Tây, Quận 2, Tp Hồ Chí Minh', 27), 
	('Trịnh Nguyễn Quốc Nguyên', 'cust23.png', 1, '1997-4-28', 'cust23@gmail.com', '0345274839', '20 Nguyễn Duy Trinh, Phường Bình Trưng Tây, Quận 2, Tp Hồ Chí Minh', 28), 
	('Đoàn Thanh Hiển', 'cust24.png', 1, '1993-11-21', 'cust24@gmail.com', '0896345678', '106 Bình Lợi, Phường 13, Quận Bình Thạnh, Tp Hồ Cí Minh', 29), 
	('Nguyễn Cao Thái Bình', 'cust25.png', 1, '1899-5-23', 'cust25@gmail.com', '0986325617', 'An Phú Đông, Quận 12, Tp Hồ Chí Minh', 30), 
    
    ('Ông Yên Nhật', 'cust26.png', 1, '2000-5-28', 'cust26@gmail.com', '0375628593', 'Phường 2, Quận Tân Bình, Tp Hồ Chí Minh', 31),
	('Phương Đình Phùng', 'cust27.png', 1, '1889-3-5', 'cust27@gmail.com', '0987452715', '274B Cách Mạng Tháng Tám, Phường 10, Quận 3, Tp Hồ Chí Minh', 32), 
	('Phan Văn An', 'cust28.png', 1, '1995-10-1', 'cust28@gmail.com', '0987123456', 'Đa Kao, Quận 1, Tp Hồ Chí Minh', 33), 
	('Phạm Nhật Trường', 'cust29.png', 1, '2001-4-1', 'cust29@gmail.com', '0833452199', '423 Lê Văn Lương, Tân Phong, Quận 7, Tp Hồ Chí Minh', 34), 
	('Trịnh Thị Anh Thơ', 'cust30.png', 10, '1987-3-30', 'cust30@gmail.com', '0967382948', 'Nguyễn Văn Lượng, Phường 6, Gò Vấp, Tp Hồ Chí Minh', 35), 
    
    ('Trương Hoàng Bảo', 'cust31.png', 1, '1998-7-21', 'cust31@gmail.com', '0934527481', 'Phước Kiến, Nhà Bè, Tp Hồ Chí Minh', 36),
	('Trương Vĩnh Phúc', 'cus32.png', 1, '1983-6-5', 'cus32@gmail.com', '0352749583', 'Phường 12, Quận Tân Bình, Tp Hồ Chí Minh', 37), 
	('Phan Bội Nghĩa', 'cust33.png', 1, '1995-7-23', 'cust33@gmail.com', '0984251637', 'Thân Nhân Trung, Phường 13, Quận Tân Bình, Tp Hồ Chí Minh', 38), 
	('Trịnh Minh Tâm', 'cust34.png', 1, '1993-4-30', 'cust34@gmail.com', '0984517382', 'Vĩnh Lộc A, Bình Chánh, Tp Hồ Chí Minh', 39), 
	('Đoàn Thị Hinh', 'cust35.png', 0, '1989-12-3', 'cust35@gmail.com', '0987365718', 'Xuân Thơi Thượng, Hóc Môn, Tp Hồ Chí Minh', 40), 
    
    ('Đỗ Mỹ Linh', 'cust36.png', 0, '1989-3-21', 'cust36@gmail.com', '0823541844', 'Lê Thị Ngay, Bình Chánh, Tp Hồ Chí Minh', 41),
	('Bùi Thảo My', 'cust37.png', 0, '1999-5-6', 'cust37@gmail.com', '0876313993', 'Bình Hưng Hòa B, Quận Bình Tân, Tp Hồ Chí Minh', 42), 
	('Phan Hiểu Nghi', 'cust38.png', 0, '2000-8-21', 'cust38@gmail.com', '0357683920', 'Tân Hiệp, Hóc Môn, Tp Hồ Chí Minh', 43), 
	('Bùi Diễm Trinh', 'cust39.png', 0, '2001-3-19', 'cust39@gmail.com', '0983547184', 'Bình Mỹ, Củ Chi, Tp Hồ Chí Minh', 44), 
	('Đoàn Duy Thanh', 'cust40.png', 1, '1995-5-8', 'cust40@gmail.com', '091235475', 'Nhị Bình, Hóc Môn, Tp Hồ Chí Minh', 45);
    
  INSERT INTO Categories(name, status) VALUES
	('Cây bonsai', 1),
    ('Cây phong thủy', 1),
    ('Hoa chậu', 1),
    ('Chậu', 1),
    ('Phân bón', 1);

  INSERT INTO Product_Material(name, is_delete ) VALUES
	('Nhựa', 1),
    ('Gốm sứ', 1),
	('Xi măng', 1);


INSERT INTO Discounts(title, scale, create_date, update_date, staff_id) VALUES
	('Mừng khai trương', 0.8, '2022-1-11', '2022-1-13', 1),
    ('Kỷ niệm 1 năm thành lập', 0.9, '2023-1-11', '2023-1-13', 1);
    
INSERT INTO Products(name, quantity, price, image, description, status, is_delete, create_date, update_date, prod_material_id, categories_id, discount_id) VALUES
	-- CÂY BONSAI
    ('Cây Phong Lá Đỏ', 10, 6000000, 'cay-phong-la-do.png', 'Cây Phong Lá Đỏ có nguồn gốc từ Nhật Bản. Trước mùa lá rụng thì cây có màu cam, đỏ rực rỡ, họa quyện với nhau. Cây này trang trí trong nhà, trên bàn làm việc sẽ lan tỏa sự ấm áp và dịu dàng của mùa thu. Màu đỏ và cam tượng trưng cho sự may mắn, tài lộc nên cây phong lá đỏ rất được ưa chuộng.', 1, 0, '2020-1-11', '2020-1-11', null, 1, 1),
	('Cây Đa Cảnh Bonsai', 20, 2500000, 'cay-da-canh.png', 'Cây đa bonsai quen thuộc với bộ rễ vô cùng hùng vĩ và được uốn nắn rất nghệ thuật tùy theo tay nghề nghệ nhân. Lá cây rất nhỏ hơn lá cây đa cổ thụ nhiều. Cây có sức chịu đựng dẻo dai và nhạy cảm với sự thay đổi của nhiệt độ môi trường.', 1, 0, '2020-1-11', '2020-1-11', null, 1, null),
	('Cây Linh Sam', 15, 6500000, 'cay-linh-sam.png', ' Cây Linh Sam có thân chắc khỏe và có hoa màu tím nổi bật, lạ mắt vì vậy mà được rất nhiều người yêu thích bonsai lựa chọn. Cây mang dáng của một trượng phu quân tử, luôn chiến thắng trong mọi cuộc đua vì vậy trồng trong nhà mang ý nghĩa xua đuổi tà khí, mang lại thịnh vượng, an khang, phát tài phát lộc cho gia đình.', 1, 0, '2020-1-11', '2020-1-11', null, 1, 1),
	('Cây Đinh Lăng', 35, 6500000, 'cay-dinh-lang.png', 'Cây đinh lăng từ lâu đời đã là một dược liệu rất quan trọng trong văn hóa Việt Nam. Không chỉ vậy, cây còn có tác dụng phong thủy rất lớn với ngôi nhà, mang tới tài lộc và may mắn cho gia đình. Đồng thời, ngăn chặn khí xấu đi vào nhà, được coi là “thần giữ hồn, giữ của” cho chủ nhà.', 1, 0, '2020-1-11', '2020-1-11', null, 1, null),
	('Cây Mai Vàng', 30, 1000000, 'cay-mai.png', 'Hoa Mai Vàng có 5 cánh, màu rực rỡ, trưng trong nhà dễ sống mà lại mang không khí rộn ràng cứ như không khí Tết sắp tràn về. Khi làm dáng Bonsai, nghệ nhân thường để cho bộ rễ của Mai vàng trồi lên to trông quyền lực hơn và tăng thêm giá trị tinh thần của ngôi nhà.', 1, 0, '2020-1-11', '2020-1-11', null, 1, 1),

    ('Cây Hoa Giấy', 45, 3000000, 'cay-hoa-giay.png', 'Cây hoa có ý nghĩa về tình yêu chân thành, mộc mạc, không vụ lợi. Cây có ý nghĩa xua đuổi hung khí, ngăn chặn điều họa vào nhà, mang lại may mắn cho gia chủ. Cây này rất đặc biệt là rất chịu nắng, nắng càng to cây càng đẹp, hoa càng rực rỡ. Vì vậy, nên trồng ở ban công nhà là tuyệt nhất.', 1, 0, '2020-1-11', '2020-1-11', null, 1, null),
    ('Cây Mẫu Đơn Đỏ', 50, 1500000, 'cay-mau-don-do.png', 'Đây là một loài cây dễ trồng, chịu được đất xấu, khô, trồng ở ánh sáng mạnh cũng được mà bóng râm cũng sống tốt. Với những cụm hoa lớn, màu đỏ rực rỡ làm sáng bừng cả khu vực xung quanh. Khi trồng trong chậu sẽ tỏa nét yêu kiều của hoa thêm sinh động.', 1, 0, '2020-1-11', '2020-1-11', null, 1, 1),
	('Cây Hải Chau', 50, 1000000, 'cay-hai-chau.png', 'Tuy có tốc độ lớn chậm và lá nhỏ nhưng cây lại mang dáng dấp khá đẹp. Hải châu mang đủ các yếu tố của một cây bonsai đẹp, thân rễ đều chắc chắn, dễ uốn các dáng hình, đáng để các bạn trưng tại vườn nhà, ban công…', 1, 0, '2020-1-11', '2020-1-11', null, 1, 1),
	('Cây Tùng Bồng Lai', 60, 16000000, 'cay-tung-bong-lai.png', 'Tùng Bồng Lai có bộ rễ khỏe khoắn vì trong tự nhiên cây này mọc ở những vách đá cheo leo. Cây Tùng Bồng Lai chịu được sức gió và khí hậu không kén chọn nên có sức sống mãnh liệt, biểu tượng của trường thọ, sống lâu.', 1, 0, '2020-1-11', '2020-1-11', null, 1, 1),
	('Cây Dâu Tằm', 30, 3500000, 'cay-mai.png', 'Cây Dâu Tằm bắt nguồn từ các khu vực phía Đông Châu Á. Cây được ưa thích vì có dáng đẹp, dễ trồng và rất dễ chăm sóc. Cây này có thể làm cây trang trí sân vườn, phòng khách, quán cafe thiên nhiên… Đặc biệt loài cây này lại có quả rất ngon trái nhỏ xinh màu đỏ tím và nhiều chất tốt cho sức khỏe.', 1, 0, '2020-1-11', '2020-1-11', null, 1, null),

	('Cây Hoa Sứ', 70, 5000000, 'cay-hoa-su.png', ' Cây hoa sứ có củ rễ to trông rất đồ sộ và vững chắc. Các rễ cây ro và uốn nắn nhiều kiểu. Cây lại có nhiều cành, nhiều nhánh tỏa ra, làm dáng cây rất đẹp. Hoa Sứ thì to nhiều màu sắc như hồng, phớt hồng, đỏ, trắng. Ban đêm Hoa Sứ tỏa hương thơm thoang thoảng. Cây rất thích hợp làm Bonsai trong nhà, trên ban công.', 1, 0, '2020-1-11', '2020-1-11', null, 1, null),
	('Cây Nguyệt Quế', 65, 2000000, 'cay-nguyet-que.png', 'Cây nguyệt quế có tên gọi rất đẹp và ý nghĩa của vinh quang. Nhắc tới nguyệt quế được đội lên đầu người chiến thắng gọi là vòng nguyệt quế. Cây mọc hoang trong rừng ở nước ta và còn được dùng như một vị thuốc.', 1, 0, '2020-1-11', '2020-1-11', null, 1, null),
	('Cây Sung Bonsai', 30, 6000000, 'cay-sung.png', 'Cây sung rất quen thuộc với thân cong và tán lá rộng. Quả sung khi sống thì có màu xanh đẹp mắt và chín thì có màu đỏ. Sung mọc từng chùm trực tiếp từ thân cây. Cây bonsai sung cảnh khi qua tay các nghệ nhân sẽ có dáng dấp riêng biệt rất đẹp. Các chùm quả nhỏ xinh lủng lẳng trông càng lạ mắt hơn.', 0, 0, '2020-1-11', '2020-1-11', null, 1, 1),
	('Cây Mai Chiếu Thủy', 45, 6000000, 'cay-mai-chieu-thuy.png', 'Cây mai chiếu thủy là một trong những thân gỗ chuyên dùng trong giới yêu thích Bonsai. Cây có những bông hoa trắng tinh khiết cực kỳ đặc biệt. Nó mọc ở ngọn cây và hướng xuống đất. Cây cũng có bộ rễ khá đặc biệt, rễ trồi lên cao hẳn mặt đất.', 0, 0, '2020-1-11', '2020-1-11', null, 1, null),
	('Cây Du Tàu', 30, 1000000, 'cay-du-tau.png', 'Cây Du Tàu có thân gỗ nhỏ, vỏ cây màu xám nhạt và lúc trưởng thành sẽ tự bong tróc. Lá cây thì có hình oval khá là nhỏ, rìa mép lá có hình răng cưa đẹp mà lạ. Cây ưa nước nên bạn phải tưới thường xuyên cho cây. Cây rất thích hợp để ở nơi có đủ ánh nắng mặt trời như ban công, cửa sổ hoặc sân vườn.', 0, 0, '2020-1-11', '2020-1-11', null, 1, 2),

    -- CÂY PHONG THỦY
    ('Cây Kim Tiền', 50, 2000000, 'cay-kim-tien.png', 'Cây kim tiền hay gọi là Kim phát tài, là một trong những loại cây may mắn nhất trong phong thuỷ, thể hiện sự phú quý, giàu sang và tiền bạc. Không những thế, theo khoa học cây kim tiền có đầy đủ các yếu tố phong thuỷ: Cây là mộc, trồng dưới đất là thổ, nước tưới là thuỷ, chậu trồng hoặc bình thuỷ sinh là kim.', 1, 0, '2020-1-11', '2020-1-11', null, 2, null),    
    ('Cây Kim Ngân', 45, 2000000, 'cay-kim-ngan.png', 'Cây kim ngân hay gọi nôm na là “cây tiền vàng”, là đại diện của sự may mắn, thịnh vượng nên đây là loại cây cảnh được ưa chuộng nhất hiện nay của người Việt. Cây có năm lá bắt nguồn từ mỗi chi nhánh và về cơ bản đại diện cho năm yếu tố của phong thủy: Kim, Mộc, Thủy, Hỏa, Thổ.', 1, 0, '2020-1-11', '2020-1-11', null, 2, 2),    
	('Cây Lưỡi Hổ', 50, 1500000, 'cay-luoi-ho.png', 'Cây lưỡi hổ được xem là loại cây trừ tà, ma quỷ, chống lại những điều xui xẻo, lá lưỡi hổ mọc thẳng và nhọn thể hiện sự quyết đoán, mạnh mẽ, ý chí tiến thủ. Điều đặc biệt ở loại cây này là mang ý nghĩa may mắn, giúp gia chủ phát tài phát lộc, dồi dào tiền bạc nên không khó để bắt gặp cây lưỡi hổ ở nhiều gia đình Việt Nam.', 1, 0, '2020-1-11', '2020-1-11', null, 2, null),    
	('Cây Thiết Mộc Lan', 20, 2500000, 'cay-thiet-moc-lan.png', 'Đúng như với cái tên Thiết mộc lan đó là loại cây rất đẹp, phát tài phát lộc. Không những làm đẹp cho không gian sống của ngôi nhà mà Thiết mộc lan còn đem đến tài vận tốt cho gia chủ, giúp gia chủ luôn được may mắn, thịnh vượng.', 1, 0, '2020-1-11', '2020-1-11', null, 2, 1),    
	('Cây Thường Xuân', 20, 1500000, 'caythuong-xuan.png', 'Cây Thường Xuân hay còn có tên gọi khác như Trường Xuân, cảnh dây Nguyệt Quế, cây Vạn Niên…Từ trước tới giờ cây luôn được mọi người ưa thích vì được ví như một bộ máy lọc không khí trong nhà. Mang ý nghĩa phong thủy xua đuổi tà ma, xóa tan âm khí, vượng dương khí mang đến bình an và may mắn cho gia chủ.', 0, 0, '2020-1-11', '2020-1-11', null, 2, null),    
	
	('Cây Lộc Vừng', 40, 10000000, 'cay-loc-vung.png', 'Cây lộc vừng xuất hiện nhiều trong phong thủy vì có ý nghĩa đem lại may mắn, yên bình và tài lộc, hạnh phúc cho gia đình. Cây ra hoa đẹp, rực rỡ màu đỏ tươi nổi bật cả vùng trời. Cây có màu hoa trắng hoặc đỏ, hoa màu trắng thì sẽ to và ít hoa hơn màu đỏ.', 0, 0, '2020-1-11', '2020-1-11', null, 2, 2),
	('Cây Trầu Bà', 30, 500000, 'cay-trau-ba.png', 'Cây trầu bà mang đến nhiều tài lộc, thịnh vượng, may mắn cho gia chủ, giúp cuộc sống gia đình tránh được nhiều vận xui, điều thị phi trong cuộc sống. Do vậy, chúng được mệnh danh là "cây tiền tài", được nhiều người yêu thích trồng trong nhà.', 0, 0, '2020-1-11', '2020-1-11', null, 2, null),
	('Cây Vạn Niên', 50, 1500000, 'cay-van-nien.png', ' Cây Vạn Niên Thanh có ý nghĩa phong thủy tăng tài vận cho gia chủ. Người ta quan niệm, trồng vạn niên thanh trong nhà ngày tết mang đến sự sung túc, trong hôn nhân cầu chúc hòa hợp như ý, trong lễ mừng thọ chúc được sống lâu.', 0, 0, '2020-1-11', '2020-1-11', null, 2, 1),
	('Cây Phú Quý', 30, 1000000, 'cay-phu-quy.png', 'Màu đỏ là màu của may mắn, do đó cây Phú Quý trong phong thủy là cây biểu tượng cho những điều tốt lành. Đồng thời, giống như tên gọi mà người ta đặt cho cây, Phú Quý mang đến sự giàu sang, tài lộc, phú quý cho người trồng.', 1, 0, '2020-1-11', '2020-1-11', null, 2, 2),

	('Cây Cọ Cảnh', 40, 200000, 'cay-co-canh.png', 'Với tán lá to tròn cùng vẻ xanh mượt cây cọ cảnh có ý nghĩa đem đến niềm vui, sự hi vọng, may mắn tài lộc đến với gia chủ, như chiếc quạt lớn xua đi những điều xấu, đem lại điềm lành, sinh tài giữ của. Cây cọ có tác dụng thanh lọc, điều hòa, cải thiện chất lượng không khí, đặc biệt hấp thụ khí thuốc lá và chất độc tồn dư do tàn thuốc lá gây nên.', 1, 0, '2020-1-11', '2020-1-11', null, 2, null),
	('Cây Trạng Ngyên', 55, 800000, 'cay-trang-nguyen.png', 'Cây Trạng Nguyên hay còn có tên gọi là cây Nhất Phẩm Hồng, cây có nguồn gốc ở miền nam Mexico, Trung Mỹ và châu Phi. Cây phù hợp để làm quà tặng khai trương, cây cảnh văn phòng, quầy lễ tân, bàn thu ngân, cây Trạng Nguyên còn thường để trước cửa nhà để đón may mắn và thành công.', 1, 0, '2020-1-11', '2020-1-11', null, 2, 2),
	('Cây Bạch Mã Hoàng Tử', 30, 2000000, 'cay-bach-ma-hoang-tu.png', 'Cây bạch mã hoàng tử được biết đến là loài cây mang lại nhiều may mắn đặc biệt với những người mạng hỏa hay mạng mộc. Cây mang vẻ sang trọng quý phái của một hoàng tử lịch lãm đầy lãng tử. Cây rất dễ trồng có thể sống trong nước hay đất vì thế có nhiều ứng dụng hơn trong việc trang trí nội thất.', 1, 0, '2020-1-11', '2020-1-11', null, 2, null),

    -- HOA CHẬU
    ('Hoa Cúc Indo', 25, 1500000, 'hoa-cuc-indo.png', 'Tên khoa học của chậu cây cúc indo là “Rondeletia leucophylla” thuộc họ Cà phê – Rubiaceae, bắt nguồn từ Mexico. Là một loại cúc lá to, có lông trắng dày và một lá nhỏ, lá nhỏ thì thường nở nhiều vào tháng 4 đến tháng 10.', 1, 0, '2020-1-11', '2020-1-11', null, 3, 1),    
	('Hoa Phù Dung', 20, 2000000, 'hoa-phu-dung.png', 'Cây hoa phù dung được nhắc đến trong chuyện cổ tích khi nói về cuộc đời của một nàng tiên nữ xinh đẹp nhưng bạc phận, chuyện tình cảm long đong và hoa phù dung nở cũng được ví như số phận của nàng tiên nữ này. Vì thế, hoa phù dung sáng nở bung trắng tinh khiết thì về trưa sẽ chuyển sáng màu hồng rồi lại hồng đậm, tối đến thì hoa có đỏ thẫm sẽ héo tàn.', 1, 0, '2020-1-11', '2020-1-11', null, 3, null),    
	('Hoa Lan Càng Cua', 25, 1000000, 'hoa-lan-cang-cua.png', 'Cây lan càng cua hay còn gọi là hoa tiểu quỳnh, chúng hiện nay trở thành thú vui tao nhã của những người trồng cảnh. Bạn muốn tô điểm cho góc vườn nhà mình với một chậu cảnh thì chắc có lẽ loại cây càng cua này sẽ phù hợp nhất. Bởi màu sắc rực rỡ của cây đã thu hút mọi ánh nhìn, chiều lòng những người khó tính nhất.', 1, 0, '2020-1-11', '2020-1-11', null, 3, null),    
	('Cây Lan Ý', 30, 1500000, 'cay-lan-y.png', 'Cây lan ý hay còn gọi là bạch môn, vỹ hoa trắng, huệ hoà bình, có độ cao khoảng 0,5m, mọc thành từng bụi. Màu lá xanh thẫm, bóng và khá dày, trên mặt lá nổi lên những vết gân màu xanh nhạt hơn. Nhiều người thường nhầm hoa lan ý là phần màu trắng muốt bao quanh nhị. Nhưng thực chất, hoa lan ý chính là phần thuôn dài màu vàng nhạt.', 1, 0, '2020-1-11', '2020-1-11', null, 3, null),
	('Hoa Cẩm Chướng', 30, 1000000, 'hoa-cam-chuong.png', 'Hoa được nhiều người biết đến là loài hoa sở hữu nhiều màu sắc như trắng, xanh, vàng, hồng, đỏ và chúng có thân hình mảnh mai, lá thuôn dài và có chiều dài từ 10 - 15cm.', 0, 0, '2020-1-11', '2020-1-11', null, 3, 1),

	('Hoa Trà', 30, 2000000, 'hoa-tra.png', 'Hoa trà có tên khoa học là camellia có nguồn gốc từ các nước nhiệt đới và tập trung nhiều ở các nước như Hàn Quốc, Nhật Bản và Việt Nam. Hoa trà được nhiều người yêu thích bởi sự sang trọng và vẻ đẹp cuốn hút, nhẹ nhàng nhưng đầy kiêu sa đã gây được ấn tượng từ cái nhìn đầu tiên.', 1, 0, '2020-1-11', '2020-1-11', null, 3, 2),
	('Hoa Dạ Yến Thảo', 60, 1600000, 'hoa-da-yen-thao.png', 'Hoa dạ yến thảo được biết là loài cây thân thảo với chiều cao trung bình khoảng 50cm, với những bông hoa nhỏ và cánh mỏng, còn phần lá tròn, không răng cưa và có đường kính khoảng 1cm. Hoa dạ yến thảo có vẻ đẹp nhẹ nhàng, ngọt ngào và vô cùng quyến rũ.', 0, 0, '2020-1-11', '2020-1-11', null, 3, null),
	('Hoa Păng-xe', 25, 800000, 'hoa-pang-xe.png', 'Hoa Păng-xê có tên gọi khác là hoa cánh bướm. Loài hoa này được nhiều người yêu thích bởi vẻ ngoài mềm mại, thanh thoát và mĩ miều khiến gia chủ cảm thấy vui vui tươi, thư giãn.', 1, 0, '2020-1-11', '2020-1-11', null, 3, 1),
	('Hoa Mai Địa Thảo', 30, 450000, 'hoa-mai-dia-thao.png', 'Hoa mai địa thảo (impatients) là những cây hoa bụi nhỏ với nhiều màu sắc khác nhau và có thể trồng quanh năm trong điều kiện thời tiết ấm áp. Bạn nên đặt chậu hoa ở nơi râm mát và tưới đủ nước để cây không bị héo và có thể phát triển khỏe mạnh.', 1, 0, '2020-1-11', '2020-1-11', null, 3, null),
	('Hoa Thu Hải Đường', 30, 1000000, 'hoa-thu-hai-duong.png', 'Nhỏ nhắn mà rực rỡ, thu hải đường là loài hoa được rất nhiều người lựa chọn để trồng trong chậu nhỏ dưới đất hay treo lên cao. Loài hoa này có thể chịu được độ nóng, nên trồng trong bóng râm.', 0, 0, '2020-1-11', '2020-1-11', null, 3, null),
	
    ('Hoa Đồng Tiền', 45, 500000, 'hoa-dong-tien.png', 'Hoa đồng tiền được xem là tượng trưng cho sự sung túc, tình yêu và hạnh phúc viên mãn.  Cây hoa đồng tiền khá dễ trồng và chăm sóc nên bạn có thể lựa chọn để tô điểm cho ngôi nhà của mình.', 1, 0, '2020-1-11', '2020-1-11', null, 3, null),
	('Hoa Thủy Tiên', 30, 1500000, 'hoa-thuy-tien.png', 'Thủy tiên - loài hoa với cái tên tên kiêu sa - được chọn là loài hoa tiêu biểu cho tháng 3, tháng của tiết xuân phân. Màu trắng thanh khiết hay màu vàng sang trọng được xem là biểu tượng cho mùa xuân, mùa của sự hồi sinh, đâm chồi nảy lộc.', 1, 0, '2020-1-11', '2020-1-11', null, 3, 2),
	('Hoa Ly', 20, 1500000, 'hoa-ly.png', 'Hoa Ly là biểu tượng của sự thanh lịch, sang trọng và nhã nhặn. Hoa ly thanh khiết và hoa ly cũng rất đẹp nên mỗi một màu ly mang một ý nghĩa khác nhau.', 1, 0, '2020-1-11', '2020-1-11', null, 3, null),
	('Hoa Hồng', 30, 2000000, 'hoa-hong.png', 'Hoa hồng được biết là loài hoa dạng bụi, có nhiều cành và gai cong. Lá hoa hồng là loại lá kép lông chim mọc cách. Còn hoa hồng là loài hoa lưỡng tính, có hương thơm nhẹ, cánh hoa mỏng nhẹ. Hoa hồng nở quanh năm và lâu tàn.', 1, 0, '2020-1-11', '2020-1-11', null, 3, null),
	('Hoa Dừa Cạn', 40, 800000, 'hoa-dua-can.png', 'Hoa dừa cạn là món quà đầy ý nghĩa trong dịp mừng sinh nhật mừng thọ, hay dịp lễ tết với ý nghĩa trường xuân. Dừa cạn cũng có ý nghĩa chúc thành công trong sự nghiệp và học vấn nếu bạn đang là một sỹ tử.', 1, 0, '2020-1-11', '2020-1-11', null, 3, null),

    -- CHẬU NHỰA - SIZE
	('Chậu Nhựa Thuận Buồm', 100, null, 'nhua-thuan-buom.png', 'Chậu Nhựa Thuận Buồm được làm bằng chất lượng nhựa cứng cao cấp không đàn hồi cứng cáp, độ bền cao, chịu được thời tiết. Bề mặt chậu lán và bóng, hoa văn in nổi ở thân chậu. Miệng chậu loe ra ngoài theo nhiều khía', 0, 0, '2020-1-11', '2020-1-11', 1, 4, null),
	('Chậu Nhựa Thân Sọc Trắng', 60, null, 'nhua-soc-trang.png', 'Chậu gân sọc trắng được thiết kế rất thích hợp để trồng các loại hoa, cây kiểng như hoa hồng, hoa mai, hoa đào, hoa hải đường, cây cao hawaii, cây tùng, cây phát lộc hoa, đa búp đỏ, đại đế vương, mai tiểu thư...', 1, 0, '2020-1-11', '2020-1-11', 1, 4, null),    
	('Chậu Bom Lùn Hoa Văn', 50, null, 'nhua-bom-lun.png', 'Chậu bom lùn hoa văn trồng hoa, cây kiểng được làm từ nhựa tổng hợp khá bền màu và chắc chắn khi sử dụng trong thời gian dài. Được thiết kế độc đáo, tựa như chậu gốm thật, với bề mặt in nổi hoa, lá vô cùng tao nhã, có thể đặt bàn hoặc trưng bày tại cầu thang, kệ sách, cửa sổ..', 1, 0, '2020-1-11', '2020-1-11', 1, 4, null),    
    ('Chậu Nhựa Vuông', 50, null, 'nhua-vuong.png', 'Chậu nhựa vuông trắng khắc hoa văn rồng bay phượng múa quanh thân rất đẹp và trang nhã, sử dụng cho các loại cây kiểng, hoa, bonsai,…tạo mảng xanh tươi mát, dễ chịu cho gian nhà.', 1, 0, '2020-1-11', '2020-1-11', 1, 4, null),    
	('Chậu Nhựa Đỏ', 50, null, 'nhua-do.png', 'Chậu nhựa tròn cao có màu đỏ,  rất nhẹ dễ dàng vận chuyển đối với tòa nhà cao tầng, thích hợp cho việc trồng hoa cây cảnh trồng trong nhà,ngoài trời rất bền, tôn lên vẻ đẹp của cây và sự sang trọng của không gian trưng bày.', 1, 0, '2020-1-11', '2020-1-11', 1, 4, null),    
     
	('Chậu Nhựa Hình Bông Lúa', 50, null, 'nhua-bong-lua.png', 'Với chất liệu được làm từ nhựa PP, một loại chất liệu nhựa dày dặn, cùng với thiết kế hình bông lúa mang lại sự cầu kỳ về hình dáng, sự phá cách trong thiết kế, tạo nên vẻ đẹp trang nhã cho ngôi nhà của bạn.', 0, 0, '2020-1-11', '2020-1-11', 1, 4, null),    
    ('Chậu Nhựa Trồng Lan', 50, null, 'nhua-trong-lan.png', 'Hoa lan là loại hoa được nhiều người yêu hoa mong muốn chinh phục và lai tạo nhất không chỉ mới gần đây, mà nó đã có từ rất rất lâu về trước, Chính vì vậy những loại chậu nhưa trồng hoa lan cũng mang theo cho mình những đặc điểm, cùng với thiết kế cầu kì, tỉ mỉ dành riêng cho thú vui chơi hoa Lan của những dân sành.', 1, 0, '2020-1-11', '2020-1-11', 1, 4, null),    
	('Chậu Nhựa Hình Ống Lá', 50, null, 'nhua-ong-la.png', 'Đây là một loại chậu nhựa có đặc thù riêng có thể nói là dành riêng cho những giống cây hoa, cây cảnh trong nhà như vạn liên thanh, lan như ý, nó vừa mang tài lộc lại hút khí vượng, làm cho ngôi nhà của bạn ngập tràn năng lượng tích cực.', 1, 0, '2020-1-11', '2020-1-11', 1, 4, null),    
    
	-- CHẬU SỨ - SIZE 
    ('Chậu Cây Cảnh Sứ Đĩa', 120, null, 'su-dia.png', 'Chậu cây cảnh sứ đĩa là chậu kích thước lớn, thích hợp cho việc trồng tiểu cảnh sen đá, xương rồng và các loại cây cảnh mini.', 1, 0, '2020-1-11', '2020-1-11', 2, 4, null),    
	('Chậu Sứ Trụ Thấp', 125, null, 'su-tru-thap.png', 'Chậu sứ Trụ thấp là sản phẩm mới của Bát Tràng được làm bằng chất liệu sứ. Đây là mẫu hot nhất năm 2021, bởi vẻ ngoài sang trọng dù để em chậu ở bất cứ góc nào cũng làm tôn lên vẻ đẹp của không gian.', 1, 0, '2020-1-11', '2020-1-11', 2, 4, null),    
    ('Chậu Sứ Đa Giác', 90, null, 'su-da-giac.png', 'Chậu sứ có tác dụng chứa đất trồng cây mini, giúp cây có môi trường phát triển tốt. Với chất liệu gốm, sứ trải qua muôn vàn khó khăn thử lửa trên lò nóng để tạo ra một hình hài hoàn chỉnh. Vậy nên chậu sứ có tuổi thọ rất dài.', 1, 0, '2020-1-11', '2020-1-11', 2, 4, null),    
	('Chậu Sứ Chữ Nhật', 50, null, 'su-chu-nhat.png', 'Chậu gốm sứ cao cấp Bát Tràng, được làm từ loại đất trắng và phủ lên mình màu trắng tinh từ loại men mát đặc biệt, tạo cho sản phẩm vẻ đẹp trang nhã. Chậu được in nhiều hình từ danh lam thắng cảnh đến hình hoa lá.', 1, 0, '2020-1-11', '2020-1-11', 2, 4, null),    
    ('Chậu Sứ Vuông Vát', 50, null, 'su-vuong-vat.png', 'Chậu men trắng, in hình xinh xắn. Mang lại cảm giác sinh động và sang trọng. Phù hợp với các loại cây văn phòng để bàn như: thường xuân, kim ngân, kim tiền, trường sinh, cau tiểu trâ', 0, 0, '2020-1-11', '2020-1-11', 2, 4, null),    
    ('Chậu Sứ Hình Giỏ Cua', 50, null, 'su-gio-cua.png', 'Chậu gốm sứ được làm từ loại đất trắng cao cấp, chậu đựic phủ lớp men hoả biến nên có màu xanh và độ bóng cao, lớp men mịn đẹp. Đáy chậu có lỗ thoát nước, giúp cây trồng thoáng đất, không bị úng nước hay thối rễ.', 1, 0, '2020-1-11', '2020-1-11', 2, 4, null),    
    ('Chậu Sứ Ang', 50, null, 'su-ang.png', 'Chậu trơn không in hình, dễ phối hợp với các loại cây cảnh. Chậu trồng cây, phù hợp trồng các cây tiểu cảnh như: sen đá, cẩm nhung, xương rồng, la há', 1, 0, '2020-1-11', '2020-1-11', 2, 4, null),    
    
    -- CHẬU XI MĂNG - SIZE
	('Chậu Xi Măng Tròn Trứng', 100, null, 'xi-mang-tron-trung.png', 'Với hình dáng tròn đứng to ở miệng, suôn ở thân – bo và nhỏ dần ở đáy,nên có tên gọi khác là Tròn Trứng  rất dễ sử dụng , phù hợp với nhiều không gian sống,…Nét đẹp của nó mang đến sự sang trọng.', 1, 0, '2020-1-11', '2020-1-11', 3, 4, null),    
    ('Chậu xi măng trứng sơn', 150, null, 'xi-mang-trung-son.png', 'Chậu Xi măng  Trứng  là mẫu mới giữa năm 2022,với thiết kế suôn từ trên miệng và bầu và bo lại ở dưới đáy,giống hình quả trứng,mang phong cách hiện đại,giúp chậu được sự cân bằng giữa miệng và đáy chậu', 1, 0, '2020-1-11', '2020-1-11', 3, 4, null),    
	('Chậu Xi Măng Xoắn Ngang', 110, null, 'xi-mang-xoan-ngang,png', 'Chậu xi măng xoắn ngang là bộ chậu 3 được ưa chuộng nhất trong dòng chậu xi măng. Chậu thường được ưu ái bố trí trong một bên góc ban công hoặc góc không gian rộng.', 1, 0, '2020-1-11', '2020-1-11', 3, 4, null),    
    ('Chậu Xi Măng Vuông', 50, null, 'xi-mang-vuong.png', 'Chậu xi măng nhẹ cấu tạo bởi xi măng, cốt là sợi thủy tinh fiber cùng dung dịch làm giảm trọng lượng của Đức. Là một trong những vật dụng phù hợp để trang trí tạo không gian xanh cho chính ngôi nhà của bạn.', 0, 0, '2020-1-11', '2020-1-11', 3, 4, null),    
    ('Chậu Xi Măng Tròn', 50, null, 'xi-mang-tron.png', 'Chậu xi măng nhẹ là loại chậu trồng cây cảnh được làm từ loại xi măng nhẹ siêu bền, có độ chống thấm cao. Chậu xi măng nhẹ với các kiểu dáng hiện đại, thiết kế sang trọng có ưu điểm về trọng lượng và mẫu mã đa dạng, đơn giản, tinh tế và hiện đại hơn.', 0, 0, '2020-1-11', '2020-1-11', 3, 4, null),    
	('Chậu Xi Măng Kiểu', 50, null, 'xi-mang-kieu.png', 'Chậu xi măng trồng cây cảnh là loại chậu trồng cây bền nhất, giá thành tốt, được nhiều người ưa thích sử dụng. Chậu xi măng tròn họa tiết được sơn giả đá, chậu phù hợp với các loại cây trồng để hiên nhà, trồng cây sân thượng, các loại cây sân vườn. ', 0, 0, '2020-1-11', '2020-1-11', 3, 4, null),    
    
    -- PHÂN BÓN
    ('Phân Bón NPK Humax Cuối Vụ', 20, 150, 'phan_bon_npk_ha_lan_humax_rong_bien_cuoi_vu_1_w300.png', 'Phân bón NPK Humax rong biển (50kg)-Mới công thức dạng chữ J phù hợp cho việc sử dụng vào đầu mùa vụ do có hàm lượng đạm (N) cao, lân (P2O5) thấp hoặc cao tương đối và kali (K2O) thấp. Giai đoạn này cây trồng cần nhiều đạm để phục hồi sau thu hoạch, sinh trưởng và phát triển tốt.', 1, 0, '2020-1-11', '2020-1-11', null, 5, null),
	('NPK 20-10-15+ TE', 20, 200000, 'phan_bon_npk_ha_lan_201015_1_w300.png', 'Phân bón NPK Hà Lan 20-10-15+TE có công thức dạng đối xứng chữ V phù hợp cho việc sử dụng vào giữa mùa vụ do có đạm (N) và kali (K2O) cao, lân (P2O5) thấp. Giai đoạn này cây mới bắt đầu ra hoa, kết trái, nuôi trái nên phân bón cần có nhiều lượng đạm để nuôi hoa quả và nhiều kali để tăng chất lượng hoa, củ, quả cho cây đạt năng suất cao.', 1, 0, '2020-1-11', '2020-1-11', null, 5, null),
    ('NPK 18-8-22 Hà Lan', 20, 180000, 'phan_bon_npk_ha_lan_18822_1_w300.png', 'Phân bón NPK Hà Lan 18-8-22+TE có công thức dạng chữ J phù hợp cho việc sử dụng vào cuối mùa vụ do có đạm (N) và lân (P2O5) thấp, kali (K2O) cao. Thời điểm này, cây trồng cần phân bón chứa kali để giúp trái to, bóng trái, màu sắc đẹp, tăng phẩm chất hoa, củ, quả mang lại năng suất cao cho cây trồng.', 1, 0, '2020-1-11', '2020-1-11', null, 5, null),
    ('NPK 17-7-21 Hà Lan', 20, 130000, 'organic-1-1_w300.png', 'Phân bón NPK Hà Lan 17-7-21 có công thức dạng chữ J phù hợp cho việc sử dụng vào cuối mùa vụ do có đạm (N) và lân (P2O5) thấp, kali (K2O) cao. Thời điểm này, cây trồng cần phân bón chứa nhiều kali để giúp trái to, bóng trái, màu sắc đẹp, tăng phẩm chất hoa, củ, quả mang lại năng suất cao cho cây trồng.', 0, 0, '2020-1-11', '2020-1-11', null, 5, null),
	('Phân Hữu Cơ Organic 1', 20, 200000, 'organic-1-1_w300.png', 'Phân bón hữu cơ Organic 1 sản phẩm được nhập khẩu trực tiếp từ Hà Lan. Dùng để bón lót nhằm cải tạo đất, giúp đất đất tươi xốp, làm môi trường trong đất trở nên thuận lợi để cây trồng hấp thu tốt các chất dinh dưỡng, tăng khả năng hấp thụ phân bón.', 1, 0, '2020-1-11', '2020-1-11', null, 5, null),
    ('NPK Phục Hồi Cây Trồng', 20, 165000, 'npk_halan.png', 'Phân bón NPK phục hồi cây trồng, công thức dạng chữ L có hàm lượng đạm (N) cao, lân (P2O5) thấp và kali (K2O) thấp phù hợp cho việc phục hồi cây trồng sau thu hoạch, giúp cây phát triển mạnh cành lá.', 0, 0, '2020-1-11', '2020-1-11', null, 5, null);

  INSERT INTO Wish_List(user_id , product_id) VALUES
	(6,1),
    (6,3),
    (6,11),
    
    (7,1),
    (7,9),
   
    (9,6),
    (9,48),
    (9,22),
    (9,10),
    (9,35),
    
    (11,59),
    (11,7),

    (12,18),
    (12,16),
    (12,21),
    
    (15,25),
    (15,16),
    (15,10),
    
    (20,11),

    (22,12),
    (22,22),

    (24,14),
    (24,41),
    (24,53),
	(24,7),
    (24,20),
	(24,25),
    (24,30),
    
    (25,15),
    (25,16),
    (25,5),
    
    (26,6),
    (26,3),
    (26,11),
    
    (28,8),
    (28,9),
    (28,12),
	(28,40),
    
    (29,9),
    (29,10),
    (29,22),
    
    (30,5),
    (30,7),
    (30,11),
    
	(32,23),
    (32,52),
    
    (34,4),
    (34,38),
    (34,50),

    (36,36),
    (36,12),
    (36,25),
    
    (37,47),
    (37,53),
    (37,17),
    
    (40,60),
    (40,50),
    (40,34),
    (40,12),
    
    (41,15),
    (41,16),
    (41,3),
    (41,1),
    (41,11),
    
    (43,1),
    (43,9),
    (43,12),
    
    (45,60),
    (45,45),
    (45,35);
    
    
INSERT INTO Size(id) VALUES
	('S'),
    ('M'),
    ('L');

INSERT INTO Product_Size(product_id, size_id, price, quantity) VALUES
	(43, 'S', 200000, 20),
    (43, 'M', 250000, 15),
    (43, 'L', 300000, 15),
    
	(44, 'S', 200000, 20),
    (44, 'M', 250000, 15),
    (44, 'L', 300000, 15),
    
	(45, 'S', 300000, 20),
    (45, 'M', 400000, 30),
    (45, 'L', 500000, 25),
    
    (46, 'S', 250000, 20),
    (46, 'M', 350000, 25),
    (46, 'L', 450000, 20),
    
    (47, 'S', 200000, 30),
    (47, 'M', 250000, 28),
    (47, 'L', 300000, 25),
    
    (48, 'S', 180000, 18),
    (48, 'M', 250000, 10),
    (48, 'L', 300000, 10),
    
    (49, 'S', 300000, 20),
    (49, 'M', 400000, 15),
    (49, 'L', 450000, 10),
    
    (50, 'S', 240000, 30),
    (50, 'M', 300000, 20),
    (50, 'L', 350000, 30),
    
    (51, 'S', 200000, 25),
    (51, 'M', 250000, 25),
    (51, 'L', 300000, 20),
    
    (52, 'S', 350000, 25),
    (52, 'M', 400000, 25),
    (52, 'L', 450000, 20),
    
    (53, 'S', 350000, 20),
    (53, 'M', 400000, 20),
    (53, 'L', 450000, 20),
    
    (54, 'S', 250000, 20),
    (54, 'M', 300000, 20),
    (54, 'L', 350000, 20),
    
    (55, 'S', 350000, 30),
    (55, 'M', 450000, 25),
    (55, 'L', 550000, 25),
    
    (56, 'S', 340000, 25),
    (56, 'M', 450000, 20),
    (56, 'L', 500000, 20),
    
    (57, 'S', 280000, 15),
    (57, 'M', 350000, 15),
    (57, 'L', 460000, 15),
    
    (58, 'S', 220000, 20),
    (58, 'M', 280000, 15),
    (58, 'L', 350000, 15),
    
    (59, 'S', 120000, 20),
    (59, 'M', 130000, 15),
    (59, 'L', 140000, 10),
    
    (60, 'S', 200000, 15),
    (60, 'M', 250000, 10),
    (60, 'L', 300000, 10),
    
    (61, 'S', 350000, 5),
    (61, 'M', 425000, 5),
    (61, 'L', 500000, 5),
    
    (62, 'S', 150000, 10),
    (62, 'M', 200000, 8),
    (62, 'L', 220000, 8),
    
    (63, 'S', 230000, 8),
    (63, 'M', 250000, 5),
    (63, 'L', 300000, 5);

INSERT INTO Order_Status(name) VALUES
	('Đơn đã đặt'),
    ('Đã xác nhận thông tin'),
	('Chờ lấy hàng'),
	('Đang giao'),
    ('Đã nhận');
  

INSERT INTO Order_Method_Payment(name) VALUES
	('Tiền mặt'),
    ('Thanh toán online');
    
INSERT INTO Orders(phone, address, note, total, create_date, update_date, orderstatus_id, ordermethod_id, profile_id) VALUES
	('0984265835', 'Lý Thường Kiệt, Phường 6, Quận Tân Bình, Tp Hồ Chí Minh', null, 680, '2022-1-11', '2020-1-13', 5, 1, 6),
	('0936584625', 'Đất Thánh, Phường 6, Quận Tân Bình, Tp Hồ Chí Minh', null, 920, '2022-1-11', '2022-1-13', 5, 1, 7),
	('0357553860', 'Lý Thường Kiệt, Phường 6, Quận Tân Bình, Tp Hồ Chí Minh', null, 4320, '2022-1-12', '2022-1-13', 5, 1, 8),
	('0893657846', '540 Cách Mạng Tháng Tám, Phường 11, Quận 3, Tp Hồ Chí Minh', null, 560, '2022-1-11', '2022-1-13', 5, 1, 9),
	('0945285638', 'Phường 15, Quận 10, Tp Hồ Chí Minh', null, 960, '2022-1-12', '2022-1-13', 5, 1, 10),
	('0345869306', '205 Trần Văn Đang, Phường 11, Quận 3, Tp Hồ Chí Minh', null, 994, '2022-1-12', '2022-1-13', 5, 1, 11),
	
    ('0956385934', '270 Phan Đình Phùng, Phường 1, Quận Phú Nhuận, Tp Hồ Chí Minh', null, 2650, '2022-2-20', '2022-2-25', 5, 1, 12),
	('0357839572', '216 Võ Thị Sáu, Quận 3, Tp Hồ Chí Minh', null, 5200, '2022-3-11', '2022-3-15', 5, 1, 14),
    ('0385626374', 'Nguyễn Tất Thành, Phường 13, Quận 4, Tp Hồ Chí Minh', null, 2000, '2022-4-21', '2022-4-23', 5, 1, 17),
    ('0835467284', '8 Nguyễn Thiện Thành, Thủ Thiêm, Tp Thủ Đức', null, 1120, '2022-4-27', '2022-4-30', 5, 1, 18),
    ('0835462748', 'Bình Trưng Tây, Phường Bình Trưng Tây, Quận 2, Tp Hô Chí Minh', null, 1260, '2022-5-20', '2022-5-22', 5, 1, 20),
    ('0987524635', 'Đường số 18, Tân Thuận Đông, Quận 7, Tp Hồ Chí Minh', null, 850, '2022-6-26', '2022-6-30', 5, 1, 21),
	('0986473859', 'Đường số 11, Phước Bình, Quận 9, Tp Hồ Chí Minh', null, 1500, '2022-6-30', '2022-7-3', 5, 1, 23),	
    ('0935478592', 'Tăng Nhơn Phú B, Quận 9, Tp Hồ Chí Minh', null, 730, '2022-7-11', '2020-7-13', 5, 1, 24),
	('0985673859', 'Phước Bình, Quận 9, Tp Hồ Chí Minh', null, 1350, '2022-7-28', '2022-8-1', 5, 1, 25),
	('0924356748', 'An Phú, Quận 2, Tp Hồ Chí Minh', null, 550, '2022-8-1', '2022-8-10', 5, 1, 26),
	
    ('0345274839', '20 Nguyễn Duy Trinh, Phường Bình Trưng Tây, Quận 2, Tp Hồ Chí Minh', null, 480, '2022-8-17', '2022-8-17', 5, 1, 28),
    ('0896345678', '106 Bình Lợi, Phường 13, Quận Bình Thạnh, Tp Hồ Chí Minh', null, 2225, '2022-9-20', '2022-9-22', 5, 1, 29),
	('0375628593', 'Phường 2, Quận Tân Bình, Tp Hồ Chí Minh', null, 800, '2022-9-30', '2022-10-1', 5, 1, 31),
	('0987123456', 'Đa Kao, Quận 1, Tp Hồ Chí Minh', null, 1100, '2022-10-27', '2022-10-30', 5, 1, 33),
	('0833452199', '423 Lê Văn Lương, Tân Phong, Quận 7, Tp Hồ Chí Minh', null, 580, '2022-11-22', '2022-11-25', 5, 1, 34),    
	('0967382948', 'Nguyễn Văn Lượng, Phường 6, Gò Vấp, Tp Hồ Chí Minh', null, 1700, '2022-11-25', '2021-11-30', 5, 1, 35),
	('0984251637', 'Thân Nhân Trung, Phường 13, Quận Tân Bình, Tp Hồ Chí Minh', null, 420, '2022-12-29', '2022-12-30', 5, 1, 38),
	('0984517382', 'Vĩnh Lộc A, Bình Chánh, Tp Hồ Chí Minh', null, 700, '2023-1-3', '2023-1-5', 5, 1, 39),    
	('0986473859', 'Đường số 11, Phước Bình, Quận 9, Tp Hồ Chí Minh', null, 900, '2023-1-5', '2023-1-8', 5, 1, 23),
	('0835467284', '8 Nguyễn Thiện Thành, Thủ Thiêm, Tp Thủ Đức', null, 1440, '2023-1-11', '2023-1-13', 5, 1, 18),
    
	('0956385934', '270 Phan Đình Phùng, Phường 1, Quận Phú Nhuận, Tp Hồ Chí Minh', null, 450, '2023-1-11', '2023-1-12', 5, 1, 12),
    ('0835462748', 'Bình Trưng Tây, Phường Bình Trưng Tây, Quận 2, Tp Hô Chí Minh', null, 530, '2023-1-11', '2023-1-13', 5, 1, 20),
	('0985673859', 'Phước Bình, Quận 9, Tp Hồ Chí Minh', null, 1170, '2023-1-11', '2023-1-13', 5, 1, 25),
	('0987452715', '274B Cách Mạng Tháng Tám, Phường 10, Quận 3, Tp Hồ Chí Minh', null, 684, '2023-1-11', '2023-1-13', 5, 1, 32),
	('0833452199', '423 Lê Văn Lương, Tân Phong, Quận 7, Tp Hồ Chí Minh', null, 350, '2023-2-1', '2023-2-8', 5, 1, 34),
	('0357553860', 'Lý Thường Kiệt, Phường 6, Quận Tân Bình, Tp Hồ Chí Minh', null, 500, '2023-2-1', '2023-2-6', 5, 1, 8),
    ('0823541844', 'Lê Thị Ngay, Bình Chánh, Tp Hồ Chí Minh', null, 1650, '2023-2-2', '2023-2-5', 5, 1, 41),
	('0876313993', 'Bình Hưng Hòa B, Quận Bình Tân, Tp Hồ Chí Minh', null, 1000, '2023-2-3', '2023-2-6', 5, 1, 42),
	('0983547184', 'Bình Mỹ, Củ Chi, Tp Hồ Chí Minh', null, 1160, '2023-2-3', '2023-2-6', 5, 1, 44),
	('0345274839', '20 Nguyễn Duy Trinh, Phường Bình Trưng Tây, Quận 2, Tp Hồ Chí Minh', null, 820, '2023-2-4', '2023-2-7', 5, 1, 28),

	('091235475', 'Nhị Bình, Hóc Môn, Tp Hồ Chí Minh', null, 220, '2023-2-5', '2023-2-9', 5, 1, 45),
	('0986473859', 'Đường số 11, Phước Bình, Quận 9, Tp Hồ Chí Minh', null, 375, '2023-2-5', '2023-2-8', 5, 1, 23),
	('0833452199', '423 Lê Văn Lương, Tân Phong, Quận 7, Tp Hồ Chí Minh', null, 1600, '2023-2-6', '2023-2-9', 5, 1, 34),    
	('0357839572', '216 Võ Thị Sáu, Quận 3, Tp Hồ Chí Minh', null, 160, '2023-2-7', '2023-2-9', 5, 1, 14);


INSERT INTO Order_Detail(price , quantity , sale , order_id , product_id ) VALUES
	(600, 1, 0.8, 1, 1),
    (200, 1, 0, 1, 69),
    
	(650, 1, 0.8, 2, 3),
    (250, 2, 0.8, 2, 19),
    
    (1000, 1, 0.8, 3, 5),
    (1600, 2, 0.8, 3, 9),
	(600, 2, 0.8, 3, 13),
   
    (150, 4, 0.8, 4, 24),
    (50, 2, 0.8, 4, 39),
    
    (150, 2, 0.8, 5, 7),
    (100, 6, 0.8, 5, 33),
	(150, 2, 0.8, 5, 29),
    
	(1000, 1, 0.8, 6, 8),
    (80, 2, 0.8, 6, 36),
	(130, 2, 0, 6, 59),
    
    (650, 1, 0, 7, 3),
    (150, 2, 0, 7, 7),
	(1000, 1, 0, 7, 5),
    (350, 2, 0, 7, 10),
    
	(300, 2, 0, 8, 6),
    (1600, 2, 0, 8, 9),
	(1000, 1, 0, 8, 15),
    (200, 2, 0, 8, 30),
    
    (1000, 2, 0, 9, 5),

	(200, 4, 0, 10, 28),
    (160, 2, 0, 10, 35),
    
	(40, 4, 0, 11, 52),
	(200, 4, 0, 11, 12),
    (150, 2, 0, 11, 32),
    
    (250, 1, 0, 12, 2),
	(150, 2, 0, 12, 29),
    (150, 2, 0, 12, 18),
    
	(600, 2, 0, 13, 14),
	(150, 2, 0, 13, 21),
   
	(150, 1, 0, 14, 20),
	(200, 2, 0, 14, 42),
    (180, 1, 0, 14, 67),
    
    (100, 1, 0, 15, 33),
	(1000, 1, 0, 15, 15),
    (250, 1, 0, 15, 19),
--    
    (100, 3, 0, 16, 38),
	(35, 2, 0, 16, 58),
    (180, 1, 0, 16, 67),
    
    (80, 1, 0, 17, 43),
	(200, 2, 0, 17, 42),
    
    (200, 2, 0, 18, 16),
	(200, 2, 0, 18, 17),
    (80, 1, 0, 18, 27),
    (45, 1, 0, 18, 37),
	(150, 2, 0, 18, 40),
    (1000, 1, 0, 18, 5),
    
    (200, 2, 0, 19, 16),
	(200, 2, 0, 19, 17),
    
    (150, 4, 0, 20, 24),
    (250, 2, 0, 20, 19),
    
	(150, 2, 0, 21, 20),
	(50, 4, 0, 21, 23),
    (20, 4, 0, 21, 26),
   
    (1000, 1, 0, 22, 8),
    (350, 2, 0, 22, 64),
    
	(150, 1, 0, 23, 65),
    (50, 4, 0, 23, 37),
	(35, 2, 0, 23, 54),
    
	(200, 2, 0, 24, 28),
    (150, 2, 0, 24, 65),
    
    (600, 1, 0, 25, 13),
    (150, 2, 0, 25, 18),
    
	(1600, 1, 0.9, 26, 15),
    
    (100, 2, 0.9, 27, 25),
	(150, 2, 0.9, 27, 40),
    
    (200, 2, 0.9, 28, 34),
    (150, 1, 0.9, 28, 40),
	(35, 2, 0, 28, 54),
    
	(150, 2, 0.9, 29, 17),
    (1000, 1, 0.9, 29, 15),
    
    (600, 1, 0.9, 30, 22),
    (80, 2, 0.9, 30, 27),
    
	(150, 1, 0, 31, 24),
    (200, 1, 0, 31, 12),
    
    (100, 2, 0, 32, 25),
	(150, 2, 0, 32, 40),
     
	(1000, 1, 0, 33, 8),
    (650, 1, 0, 33, 4),
    
    (1000, 1, 0, 34, 8),
    
	(1000, 1, 0, 35, 22),
	(30, 2, 0, 35, 44),
	(50, 2, 0, 35, 56),

    (150, 1, 0, 36, 32),
	(100, 2, 0, 36, 31),
	(200, 2, 0, 36, 30),
    (35, 2, 0, 36, 50),
    
	(150, 1, 0, 37, 32),
	(35, 2, 0, 37, 50),
    
	(200, 2, 0, 38, 30),
    (35, 2, 0, 38, 50),
    
	(1000, 1, 0, 39, 15),
	(100, 2, 0, 39, 25),
	(200, 2, 0, 39, 28),
    
    (500, 2, 0, 40, 11),
    (200, 1, 0, 40, 69),
    (200, 2, 0, 40, 42);
    
INSERT INTO Product_Rate(rate, comment, create_date, profile_id, product_id) VALUES
	(5, 'Giao hàng nhanh, hoa đẹp', '2022-1-14', 6, 1),
    (5, 'Giao hàng nhanh, nhân viên nhiệt tình', '2022-1-15', 10, 7),
    (4, 'Hoa đẹp', '2022-1-15', 10, 33),
    (5, 'Sản phẩm chất lượng', '2022-5-1', 18, 28),
    (5, 'Hoa đẹp', '2022-5-1', 18, 35),
    
	(5, 'Giao hàng nhanh, hoa đẹp', '2022-1-15', 7, 19),
    (5, 'Giao hàng nhanh, nhân viên nhiệt tình', '2022-1-20', 8, 9),
    (5, 'Sản phẩm chất lượng', '2022-1-15', 9, 39),
	(5, 'Giao hàng nhanh, nhân viên nhiệt tình', '2022-1-20', 11, 36),
    (5, 'Sản phẩm chất lượng', '2022-2-28', 12, 7),
    
    (5, 'Giao hàng nhanh, hoa đẹp', '2022-3-18', 14, 15),
    (5, 'Giao hàng nhanh, nhân viên nhiệt tình', '2022-3-18', 14, 30),
    (5, 'Sản phẩm chất lượng', '2022-7-2', 21, 29),
    (5, 'Giao hàng nhanh', '2022-7-2', 21, 18),
    (5, 'Hoa đẹp', '2022-7-15', 24, 42),
    
    (5, 'Hoa đẹp', '2022-5-25', 20, 12),
    (5, 'Hoa đẹp', '2022-7-2', 21, 29),
    (5, 'Hoa đẹp', '2022-7-5', 23, 21),
	(5, 'Hoa đẹp', '2022-8-5', 25, 15),
    (5, 'Hoa đẹp', '2022-8-12', 26, 58),
        
    (5, 'Giao hàng nhanh, hoa đẹp', '2022-8-30', 28, 43),
    (5, 'Giao hàng nhanh, nhân viên nhiệt tình', '2022-8-30', 28, 42),
    (5, 'Sản phẩm chất lượng', '2022-9-25', 29, 27),
    (5, 'Giao hàng nhanh', '2022-9-25', 29, 37),
    (5, 'Hoa đẹp', '2022-10-2', 31, 16),
    
    (5, 'Giao hàng nhanh, nhân viên nhiệt tình', '2022-11-30', 34, 20),
    (5, 'Giao hàng nhanh, hoa đẹp', '2022-11-30', 34, 26),
    (5, 'Giao hàng nhanh, nhân viên nhiệt tình', '2023-1-15', 38, 54),
    (5, 'Giao hàng nhanh, hoa đẹp', '2023-1-15', 38, 37),
    (5, 'Giao hàng nhanh, nhân viên nhiệt tình', '2023-1-15', 39, 28),
    
    (5, 'Giao hàng nhanh, hoa đẹp', '2022-1-14', 6, 1),
    (5, 'Giao hàng nhanh, nhân viên nhiệt tình', '2022-1-15', 10, 7),
    (5, 'Sản phẩm chất lượng', '2022-1-14', 10, 1),
    (4, 'Giao hàng nhanh', '2022-1-15', 10, 1),
    (4, 'Hoa đẹp', '2022-1-14', 6, 1);
    
   







