package com.mycompany.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mycompany.vo.CartVO;
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
    
    //현재 사용자의 정보를 반환
    @Select("select * from users where email=#{email}")
    public User selectUserByEmail(String email);

    //현재 사용자에 대한 쇼핑카트 번호가 있는지를 체크한다.???????
    @Select("select exists(select 1 from carts where status=#{status} and user_id=#{user_id})")
	public boolean hasCart(@Param("status")int status, @Param("user_id")int user_id);

    //현재 사용자애 대한 카트번호를 가져온다.
    @Select("select * from carts where user_id=#{user_id} and status=0")
	public CartVO getCart(int id);

    //주문목록보기
    @Select("select * from carts where user_id=#{userId} and status>0")
	public List<CartVO> getOrders(int userId);
    
    
}
