package com.example.demo.dao;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.Mapping;

@Repository
@Mapper
public interface UserDao {
    @Select({"select * from user where id = #{id}"})
    User selectUserById(@Param("id") Integer id);

    @Select({"select * from user where username = #{username}"})
    User selectByUsername(String username);

    @Insert({"insert into user(username,password)values(#{username},#{password})"})
    void register(@Param("username") String username, @Param("password") String password);
}
