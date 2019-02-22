package com.mycompany.service;


/** custom exception
 *  use : 
 *  DataNotFoundException df  = new DataNotFoundException("dataError",new DataNotFoundException());
 *  
 *  이건 사용자 정의 Exception class니 이걸 이용하여 보여주거나, 아니면 이 클래스를 사용하지 않고 
 *  @ExceptionHandler를 이용해서 jsp에서 에러유형을 보여줄 수 있다.
 *  
 * @param msg
 * @param th
 * 
 */
public class DataNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public DataNotFoundException() {
		System.out.println("DataNotFoundException 발생");
	}

	public DataNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public DataNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}	


}
