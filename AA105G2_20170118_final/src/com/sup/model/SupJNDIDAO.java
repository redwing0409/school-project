package com.sup.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.place.model.PlaceVO;
import com.adv.model.*;

public class SupJNDIDAO implements SupDAO_interface {

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
			"INSERT INTO SUPPLIER (sup_no,sup_name,sup_acct,sup_pwd,sup_id,sup_con,sup_telcode,sup_tel,sup_tax,sup_adcode,sup_addr,sup_note,sup_type) VALUES (sup_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT sup_no,sup_name,sup_acct,sup_pwd,sup_id,sup_con,sup_telcode,sup_tel,sup_tax,sup_adcode,sup_addr,sup_note,sup_type FROM SUPPLIER order by sup_no";
	    private static final String GET_ALL_CHECK_STMT = 
			"SELECT sup_no,sup_name,sup_acct,sup_pwd,sup_id,sup_con,sup_telcode,sup_tel,sup_tax,sup_adcode,sup_addr,sup_note,sup_type FROM SUPPLIER where sup_note=1 order by sup_no";
		private static final String GET_ALL_UNCHECK_STMT = 
				"SELECT sup_no,sup_name,sup_acct,sup_pwd,sup_id,sup_con,sup_telcode,sup_tel,sup_tax,sup_adcode,sup_addr,sup_note,sup_type FROM SUPPLIER where sup_note=0 order by sup_no";
		private static final String GET_ONE_STMT = 
			"SELECT sup_no,sup_name,sup_acct,sup_pwd,sup_id,sup_con,sup_telcode,sup_tel,sup_tax,sup_adcode,sup_addr,sup_note,sup_type FROM SUPPLIER where sup_no = ?";
		private static final String DELETE = 
			"DELETE FROM SUPPLIER where sup_no = ?";
		private static final String UPDATE = 
			"UPDATE SUPPLIER set sup_name=?, sup_acct=?, sup_pwd=?, sup_id=?, sup_con=?,sup_telcode=?,sup_tel=?,sup_tax=?,sup_adcode=?,sup_addr=?,sup_note=?,sup_type=? where sup_no = ?";

		private static final String GET_Place_BySup_no_STMT = 
			"SELECT place_no,sup_no,place_type,place_name,place_area,place_adds,place_pic,place_note,place_status FROM place where sup_no = ? order by place_no";
		
		private static final String GET_Adv_BySup_no_STMT = 
			"SELECT adv_no,sup_no,adv_pic,adv_note FROM ADVERTISING where sup_no = ? order by adv_no";
			
		
	@Override
	public void insert(SupVO SupVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, SupVO.getSup_name());
			pstmt.setString(2, SupVO.getSup_acct());
			pstmt.setString(3, SupVO.getSup_pwd());
			pstmt.setInt(4, SupVO.getSup_id());
			pstmt.setString(5, SupVO.getSup_con());
			pstmt.setInt(6, SupVO.getSup_telcode());
			pstmt.setInt(7, SupVO.getSup_tel());
			pstmt.setInt(8, SupVO.getSup_tax());
			pstmt.setInt(9, SupVO.getSup_adcode());
			pstmt.setString(10, SupVO.getSup_addr());
			pstmt.setString(11, SupVO.getSup_note());
			pstmt.setString(12, SupVO.getSup_type());

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
	public void update(SupVO SupVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, SupVO.getSup_name());
			pstmt.setString(2, SupVO.getSup_acct());
			pstmt.setString(3, SupVO.getSup_pwd());
			pstmt.setInt(4, SupVO.getSup_id());
			pstmt.setString(5, SupVO.getSup_con());
			pstmt.setInt(6, SupVO.getSup_telcode());
			pstmt.setInt(7, SupVO.getSup_tel());
			pstmt.setInt(8, SupVO.getSup_tax());
			pstmt.setInt(9, SupVO.getSup_adcode());
			pstmt.setString(10, SupVO.getSup_addr());
			pstmt.setString(11, SupVO.getSup_note());
			pstmt.setString(12, SupVO.getSup_type());
			pstmt.setString(13, SupVO.getSup_no());

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
	public void delete(String supno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, supno);

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
	public SupVO findByPrimaryKey(String supno) {

		SupVO SupVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, supno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				SupVO = new SupVO();
				SupVO.setSup_no(rs.getString("sup_no"));
				SupVO.setSup_name(rs.getString("sup_name"));
				SupVO.setSup_acct(rs.getString("sup_acct"));
				SupVO.setSup_pwd(rs.getString("sup_pwd"));
				SupVO.setSup_id(rs.getInt("sup_id"));
				SupVO.setSup_con(rs.getString("sup_con"));
				SupVO.setSup_telcode(rs.getInt("sup_telcode"));
				SupVO.setSup_tel(rs.getInt("sup_tel"));
				SupVO.setSup_tax(rs.getInt("sup_tax"));
				SupVO.setSup_adcode(rs.getInt("sup_adcode"));
				SupVO.setSup_addr(rs.getString("sup_addr"));
				SupVO.setSup_note(rs.getString("sup_note"));
				SupVO.setSup_type(rs.getString("sup_type"));
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
		return SupVO;
	}

