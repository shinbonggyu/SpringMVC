package com.perceptron.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.perceptron.dao.BoardDao;
import com.perceptron.vo.ContentVO;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class MainService {
	@Value("${main.listcnt}")
	private int main_listcnt;
	
	@Autowired
	private BoardDao boardDao;
	
	public List<ContentVO> getMainList(int board_info_idx){
		RowBounds rowBounds = new RowBounds(0, main_listcnt);
		return boardDao.getContentList(board_info_idx, rowBounds);
	}
}
