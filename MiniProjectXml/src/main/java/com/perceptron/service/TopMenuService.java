package com.perceptron.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perceptron.dao.TopMenuDao;
import com.perceptron.vo.BoardInfoVO;

@Service
public class TopMenuService {
	@Autowired
	private TopMenuDao topMenuDao;

	public List<BoardInfoVO> getTopMenuList() {
		List<BoardInfoVO> topMenuList = topMenuDao.getTopMenuList();
		return topMenuList;
	}
}
