package com.mycompany.controller;


import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.mapper.BookMapper;
import com.mycompany.vo.BookVO;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/bookCon/**")
public class BookController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private BookMapper bookMapper;
	
	/**
	 * BOOK MENU LIST 
	 * Model Class - 스프링 MVC에서 제공하는 데이터 전달용 객체
	 * 과거 Servlet에서는 RequestDispatcher에 데이터를 저장했듯이, 스프링에서는 Model을 이용하여 데이터를 저장.
	 */	
	@RequestMapping(value = "/books", method = GET) 
	public String index(Model model) throws Exception {
		logger.info("getList book");
		List<BookVO> list = bookMapper.getList();
		model.addAttribute("bookVO", list);
		return "books/index";
	}

	@RequestMapping(value="/books_new",method=GET)
	public String newBook() {
		return "books/new";
	}
	
	/*
	 *  BOOK 등록하기
	 * @ModelAttribute라는 어노테이션만 붙여도 클라이언트에서 전달하는 파라미터를 1:1로 전담 프로퍼티에 자동 바인딩함.
	 * 즉, 서블릿에서 String parameter1 = request.getParameter("parameter1"); 이런식의 코드가 없어지는거임. 
	*/
	@RequestMapping(value = "/register", method = POST)
	public String create(@ModelAttribute BookVO bookVO) throws Exception {
		logger.info("register enter");
		logger.info(bookVO.toString());
		bookMapper.register(bookVO);
		
		return "redirect:/bookCon/books";
	}

	
	// BOOK UPDATE
	// @PathVariable - URI 경로에서 원하는 데이터를 추출하는 용도로 사용.
	@RequestMapping(value="/books/update/{id}", method=GET)
	public String update(@PathVariable("id") Integer id, Model model) throws Exception{
		logger.info("updateForm enter ID >>" + id);

		BookVO vo = bookMapper.getBookInfo(id);
		model.addAttribute("vo",vo);
		
		return "books/updateForm";
	}
	
	@RequestMapping(value="/books/update",method=POST)
	public String update(@ModelAttribute BookVO bookVO) throws Exception{
		logger.info("update enter");
		logger.info("bookVO Content >> " + bookVO.toString());
		
		//업데이트된 개수 : result - 여기 예외처리하는거 있는지 보기.
		int result = bookMapper.updateBook(bookVO);
		logger.info("update result >> " + result);
		return "redirect:/bookCon/books";
	}
	
	// BOOK DELETE GET
	@RequestMapping(value="/books/delete/{id}", method=GET)
	public String delete(@PathVariable("id") Integer id) throws Exception{
		logger.info("delete enter id >> " + id);
		
		int result = bookMapper.deleteBook(id);
		logger.info("delete result >> " + result);
		
		return "redirect:/bookCon/books";
	}
}//end controller
