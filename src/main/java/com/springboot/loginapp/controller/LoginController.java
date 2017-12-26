package com.springboot.loginapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.loginapp.model.User;
import com.springboot.loginapp.service.ILoginService;

/**
 * <P>
 * This class is used to receive all requests from clients (Browser) & talks to
 * the Service & Dao layer and then sends response back to the Client again.
 * </P>
 * 
 * @author munirathinavel
 *
 */
@Controller
public class LoginController {

	private static final Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	ILoginService service;

	/*
	 * This method redirects the user to login page.
	 */
	@GetMapping(value = "/loginPage")
	public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		
		logger.debug("Entering into loginPage Method!..");
		
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid Credentials provided.");
		}

		if (logout != null) {
			model.addObject("message", "Logged out from successfully.");
		}

		model.setViewName("loginPage");
		return model;
	}

	/*
	 * This method is used to validate the user's input & shows logout or error
	 * based on the result.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody String login(@RequestParam String name, @RequestParam String password,
			HttpServletRequest request, HttpServletResponse response) {
		
		logger.debug("Entering into login Method with name:" + name + ", password:" + password);
		
		String result = "";
		if (name == null || name == "" || password == null || password == "") {
			return "ERROR";
		}
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		boolean isValidUser = service.validateUser(user);
		if (!isValidUser) {
			result = "ERROR";
		} else {
			// Returns current Session or returns new session if null.
			HttpSession session = request.getSession();
			session.setAttribute("msg", "User Logged in successfully");
			session.setAttribute("name", name);
			session.setAttribute("password", password);

			result = "SUCCESS";
		}

		return result;
	}

	/*
	 * This method clears the session & redirects to login page.
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public @ResponseBody String logout(HttpServletRequest request) {
		
		logger.debug("Entering into logout Method");
		
		ModelAndView model = new ModelAndView();
		// Clear Session on select of logout button.
		HttpSession session = request.getSession();
		if (session != null) {
			session.invalidate();
		}
		model.setViewName("loginPage");
		return "SUCCESS";
	}

}