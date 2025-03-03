# 📚 Thuê Gia Sư Online

## 📌 Giới thiệu
Dự án "Thuê Gia Sư Online" là một nền tảng giúp kết nối học viên với gia sư một cách dễ dàng và tiện lợi. Học viên có thể tìm kiếm gia sư theo môn học, trình độ và địa điểm, trong khi gia sư có thể đăng ký tài khoản và cung cấp dịch vụ giảng dạy.

## 🚀 Tính năng
- Đăng ký/Đăng nhập cho người dùng. Người dùng có thể tìm giáo viên hoặc trở thành giáo viên tìm việc.
- Tìm kiếm và lọc gia sư theo trường học, chứng chỉ, kinh nghiệm và khu vực
- Đánh giá và phản hồi gia sư
- Tạo bài viết tuyển gia sư hoặc tìm việc làm.
- Học viên và gia sư có thể tìm thấy và nhắn tin với nhau qua bài viết được đăng tải.
- Phân quyền các API Endpoint.
- Bắt lỗi và trả đúng mã lỗi , chuẩn hóa trả về
- 
## 🛠 Công nghệ sử dụng
- **Back-end:** Java Spring Boot
- **Front-end:** ReactJS, Tailwind CSS
- **Real-time Communication:** WebSocket
- **Database:** MySQL
- **Authentication:** JWT (JSON Web Token)
- **Security:** Spring Security

## 📥 Cài đặt và chạy dự án
### Yêu cầu hệ thống
- Java >=17
- Springboot 3

### Cách cài đặt trên Java (Spring Boot)
1. Clone repository:
   ```sh
   git clone https://github.com/asxi3011/BE_RentTeacherWeb
   cd /demo/src
   ```
2. Cấu hình file `application.properties` trong `src/main/resources/`
   ```properties
   server.port=8080
   spring.datasource.url=jdbc:mysql://localhost:3306/tutor_online
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   jwt.SECRET_KEY=your_jwt_secret
   cloudinary.cloud-name: your_cloud_name
   cloudinary.api-key: your_api_key
   cloudinary.api-secret: your_api_secret
   ```
3. Cài đặt và chạy ứng dụng:
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```
4. Truy cập ứng dụng tại `http://localhost:8080`

## 📌Các API Endpoints chính
### 1. Xác thực người dùng
- **Đăng ký**
  - `POST /add`
  - Request Body:
    ```json
    {
      "username": "john_doe",
      "password": "SecurePass123",
      "firstName": "John",
      "lastName": "Doe",
      "email": "johndoe@example.com"
    }
    ```
- **Đăng nhập**
  - `POST /auth/login`
  - Request Body:
    ```json
    {
      "username": "john_doe",
      "password": "SecurePass123"
    }
    ```
- **Đăng xuất**
  - `POST /auth/logout`
  - Request Body:
    ```json
    {
      "accessToken": "your_access_token_here",
      "refreshToken": "your_refresh_token_here"
    }
    ```

### 2. Quản lý giáo viên
- **Tạo giáo viên**
  - `POST /teacher/add`
  - Request Body:
    ```json
    {
      "description": "Tôi là giáo viên dạy Toán với 10 năm kinh nghiệm.",
      "image": "teacher_profile.jpg",
      "video": "introduction.mp4",
      "typeTimeWork": "FULLTIME",
      "wagePayments": "DAY"
    }
    ```
- **Thêm ngành tốt nghiệp** 
  - `POST /graduate/add`
  - Request Body:
    ```json
    {
      "major": "Toán học ứng dụng",
      "schoolYear": "2015-2019",
      "gpa": 8.5,
      "isComplete": true,
      "codeUniversity": "HUST",
      "nameUniversity": "Đại học Bách Khoa Hà Nội"
    }
    ```
- **Thêm ngành chứng chỉ**
  - `POST /graduate/add`
  - Request Body:
    ```json
    {
      "trainingProgram":"Toeic 650",
      "reference" :"Trung Tâm Việt Úc",
      "issueDate":"2023-02-22",
      "expiryDate":"2024-02-22"
    }
    ```

### 3. Tính năng Chat Real-time
- **Gửi tin nhắn**
  - `POST /api/chat/send`
  - Request Body:
    ```json
    {
      "senderId": "user_123",
      "receiverId": "teacher_456",
      "message": "Chào thầy, em muốn học Toán lớp 12!"
    }
    ```
- **Lấy lịch sử tin nhắn**
  - `GET /api/chat/history/:userId/:teacherId`
- **Nhận tin nhắn theo thời gian thực**
  - Sử dụng WebSocket với sự kiện `new_message`
  - Ví dụ:
    ```js
    socket.on("new_message", (data) => {
      console.log("Tin nhắn mới:", data);
    });
    ```

## 📖 Hướng dẫn sử dụng API
1. **Xác thực**: Sau khi đăng nhập, nhận token JWT và gửi trong header với mỗi request:
   ```sh
   Authorization: Bearer your_jwt_token
   ```
2. **Gọi API**: Dùng Postman hoặc cURL để thử nghiệm các endpoint.
   ```sh
   curl -X GET "http://localhost:5000/api/teachers" -H "Authorization: Bearer your_jwt_token"
   ```
##
Sơ đồ ERD:
  ![ERD_TutorOnline_v2](https://github.com/user-attachments/assets/863e9879-c1e7-41f6-861d-78350f171bc3)
Sơ đồ Class: 
  ![image](https://github.com/user-attachments/assets/da236f84-a8b4-49de-9064-2799fbba7ca7)

  
## 🤝 Đóng góp
Mọi đóng góp đều được hoan nghênh! Vui lòng tạo một pull request hoặc issue để báo lỗi hoặc đề xuất tính năng mới.

## 📧 Liên hệ
- Email: asxi3011@gmail.com
- Facebook: ([Say Dậu](https://www.facebook.com/ga.con.3011/?locale=vi_VN))
- API-Public: ([https://tutoronline.com](https://berentteacherweb-production.up.railway.app/))

---
Cảm ơn bạn đã quan tâm đến dự án! 🚀
