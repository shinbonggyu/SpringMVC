package com.perceptron.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.perceptron.vo.UserVO;

public class UserValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UserVO.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("[Validator] ===> UserValidator.validate");
		
		UserVO userVO = (UserVO)target;
		String beanName = errors.getObjectName(); 
		
		if (beanName.equals("joinUserVO")|| beanName.equals("modifyUserVO")) {
			if (userVO.getUser_pw().equals(userVO.getUser_pw2()) == false) {
				errors.rejectValue("user_pw", "NotEquals");
				errors.rejectValue("user_pw2", "NotEquals");
			}
		}
		
		if (beanName.equals("joinUserVO")) {
			if (userVO.isUserIdExist() == false) {
				errors.rejectValue("user_id", "DontCheckUserIdExist"); 
			}
		}
	}
}
