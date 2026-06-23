package com.uttam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uttam.entities.User;
import com.uttam.forms.UserForm;
import com.uttam.helper.Message;
import com.uttam.helper.MessageType;
import com.uttam.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {


	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String index() {
		return "redirect:/home";
	}
	
	@RequestMapping("/home")
	public String home(Model model) {
		System.out.println("HOME PAGE");
		model.addAttribute("name", "Uttam Gouda");
		model.addAttribute("age", 22);
		model.addAttribute("clg", "GIETU");
		model.addAttribute("github", "https://github.com/uttam0665");
		return "home";
	}
	
	@RequestMapping("/about")
	public String about() {
		System.out.println("About Page");
		return "about";
	}
	
	@RequestMapping("/services")
	public String services(Model model) {
		model.addAttribute("isLogin", true);
		System.out.println("Service Page");
		return "services";
	}
	
	@GetMapping("/contact")
	public String contact() {
		
		System.out.println("Contact Page");
		return "contact";
	}
	
	// this is showing login page
	
	@GetMapping("/login")
	public String login() {
		System.out.println("Login Page");
		return "login";
	}
	
	//registration page
	
	@GetMapping("/register")
	public String register(Model model, HttpSession session) {
	    model.addAttribute("userForm", new UserForm());

//	    Object msg = session.getAttribute("message");
//
//	    if (msg != null) {
//	        model.addAttribute("message", msg);
//	        session.removeAttribute("message");
//	    }

	    return "register";
	}
	
	//registration processing
	
	@RequestMapping(value = "/do-register", method = RequestMethod.POST)
	public String processRegister(@Valid @ModelAttribute UserForm userForm,BindingResult reBindingResult,HttpSession session) {
		System.out.println("processing on data fetching");
		// fetch   from data
		System.out.println(userForm);
		//UserForm
		//validate from data
		
		if(reBindingResult.hasErrors())return "register";
		
		//save to Database

		// userService

			// UserForm---->User

			User user=new User();
			user.setName(userForm.getName());
			user.setEmail(userForm.getEmail());
			user.setPassword(userForm.getPassword());
			user.setPhoneNumber(userForm.getPhoneNumber());
			user.setAbout(userForm.getAbout());
			user.setProfilePic("data:image/jpeg;base64,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");

			userService.saveUser(user);
			System.out.println("User saved !!!");


		//message registion complete or not

		Message msg=Message.builder().content("RegiStration Sucessful").type(MessageType.green).build();
			
		// add message

		session.setAttribute("message", msg);

		//redirect login page
		return "redirect:/register";
	}
	
	
}
