package com.mycompany.mapper;

import java.awt.print.Book;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.mycompany.vo.BookVO;

//Mapper interface 구현을 mybatis-spring에서 자동으로 해줌
public interface BookMapper {
	
	@Insert("insert into books (title, author, image) values (#{title}, #{author}, #{image})")
	void register(BookVO bookVo);
	
	@Select("select * from books")
	public List<BookVO> getList();
			
}
