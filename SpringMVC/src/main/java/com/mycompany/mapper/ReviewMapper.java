package com.mycompany.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.mycompany.vo.ReviewVO;

public interface ReviewMapper {
	
	@Insert("insert into reviews(text,book_id) values (#{text},#{bookId})")
	void register(ReviewVO reviewVO);

	@Select("select * from reviews where book_id = #{bookId} order by id desc")
	List<ReviewVO> getReviews(Integer id);

}
