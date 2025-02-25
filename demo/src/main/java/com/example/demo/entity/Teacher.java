package com.example.demo.entity;

public class Teacher extends User{
    String description;
    String image;
    String video;

    //Relation
    //Graduate List<graduate>;

    //Buoc 1 Class School => Graduate => Experience => Certificate

    //School Chi Duoc Luu khi ma Nguoi dung them vao` hoac sử dụng còn khogn6 thì gọi api của tk khác để search
    // https://diemthi.tuyensinh247.com/api/school/search?q=
}
