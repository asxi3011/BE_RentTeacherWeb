
URL Mặc định (URL_DEFAULT) : http://localhost:8080/indetity (thay đổi tùy theo máy)

1. Để chạy dc thì tạo file "application.yaml" ở path "/src/main/resouces"
2. và config theo sau:
```
server:
  port: 8080
  servlet:
    context-path: /identity
spring:
  application:
    name: AppMusic
  datasource:
    url: "jdbc:mysql://localhost:3306/identity_service"
    username: root
    password: root
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
jwt:
  SECRET_KEY: ""

```
SECRET_KEY THÌ VÀO ĐÂY MÀ GEN KEY 32 BYTES R BỎ VÀO https://generate-random.org/encryption-key-generator

3. Sau khi kết nối DB local thành công thì chạy file "inser_data_updated.sql" có trong source để khởi tạo dữ liệu city,district
4. Các phiên bản theo file pom.XML
5. Để truy cập các tính năng yêu cầu xác thực quyền. Tài khoản để test (ROLE ADMIN)
   > tk: admin
   > mk: admin 
6. HDSD Các RestAPI
  > POST URL_DEFAULT/login ( đăng nhập để lấy token, lấy token đó để mà xác thực r mới truy cập dc cái API khác) cần Body (username,password)

  > GET URL_DEFAULT/city/getAll : lấy tất cả City có trả về District trong City

  > GET URL_Default/city/getCity/{idCity}: lấy riêng một city

  > POST URL_Default/post/add: thêm bài post


  > GET URL_Default/post/getAllPost : lấy tất cả post

  > POST URL_Default/university/add : thêm trường đại học

  > GET URL_Default/university/getUniversitiesOnline : lấy tất cả trường ĐH VN

  > GET URL_Default/getUniversity/{idUniversity}: lấy thông tin 1 trường ĐH

  > POST URL_Default/user/add : Thêm user

  > GET URL_Default/users : Lấy hết thông tin user

  > GET URL_Default/user: Lấy thông tin cá nhân chính mình

  > PUT URL_Default/editUser: ĐỔI THÔNG TIN USER

  > DELETE URL_Default/deleteUser: XÓA USER



