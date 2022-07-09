package com.perceptron.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.context.annotation.Lazy;

import com.perceptron.dao.BoardDao;
import com.perceptron.vo.ContentVO;
import com.perceptron.vo.PageVO;
import com.perceptron.vo.UserVO;
@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class BoardService {
	@Value("${path.upload}")
	private String path_upload;
	
	@Resource(name="loginUserVO")
	@Lazy
	private UserVO loginUserVO;
	
	@Autowired
	private BoardDao boardDao;
	
	@Value("${page.listcnt}")
	private int page_listcnt;
	
	@Value("${page.paginationcnt}")
	private int page_paginationcnt;
	
	public String getBoardInfoName(int board_info_idx) {
		System.out.println("[Controller] ===> BoardService.getBoardInfoName");
		
		return boardDao.getBoardInfoName(board_info_idx);
	}
	
	private String saveUploadFile(MultipartFile upload_file) {
		System.out.println("[Controller] ===> BoardService.saveUploadFile");
		
		String file_name = System.currentTimeMillis() + "_" + upload_file.getOriginalFilename();
		
		try {
			upload_file.transferTo(new File(path_upload +"/" + file_name));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return file_name;
	}
	
	public void addContentInfo (ContentVO contentVO) {
		System.out.println("[Controller] ===> BoardService.addContentInfo");
		MultipartFile upload_file = contentVO.getUpload_file();
		if (upload_file.getSize() > 0) {
			String file_name = saveUploadFile(upload_file);
			contentVO.setContent_file(file_name);
		}
		
		contentVO.setContent_writer_idx(loginUserVO.getUser_idx());
		boardDao.addContentInfo(contentVO);
	}
	
	public List<ContentVO> getContentList(int board_info_idx, int page) {
		System.out.println("[Controller] ===> BoardService.getContentList");
		int start=(page - 1) * page_listcnt;
		
		RowBounds rowBounds = new RowBounds(start, page_listcnt);
		return boardDao.getContentList(board_info_idx, rowBounds);
	}
	
	public PageVO getContentCnt(int content_board_idx, int currentPage) {
		System.out.println("[Controller] ===> BoardService.getContentCnt");
		int content_cnt = boardDao.getContentCnt(content_board_idx); 
		
		PageVO pageVO = new PageVO(content_cnt, currentPage, page_listcnt, page_paginationcnt);
		
		return pageVO;
	}
	public ContentVO getContentInfo(int content_idx) {
		System.out.println("[Controller] ===> BoardService.getContentInfo");
		
		return boardDao.getContentInfo(content_idx);
	}
	
	public void modifyContentInfo(ContentVO contentVO) {
		System.out.println("[Controller] ===> BoardService.modifyContentInfo");
		
		MultipartFile upload_file = contentVO.getUpload_file(); 
		
		if (upload_file.getSize() > 0) {
			String file_name = saveUploadFile(upload_file);
			contentVO.setContent_file(file_name);
		}
		contentVO.setContent_writer_idx(loginUserVO.getUser_idx());

		boardDao.modifyContentInfo(contentVO);		
	}
	public void deleteContentInfo(int content_idx) {
		System.out.println("[Controller] ===> BoardService.deleteContentInfo");

		boardDao.deleteContentInfo(content_idx);		
	}
}
