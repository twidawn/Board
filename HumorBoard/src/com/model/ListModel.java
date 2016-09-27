package com.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.*;

public class ListModel implements Model{

	@Override
	public String handlerRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		String pageStr = req.getParameter("page");
		String categoryStr = req.getParameter("category");
		if(pageStr==null){
			pageStr = "1";
		}
		
		int page = Integer.parseInt(pageStr);
		
		if(categoryStr==null){
			//humorBoard(page , req);
		}else{
			int category = Integer.parseInt(categoryStr);
			req.setAttribute("category", category);
			//categoryBoard(page, req, category);
		}
		
		String msg = "관리자에 의해 삭제된 게시물";
		req.setAttribute("msg", msg);
		req.setAttribute("curpage", page);
		
		String[] categoryList = {"전체","유머","스포츠","연예","게임","서브컬쳐","동물"};
		req.setAttribute("categoryList", categoryList);
		
		return "./board/list.jsp";
	}
	
	
	public void humorBoard(int page, HttpServletRequest req){
		BoardDAO dao = new BoardDAO();
		List<BoardDTO> list = dao.allBoardData(page);
		
		req.setAttribute("list", list);
		
		int total = dao.totalPage();
		req.setAttribute("total", total);
		
		blockSetting(total, page, req);
	}
	
	public void categoryBoard(int page, HttpServletRequest req, int category){
		BoardDAO dao = new BoardDAO();
		List<BoardDTO> list = dao.categoryBoardData(page, category);
		
		req.setAttribute("list", list);
		
		int total = dao.categoryTotalPage(category);
		req.setAttribute("total", total);
		
		
		blockSetting(total, page, req);
	}
	
	public void blockSetting(int totalPage, int page, HttpServletRequest req){
		int block=10;
		int formPage = ((page-1)/block*block)+1;
		int toPage = ((page-1)/block*block)+block;
		
		if(toPage> totalPage) toPage = totalPage;
		
		req.setAttribute("formpage", formPage);
		req.setAttribute("topage", toPage);
		req.setAttribute("block", block);
	}

}
