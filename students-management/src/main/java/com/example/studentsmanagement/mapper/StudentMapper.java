package com.example.studentsmanagement.mapper;

import com.example.studentsmanagement.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {

    // SELECT * FROM student WHERE name lIKE %T%;
    @Select("SELECT * FROM student where name LIKE #{name}")
    // 在@Select里表达%是很难的，所以我们可以当service里call getStudentsContainedStrInName这个函数的时候，我们把name前面加上
    // %，后面也加上%
    List<Student> getStudentsContainStrInName(@Param("name") String name);
    // @Param("name") String name 就是告诉name这个变量来自String name，这里是一个映射

    // SELECT * from student where university_class_id IN
    // (SELECT id from university_class where year = 2021 AND number = 1);
    @Select("SELECT * from student where university_class_id IN" +
            "(SELECT id from university_class where year = #{year} AND number = #{number})")
    List<Student> getStudentsInClass(@Param("year") int year, @Param("number") int number);
}
