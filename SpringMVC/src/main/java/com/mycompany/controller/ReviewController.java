package com.mycompany.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.mapper.ReviewMapper;
import com.mycompany.vo.ReviewVO;

@Controller
@RequestMapping("/ReviewCon/**")
public class ReviewController {
	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	@Autowired
	private ReviewMapper reviewMapper;
	
	//리뷰 업데이트
	@RequestMapping(value="/review" ,method=POST)
	public String review(@ModelAttribute ReviewVO reviewVO) throws Exception{
		logger.info("Review Controller enter");
		logger.info("reviewVO : " + reviewVO );
		
		reviewMapper.create(reviewVO);
		
		return "redirect:/bookCon/books/detailReview/"+reviewVO.getBookId();
		
	}
}
