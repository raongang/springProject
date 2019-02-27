package com.mycompany.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycompany.mapper.ReviewMapper;
import com.mycompany.vo.ReviewVO;

@Controller
@RequestMapping("/ReviewCon/**")
public class ReviewController {
	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	@Autowired
	private ReviewMapper reviewMapper;
	

	
	/** 리뷰 등록
	 *  RedirectAttributes - 리다이렉트가 발생하기 전에 모든 플래시 속성을 세션에 복사한다. 
		                     리다이렉션 이후에는 저장된 플래시 속성을 세션에서 모델로 이동시킨다. 
		                     헤더에 파라미터를 붙이지 않기 때문에 URL에 노출되지 않는다.
	 * @param reviewVO
	 * @param bind
	 * @param attr
	 * @return
	 */
	@RequestMapping(value="/review",method=POST)
	public String RegiReview(@Valid @ModelAttribute ReviewVO reviewVO, BindingResult bind, RedirectAttributes attr) {
		
		logger.info("RegiReview enter");
		
		if(bind.hasErrors()) {
			logger.info("bind hass Errors");
			List<FieldError> fieldError = bind.getFieldErrors();
			attr.addFlashAttribute("fieldErrors",fieldError);
			attr.addFlashAttribute("reviewVO",reviewVO);
			return "redirect:/bookCon/books/detailReview/"+reviewVO.getBookId();
		}
		
		reviewMapper.register(reviewVO);
		return "redirect:/bookCon/books/detailReview/"+reviewVO.getBookId();
	}
}
