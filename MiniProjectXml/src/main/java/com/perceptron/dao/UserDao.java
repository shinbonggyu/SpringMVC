package com.perceptron.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.perceptron.vo.UserVO;

@Repository
public class UserDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public String checkUserIdExist(String user_id) {
		System.out.println("[Dao] ===> UserDao.checkUserIdExist");
		
		return sqlSessionTemplate.selectOne("UserMapper.checkUserIdExist", user_id);
	}
	public void addUserInfo(UserVO userVO) {
		System.out.println("[Dao] ===> UserDao.addUserInfo");
		
		sqlSessionTemplate.insert("UserMapper.addUserInfo", userVO);
	}
	
	public UserVO getLoginUserInfo(UserVO userVO) {
		System.out.println("[Dao]===> UserDao.getLoginUserInfo");
		
		return sqlSessionTemplate.selectOne("UserMapper.getLoginUserInfo", userVO);
	}
	
	public void modifyUserInfo(UserVO userVO) {
		System.out.println("[Dao] ===> UserDao.modifyUserInfo");
		
		sqlSessionTemplate.update("UserMapper.modifyUserInfo", userVO);
	}
	
	public UserVO getModifyUserInfo(int user_idx) {
		System.out.println("[Dao] ===> UserDao.getModifyUserInfo");
		
		return sqlSessionTemplate.selectOne("UserMapper.getModifyUserInfo", user_idx);
	}
}
