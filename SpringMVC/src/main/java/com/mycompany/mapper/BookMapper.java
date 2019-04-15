package com.mycompany.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mycompany.vo.BookVO;

//Mapper interface 구현을 mybatis-spring에서 자동으로 해줌
public interface BookMapper {
	
	@Insert("insert into books (title, author, image) values (#{title}, #{author}, #{image})")
	public void register(BookVO bookVO);
	
	@Select("select * from books order by id")
	public List<BookVO> getList();
	
	//update에 사용하기 위해 글에 해당하는 아이디 알려주기
	//@Param을 이용한 다중 파라미터 처리 설정
	@Select("select * from books where id=#{id}")
	public BookVO getBookInfo(@Param("id") Integer id);
	
	@Update("update books set title=#{title},author=#{author},image=#{image} where id=#{id}")
	public int updateBook(BookVO bookVO);
			
	@Delete("delete from books where id=#{id}")
	public int deleteBook(@Param("id") Integer id);
	
	@Select("select * from books where title=#{title}")
	public List<BookVO> getSearchBook(@Param("title") String title);
	
	
	/**
	 * 
	   <pre>
	    1 내용 
	   </pre>
	 *  @Author : raongang
	 *  @Date   : 2019. 4. 5.
	 *  
	 *  주의사항 :
	 *  LIKE '%#{title}%' -------->> LIKE '%'title'%' 변환됨 ( SQLException 발생 )
	 *  
	 *  해결방안 : 
	 *   1. RDBMS 에서 제공하는 Built-In 함수를 사용
	 *    - for Oracle:   WHERE GAME_ID LIKE '%' || #{title} || '%'
	 *	  - for MySQL :   WHERE GAME_ID LIKE CONCAT('%', #{title}, '%')
     *
	 *	 2. RDBMS 상관없이 간단하게 쓸수 있는 법
     *       - WHERE GAME_ID LIKE  '%${title}%'
     *       - $를 쓰게 될 경우, iBatis에서도 '' <ㅡ 이렇게 따옴표를 감싸지 않고 Direct하게 받게 해주는 속성이다.
	 *   
	 *   
	 */
	@Select("select * from books where title like '%${title}%'")
	public List<BookVO> search(@Param("title") String title);

	

}
