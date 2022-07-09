package com.perceptron.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.perceptron.vo.BoardInfoVO;

@Repository
public class TopMenuDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<BoardInfoVO> getTopMenuList(){
		List<BoardInfoVO> topMenuList=sqlSessionTemplate.selectList("TopMenuMapper.getTopMenuList");
		
		return topMenuList;
	}
}
