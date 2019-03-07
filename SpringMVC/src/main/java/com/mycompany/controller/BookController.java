package com.mycompany.controller;


import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycompany.mapper.BookMapper;
import com.mycompany.mapper.ReviewMapper;
import com.mycompany.vo.BookVO;
import com.mycompany.vo.ReviewVO;

/**
 * Handles requests for the application home page.
 */

@Controller
@RequestMapping("/bookCon/**")
public class BookController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private ReviewMapper reviewMapper;
	
	/**	
	   WebDataBinder - 스프링을 베이스로 한 웹 애플리케이션에서 데이터 바인딩을 구현한 오브젝트
	   StringTrimmerEditor Class - 웹어플에서 입력란에 아무것도 입력하지 않고 요청할 경우 getParameter를 하면 null이 아닌 공백이 들어온다. 
	   이를 방지하기 위해서 사용함
	   StringTrimmerEditor 오브젝트는 String 오브젝트의 trim메소드의 결과로 변환해주는 PropertyEditor이지만,
	   생성자에 true를 넣으면 공백을 null로 변환해주므로 매우 유용
    */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		logger.info("Init Binder enter!!");
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@RequestMapping(value="/test", method=GET)
	public String test() throws Exception{
		logger.info("test enter");
		return "test";
	}
	
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
		
		Thread.sleep(15000); //로딩바 테스트를 위한 강제 슬립.
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
	
	/**
	 *  데이터 바인딩, 검증 이벤트 구현
	 *  데이터 바인딩이 자동으로 진행되고 @Valid에 의해서 자동으로 검증처리가 진행된다.
	 *  @Valid에 의한 검증결과인 Errors 오브젝트가 erros에 저장된다.
	 *  
	 *   Erros 오브젝트는 반드시 대상으로 하는 ModelAttribute 오브젝트의 뒤에 위치해야 한다.
	 *   
	 *   Errors 대신 BindingResult 를 써도 됨.
	 */
	
	@RequestMapping(value="/books/update",method=POST)
	public String update(@Valid @ModelAttribute BookVO bookVO, Errors erros, RedirectAttributes attr) throws Exception{
		logger.info("update enter");
		logger.info("bookVO Content >> " + bookVO.toString());
		
		if(erros.hasErrors()) {
			logger.info("errors.hasErrors enter");
			List<FieldError> fieldErrors= erros.getFieldErrors();
			attr.addFlashAttribute("fieldErrors",fieldErrors);
			attr.addFlashAttribute("bookVO",bookVO);
			return "redirect:/bookCon/books/update/"+bookVO.getId();
		}
		
		//업데이트된 개수 : result - 여기 예외처리하는거 있는지 보기.
		int result = bookMapper.updateBook(bookVO);
		logger.info("update result >> " + result);
		return "redirect:/bookCon/books"; 
	}
	
	
	@RequestMapping(value="/books/delete/{id}", method=GET)
	public String delete(@PathVariable("id") Integer id) throws Exception{
		
		logger.info("delete enter id >> " + id);
		
		/* error.jsp테스트 >> hanlderException를 실행해서 /books/error.jsp에 에러메세지를 보여준다.
		boolean chk=true;
		if(chk) {
			throw new Exception("강제적인 exception 테스트");
		}*/
		
		int result = bookMapper.deleteBook(id);
		logger.info("delete result >> " + result);
		return "redirect:/bookCon/books";
	}
	
	//detail 및 리뷰 화면
	@RequestMapping(value="/books/detail/{id}",method=GET)
	public String detail(@PathVariable("id") Integer id, Model model) throws Exception{
		logger.info("Detail Controller enter");
		
		BookVO vo = bookMapper.getBookInfo(id);
		model.addAttribute("bookVO",vo);
		
		//폼 태그에서 <form:form modelAttribute="review"> 를 읽어오게 하기 위해
		ReviewVO review = new ReviewVO();
		review.setBookId(id);
		model.addAttribute("review",review);
		
		return "books/detail";
		
	}
	
	//get 리뷰 리스트
	@RequestMapping(value="/books/detailReview/{id}",method=GET)
	public String showReviewList(@PathVariable("id") Integer id, Model model) throws Exception{
		logger.info("showReviewList Controller enter");
		
		//게시글 가져오기
		BookVO vo = bookMapper.getBookInfo(id);
		model.addAttribute("bookVO",vo);
		
		//리뷰리스트 가져오기
		List<ReviewVO> reviewList = reviewMapper.getReviews(id);
		model.addAttribute("reviewList",reviewList);
		
		//폼 태그에서 <form:form modelAttribute="review"> 를 읽어오게 하기 위해
		ReviewVO review = new ReviewVO();
		review.setBookId(id);
		model.addAttribute("review",review);
		
		return "books/detail";
		
	}
	
	
	/**
	 *  CustomerControllAdvice.java로 이동 
	 *  
	 *  @ExceptionHandler 
	 *  
	 *  만약 exception이 발생하게 되면 DataNotFuoundException 처리가 되어서 화면에 예외정보가 그대로 보이게 된다.
	 *  물론, try~catch로 이 부분을 감싸서 해결할수도 있지만, 스프링MVC에서는 @ExceptionHandler 어노테이션을 이용하여 
	 *  예외처리 메소드를 추가할 수 있다.
	 *  
	 *  인수로 서블릿 API의 오브젝트와 WebRequest 오브젝트등을 설정할 수 있다. 반면 Model 오브젝트나 요청 파라미터 등은 설정 할 수 없다.
	 *  
	 *  @ExceptionHandler({DataNotFoundException.class, BarException.class}) <ㅡ 여러개 선언
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView hanlderException(Exception e) {
		
		ModelAndView mv = new ModelAndView("/books/error");
		mv.addObject("exceptionMsg",e.getMessage());
		return mv;
	}
}//end controller
