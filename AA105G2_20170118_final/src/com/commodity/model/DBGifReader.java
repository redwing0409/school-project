package com.commodity.model;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
public class DBGifReader extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/jpg");
		ServletOutputStream out = res.getOutputStream();
		req.setCharacterEncoding("BIG5");
		BufferedInputStream in = null;
		try {
			Statement stmt = con.createStatement();
			String name2 = req.getParameter("name");
			String name = new String(name2.getBytes("ISO-8859-1"),"BIG5");
			ResultSet rs = stmt.executeQuery(
				"SELECT com_pic FROM commodity WHERE com_no = '" + name+"'");
			
			if (rs.next()) {
				 in = new BufferedInputStream(rs.getBinaryStream("com_pic"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			in.close();
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println(e + "  DBGif");
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			con = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
