package com.mycompany.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mycompany.vo.User;

public interface UserMapper {

	@Insert("INSERT INTO users(email,password,enabled)"+ "VALUES (#{email},#{password},true)")
	public boolean insertUser(User user);
	
    @Insert("INSERT INTO authorities (email, authority) " + "VALUES (#{email}, #{authority})")
    public boolean insertAuthority(@Param("email") String email, @Param("authority") String authority);

    //로그인 정보조회
    //@Select("select count(*) from users where email=#{email} and password=#{password} and enabled='T'")
    @Select("select * from users where email=#{email} and password=#{password} and enabled='T'")
	public User getLoginInfo(User user);
	
    
    //세션정보 update
    @Update("update users set sessionkey=#{sessionkey}, sessionlimit=#{sessionLimit} where email=#{email}")
    public int keepLogin(@Param("email")String email , @Param("sessionkey") String sessionkey, @Param("sessionLimit") Date sessionLimit);
    
    //쿠키값 검색
    
    @Select("select * from users where sessionkey=#{cookieValue}")
    public User checkLoginBefore(@Param("cookieValue")String cookieValue);
    
    
}
