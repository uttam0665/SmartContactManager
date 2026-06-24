package com.uttam.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Component
public class SessionHelper {
	
	public static void removeMessage() {
//	    ServletRequestAttributes attrs =
//	        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//
//	    if (attrs != null) {
//	        HttpSession session = attrs.getRequest().getSession(false);
//	        if (session != null) {
//	            session.removeAttribute("msg");
//	        }
//	    }
		
		
		try {
			HttpSession session =((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
					.getRequest().getSession();
			
			session.removeAttribute("message");
		}catch (Exception e) {
			System.out.println("Error in session helper");
			e.printStackTrace();
		}
	}

}
