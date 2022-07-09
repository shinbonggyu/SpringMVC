package com.perceptron.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.perceptron.service.MainService;
import com.perceptron.service.TopMenuService;
import com.perceptron.vo.BoardInfoVO;
import com.perceptron.vo.ContentVO;

@Controller
public class MainController {
	@Autowired
	private MainService mainService;
	
	@Autowired
	private TopMenuService topMenuService;	
	
	@GetMapping("/main")
	public String main(Model model) {
		System.out.println("[Controller] ===> MainController.main");
		
		ArrayList<List<ContentVO>> arraylist = new ArrayList<List<ContentVO>>();
		
		List<BoardInfoVO> board_list=topMenuService.getTopMenuList();
		model.addAttribute("board_list", board_list);
		
		for(int i=1;i<=board_list.size();i++) {
			List<ContentVO> list1 = mainService.getMainList(i);
			arraylist.add(list1);
		}
		
		model.addAttribute("arraylist", arraylist);
		return "main";
	}
}
