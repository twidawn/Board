package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	private Connection conn;
	private PreparedStatement ps;

	public void disConncection() {
		try {
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void getConnection() {
		try {
			//저장위츠를 찾아온다(JNDI)
			Context init = new InitialContext();
			Context c = (Context) init.lookup("java://comp/env");
			DataSource ds = (DataSource) c.lookup("jdbc/oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/*
	 * no int
	 * name string
	 * category int
	 * subject string
	 * content string
	 * pwd string
	 * regdate date
	 * hit int
	 * filename name
	 * filesize int
	 * 
	 */	
	public List<BoardDTO> allBoardData(int page){
		List<BoardDTO> list = new ArrayList<>();
		
		try {
			getConnection();

			//String sql = "SELECT no,category,subject,name,regdate,hit FROM humorboard";
			
			int rowSize =19;
			int start = (rowSize * page) - (rowSize-1);
			int end = (rowSize * page);
			String sql="SELECT no,category,subject,name,regdate,hit, num "
					+ "FROM (SELECT no,category,subject,name,regdate,hit, rownum as num "
					+ "FROM (SELECT no,category,subject,name,regdate,hit FROM humorBoard"
					+ " ORDER BY no DESC)) WHERE num BETWEEN " + start + " AND " + end;
			
			
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				BoardDTO dto = new BoardDTO();
				dto.setNo(rs.getInt(1));
				dto.setCategory(rs.getInt(2));
				System.out.println(rs.getInt(2));
				dto.setSubject(rs.getString(3));
				dto.setName(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
				dto.setHit(rs.getInt(6));
				list.add(dto);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConncection();
		}
		
		return list;
	}
	
	public int totalPage(){
		int abc = 0;
		try {
			getConnection();

			String sql="SELECT CEIL(COUNT(*)/19) FROM humorboard";
			
			
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			abc = rs.getInt(1);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConncection();
		}
		
		return abc;
	}
	
	public List<BoardDTO> categoryBoardData(int page, int category){
		List<BoardDTO> list = new ArrayList<>();
		
		try {
			getConnection();
			
			int rowSize =19;
			int i = 0;
			int j = 0;
			int pagecnt = (page * rowSize) - (rowSize);

			String sql = "SELECT no,category,subject,name,regdate,hit FROM humorboard WHERE category=" + category +" ORDER BY no DESC";
			
			/*int rowSize =19;
			int start = (rowSize * page) - (rowSize-1);
			int end = (rowSize * page);
			String sql="SELECT no,category,subject,name,regdate,hit, num "
					+ "FROM (SELECT no,category,subject,name,regdate,hit, rownum as num "
					+ "FROM (SELECT no,category,subject,name,regdate,hit FROM humorBoard"
					+ " ORDER BY no DESC)) WHERE category=" + category + " AND num BETWEEN " + start + " AND " + end;
			*/
			
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				if (i < rowSize && j >= pagecnt) {
					BoardDTO dto = new BoardDTO();
					dto.setNo(rs.getInt(1));
					dto.setCategory(rs.getInt(2));
					dto.setSubject(rs.getString(3));
					dto.setName(rs.getString(4));
					dto.setRegdate(rs.getDate(5));
					dto.setHit(rs.getInt(6));
					list.add(dto);
					i++;
					System.out.println(i);
				}
				j++;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConncection();
		}
		
		return list;
	}
	
	public int categoryTotalPage(int category){
		int abc = 0;
		try {
			getConnection();

			String sql="SELECT CEIL(COUNT(*)/19) FROM humorboard WHERE category=" + category;
			
			
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			abc = rs.getInt(1);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConncection();
		}
		
		return abc;
	}
}
