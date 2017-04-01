package com.place.model;

import java.util.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.*;
import java.io.IOException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.appointment.model.*;
import com.menu.model.*;

public class PlaceDAO implements PlaceDAO_interface {

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
		"INSERT INTO PLACE (place_no,sup_no,place_type,place_name,place_area,place_adds,place_pic,place_note,place_status) VALUES (PLACE_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT place_no,sup_no,place_type,place_name,place_area,place_adds,place_pic,place_note,place_status FROM PLACE WHERE place_status=1 order by place_no";
	private static final String GET_ONE_STMT = 
		"SELECT place_no,sup_no,place_type,place_name,place_area,place_adds,place_pic,place_note,place_status FROM PLACE where place_no = ?";
	private static final String GET_ONE_TYPE_STMT = 
			"SELECT place_no,sup_no,place_type,place_name,place_area,place_adds,place_pic,place_note,place_status FROM PLACE where place_type = ?";

	
	private static final String GET_ONE_STMT_TYPE = 
			"SELECT place_no,sup_no,place_type,place_name,place_area,place_adds,place_pic,place_note,place_status FROM PLACE where place_type = ?";
	private static final String GET_ONE_AREA_STMT = 
			"SELECT place_no,sup_no,place_type,place_name,place_area,place_adds,place_pic,place_note,place_status FROM PLACE where place_area = ?";
	private static final String DELETE = 
		"DELETE FROM PLACE where place_no = ?";
	private static final String UPDATE = 
		"UPDATE PLACE set  sup_no=?, place_type=?, place_name=?, place_area=?, place_adds=?, place_pic=?, place_note=?, place_status=? where place_no = ?";
    
	private static final String GET_Menus_ByPlace_no_STMT = 
		"SELECT menu_no,place_no,menu_name,menu_note,menu_pic,menu_price FROM menu where place_no = ? order by menu_no";
	
	private static final String GET_Appointment_ByPlace_no_STMT = 
			"SELECT app_no,place_no,member_no,app_date,app_place_date,app_status FROM appointment where place_no = ? order by app_no";
	

	
	private static final String GET_ALL_STMT_NOPIC = 
			"SELECT place_no,sup_no,place_type,place_name,place_area,place_adds,place_pic,place_note,place_status FROM PLACE where place_status = 1 order by place_no";
	private static final String GET_ALL_STMT_NOPIC_PLACEAREA = 
			"SELECT place_no,sup_no,place_type,place_name,place_area,place_adds,place_pic,place_note,place_status FROM PLACE where place_status = 1 and place_area = ? order by place_no";
	private static final String GET_ALL_STMT_NOPIC_PLACETYPE = 
			"SELECT place_no,sup_no,place_type,place_name,place_area,place_adds,place_pic,place_note,place_status FROM PLACE where place_status = 1 and place_type = ? order by place_no";
	private static final String GET_ALL_STMT_NOPIC_PLACEAREATYPE = 
			"SELECT place_no,sup_no,place_type,place_name,place_area,place_adds,place_pic,place_note,place_status FROM PLACE where place_status = 1 and place_area = ? and place_type = ? order by place_no";