	@Override
	public List<SupVO> getAll() {
		List<SupVO> list = new ArrayList<SupVO>();
		SupVO SupVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// SupVO 也稱為 Domain objects
				SupVO = new SupVO();
				SupVO.setSup_no(rs.getString("sup_no"));
				SupVO.setSup_name(rs.getString("sup_name"));
				SupVO.setSup_acct(rs.getString("sup_acct"));
				SupVO.setSup_pwd(rs.getString("sup_pwd"));
				SupVO.setSup_id(rs.getInt("sup_id"));
				SupVO.setSup_con(rs.getString("sup_con"));
				SupVO.setSup_telcode(rs.getInt("sup_telcode"));
				SupVO.setSup_tel(rs.getInt("sup_tel"));
				SupVO.setSup_tax(rs.getInt("sup_tax"));
				SupVO.setSup_adcode(rs.getInt("sup_adcode"));
				SupVO.setSup_addr(rs.getString("sup_addr"));
				SupVO.setSup_note(rs.getString("sup_note"));
				SupVO.setSup_type(rs.getString("sup_type"));
				list.add(SupVO); // Store the row in the list
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
	public Set<PlaceVO> getPlaceBySup_no(String sup_no) {
		Set<PlaceVO> set = new LinkedHashSet<PlaceVO>();
		PlaceVO PlaceVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Place_BySup_no_STMT);
			pstmt.setString(1, sup_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				PlaceVO = new PlaceVO();
				PlaceVO.setPlace_no(rs.getString("place_no"));
				PlaceVO.setSup_no(rs.getString("sup_no"));
				PlaceVO.setPlace_type(rs.getInt("place_type"));
				PlaceVO.setPlace_name(rs.getString("place_name"));
				PlaceVO.setPlace_area(rs.getString("place_area"));
				PlaceVO.setPlace_adds(rs.getString("place_adds"));
				PlaceVO.setPlace_pic(rs.getBytes("place_pic"));
				PlaceVO.setPlace_note(rs.getString("place_note"));
				PlaceVO.setPlace_status(rs.getInt("place_status"));
				set.add(PlaceVO); // Store the row in the vector
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
	public List<SupVO> getAllCheck() {
		List<SupVO> list = new ArrayList<SupVO>();
		SupVO SupVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_CHECK_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// SupVO 也稱為 Domain objects
				SupVO = new SupVO();
				SupVO.setSup_no(rs.getString("sup_no"));
				SupVO.setSup_name(rs.getString("sup_name"));
				SupVO.setSup_acct(rs.getString("sup_acct"));
				SupVO.setSup_pwd(rs.getString("sup_pwd"));
				SupVO.setSup_id(rs.getInt("sup_id"));
				SupVO.setSup_con(rs.getString("sup_con"));
				SupVO.setSup_telcode(rs.getInt("sup_telcode"));
				SupVO.setSup_tel(rs.getInt("sup_tel"));
				SupVO.setSup_tax(rs.getInt("sup_tax"));
				SupVO.setSup_adcode(rs.getInt("sup_adcode"));
				SupVO.setSup_addr(rs.getString("sup_addr"));
				SupVO.setSup_note(rs.getString("sup_note"));
				SupVO.setSup_type(rs.getString("sup_type"));
				list.add(SupVO); // Store the row in the list
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
	public List<SupVO> getAllUnCheck() {
		List<SupVO> list = new ArrayList<SupVO>();
		SupVO SupVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_UNCHECK_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// SupVO 也稱為 Domain objects
				SupVO = new SupVO();
				SupVO.setSup_no(rs.getString("sup_no"));
				SupVO.setSup_name(rs.getString("sup_name"));
				SupVO.setSup_acct(rs.getString("sup_acct"));
				SupVO.setSup_pwd(rs.getString("sup_pwd"));
				SupVO.setSup_id(rs.getInt("sup_id"));
				SupVO.setSup_con(rs.getString("sup_con"));
				SupVO.setSup_telcode(rs.getInt("sup_telcode"));
				SupVO.setSup_tel(rs.getInt("sup_tel"));
				SupVO.setSup_tax(rs.getInt("sup_tax"));
				SupVO.setSup_adcode(rs.getInt("sup_adcode"));
				SupVO.setSup_addr(rs.getString("sup_addr"));
				SupVO.setSup_note(rs.getString("sup_note"));
				SupVO.setSup_type(rs.getString("sup_type"));
				list.add(SupVO); // Store the row in the list
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
	public Set<AdvVO> getAdvBySup_no(String sup_no) {
		Set<AdvVO> set = new LinkedHashSet<AdvVO>();
		AdvVO AdvVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Adv_BySup_no_STMT);
			pstmt.setString(1, sup_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				AdvVO = new AdvVO();
				AdvVO.setAdv_no(rs.getString("adv_no"));
				AdvVO.setSup_no(rs.getString("sup_no"));
				AdvVO.setAdv_pic(rs.getBytes("adv_pic"));
				AdvVO.setAdv_note(rs.getString("adv_note"));
				set.add(AdvVO); // Store the row in the vector
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
		
}
