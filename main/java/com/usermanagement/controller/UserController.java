package com.usermanagement.controller;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.usermanagement.model.User;
import com.usermanagement.service.GenericService;
import com.usermanagement.service.UserService;

@MultipartConfig
@Controller
@EnableWebMvc
public class UserController<T> {
	//private static Logger logger = Logger.getLogger(UserController.class.getName());
	@Autowired
	private UserService userService;

	@Autowired
	private GenericService<User> genericService;

	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();

	@RequestMapping(value = { "/", "/index","/Logout"})
	public String index(HttpSession session) {
		session.invalidate();
		return "index";
	}

	@RequestMapping(value = { "/UserRegister", "/addUser" })
	public String userRegister(Model model,HttpSession session,
			@RequestParam(value = "user", required = false) String userName) {
		session.invalidate();
		model.addAttribute("user", userName);
		return "UserRegister";
	}

	@RequestMapping(value = "/ForgotPassword")
	public String forgotPassword() {
		return "ForgotPassword";
	}

	@RequestMapping(value = "/AdminDashboard")
	public String adminDashboard() {
		
		return "AdminDashboard";

	}

	@RequestMapping(value = "/UserDashboard")
	public String userDashboard() {
		return "UserDashboard";

	}

	@RequestMapping("/ViewUsers")
	public ModelAndView viewUsers(User user) {
		List<User> userList = genericService.getAllUser(user);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("UserList", userList);
		modelAndView.setViewName("ViewUsers");
		return modelAndView;
	}

	@RequestMapping(value = "/LoginInfo")
	public String loginInformation() {
		return "LoginInfo";
	}

	@RequestMapping(value = "/AddressInfo")
	public String addressInformation(Model model, @RequestParam("id") int userId) {
		User addressList = userService.displayUser(userId);
		model.addAttribute("address", addressList);
		return "AddressInfo";
	}

	@RequestMapping(value = "/editProfile",method = RequestMethod.GET)
	public String editProfile(HttpSession session,Model model,@RequestParam(value = "id", required = false) String id,
			@RequestParam(value="user",required=false) String userName) {
		model.addAttribute("user", userName);
		if (id != null) {
			User userProfile = userService.displayUser(Integer.parseInt(id));
			byte[] image=userProfile.getImage();
			session.setAttribute("UserImage", image);
			session.setAttribute("userProfile", userProfile);
		}
		return "UserRegister";

	}

	@RequestMapping(value = { "/RegisterURL", "/addUser" }, method = RequestMethod.POST)
	public String register(HttpSession session, @ModelAttribute("registerForm") User user,
			@RequestParam(value = "myuser", required = false) String userName) {
			genericService.register(user);
		if (userName != null) {
			System.out.println("myuser"+userName);
			User adminProfile = genericService.displayAdmin(user);
			session.setAttribute("adminProfile", adminProfile);
			return "AdminDashboard";
		} else {
			return "index";
		}

	}

	@RequestMapping(value = "/LoginURL", method = RequestMethod.POST)
	public String login(Model model, @ModelAttribute("LoginForm") User user, HttpSession session) {
		boolean isValid = false;
		isValid = genericService.userLogin(user);
		if (isValid) {
			if (user.isAdmin()) {
				User adminProfile = genericService.displayAdmin(user);
				session.setAttribute("adminProfile", adminProfile);
				return "AdminDashboard";
			} else {
				
				User userProfile = genericService.getProfile(user);
				byte[] image=userProfile.getImage();
				session.setAttribute("UserImage", image);
				session.setAttribute("userProfile", userProfile);
				return "UserDashboard";
			}
		}
		model.addAttribute("errorMsg", "Invalid  email id and password");
		return "index";
	}

	@RequestMapping(value = "/passwordURL", method = RequestMethod.POST)
	public String forgotPassword(@ModelAttribute("resetPassword") User user) {
		genericService.resetPassword(user);
		return "index";
	}

	@RequestMapping(value = "/DeleteUser", method = RequestMethod.POST)
	public void deleteUser(@RequestParam("userId") int userId, HttpServletResponse response) throws IOException {
		userService.deleteUser(userId);
		response.getWriter().write("in success");

	}

	@RequestMapping(value = "/checkUserEmail", method = RequestMethod.GET)
	public void checkUserEmail(@RequestParam("email") String email, HttpServletResponse response) throws IOException {
		boolean isValid = userService.checkEmail(email);
		if (isValid) {
			response.getWriter().write("Matched");
		} else {
			response.getWriter().write("Not Matched");
		}
	}

	@RequestMapping(value = "/EditURL", method = RequestMethod.POST)
	public String update(HttpSession session,Model model, @ModelAttribute("registerForm") User user,@RequestParam("file")CommonsMultipartFile image,
			@RequestParam(value = "user", required = false) String userName){

		if (image.isEmpty()) {
			byte[] imagebytes = (byte[]) session.getAttribute("UserImage");
			user.setImage(imagebytes);
		}
		genericService.updateUser(user);
		if (userName.equals("userEdit")) {
			User userProfile = genericService.getProfile(user);	
			model.addAttribute("userProfile", userProfile);
			return "UserDashboard";
		} else {
			return "redirect:/ViewUsers";
		}

	}

	@RequestMapping(value = "/getCSV", method = RequestMethod.POST)
	public String generateCSV(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate)
			throws FileNotFoundException {
		userService.generateCSV(startDate, endDate);
		return "LoginInfo";
	}
}
