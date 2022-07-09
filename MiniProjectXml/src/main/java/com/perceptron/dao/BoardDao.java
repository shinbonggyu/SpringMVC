package com.perceptron.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.perceptron.vo.ContentVO;

@Repository
public class BoardDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public String getBoardInfoName(int board_info_idx) {
		System.out.println("[Controller] ===> BoardDao.getBoardInfoName");
		
		return sqlSessionTemplate.selectOne("BoardMapper.getBoardInfoName", board_info_idx);
	}
	
	public void addContentInfo(ContentVO contentVO) {
		System.out.println("[Controller] ===> BoardDao.addContentInfo");
		
		sqlSessionTemplate.insert("BoardMapper.addContentInfo", contentVO);
	}
	
	public List<ContentVO> getContentList(int board_info_idx, RowBounds rowBounds) {
		System.out.println("[Controller] ===> BoardDao.getContentList");
		
		return sqlSessionTemplate.selectList("BoardMapper.getContentList", board_info_idx, rowBounds);
	}
	
	public int getContentCnt(int content_board_idx) {
		System.out.println("[Controller] ===> BoardDao.getContentCnt");
		
		return sqlSessionTemplate.selectOne("BoardMapper.getContentCnt", content_board_idx);
	}
	public ContentVO getContentInfo(int content_idx) {
		System.out.println("[Controller] ===> BoardDao.getContentInfo");
		
		return sqlSessionTemplate.selectOne("BoardMapper.getContentInfo", content_idx);
	}
	public void modifyContentInfo(ContentVO contentVO) {
		System.out.println("[Controller] ===> BoardDao.modifyContentInfo");
		
		sqlSessionTemplate.update("BoardMapper.modifyContentInfo", contentVO);
	}
	public int deleteContentInfo(int content_board_idx) {
		System.out.println("[Controller] ===> BoardDao.deleteContentInfo");
		
		return sqlSessionTemplate.selectOne("BoardMapper.getContentCnt", content_board_idx);
	}
}
