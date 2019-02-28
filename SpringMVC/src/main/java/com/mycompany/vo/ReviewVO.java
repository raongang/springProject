package com.mycompany.vo;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author admin
 *  Spring MVC의 data binding은 default로 설정되어 있기 때문에, 별도의 설정을 할 필요가 없다.
 *  Spring은 Bean Validation기능을 제공한다.
 *  
 *  
 */

/**
 *  스프링 디폴트 데이터 바인딩 검증 순서
 *   1 스프링이 제공하는 Bean Validation에 정의된 디폴트 애노테이션 선언 (@NotEmpty ) 
 *   2 Form 입력값이 "" 공백 방지를 위한 @InitBinder 선언 ( ex. ReviewController )
 *   3 검증 메소드에 검증 애노테이션 설정 @Vlidate ( BookController update 메소드 )
 *   4 1에서 선언한 @NotEmpty에 대해 바인딩 결과를 저장할 수 있는 BindingResult 선언 
 *    ( Errors 인터페이스도 가능, BindingResult 인터페이스가 하위 인터페이스임
 *   5 BindingResult에 Error값 저장 및 페이지 리프레쉬를 위한 RedirectAttributes 선언
 *   6 jsp에서 Error값 표시 ( detailReview ) 
 */


public class ReviewVO {
	Integer id;
	
	//@NotEmpty - Bean Validation 의 구현인 Hibernate Validator에 정의된 Annotation
	@NotEmpty
	String text;
	@NotNull
	Integer bookId;
	Integer userId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "Review [id=" + id + ", text=" + text + ", bookId=" + bookId + ", userId=" + userId + "]";
	}
}
