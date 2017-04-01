package com.menu.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.*;

import com.place.model.PlaceVO;

public class MenuDAO implements MenuDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
		private static final String INSERT_STMT = 
			"INSERT INTO MENU (menu_no,place_no,menu_name,menu_note,menu_pic,menu_price) VALUES (menu_seq.NEXTVAL, ?, ?, ?, ?, ? )";
		private static final String GET_ALL_STMT = 
			"SELECT menu_no,place_no,menu_name,menu_note,menu_pic,menu_price FROM MENU order by menu_no";
		private static final String GET_ONE_STMT = 
			"SELECT menu_no,place_no,menu_name,menu_note,menu_pic,menu_price FROM MENU where menu_no = ?";
		private static final String DELETE = 
			"DELETE FROM MENU where menu_no = ?";
		private static final String UPDATE = 
			"UPDATE MENU set place_no=?, menu_name=?, menu_note=?, menu_pic=?, menu_price=? where menu_no = ?";
		
		private static final String GET_APP_BYSUP=
				"select a.menu_no,a.place_no,a.menu_name,a.menu_note,a.menu_pic,a.menu_price,p.sup_no from menu a join place p on a.place_no=p.place_no join supplier s on p.sup_no=s.sup_no where p.sup_no=?";
		private static final String GET_MEN_BYPLACE=
				"select menu_no,place_no,menu_name,menu_note,menu_pic,menu_price from menu where place_no=?";
		
		
		private static final String GET_ONE_STMT_FORPIC = 
		"SELECT menu_no,place_no,menu_name,menu_note,menu_pic,menu_price FROM MENU where place_no = ?";
		private static final String GET_ALL_STMT_NOPIC = 
				"SELECT menu_no,place_no,menu_name,menu_note,menu_pic,menu_price FROM MENU order by menu_no";
		private static final String GET_ALL_STMT_PIC_BYPLACENO = 
				"SELECT menu_no,place_no,menu_name,menu_note,menu_pic,menu_price FROM MENU where place_no = ?";
		
	@Override
	public void insert(MenuVO MenuVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			byte[] menu_pic = MenuVO.getMenu_pic();
			int flen =menu_pic.length;
			InputStream fin = new ByteArrayInputStream(menu_pic);

			pstmt.setString(1, MenuVO.getPlace_no());
			pstmt.setString(2, MenuVO.getMenu_name());
			pstmt.setString(3, MenuVO.getMenu_note());
			pstmt.setBinaryStream(4, fin,flen);
			pstmt.setInt(5, MenuVO.getMenu_price());

			pstmt.executeUpdate();
            fin.close();
            
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}catch (IOException ioe){
			ioe.printStackTrace();
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

	@Override
	public void update(MenuVO MenuVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		InputStream fin = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			byte[] menu_pic = MenuVO.getMenu_pic();
			int flen =0;
			if(menu_pic!=null){
				flen =menu_pic.length;
				 fin = new ByteArrayInputStream(menu_pic);
			}
			
            
			pstmt.setString(1, MenuVO.getPlace_no());
			pstmt.setString(2, MenuVO.getMenu_name());
			pstmt.setString(3, MenuVO.getMenu_note());
			pstmt.setBinaryStream(4, fin,flen);
			pstmt.setInt(5, MenuVO.getMenu_price());
			pstmt.setString(6, MenuVO.getMenu_no());

//			System.out.println(	pstmt.executeUpdate());
		
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(fin!=null){
				try{
					fin.close();
				}catch(IOException ioe) {
					ioe.printStackTrace();
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

	@Override
	public void delete(String menu_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, menu_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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

	@Override
	public MenuVO findByPrimaryKey(String menu_no) {

		MenuVO MenuVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, menu_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// MenuVo 也稱為 Domain objects
				MenuVO = new MenuVO();
				MenuVO.setMenu_no(rs.getString("menu_no"));
				MenuVO.setPlace_no(rs.getString("place_no"));
				MenuVO.setMenu_name(rs.getString("menu_name"));
				MenuVO.setMenu_note(rs.getString("menu_note"));
				MenuVO.setMenu_pic(rs.getBytes("menu_pic"));
				MenuVO.setMenu_price(rs.getInt("menu_price"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return MenuVO;
	}

	@Override
	public List<MenuVO> getAll() {
		List<MenuVO> list = new ArrayList<MenuVO>();
		MenuVO MenuVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				MenuVO = new MenuVO();
				MenuVO.setMenu_no(rs.getString("menu_no"));
				MenuVO.setPlace_no(rs.getString("place_no"));
				MenuVO.setMenu_name(rs.getString("menu_name"));
				MenuVO.setMenu_note(rs.getString("menu_note"));
				MenuVO.setMenu_pic(rs.getBytes("menu_pic"));
				MenuVO.setMenu_price(rs.getInt("menu_price"));
				list.add(MenuVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return list;
	}//end of getAll()
	@Override
	public List<MenuVO> getMenusBySup(String sup_no) {
		List<MenuVO> list = new ArrayList<MenuVO>();
		MenuVO MenuVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_APP_BYSUP);
			
			pstmt.setString(1, sup_no);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				MenuVO = new MenuVO();
				MenuVO.setMenu_no(rs.getString("menu_no"));
				MenuVO.setPlace_no(rs.getString("place_no"));
				MenuVO.setMenu_name(rs.getString("menu_name"));
				MenuVO.setMenu_note(rs.getString("menu_note"));
				MenuVO.setMenu_pic(rs.getBytes("menu_pic"));
				MenuVO.setMenu_price(rs.getInt("menu_price"));
				list.add(MenuVO); // Store the row in the list
			}
		
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return list;
	}

	@Override
	public List<MenuVO> getMenusByPlace(String menu_no) {
		List<MenuVO> list = new ArrayList<MenuVO>();
		MenuVO MenuVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MEN_BYPLACE);
			
			pstmt.setString(1, menu_no);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				MenuVO = new MenuVO();
				MenuVO.setMenu_no(rs.getString("menu_no"));
				MenuVO.setPlace_no(rs.getString("place_no"));
				MenuVO.setMenu_name(rs.getString("menu_name"));
				MenuVO.setMenu_note(rs.getString("menu_note"));
				MenuVO.setMenu_pic(rs.getBytes("menu_pic"));
				MenuVO.setMenu_price(rs.getInt("menu_price"));
				list.add(MenuVO); // Store the row in the list
			}
		
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return list;
	}	
	
	
	
	
//***************************************** for App  ******************************************
	@Override
	public MenuVO findByPrimaryKeyForPicture(String menu_no) {
		
		MenuVO MenuVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_FORPIC);

			pstmt.setString(1, menu_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// MenuVo 也稱為 Domain objects
				MenuVO = new MenuVO();
//				MenuVO.setMenu_no(rs.getString("menu_no"));
//				MenuVO.setPlace_no(rs.getString("place_no"));
//				MenuVO.setMenu_name(rs.getString("menu_name"));
//				MenuVO.setMenu_note(rs.getString("menu_note"));
				MenuVO.setMenu_pic(rs.getBytes("menu_pic"));
//				MenuVO.setMenu_price(rs.getInt("menu_price"));
				
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return MenuVO;
	}  // end of findByPrimaryKeyForPicture 
	@Override
	public List<MenuVO> getMenuAllNoPicture() {
		List<MenuVO> list = new ArrayList<MenuVO>();
		MenuVO menuVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			 con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_NOPIC);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// PLACEVO 也稱為 Domain objects
				menuVO = new MenuVO();
				menuVO.setMenu_no(rs.getString("menu_no"));
				menuVO.setPlace_no(rs.getString("place_no"));
				menuVO.setMenu_name(rs.getString("menu_name"));
//				menuVO.setMenu_note(rs.getString("menu_note"));
//				menuVO.setMenu_pic(rs.getBytes("menu_pic"));
//				menuVO.setMenu_price(rs.getInt("menu_price"));
				list.add(menuVO); // Store the row in the list
			}

			// Handle any driver errors
		}
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return list;
	}//end of getAllNoPicture
	@Override
	public List<MenuVO> MenuGetAllImageNoTextByPlaceNo(String place_no) {
		List<MenuVO> list = new ArrayList<MenuVO>();
		MenuVO menuVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			 con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_PIC_BYPLACENO);
			
			pstmt.setString(1, place_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// PLACEVO 也稱為 Domain objects
				menuVO = new MenuVO();
//				menuVO.setMenu_no(rs.getString("menu_no"));
//				menuVO.setPlace_no(rs.getString("place_no"));
//				menuVO.setMenu_name(rs.getString("menu_name"));
//				menuVO.setMenu_note(rs.getString("menu_note"));
				menuVO.setMenu_pic(rs.getBytes("menu_pic"));
//				menuVO.setMenu_price(rs.getInt("menu_price"));
				list.add(menuVO); // Store the row in the list
			}

			// Handle any driver errors
		}
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return list;
	}//end of MenuGetAllImageNoText

}