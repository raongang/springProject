package com.mycompany.mapper;

import org.apache.ibatis.annotations.Insert;

import com.mycompany.vo.BookVO;

public interface BookMapper {
	@Insert("insert into books (title, author, image) values (#{title}, #{author}, #{image})")
	void register(BookVO bookVo);
	
}
