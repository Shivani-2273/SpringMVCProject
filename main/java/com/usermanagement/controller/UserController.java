package com.usermanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.transform.impl.AddDelegateTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.usermanagement.model.Address;
import com.usermanagement.model.User;
import com.usermanagement.service.UserService;

@Controller
@EnableWebMvc
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value= {
			"/",
			"/index",
			"Logout"
	})
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="/UserRegister")
	public String userRegister() {
		return "UserRegister";
	}
	
	@RequestMapping(value="/ForgotPassword")
	public String forgotPassword() {
		return "ForgotPassword";
	}
	
	@RequestMapping(value="/AdminDashboard")
	public String adminDashboard() {
		return "AdminDashboard";
	}
	
	@RequestMapping(value="/UserDashboard")
	public String userDashboard() {
		return "UserDashboard";
	}
	
	@RequestMapping(value="/ViewUsers")
	public String viewUsers() {
		return "ViewUsers";
	}
	
	@RequestMapping(value="/LoginInfo")
	public String loginInformation() {
		return "LoginInfo";
	}
	
	@RequestMapping(value="/AddressInfo")
	public String addressDetails() {
		return "AddressInfo";
	}
	
	@RequestMapping(value="/RegisterURL",method=RequestMethod.POST)
	public String register(Model model,@ModelAttribute("registerForm") User user,
			@RequestParam("addressLine[]") String[] addressLine,
			@RequestParam("city[]") String[] city,
			@RequestParam("state[]") String[] state,
			@RequestParam("pin[]") String[] pin){
	
		List<Address> addressList=new ArrayList<Address>();
		Address addr_obj=null;
		
		int loopCounter = 0;
		while (loopCounter < addressLine.length) {
			 addr_obj = new Address();
			
			addr_obj.setAddressLine(addressLine[loopCounter]);
			addr_obj.setCity(city[loopCounter]);
			addr_obj.setState(state[loopCounter]);
			addr_obj.setPin(pin[loopCounter]);
			addr_obj.setUser(user);
			
			addressList.add(addr_obj);
		
			loopCounter++;
		}
		user.setAddress(addressList);
		userService.userRegister(user);
		
		return "index";
		
	}
	
	
}
