package com.perceptron.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.perceptron.service.UserService;
import com.perceptron.validator.UserValidator;
import com.perceptron.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@Resource(name = "loginUserVO")
	@Lazy
	private UserVO loginUserVO;

	@GetMapping("/login")
	public String login(@ModelAttribute("tempLoginUserVO") UserVO userVo) {
		System.out.println("[Controller] ===> UserController.login");

		return "/user/login";
	}

	@PostMapping("/login_pro")
	public String login_pro(@Valid @ModelAttribute("tempLoginUserVO") UserVO userVo, BindingResult result) {
		System.out.println("[Controller] ===> UserController.login_pro");
		if (result.hasErrors()) {
			return "user/login";
		}
		userService.getLoginUserInfo(userVo);

		if (loginUserVO.isUserLogin() == false) {
			return "user/login_fail";
		}

		return "user/login_success";
	}

	@GetMapping("/join")
	public String join(@ModelAttribute("joinUserVO") UserVO userBean) {
		System.out.println("[Controller] ===> UserController.join");
		return "/user/join";
	}

	@PostMapping("/join_pro")
	public String join_pro(@Valid @ModelAttribute("joinUserVO") UserVO userVO, BindingResult result) {
		System.out.println("[Controller] ===> UserController.join_pro");
		if (result.hasErrors()) {
			return "/user/join";
		}
		userService.addUserInfo(userVO);
		return "/user/join_success";
	}

	@GetMapping("/logout")
	public String logout() {
		loginUserVO.setUserLogin(false);
		return "/user/logout";
	}

	@GetMapping("/not_login")
	public String not_login() {
		return "user/not_login";
	}
	@GetMapping("/modify")
	public String modify(@ModelAttribute("modifyUserVO") UserVO userVO) {
		System.out.println("[Controller] ===> UserController.modify");

		userService.getModifyUserInfo(userVO);

		return "user/modify";
	}

	@PostMapping("/modify_pro")
	public String modifyUserInfo(@Valid @ModelAttribute("modifyUserVO") UserVO userVO, BindingResult result) {
		System.out.println("[Controller] ===> UserController.modifyUserInfo");

		if (result.hasErrors() == true) {
			return "user/modify";
		}

		userService.modifyUserInfo(userVO);

		return "user/modify_success";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		UserValidator validator1 = new UserValidator();
		binder.addValidators(validator1);
	}
}
