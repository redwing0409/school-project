package com.menu.controller;

import java.io.*;
import java.sql.*;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.*;


//@WebServlet("/menu/ShowMenu_Pic.do")
public class ShowMenu_Pic_V1 extends HttpServlet {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			res.setContentType("image/gif");
			String menu_no = req.getParameter("menu_no");
			OutputStream out = res.getOutputStream();
			
			con = ds.getConnection();
			pstmt = con.prepareStatement("SELECT memu_pic FROM MENU WHERE menu_no = " + menu_no);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("menu_pic"));
				byte[] buffer = new byte[rs.getBytes("menu_pic").length];
				int len = 0;
				while ((len = in.read(buffer)) != -1) {
					 out.write(buffer, 0, len);
				}
				in.close();
			}
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

}
