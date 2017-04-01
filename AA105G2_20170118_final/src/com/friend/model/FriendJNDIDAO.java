package com.friend.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class FriendJNDIDAO implements FriendDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO FRIEND(member_no,friend_no) VALUES (?,?)";
	private static final String GET_ALL_STMT = "SELECT member_no,friend_no FROM friend order by member_no";
	private static final String GET_ONE_STMT = "SELECT member_no,friend_no FROM friend where member_no = ?";
	private static final String DELETE = "DELETE FROM friend where member_no = ? AND friend_no = ?";
	private static final String UPDATE = "UPDATE friend set friend_no=? where member_no=?";
	

	@Override
	public int insert(FriendVO friendVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, friendVO.getMember_no());
			pstmt.setString(2, friendVO.getFriend_no());

			updateCount = pstmt.executeUpdate();
			
			//因為互相都是朋友
			pstmt.setString(2, friendVO.getMember_no());
			pstmt.setString(1, friendVO.getFriend_no());
			
			updateCount = pstmt.executeUpdate()+updateCount;

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return updateCount;
	}

//	@Override
//	public int update(FriendVO friendVO) {
//	}

	@Override
	public int delete(String member_no,String friend_no) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, member_no);
			pstmt.setString(2, friend_no);
			
			updateCount = pstmt.executeUpdate();
			
			//因為彼此為好友
			pstmt.setString(2, member_no);
			pstmt.setString(1, friend_no);

			updateCount = pstmt.executeUpdate()+updateCount;
			

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
		return updateCount;
	}

	@Override
	public List<FriendVO> findByPrimaryKey(String member_no) {
		List<FriendVO> list = new ArrayList<FriendVO>();
		
		FriendVO friendVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, member_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// FriendVO 也稱為 Domain objects
				friendVO = new FriendVO();
				friendVO.setMember_no(rs.getString("member_no"));
				friendVO.setFriend_no(rs.getString("friend_no"));
				list.add(friendVO); // Store the row in the vector
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
	public List<FriendVO> getAll() {
		List<FriendVO> list = new ArrayList<FriendVO>();
		FriendVO friendVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// FriendVO 也稱為 Domain objects
				friendVO = new FriendVO();
				friendVO.setMember_no(rs.getString("member_no"));
				friendVO.setFriend_no(rs.getString("friend_no"));
				list.add(friendVO); // Store the row in the vector
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

}
