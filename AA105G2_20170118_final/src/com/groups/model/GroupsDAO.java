package com.groups.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class GroupsDAO implements GroupsDAO_interface{
	
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
		"INSERT INTO GROUPS (groups_no,groups_owner,groups_title,groups_time,groups_status) VALUES (GROUPS_seq.NEXTVAL, ?, ?, ?, 1)";
	private static final String GET_ALL_STMT_Back = 
		"SELECT groups_no,groups_owner,groups_title,to_char(groups_time,'yyyy-mm-dd') groups_time,groups_status FROM GROUPS order by groups_no";
	private static final String GET_ALL_STMT_Front = 
		"SELECT groups_no,groups_owner,groups_title,to_char(groups_time,'yyyy-mm-dd') groups_time FROM GROUPS where groups_status = 1 order by groups_no";
	private static final String GET_ONE_STMT = 
		"SELECT groups_no,groups_owner,groups_title,to_char(groups_time,'yyyy-mm-dd') groups_time FROM GROUPS where groups_no = ? and groups_status = 1";
	private static final String DELETE = 
		"UPDATE GROUPS set groups_status = 0 where groups_no = ?";
	private static final String UPDATE = 
		"UPDATE GROUPS set groups_owner=?, groups_title=?, groups_time=? where groups_no = ? and groups_status = 1";
	private static final String GET_ALL_STMT_GROUPS = 
			"SELECT groups_no,groups_owner,groups_title,to_char(groups_time,'yyyy-mm-dd') groups_time FROM GROUPS where groups_status = 1 and groups_owner = ?";
	
	
	@Override
	public void insert(GroupsVO groupsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, groupsVO.getGroups_owner());
			pstmt.setString(2, groupsVO.getGroups_title());
			pstmt.setDate(3, groupsVO.getGroups_time());

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
	public void update(GroupsVO groupsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, groupsVO.getGroups_owner());
			pstmt.setString(2, groupsVO.getGroups_title());
			pstmt.setDate(3, groupsVO.getGroups_time());
			pstmt.setString(4, groupsVO.getGroups_no());

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
	public void delete(String groups_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, groups_no);

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
	public GroupsVO findByPrimaryKey(String groups_no) {

		GroupsVO groupsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, groups_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				groupsVO = new GroupsVO();
				groupsVO.setGroups_no(rs.getString("groups_no"));
				groupsVO.setGroups_owner(rs.getString("groups_owner"));
				groupsVO.setGroups_title(rs.getString("groups_title"));
				groupsVO.setGroups_time(rs.getDate("groups_time"));
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
		return groupsVO;
	}

	@Override
	public List<GroupsVO> getAllBack() {
		List<GroupsVO> list = new ArrayList<GroupsVO>();
		GroupsVO groupsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_Back);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				groupsVO = new GroupsVO();
				groupsVO.setGroups_no(rs.getString("groups_no"));
				groupsVO.setGroups_owner(rs.getString("groups_owner"));
				groupsVO.setGroups_title(rs.getString("groups_title"));
				groupsVO.setGroups_time(rs.getDate("groups_time"));
				groupsVO.setGroups_status(rs.getInt("groups_status"));
				list.add(groupsVO); // Store the row in the list
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
	public List<GroupsVO> getAllFront() {
		List<GroupsVO> list = new ArrayList<GroupsVO>();
		GroupsVO groupsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_Front);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				groupsVO = new GroupsVO();
				groupsVO.setGroups_no(rs.getString("groups_no"));
				groupsVO.setGroups_owner(rs.getString("groups_owner"));
				groupsVO.setGroups_title(rs.getString("groups_title"));
				groupsVO.setGroups_time(rs.getDate("groups_time"));
				list.add(groupsVO); // Store the row in the list
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
	// **************************************	for app  method  ********************************************************
	@Override
		public List<GroupsVO> GroupsGetAll(String member_no) {
			List<GroupsVO> list = new ArrayList<GroupsVO>();
			GroupsVO groupsVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT_GROUPS);
				
				pstmt.setString(1, member_no);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					groupsVO = new GroupsVO();
					groupsVO.setGroups_no(rs.getString("groups_no"));
					groupsVO.setGroups_owner(rs.getString("groups_owner"));
					groupsVO.setGroups_title(rs.getString("groups_title"));
//					groupsVO.setGroups_time(rs.getDate("groups_time"));
					list.add(groupsVO); // Store the row in the list
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