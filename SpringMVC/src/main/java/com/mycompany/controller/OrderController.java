package com.mycompany.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.mapper.BookMapper;
import com.mycompany.mapper.CartMapper;
import com.mycompany.mapper.UserMapper;
import com.mycompany.vo.CartVO;
import com.mycompany.vo.ItemVO;
import com.mycompany.vo.User;

@Controller
@RequestMapping("/orderCon/**")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private CartMapper cartMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private BookMapper bookMapper;
	
	
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
	
	@RequestMapping(value="/cartAdd" , method=POST)
	public String addCart(HttpServletRequest request, @ModelAttribute ItemVO itemVO) throws Exception{
		
		logger.info("addCard enter");
		logger.info("cartVO >> " + itemVO.toString());
		User userVO = (User)request.getSession().getAttribute("loginInfo");
		//현재 사용자를 세션에서 가져와서 조회한다.
		User currentUser = userMapper.selectUserByEmail(userVO.getEmail());
		
		//쇼핑카트에(현재사용자 소유) 번호를 아이템에 저장한다.
		if(!userMapper.hasCart(CartVO.SHOPPING, currentUser.getId())){
			
			CartVO tempVO = new CartVO(CartVO.SHOPPING,currentUser.getId());
			int cartId = cartMapper.create(tempVO);
			
			itemVO.setCart_id(cartId); //자동증가된 cartId 시퀀스 번호를 저장해준다.
		}else {
			itemVO.setCart_id(userMapper.getCart(currentUser.getId()).getId());
		}
		//쇼핑카트에 아이템을 담는다.
        // 쇼핑카트에 아이템을 담음
		
		logger.info("카트에 저장 시작.");
        cartMapper.add(itemVO);
        logger.info("카트에 저장 완료");
        return "redirect:/orderCon/carts";
	}
	
	
	@RequestMapping(value="/carts" , method=RequestMethod.GET)
	public String addCart(Model model,HttpServletRequest request) {
		logger.info("/carts index enter");
		
		User userVO = (User)request.getSession().getAttribute("loginInfo");
		logger.info("userVO >> " + userVO.toString());
		
		//현재 사용자를 세션에서 가져와서 조회한다.
		User currentUser = userMapper.selectUserByEmail(userVO.getEmail());
		int currentUserId = currentUser.getId();
		
		//카트에 
		CartVO cart = userMapper.getCart(currentUserId);
		if(cart!=null) {
			//담긴 아이템
			List<ItemVO> items = cartMapper.getItems(cart);
			for(ItemVO i : items) {
				
				i.setBook(bookMapper.getBookInfo(i.getBook_id())); //아이템이 가르키는 책
			}
			
			//합계
			int sum=0;
			for(ItemVO i : items) {
				sum+=24000 * i.getAmount();
			}
			cart.setTotalPrice(sum);
			model.addAttribute("cart",cart);
			model.addAttribute("items", items);
		}
		
		return "books/addCarts";
	}
	
	//주문하기
	@RequestMapping(value="/orders", method=RequestMethod.POST)
	public String orders(@ModelAttribute CartVO cart) {
		cartMapper.order(cart);
		return "redirect:/orderCon/orders";
	}
	
	@RequestMapping(value="/orders" , method=RequestMethod.GET)
	public String orders(HttpServletRequest request,Model model) {
		logger.info("orders GET enter");
		//현재 사용자 추출
		User userVO = (User)request.getSession().getAttribute("loginInfo");
		
		//현재 사용자를 세션에서 가져와서 조회한다.
		User currentUser = userMapper.selectUserByEmail(userVO.getEmail());
		int currentUserId = currentUser.getId();
		
		//주문 목록 보기
		List<CartVO> orders = userMapper.getOrders(currentUserId);
		
		//주문별 아이템
		for(CartVO order : orders) {
			order.setItem(cartMapper.getItems(order));
			//아이템
			int sum=0;
			for(ItemVO i : order.getItem()) {
				i.setBook(bookMapper.getBookInfo(i.getBook_id()));
				sum+=23000*i.getAmount();
			}
			
			order.setTotalPrice(sum);
		}
		
		model.addAttribute("orders",orders);
		return "books/addCartResult";
	}
	
	
	
}
