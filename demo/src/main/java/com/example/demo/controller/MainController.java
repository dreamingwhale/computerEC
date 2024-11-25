package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.classes.Cart;
import com.example.demo.config.SessionStorage;
import com.example.demo.dao.Com_KaiinDAO;
import com.example.demo.dao.Com_Order_ListDAO;
import com.example.demo.dao.Com_ShohinDAO;
import com.example.demo.dto.Com_KaiinDTO;
import com.example.demo.dto.Com_Order_ListDTO;
import com.example.demo.dto.Com_ShohinDTO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@Log4j
public class MainController {

	Com_KaiinDAO kaiinDAO = new Com_KaiinDAO();
	Com_ShohinDAO shohinDAO = new Com_ShohinDAO();
	Com_Order_ListDAO ListDAO = new Com_Order_ListDAO();
	
	@Autowired
	private SessionStorage sessionStorage;
	
    @GetMapping("/")
    public String firstPage(HttpServletRequest request) {
    	request.getSession().invalidate();
    	return "Login";
    }
    
    @PostMapping("/Login")
    public String loginPage() {
    	return "redirect:/";
    }
    
    @PostMapping(value="/Main")
    public String login(@ModelAttribute Com_KaiinDTO kaiinDTO, HttpServletRequest request) {
    	if(kaiinDAO.checkIDAndPW(kaiinDTO)) {
    		if(kaiinDTO.getUserID().equals("master"))
    		{
    			sessionStorage.saveSessionData(request, "Kaiin", kaiinDTO);
    			return "redirect:/Master";
    		}
    		sessionStorage.saveSessionData(request, "Kaiin", kaiinDTO);
    		sessionStorage.saveSessionData(request, "Shohins", shohinDAO.getAllShohinNotDeleted());
    		return "Main";
    	}
    	return "Login"; // JSP 파일 이름
    }
    
    @GetMapping(value="/Header_ichiran")
    public String Header_ichiran() {
    	return "/Main";
    }
    
    @GetMapping(value="/Header_Cart")
    public String Header_Cart() {
    	return"/Cart";
    }
    

    @RequestMapping("/Master")
    public String masterPage(HttpServletRequest request) {
    	sessionStorage.saveSessionData(request, "Shohins", shohinDAO.getAllShohin());
    	return "Master";
    }
    @RequestMapping("/InsertShohinPage")
    public String InsertShohinPage(HttpServletRequest request) {
    	sessionStorage.saveSessionData(request, "Shohins", shohinDAO.getAllShohin());
    	return "InsertShohinPage";
    }
    @PostMapping("/InsertShohin")
    public String InsertShohin(@ModelAttribute Com_ShohinDTO shohinDTO) {
    	shohinDAO.InsertShohin(shohinDTO);
    	return "redirect:/Master";
    }
    
    @GetMapping("/InsertCart")
    public String InsertCart(HttpServletRequest request, @RequestParam("Shohin_ID") int shohinID) {
    	Cart cart = new Cart();
    	ArrayList <Cart> oldCartList = (ArrayList<Cart>) sessionStorage.getSessionData(request, "CartList");
    	if(oldCartList!=null)
    	for(int i=0;i<oldCartList.size();i++) {
    		if(oldCartList.get(i).getShohinDTO().getShohin_id()==shohinID) {
    			return "/Cart";
    		}
    	}
    	
    	cart.setShohinDTOByshohinID(shohinID);
    	Com_KaiinDTO kaiin = new Com_KaiinDTO();
    	kaiin = (Com_KaiinDTO) sessionStorage.getSessionData(request, "Kaiin");
    	cart.setUserID(kaiin.getUserID());
    	cart.setOrderID(shohinID);
    	sessionStorage.saveSessionData(request,"Cart",cart);
    	sessionStorage.replaceCartSession(request, "CartList", cart);
    	return "/Cart";
    }
    
    @GetMapping("/DeleteCart")
    public String DeleteCart(@RequestParam ("Shohin_ID") int shohinID, HttpServletRequest request) {
    	sessionStorage.removeCartSession(request, shohinID);
    	return "/Main";
    }
    
    
    @GetMapping("/SelectShohinPage")
    public String SelectShohinPage(HttpServletRequest request) {
    	sessionStorage.saveSessionData(request, "shousaiShohin", shohinDAO.getShousaiShohin(Integer.parseInt(request.getParameter("searchShohin_id"))));
    	return "SelectShohinPage";
    }
    @GetMapping("/DeleteShohin")
    public String DeleteShohin(@RequestParam("Shohin_ID") int shohinId, HttpServletRequest request) {
    	shohinDAO.deleteShohin(shohinId);
    	return "redirect:/Master";
    }
    
    @PostMapping("/UpdateShohin")
    public String UpdateShohin(HttpServletRequest request,@ModelAttribute Com_ShohinDTO shohinDTO) {
    	System.out.println("修正ID:"+shohinDTO.getShohin_id());
    	shohinDAO.UpdateShohin(shohinDTO);
    	sessionStorage.saveSessionData(request, "Shohins",shohinDAO.getAllShohin());
    	return "redirect:/Master";
    }
    
    @GetMapping("/Order")
    public String Order(HttpServletRequest request) {
    	Com_KaiinDTO kaiin = (Com_KaiinDTO) sessionStorage.getSessionData(request, "Kaiin");
    	ListDAO.InsertOrderList(kaiin.getUserID(), request);
    	ListDAO.InsertOrderListProduct((ArrayList<Cart>)sessionStorage.getSessionData(request, "CartList"));
    	Cart tmpCart = (Cart)sessionStorage.getSessionData(request, "Cart");
    	sessionStorage.saveSessionData(request, "OrderedProducts",ListDAO.getOrderedProducts());
    	return "OrderConfirm";
    }
    
    
    @RequestMapping(value="/SignUp")
    public String signUp() {
    	return "SignUp";
    }
    
    @PostMapping("/KaiinToroku")
    public String KaiinToroku(@ModelAttribute Com_KaiinDTO kaiinDTO) {
    	if(kaiinDAO.checkID(kaiinDTO)) {
    		System.out.println("동일한 아이디가 존재합니다.");
    		return "SignUp";
    	}
    	System.out.printf(kaiinDTO.getUserID());
    	kaiinDAO.InsertKaiin(kaiinDTO);
    	return "Login";
    }
    
    
}