	@Override
	public void insert(PlaceVO placeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			

			
			pstmt.setString(1, placeVO.getSup_no());
			pstmt.setInt(2, placeVO.getPlace_type());
			pstmt.setString(3, placeVO.getPlace_name());
			pstmt.setString(4, placeVO.getPlace_area());
			pstmt.setString(5, placeVO.getPlace_adds());
			pstmt.setBytes(6, placeVO.getPlace_pic());
			pstmt.setString(7, placeVO.getPlace_note());
			pstmt.setInt(8, placeVO.getPlace_status());


			pstmt.executeUpdate();

            
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}  finally {
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
	public void update(PlaceVO placeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		InputStream fin = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			byte[] place_pic = placeVO.getPlace_pic();
			int flen = 0;
			
			if(place_pic != null){
				flen = place_pic.length;
				 fin = new ByteArrayInputStream(place_pic);
			}
		

			pstmt.setString(1, placeVO.getSup_no());
			pstmt.setInt(2, placeVO.getPlace_type());
			pstmt.setString(3, placeVO.getPlace_name());
			pstmt.setString(4, placeVO.getPlace_area());
			pstmt.setString(5, placeVO.getPlace_adds());
			pstmt.setBinaryStream(6, fin,flen);
			pstmt.setString(7, placeVO.getPlace_note());
			pstmt.setInt(8, placeVO.getPlace_status());
			pstmt.setString(9, placeVO.getPlace_no());
			
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
	public void delete(String place_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, place_no);

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
	public PlaceVO findByPrimaryKey(String place_no) {

		PlaceVO placeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, place_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// PLACEVO 也稱為 Domain objects
				placeVO = new PlaceVO();
				placeVO.setPlace_no(rs.getString("place_no"));
				placeVO.setSup_no(rs.getString("sup_no"));
				placeVO.setPlace_type(rs.getInt("place_type"));
				placeVO.setPlace_name(rs.getString("place_name"));
				placeVO.setPlace_area(rs.getString("place_area"));
				placeVO.setPlace_adds(rs.getString("place_adds"));
				placeVO.setPlace_pic(rs.getBytes("place_pic"));
				placeVO.setPlace_note(rs.getString("place_note"));
				placeVO.setPlace_status(rs.getInt("place_status"));
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
		return placeVO;
	}
	
	@Override
	public List<PlaceVO> findByPrimaryKeyType(Integer place_type) {
		List<PlaceVO> list = new ArrayList<PlaceVO>();
		PlaceVO placeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_TYPE);
			
			pstmt.setInt(1, place_type);
			
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				placeVO = new PlaceVO();
				placeVO.setPlace_no(rs.getString("place_no"));
				placeVO.setSup_no(rs.getString("sup_no"));
				placeVO.setPlace_type(rs.getInt("place_type"));
				placeVO.setPlace_name(rs.getString("place_name"));
				placeVO.setPlace_area(rs.getString("place_area"));
				placeVO.setPlace_adds(rs.getString("place_adds"));
				placeVO.setPlace_pic(rs.getBytes("place_pic"));
				placeVO.setPlace_note(rs.getString("place_note"));
				placeVO.setPlace_status(rs.getInt("place_status"));
				list.add(placeVO); // Store the row in the list
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
	}
	
	@Override
	public List<PlaceVO> findByPrimaryKeyArea(String place_area) {
		List<PlaceVO> list = new ArrayList<PlaceVO>();
		PlaceVO placeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_AREA_STMT);
			
			pstmt.setString(1, place_area);
			
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				placeVO = new PlaceVO();
				placeVO.setPlace_no(rs.getString("place_no"));
				placeVO.setSup_no(rs.getString("sup_no"));
				placeVO.setPlace_type(rs.getInt("place_type"));
				placeVO.setPlace_name(rs.getString("place_name"));
				placeVO.setPlace_area(rs.getString("place_area"));
				placeVO.setPlace_adds(rs.getString("place_adds"));
				placeVO.setPlace_pic(rs.getBytes("place_pic"));
				placeVO.setPlace_note(rs.getString("place_note"));
				placeVO.setPlace_status(rs.getInt("place_status"));
				list.add(placeVO); // Store the row in the list
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
	}

	@Override
	public List<PlaceVO> getAll() {
		List<PlaceVO> list = new ArrayList<PlaceVO>();
		PlaceVO placeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				placeVO = new PlaceVO();
				placeVO.setPlace_no(rs.getString("place_no"));
				placeVO.setSup_no(rs.getString("sup_no"));
				placeVO.setPlace_type(rs.getInt("place_type"));
				placeVO.setPlace_name(rs.getString("place_name"));
				placeVO.setPlace_area(rs.getString("place_area"));
				placeVO.setPlace_adds(rs.getString("place_adds"));
				placeVO.setPlace_pic(rs.getBytes("place_pic"));
				placeVO.setPlace_note(rs.getString("place_note"));
				placeVO.setPlace_status(rs.getInt("place_status"));
				list.add(placeVO); // Store the row in the list
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
	}

	@Override
	public Set<AppointmentVO> getAppointmentByPlace_no(String place_no) {
		Set<AppointmentVO> set = new LinkedHashSet<AppointmentVO>();
		AppointmentVO appointmentVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Appointment_ByPlace_no_STMT);
			pstmt.setString(1, place_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				appointmentVO = new AppointmentVO();
				appointmentVO.setApp_no(rs.getString("app_no"));
				appointmentVO.setPlace_no(rs.getString("place_no"));
				appointmentVO.setMember_no(rs.getString("member_no"));
				appointmentVO.setApp_date(rs.getDate("app_date"));
				appointmentVO.setApp_place_date(rs.getDate("app_place_date"));
				appointmentVO.setApp_status(rs.getInt("app_status"));
				set.add(appointmentVO); // Store the row in the vector
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return set;
	}

	@Override
	public Set<MenuVO> getMenusByPlace_no(String place_no) {
		Set<MenuVO> set = new LinkedHashSet<MenuVO>();
		MenuVO MenuVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Menus_ByPlace_no_STMT);
			pstmt.setString(1, place_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				MenuVO = new MenuVO();
				MenuVO.setMenu_no(rs.getString("menu_no"));
				MenuVO.setPlace_no(rs.getString("place_no"));
				MenuVO.setMenu_name(rs.getString("menu_name"));
				MenuVO.setMenu_note(rs.getString("menu_note"));
				MenuVO.setMenu_pic(rs.getBytes("menu_pic"));
				MenuVO.setMenu_price(rs.getInt("menu_price"));
				set.add(MenuVO); // Store the row in the vector
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return set;
	}
	
