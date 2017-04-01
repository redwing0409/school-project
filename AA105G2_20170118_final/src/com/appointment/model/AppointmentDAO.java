package com.appointment.model;

import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AppointmentDAO implements AppointmentDAO_interface {

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
		"INSERT INTO APPOINTMENT (app_no,place_no,member_no,app_date,app_place_date,app_status) VALUES (APPOINTMENT_seq.NEXTVAL, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT app_no,place_no,member_no,to_char(app_date,'yyyy-mm-dd') app_date,to_char(app_place_date,'yyyy-mm-dd') app_place_date,app_status FROM APPOINTMENT order by app_no";
	private static final String GET_ONE_STMT = 
		"SELECT app_no,place_no,member_no,to_char(app_date,'yyyy-mm-dd') app_date,to_char(app_place_date,'yyyy-mm-dd') app_place_date,app_status FROM APPOINTMENT where app_no = ?";
	private static final String DELETE = 
		"DELETE FROM APPOINTMENT where app_no = ?";
	private static final String UPDATE = 
		"UPDATE APPOINTMENT set  place_no=?, member_no=?, app_date=?, app_place_date=?, app_status=?  where app_no = ?";
	private static final String GET_OWN_STMT = 
			"SELECT app_no,place_no,member_no,to_char(app_date,'yyyy-mm-dd') app_date,to_char(app_place_date,'yyyy-mm-dd') app_place_date,app_status FROM APPOINTMENT where member_no = ? order by app_no ";
	private static final String GET_APP_BYSUP=
			"select a.app_no,a.place_no,a.member_no,a.app_date,a.app_place_date,a.app_status,p.sup_no from appointment a join place p on a.place_no=p.place_no join supplier s on p.sup_no=s.sup_no where p.sup_no=?";
	private static final String GET_ALL_STMT_FROM_PLACENO = 
			"SELECT app_no,place_no,member_no,to_char(app_date,'yyyy-mm-dd') app_date,to_char(app_place_date,'yyyy-mm-dd') app_place_date,app_status FROM APPOINTMENT where place_no = ? and app_status= 1";
	private static final String INSERT_STMT_FROM_APP = 
			"INSERT INTO APPOINTMENT (app_no,place_no,member_no,app_date,app_place_date,app_status) VALUES (APPOINTMENT_seq.NEXTVAL, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT_FROM_MEMBERNO = 
			"SELECT app_no,place_no,member_no,to_char(app_date,'yyyy-mm-dd') app_date,to_char(app_place_date,'yyyy-mm-dd') app_place_date,app_status FROM APPOINTMENT where member_no = ? and app_status= 1";
		private static final String DELETE_BY_APPNO = 
			"UPDATE APPOINTMENT set app_status = 0 where app_no = ?";

	
	@Override
	public void insert(AppointmentVO appointmentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, appointmentVO.getPlace_no());
			pstmt.setString(2, appointmentVO.getMember_no());
			pstmt.setDate(3, appointmentVO.getApp_date());
			pstmt.setDate(4, appointmentVO.getApp_place_date());
			pstmt.setInt(5, appointmentVO.getApp_status());


			pstmt.executeUpdate();

			// Handle any SQL errors
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
	public void update(AppointmentVO appointmentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, appointmentVO.getPlace_no());
			pstmt.setString(2, appointmentVO.getMember_no());
			pstmt.setDate(3, appointmentVO.getApp_date());
			pstmt.setDate(4, appointmentVO.getApp_place_date());
			pstmt.setInt(5, appointmentVO.getApp_status());
            pstmt.setString(6, appointmentVO.getApp_no());
            
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
	public void delete(String app_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, app_no);

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
	public AppointmentVO findByPrimaryKey(String app_no) {

		AppointmentVO appointmentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, app_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// AppointmentVO 也稱為 Domain objects
				appointmentVO = new AppointmentVO();
				appointmentVO.setApp_no(rs.getString("app_no"));
				appointmentVO.setPlace_no(rs.getString("place_no"));
				appointmentVO.setMember_no(rs.getString("member_no"));
				appointmentVO.setApp_date(rs.getDate("app_date"));
				appointmentVO.setApp_place_date(rs.getDate("app_place_date"));
				appointmentVO.setApp_status(rs.getInt("app_status"));

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
		return appointmentVO;
	}

	@Override
	public List<AppointmentVO> getAll() {
		List<AppointmentVO> list = new ArrayList<AppointmentVO>();
		AppointmentVO appointmentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// AppointmentVO 也稱為 Domain objects
				appointmentVO = new AppointmentVO();
				appointmentVO.setApp_no(rs.getString("app_no"));
				appointmentVO.setPlace_no(rs.getString("place_no"));
				appointmentVO.setMember_no(rs.getString("member_no"));
				appointmentVO.setApp_date(rs.getDate("app_date"));
				appointmentVO.setApp_place_date(rs.getDate("app_place_date"));
				appointmentVO.setApp_status(rs.getInt("app_status"));
				list.add(appointmentVO); // Store the row in the list
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
	public List<AppointmentVO> getOwn(String member_no) {
		List<AppointmentVO> list = new ArrayList<AppointmentVO>();
		AppointmentVO appointmentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_OWN_STMT);
			
			pstmt.setString(1, member_no);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// AppointmentVO 也稱為 Domain objects
				appointmentVO = new AppointmentVO();
				appointmentVO.setApp_no(rs.getString("app_no"));
				appointmentVO.setPlace_no(rs.getString("place_no"));
				appointmentVO.setMember_no(rs.getString("member_no"));
				appointmentVO.setApp_date(rs.getDate("app_date"));
				appointmentVO.setApp_place_date(rs.getDate("app_place_date"));
				appointmentVO.setApp_status(rs.getInt("app_status"));
				list.add(appointmentVO); // Store the row in the list
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
	public List<AppointmentVO> getAppBySup(String sup_no) {
		List<AppointmentVO> list = new ArrayList<AppointmentVO>();
		AppointmentVO appointmentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_APP_BYSUP);
			
			pstmt.setString(1, sup_no);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// AppointmentVO 也稱為 Domain objects
				appointmentVO = new AppointmentVO();
				appointmentVO.setApp_no(rs.getString("app_no"));
				appointmentVO.setPlace_no(rs.getString("place_no"));
				appointmentVO.setMember_no(rs.getString("member_no"));
				appointmentVO.setApp_date(rs.getDate("app_date"));
				appointmentVO.setApp_place_date(rs.getDate("app_place_date"));
				appointmentVO.setApp_status(rs.getInt("app_status"));
				list.add(appointmentVO); // Store the row in the list
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
	public List<AppointmentVO> getAllFromPlaceNo(String place_no) {
		List<AppointmentVO> list = new ArrayList<AppointmentVO>();
		AppointmentVO appointmentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_FROM_PLACENO);
			pstmt.setString(1, place_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// AppointmentVO 也稱為 Domain objects
				appointmentVO = new AppointmentVO();
				appointmentVO.setApp_no(rs.getString("app_no"));
				appointmentVO.setPlace_no(rs.getString("place_no"));
				appointmentVO.setMember_no(rs.getString("member_no"));
				appointmentVO.setApp_date(rs.getDate("app_date"));
				appointmentVO.setApp_place_date(rs.getDate("app_place_date"));
				appointmentVO.setApp_status(rs.getInt("app_status"));
				list.add(appointmentVO); // Store the row in the list
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
	} //end of getAllFromPlaceNo()

	 
	 @Override
		public int insertAppointmentFromAPP(String place_no,String member_no,String date_current,String app_place_date,int app_status) {
		 int count=0;
			Connection con = null;
			PreparedStatement pstmt = null;
			java.sql.Date date_currentToDB = null;
			java.sql.Date app_place_dateToDB = null;
		
			try{
			   SimpleDateFormat sdf_date_current = new SimpleDateFormat("yyyy-MM-dd");
			   java.util.Date date_current_Convert = sdf_date_current.parse(date_current);
			   date_currentToDB = new Date(date_current_Convert.getTime()); 
			   
			   SimpleDateFormat sdf_app_place_date = new SimpleDateFormat("yyyy-MM-dd");
			   java.util.Date date_app_place_date_Convert = sdf_app_place_date.parse(app_place_date);
			   app_place_dateToDB = new Date(date_app_place_date_Convert.getTime());     //******************ERROR********
			}
			catch(IllegalArgumentException | ParseException ie){
				ie.printStackTrace(System.err);
			}
			System.out.println("insertAppointmentFromAPP:"+date_currentToDB+"-"+app_place_dateToDB);
		        
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT_FROM_APP);

				pstmt.setString(1, place_no);
				pstmt.setString(2, member_no);
				pstmt.setDate(3,date_currentToDB );
				pstmt.setDate(4,app_place_dateToDB);
				pstmt.setInt(5, app_status);

				count=pstmt.executeUpdate();

				// Handle any driver errors
			}  catch (SQLException se) {
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
		return count;
		}//end of  insertAppointmentFromAPP()
	 
	 @Override
		public List<AppointmentVO> getAllFromMemberNo(String member_no) {
			List<AppointmentVO> list = new ArrayList<AppointmentVO>();
			AppointmentVO appointmentVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT_FROM_MEMBERNO);
				pstmt.setString(1, member_no);
				
				rs = pstmt.executeQuery();
				

				while (rs.next()) {
					// AppointmentVO 也稱為 Domain objects
					appointmentVO = new AppointmentVO();
					appointmentVO.setApp_no(rs.getString("app_no"));
					appointmentVO.setPlace_no(rs.getString("place_no"));
					appointmentVO.setMember_no(rs.getString("member_no"));
					appointmentVO.setApp_date(rs.getDate("app_date"));
					appointmentVO.setApp_place_date(rs.getDate("app_place_date"));
//					appointmentVO.setApp_status(rs.getInt("app_status"));
					list.add(appointmentVO); // Store the row in the list
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
		} //end of getAllFromMemberNo()
	 @Override
		public int deleteByAppNO(String App_no) {
			int count = 0;
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				 con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE_BY_APPNO);

				pstmt.setString(1, App_no);

				count =pstmt.executeUpdate();

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
			return count;					
		}
	 
	 
	
}