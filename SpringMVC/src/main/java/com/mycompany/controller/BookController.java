package com.mycompany.controller;


import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/books", method = GET)
	public String index() {
		return "books/index";
	}

	/*@ModelAttribute라는 어노테이션만 붙여도 클라이언트에서 전달하는 파라미터를 1:1로 전담 프로퍼티에 자동 바인딩함.
	 * 즉, 서블릿에서 String parameter1 = request.getParameter("parameter1"); 이런식의 코드가 없어지는거임. 
	*/
	@RequestMapping(value = "/books", method = POST)
	public String create(@ModelAttribute BookVO bookVo) throws Exception {
		logger.info("register enter");
		logger.info(bookVo.toString());
		bookMapper.register(bookVo);
		logger.info("register finish");
		return "redirect:/bookCon/books";
	}
	
	@RequestMapping(value="/books_new",method=GET)
	public String newBook() {
		return "books/new";
	}
	
	
}
