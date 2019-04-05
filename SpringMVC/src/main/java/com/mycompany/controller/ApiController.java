package com.mycompany.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.mapper.BookMapper;
import com.mycompany.vo.BookVO;

@Controller
public class ApiController {
	
	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
	
	@Autowired
	BookMapper bookMapper;
	
	/** JsonViewResolver / JsonView 를 이용한 처리 */
	@RequestMapping(value="/api/books/search" , method=RequestMethod.GET)
	public ModelAndView searchBook(String title) {
		
		logger.info(">> SearchBook Enter");
		logger.info(">> title : " + title);
	
		List<BookVO> booklist = bookMapper.search(title);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("bookList",booklist);
		mv.setViewName("jsonView");
		return mv;
	}
	
	/**
	//GET으로 받거나 단일 파라미터를 받을경우 @RequestParam으로도 데이터를 받을 수 있다.
	@RequestMapping(value="/api/books/search" , method=RequestMethod.GET)
	public @ResponseBody List<BookVO> searchBook(@RequestParam("title") String title) {
		logger.info(">> SearchBook Enter");
		logger.info(">> title : " + title);
	
		List<BookVO> bookList = bookMapper.search(title);
		
		if(bookList==null) { 
			logger.info("Search BookList is null"); 
		}else {
			logger.info("Search BookList is not null");
			//값 출력
			for(BookVO vo: bookList) {
				System.out.println(">> vo.getTitle : " + vo.getTitle());
			}
		}
		return bookList;
		
	}*/
	
}