	//****************************************  for APP  ********************************************** 
		@Override
		public List<PlaceVO> getAllNoPicture() {
			List<PlaceVO> list = new ArrayList<PlaceVO>();
			PlaceVO placeVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				 con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT_NOPIC);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// PLACEVO 也稱為 Domain objects
					placeVO = new PlaceVO();
					placeVO.setPlace_no(rs.getString("place_no"));
					placeVO.setSup_no(rs.getString("sup_no"));
					placeVO.setPlace_type(rs.getInt("place_type"));
					placeVO.setPlace_name(rs.getString("place_name"));
					placeVO.setPlace_area(rs.getString("place_area"));
					placeVO.setPlace_adds(rs.getString("place_adds"));
//					placeVO.setPlace_pic(rs.getBytes("place_pic"));
					placeVO.setPlace_note(rs.getString("place_note"));
//					placeVO.setPlace_status(rs.getInt("place_status"));
					list.add(placeVO); // Store the row in the list
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
		public List<PlaceVO> getAllNoPicturePlaceArea(String PlaceArea) {
			List<PlaceVO> list = new ArrayList<PlaceVO>();
			PlaceVO placeVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				 con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT_NOPIC_PLACEAREA);
				
				pstmt.setString(1, PlaceArea);
				rs = pstmt.executeQuery();


				while (rs.next()) {
					// PLACEVO 也稱為 Domain objects
					placeVO = new PlaceVO();
					placeVO.setPlace_no(rs.getString("place_no"));
					placeVO.setSup_no(rs.getString("sup_no"));
					placeVO.setPlace_type(rs.getInt("place_type"));
					placeVO.setPlace_name(rs.getString("place_name"));
					placeVO.setPlace_area(rs.getString("place_area"));
//					placeVO.setPlace_adds(rs.getString("place_adds"));
//					placeVO.setPlace_pic(rs.getBytes("place_pic"));
					placeVO.setPlace_note(rs.getString("place_note"));
//					placeVO.setPlace_status(rs.getInt("place_status"));
					list.add(placeVO); // Store the row in the list
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
		}//end of getAllNoPicturePlaceArea
		@Override
		public List<PlaceVO> getAllNoPicturePlaceType(String PlaceType) {
			List<PlaceVO> list = new ArrayList<PlaceVO>();
			PlaceVO placeVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				 con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT_NOPIC_PLACETYPE);
				
				pstmt.setString(1, PlaceType);
				rs = pstmt.executeQuery();


				while (rs.next()) {
					// PLACEVO 也稱為 Domain objects
					placeVO = new PlaceVO();
					placeVO.setPlace_no(rs.getString("place_no"));
					placeVO.setSup_no(rs.getString("sup_no"));
					placeVO.setPlace_type(rs.getInt("place_type"));
					placeVO.setPlace_name(rs.getString("place_name"));
					placeVO.setPlace_area(rs.getString("place_area"));
//					placeVO.setPlace_adds(rs.getString("place_adds"));
//					placeVO.setPlace_pic(rs.getBytes("place_pic"));
					placeVO.setPlace_note(rs.getString("place_note"));
//					placeVO.setPlace_status(rs.getInt("place_status"));
					list.add(placeVO); // Store the row in the list
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
		}//end of getAllNoPicturePlaceType
		
		@Override
		public List<PlaceVO> getAllNoPicturePlaceAreaType(String PlaceArea,String PlaceType) {
			List<PlaceVO> list = new ArrayList<PlaceVO>();
			PlaceVO placeVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				 con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT_NOPIC_PLACEAREATYPE);
				pstmt.setString(1, PlaceArea);
				pstmt.setString(2, PlaceType);
				rs = pstmt.executeQuery();


				while (rs.next()) {
					// PLACEVO 也稱為 Domain objects
					placeVO = new PlaceVO();
					placeVO.setPlace_no(rs.getString("place_no"));
					placeVO.setSup_no(rs.getString("sup_no"));
					placeVO.setPlace_type(rs.getInt("place_type"));
					placeVO.setPlace_name(rs.getString("place_name"));
					placeVO.setPlace_area(rs.getString("place_area"));
					placeVO.setPlace_adds(rs.getString("place_adds"));
//					placeVO.setPlace_pic(rs.getBytes("place_pic"));
					placeVO.setPlace_note(rs.getString("place_note"));
//					placeVO.setPlace_status(rs.getInt("place_status"));
					list.add(placeVO); // Store the row in the list
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
		}//end of getAllNoPicturePlaceType
	
}