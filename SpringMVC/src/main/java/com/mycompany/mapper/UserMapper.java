package com.mycompany.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.mycompany.vo.User;

public interface UserMapper {

	@Insert("INSERT INTO users(email,password,enabled)"+ "VALUES (#{email},#{password},true)")
	public boolean insertUser(User user);
	
    @Insert("INSERT INTO authorities (email, authority) " + "VALUES (#{email}, #{authority})")
    public boolean insertAuthority(@Param("email") String email, @Param("authority") String authority);

    //로그인 정보조회
    @Select("select count(*) from users where email=#{email} and password=#{password} and enabled='T'")
	public int getLoginInfo(User user);
	
}
