package com.perceptron.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.perceptron.service.BoardService;
import com.perceptron.vo.ContentVO;
import com.perceptron.vo.PageVO;
import com.perceptron.vo.UserVO;
import org.springframework.context.annotation.Lazy;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@Resource(name="loginUserVO")
	@Lazy
	private UserVO loginUserVO;
	
	@GetMapping("/main")
	public String main(@RequestParam("board_info_idx") int board_info_idx,
			@RequestParam(value="page", defaultValue="1") int page,
			Model model) {
		System.out.println("[Controller] ===> BoardController.main");
		
		model.addAttribute("board_info_idx", board_info_idx);
		
		String boardInfoName= boardService.getBoardInfoName(board_info_idx);
		model.addAttribute("boardInfoName", boardInfoName);
		
		List<ContentVO> contentList=boardService.getContentList(board_info_idx, page);
		model.addAttribute("contentList", contentList);
		
		PageVO pageVO = boardService.getContentCnt(board_info_idx, page);
		model.addAttribute("pageVO", pageVO);
		
		model.addAttribute("page", page);
		
		return "board/main";
	}
	@GetMapping("read")
	public String read(@RequestParam("board_info_idx") int board_info_idx,
					   @RequestParam("content_idx") int content_idx,
					   @RequestParam("page") int page,
					   Model model) {
		
		System.out.println("[Controller] ===> BoardController.read");
		
		model.addAttribute("board_info_idx", board_info_idx);
		model.addAttribute("content_idx", content_idx);
		
		ContentVO readContentVO = boardService.getContentInfo(content_idx);
		
		model.addAttribute("readContentVO", readContentVO);
		model.addAttribute("loginUserVO", loginUserVO);
		model.addAttribute("page", page);
		
		return "board/read";
	}
	
	@GetMapping("/write")
	public String write(@ModelAttribute("writeContentVO") ContentVO contentVO,
			@RequestParam("board_info_idx") int board_info_idx) {
		System.out.println("[Controller] ===> BoardController.write");
		
		contentVO.setContent_board_idx(board_info_idx);
		
		return "board/write";
	}
	
	@PostMapping("/write_pro")
	public String write_pro(@Valid @ModelAttribute("writeContentVO") ContentVO contentVO, BindingResult result) {
		System.out.println("[Controller] ===> BoardController.write_pro");
		if(result.hasErrors()) {
			return "board/write";
		}
		
		boardService.addContentInfo(contentVO);
	
		return "/board/write_success";
	}
	
	@GetMapping("/not_write")
	public String not_write() {
		System.out.println("[Controller] ===> BoardController.not_write");
		
		return "board/not_write";
	}
	
	@GetMapping("/modify")
	public String modify(@RequestParam("board_info_idx") int board_info_idx,
			             @RequestParam("content_idx") int content_idx,
			             @RequestParam("page") int page,
			             @ModelAttribute("modifyContentVO") ContentVO contentVO,
			             Model model) {
		System.out.println("[Controller] ===> BoardController.modify");
		
		model.addAttribute("board_info_idx", board_info_idx);
		model.addAttribute("content_idx", content_idx);
		model.addAttribute("page", page);
		
		ContentVO tempContentVO =  boardService.getContentInfo(content_idx);
		contentVO.setContent_writer_name(tempContentVO.getContent_writer_name());
		contentVO.setContent_date(tempContentVO.getContent_date());
		contentVO.setContent_subject(tempContentVO.getContent_subject());
		contentVO.setContent_text(tempContentVO.getContent_text());
		contentVO.setContent_file(tempContentVO.getContent_file());
		contentVO.setContent_writer_idx(tempContentVO.getContent_writer_idx());
		contentVO.setContent_board_idx(tempContentVO.getContent_board_idx());
		contentVO.setContent_idx(tempContentVO.getContent_idx());
		
		return "board/modify";
	}
	
	@PostMapping("/modify_pro")
	public String modify_pro(@Valid @ModelAttribute("modifyContentVO") ContentVO contentVO,
							 BindingResult result,
							 @RequestParam("page") int page,
							 Model model) {
		System.out.println("[Controller] ===> BoardController.modify_pro");
		
		model.addAttribute("page", page);
		
		if(result.hasErrors()) {
			return "board/modify";
		}
		
		boardService.modifyContentInfo(contentVO);
		
		return "board/modify_success";
	}
	
	@GetMapping("/delete_pro")
	public String delete_pro(@RequestParam("board_info_idx") int board_info_idx,
							 @RequestParam("content_idx") int content_idx,
							 Model model) {
		System.out.println("[Controller] ===> BoardController.delete_pro");
		
		boardService.deleteContentInfo(content_idx);
		model.addAttribute("board_info_idx", board_info_idx);
		
		return "board/delete_success";
	}
}
