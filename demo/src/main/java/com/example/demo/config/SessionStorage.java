package com.example.demo.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.example.demo.classes.Cart;

@Component // 유틸리티 클래스이므로 @Component를 사용합니다
public class SessionStorage {

    // 세션에 데이터를 저장하는 메서드
    public void saveSessionData(HttpServletRequest request, String key, Object value) {
        HttpSession session = request.getSession();
        session.setAttribute(key, value);
    }

    // 세션에서 데이터를 가져오는 메서드
    public Object getSessionData(HttpServletRequest request, String key) {
        HttpSession session = request.getSession();
        return session.getAttribute(key);
    }

    // 세션에서 데이터를 제거하는 메서드
    public void removeSessionData(HttpServletRequest request, String key) {
        HttpSession session = request.getSession();
        session.removeAttribute(key);
    }

    // 세션을 무효화하는 메서드
    public void invalidateSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
    }
    
    // 기존 세션을 수정하는 메서드
    public void replaceCartSession(HttpServletRequest request, String key, Cart newCart) {
        HttpSession session = request.getSession();
        
        // 세션에서 기존 리스트를 가져옴
        ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute(key);
        
        if (cartList == null) {
            // 리스트가 없으면 새로 생성
            cartList = new ArrayList<>();
        }
        
        // 새로운 Cart 객체를 리스트에 추가
        cartList.add(newCart);
        
        // 리스트를 세션에 다시 저장
        session.setAttribute(key, cartList);
    }
    
 // 세션에서 Cart를 삭제하는 메서드,
   /*
    * 키값으로 저거 뭐냐........orderID를 입력할것
    */
    public void removeCartSession(HttpServletRequest request, int shohinID) {
    	HttpSession session = request.getSession();
        
        // 세션에서 기존 리스트를 가져옴
        ArrayList<Cart> oldCartList = (ArrayList<Cart>) session.getAttribute("CartList");
        ArrayList<Cart> newCartList = new ArrayList<Cart>();
        
        for(int i=0;i<oldCartList.size();i++) {
        	if(oldCartList.get(i).getShohinDTO().getShohin_id()!=shohinID) {
        		newCartList.add(oldCartList.get(i));
        	}
        }
        
        
        // 리스트를 세션에 다시 저장
        session.setAttribute("CartList", newCartList);
    }
    
}
