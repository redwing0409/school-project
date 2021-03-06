package com.adv.model;


import java.util.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdvJNDIDAO implements AdvDAO_interface {

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
			"INSERT INTO ADVERTISING (adv_no,sup_no,adv_pic,adv_note) VALUES (advertising_seq.NEXTVAL, ?, ?, ? )";
	private static final String GET_ALL_STMT = 
			"SELECT adv_no,sup_no,adv_pic,adv_note FROM ADVERTISING order by adv_no";
	private static final String GET_ONE_STMT = 
			"SELECT adv_no,sup_no,adv_pic,adv_note FROM ADVERTISING where adv_no = ?";
	private static final String DELETE = 
			"DELETE FROM ADVERTISING where adv_no = ?";
	private static final String UPDATE = 
			"UPDATE ADVERTISING set sup_no=?, adv_pic=?, adv_note=? where adv_no = ?";
	private static final String GET_OWN_STMT = 
			"SELECT adv_no,sup_no,adv_pic,adv_note FROM ADVERTISING where sup_no = ?";
	
	@Override
	public void insert(AdvVO AdvVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			byte[] adv_pic = AdvVO.getAdv_pic();
			int flen = adv_pic.length;
			InputStream fin = new ByteArrayInputStream(adv_pic);

			    pstmt.setString(1, AdvVO.getSup_no());
				pstmt.setBinaryStream(2, fin, flen);
				pstmt.setString(3, AdvVO.getAdv_note());
				
				pstmt.executeUpdate();
                fin.close();
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (IOException ioe) {
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
	public void update(AdvVO AdvVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			byte[] adv_pic = AdvVO.getAdv_pic();
			int flen = adv_pic.length;
			InputStream fin = new ByteArrayInputStream(adv_pic);

			pstmt.setString(1, AdvVO.getSup_no());
			pstmt.setBinaryStream(2, fin, flen);
			pstmt.setString(3, AdvVO.getAdv_note());
			pstmt.setString(4, AdvVO.getAdv_no());
			
			pstmt.executeUpdate();
            fin.close();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}catch (IOException ioe) {
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
	public void delete(String adv_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, adv_no);

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
	public AdvVO findByPrimaryKey(String adv_no) {

		AdvVO AdvVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, adv_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				AdvVO = new AdvVO();
				AdvVO.setAdv_no(rs.getString("adv_no"));
				AdvVO.setSup_no(rs.getString("sup_no"));
				AdvVO.setAdv_pic(rs.getBytes("adv_pic"));
				AdvVO.setAdv_note(rs.getString("adv_note"));
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
		return AdvVO;
	}

	@Override
	public List<AdvVO> getAll() {
		List<AdvVO> list = new ArrayList<AdvVO>();
		AdvVO AdvVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				AdvVO = new AdvVO();
				AdvVO.setAdv_no(rs.getString("adv_no"));
				AdvVO.setSup_no(rs.getString("sup_no"));
				AdvVO.setAdv_pic(rs.getBytes("adv_pic"));
				AdvVO.setAdv_note(rs.getString("adv_note"));
				list.add(AdvVO); // Store the row in the list
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

	public List<AdvVO> findBySup(String sup_no) {
		List<AdvVO> list = new ArrayList<AdvVO>();
		AdvVO AdvVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_OWN_STMT);
			
			pstmt.setString(1, sup_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				AdvVO = new AdvVO();
				AdvVO.setAdv_no(rs.getString("adv_no"));
				AdvVO.setSup_no(rs.getString("sup_no"));
				AdvVO.setAdv_pic(rs.getBytes("adv_pic"));
				AdvVO.setAdv_note(rs.getString("adv_note"));
				list.add(AdvVO); // Store the row in the list
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
	public List<AdvVO> getAllForTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AdvVO> getAllForPic() {
		// TODO Auto-generated method stub
		return null;
	}
}