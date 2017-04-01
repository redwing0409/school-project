package com.groups_list.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class Groups_ListDAO implements Groups_ListDAO_interface{

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
		"INSERT INTO GROUPS_LIST (groups_no,member_no) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT groups_no,member_no FROM GROUPS_LIST order by groups_no";
	private static final String GET_GROUPS_STMT = 
		"SELECT groups_no,member_no FROM GROUPS_LIST where groups_no = ?";
	private static final String GET_MEMBER_STMT = 
		"SELECT member_no,groups_no FROM GROUPS_LIST where member_no = ?";
	private static final String DELETE = 
		"DELETE FROM GROUPS_LIST where groups_no = ? AND member_no = ?";
	
	@Override
	public void insert(Groups_ListVO groups_ListVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, groups_ListVO.getGroups_no());
			pstmt.setString(2, groups_ListVO.getMember_no());

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
	public void delete(String gorups_no, String member_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, gorups_no);
			pstmt.setString(2, member_no);

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
	public List<Groups_ListVO> findByGroupsNo(String gorups_no) {
		List<Groups_ListVO> list = new ArrayList<Groups_ListVO>();
		Groups_ListVO groups_ListVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_GROUPS_STMT);
			pstmt.setString(1, gorups_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				groups_ListVO = new Groups_ListVO();
				groups_ListVO.setGroups_no(rs.getString("groups_no"));
				groups_ListVO.setMember_no(rs.getString("member_no"));
				list.add(groups_ListVO); // Store the row in the list
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
	public List<Groups_ListVO> findByMemberNo(String member_no) {
		List<Groups_ListVO> list = new ArrayList<Groups_ListVO>();
		Groups_ListVO groups_ListVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MEMBER_STMT);
			pstmt.setString(1, member_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				groups_ListVO = new Groups_ListVO();
				groups_ListVO.setMember_no(rs.getString("member_no"));
				groups_ListVO.setGroups_no(rs.getString("groups_no"));
				list.add(groups_ListVO); // Store the row in the list
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
	public List<Groups_ListVO> getAll() {
		List<Groups_ListVO> list = new ArrayList<Groups_ListVO>();
		Groups_ListVO groups_ListVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				groups_ListVO = new Groups_ListVO();
				groups_ListVO.setGroups_no(rs.getString("groups_no"));
				groups_ListVO.setMember_no(rs.getString("member_no"));
				list.add(groups_ListVO); // Store the row in the list
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
