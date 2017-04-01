package com.menu.model;

import java.sql.*;
import java.io.*;

public class MenuPhotoWrite {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String argv[]) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "aloha";
		String passwd = "pk7835085";

		try {
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement("update MENU set MENU_PIC =? where MENU_NO =?");
			PreparedStatement spstmt = con.prepareStatement("select MENU_NO from MENU");
			ResultSet rs = spstmt.executeQuery();

			File directory = new File("WebContent/front_end/photo/menu pic");
			
			File[] files = directory.listFiles();		//files ∞Oæ–≈È¶Ï∏m	
System.out.println(files);
			while (rs.next()) {
				for (File file : files) {
					String fileName = file.getName();
					int dotPos = fileName.indexOf('.');
					String fno = fileName.substring(0, dotPos);

					if (rs.getString("menu_no").equals(fno)) {						
						InputStream fin = new FileInputStream(file);						
						pstmt.setBinaryStream(1, fin);
						pstmt.setString(2, rs.getString("menu_no"));
						pstmt.executeUpdate();
						fin.close();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				
			}
		}
	}
}
