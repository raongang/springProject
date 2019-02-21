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
	
}
