package com.mycompany.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mycompany.vo.CartVO;
import com.mycompany.vo.ItemVO;

public interface CartMapper {

	/*쇼핑카트(현재 사용자 소유) 번호를 아이템에 저장
	 *  MyBatis에서 insert시 자동생성키 사용하기
	 *   └ DBMS가 자동생성키를 지원할 경우
	 *       - 
	 *       ex)
	 *       <insert id="insertStudents" useGeneratedKeys="true"
			    keyProperty="id">
			  insert into Students (name ,email)
			  values (#{name },#{email})
			</insert>
			
          └Oracle 은 AUTO INCREMENT 가 없으므로 SEQUENCE 를 사용해야 한다. 
                   해당 시퀀스명이 SEQ_STUDENT 일때 다음과 같이 사용		
           ex)
           <insert id="insertStudents">
			  <selectKey keyProperty="id" resultType="int" order="BEFORE">
			    select SEQ_ID.nextval FROM DUAL
			  </selectKey>
			  insert into Students
			    (id, name , email)
			  values
			    (#{id}, #{name}, #{email})
			</insert>

	 */
	

    @Insert("insert into carts (user_id, status) values (#{user_id}, #{status})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    int create(CartVO cartVO);	
	
	
	//item을 담는다.
	 @Insert("insert into item (cart_id, book_id, amount) values (#{cart_id}, #{book_id}, #{amount})")
	 public void add(ItemVO itemVO);

	@Select("select * from item where cart_id = #{id}") 
	List<ItemVO> getItems(CartVO cart);

	//주문하기 - 주문상태를 쇼핑중 (0) 에서  ORDERED(1)로 변경한다.
	@Update("update carts set status=1 where id=#{id}")
	public void order(CartVO cart);
	


}
