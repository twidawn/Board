package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.model.*;

public class DispatcherServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map clsMap = new HashMap<>();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			String path =config.getInitParameter("contextConfigLocation");
			System.out.println(path);
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			Document doc = db.parse(new File(path));
			Element root = doc.getDocumentElement();
			NodeList list = root.getElementsByTagName("bean");
			
			for(int i = 0; i < list.getLength();i++){
				Element elem = (Element) list.item(i);
				String id = elem.getAttribute("id");
				String cls = elem.getAttribute("class");
				System.out.println(id +", " +cls);
				Class clsName = Class.forName(cls);
				//클래스 정보를 읽어온다.
				//=>메소드,멤버변수,생성자 제어가 가능.
				//메모리 할당
				
				Object obj=clsName.newInstance();
				System.out.println(obj);
				
				clsMap.put(id, obj);
			}			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String cmd = req.getRequestURI();
			System.out.println(cmd);
			cmd = cmd.substring(req.getContextPath().length()+1, cmd.lastIndexOf("."));
			System.out.println(cmd);
			Model md = (Model)clsMap.get(cmd);
			
			String js = md.handlerRequest(req, resp);
			String temp = js.substring(js.lastIndexOf(".")+1);
			System.out.println(temp);
			
			
			if(temp.equals("do")){
				resp.sendRedirect(js);
			}else{
				RequestDispatcher rd = req.getRequestDispatcher(js);
				rd.forward(req, resp);
			}			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	

}
