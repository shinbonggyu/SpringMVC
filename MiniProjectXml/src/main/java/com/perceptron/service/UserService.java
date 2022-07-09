package com.perceptron.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.perceptron.dao.UserDao;
import com.perceptron.vo.UserVO;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	@Resource(name="loginUserVO")
	@Lazy
	private UserVO loginUserVO;
	public boolean checkUserIdExist(String user_id) {
		System.out.println("[Service] ===> UserService.checkUserIdExist");
		
		String user_name = userDao.checkUserIdExist(user_id);
		
		if (user_name == null) {
			return true;
		} else {
			return false;
		}
	}
	public void addUserInfo (UserVO userVO) {
		System.out.println("[Service] ===> UserService.addUserInfo");
		
		userDao.addUserInfo(userVO);
	}
	
	public void getLoginUserInfo(UserVO userVO) {
		System.out.println("[Service] ===> UserService.getLoginUserInfo");
		UserVO tempLoginUserVO = userDao.getLoginUserInfo(userVO);
		if (tempLoginUserVO != null) {			
			loginUserVO.setUser_idx(tempLoginUserVO.getUser_idx());
			loginUserVO.setUser_id(tempLoginUserVO.getUser_id());
			loginUserVO.setUser_name(tempLoginUserVO.getUser_name());

			loginUserVO.setUserLogin(true);
		}
	}
	
	public void modifyUserInfo(UserVO userVO) {
		System.out.println("[Service] ===> UserService.getModifyUserInfo");
		
		userVO.setUser_idx(loginUserVO.getUser_idx());
		
		userDao.modifyUserInfo(userVO);
	}
	
	public void getModifyUserInfo(UserVO userVO) {
		System.out.println("[Service] ===> UserService.getModifyUserInfo");
		
		UserVO tempModifyUserBean = userDao.getModifyUserInfo(loginUserVO.getUser_idx());
		userVO.setUser_id(tempModifyUserBean.getUser_id());
		userVO.setUser_name(tempModifyUserBean.getUser_name());
		userVO.setUser_idx(tempModifyUserBean.getUser_idx());
	}
}
