package com.bulletin.model;

import java.io.*;
import java.sql.*;
import java.util.*;

import com.groups.model.GroupsVO;


public class BulletinJDBCDAO implements BulletinDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "ALOHA";
	String passwd = "pk7835085";

	private static final String INSERT_STMT = 
			"INSERT INTO BULLETIN (bulletin_no,groups_no,member_no,bulletin_content,bulletin_time,bulletin_status) VALUES (BULLETIN_seq.NEXTVAL,?,?,?,?,1)";
	private static final String GET_ALL_STMT_Back = 
			"SELECT bulletin_no,groups_no,member_no,bulletin_content,to_char(bulletin_time,'YYYY-MM-DD HH24:MI:SS') bulletin_time,bulletin_status FROM BULLETIN order by bulletin_no";
	private static final String GET_ALL_STMT_Front = 
			"SELECT bulletin_no,groups_no,member_no,bulletin_content,to_char(bulletin_time,'YYYY-MM-DD HH24:MI:SS') bulletin_time FROM BULLETIN where bulletin_status = 1 order by bulletin_no";
	private static final String GET_BULLETIN_STMT = 
			"SELECT bulletin_no,groups_no,member_no,bulletin_content,to_char(bulletin_time,'YYYY-MM-DD HH24:MI:SS') bulletin_time FROM BULLETIN where groups_no = ? and bulletin_status = 1 order by bulletin_time desc";
	private static final String GET_ONE_STMT = 
			"SELECT bulletin_no,groups_no,member_no,bulletin_content,to_char(bulletin_time,'YYYY-MM-DD HH24:MI:SS') bulletin_time FROM BULLETIN where bulletin_no = ? and bulletin_status = 1";
	private static final String DELETE = 
			"UPDATE BULLETIN set bulletin_status = 0 where bulletin_no = ?";
	private static final String UPDATE = 
			"UPDATE BULLETIN set groups_no=?,member_no=?,bulletin_content=?,bulletin_time=? where bulletin_no = ? and bulletin_status = 1";
	
	@Override
	public void insert(BulletinVO bulletinVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			
			pstmt.setString(1, bulletinVO.getGroups_no());
			pstmt.setString(2, bulletinVO.getMember_no());
			pstmt.setString(3, bulletinVO.getBulletin_content());
			pstmt.setTimestamp(4, bulletinVO.getBulletin_time());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void update(BulletinVO bulletinVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, bulletinVO.getGroups_no());
			pstmt.setString(2, bulletinVO.getMember_no());
			pstmt.setString(3, bulletinVO.getBulletin_content());
			pstmt.setTimestamp(4, bulletinVO.getBulletin_time());
			pstmt.setString(5, bulletinVO.getBulletin_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void delete(String bulletin_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, bulletin_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public List<BulletinVO> findBulletinByGroupsNo(String groups_no) {
		List<BulletinVO> list = new ArrayList<BulletinVO>();
		BulletinVO bulletinVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BULLETIN_STMT);

			pstmt.setString(1, groups_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				bulletinVO = new BulletinVO();
				bulletinVO.setBulletin_no(rs.getString("bulletin_no"));
				bulletinVO.setGroups_no(rs.getString("groups_no"));
				bulletinVO.setMember_no(rs.getString("member_no"));
				bulletinVO.setBulletin_content(rs.getString("bulletin_content"));
				bulletinVO.setBulletin_time(rs.getTimestamp("bulletin_time"));
				list.add(bulletinVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (Exception exc) {
			throw new RuntimeException("A database error occured. "
					+ exc.getMessage());
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
	public BulletinVO findByPrimaryKey(String bulletin_no) {

		BulletinVO bulletinVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, bulletin_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 嘿 Domain objects
				bulletinVO = new BulletinVO();
				bulletinVO.setBulletin_no(rs.getString("bulletin_no"));
				bulletinVO.setGroups_no(rs.getString("groups_no"));
				bulletinVO.setMember_no(rs.getString("member_no"));
				bulletinVO.setBulletin_content(rs.getString("bulletin_content"));
				bulletinVO.setBulletin_time(rs.getTimestamp("bulletin_time"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return bulletinVO;
	}
	
	@Override
	public List<BulletinVO> getAllBack() {
		List<BulletinVO> list = new ArrayList<BulletinVO>();
		BulletinVO bulletinVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT_Back);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bulletinVO = new BulletinVO();
				bulletinVO.setBulletin_no(rs.getString("bulletin_no"));
				bulletinVO.setGroups_no(rs.getString("groups_no"));
				bulletinVO.setMember_no(rs.getString("member_no"));
				bulletinVO.setBulletin_content(rs.getString("bulletin_content"));
				bulletinVO.setBulletin_time(rs.getTimestamp("bulletin_time"));
				bulletinVO.setBulletin_status(rs.getInt("bulletin_status"));
				list.add(bulletinVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (Exception exc) {
			throw new RuntimeException("A database error occured. "
					+ exc.getMessage());
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
	public List<BulletinVO> getAllFront() {
		List<BulletinVO> list = new ArrayList<BulletinVO>();
		BulletinVO bulletinVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT_Front);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bulletinVO = new BulletinVO();
				bulletinVO.setBulletin_no(rs.getString("bulletin_no"));
				bulletinVO.setGroups_no(rs.getString("groups_no"));
				bulletinVO.setMember_no(rs.getString("member_no"));
				bulletinVO.setBulletin_content(rs.getString("bulletin_content"));
				bulletinVO.setBulletin_time(rs.getTimestamp("bulletin_time"));
				list.add(bulletinVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (Exception exc) {
			throw new RuntimeException("A database error occured. "
					+ exc.getMessage());
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

	public static void main(String[] args) throws Throwable  {
		
		BulletinJDBCDAO dao = new BulletinJDBCDAO();
		
		// 穝糤
//		BulletinVO bulletinVO = new BulletinVO();
//		bulletinVO.setGroups_no("20005");
//		bulletinVO.setMember_no("10005");
//		bulletinVO.setBulletin_content("代刚");
//		bulletinVO.setBulletin_time(java.sql.Timestamp.valueOf("2016-01-01 15:25:30"));
//		dao.insert(bulletinVO);	
		
		// э
//		BulletinVO bulletinVO2 = new BulletinVO();
//		bulletinVO2.setBulletin_no("30001");
//		bulletinVO2.setGroups_no("20005");
//		bulletinVO2.setMember_no("10005");
//		bulletinVO2.setBulletin_content("代刚");
//		bulletinVO2.setBulletin_time(java.sql.Timestamp.valueOf("2016-01-01 15:25:30"));
//		dao.update(bulletinVO2);	
		
		// 埃
//		dao.delete("30005");
		
		//琩高疭﹚竤
//		List<BulletinVO> list = dao.findByGroupsNo("20002");
//		for (BulletinVO aBulletin : list) {
//			System.out.print(aBulletin.getBulletin_no() + ",");
//			System.out.print(aBulletin.getGroups_no() + ",");
//			System.out.print(aBulletin.getMember_no() + ",");
//			System.out.print(aBulletin.getBulletin_content() + ",");
//			System.out.println(aBulletin.getBulletin_time());
//		}
		
//		BulletinVO bulletinVO3 = dao.findByPrimaryKey("30002");
//		System.out.print(bulletinVO3.getBulletin_no() + ",");
//		System.out.print(bulletinVO3.getGroups_no() + ",");
//		System.out.print(bulletinVO3.getMember_no() + ",");
//		System.out.print(bulletinVO3.getBulletin_content() + ",");
//		System.out.println(bulletinVO3.getBulletin_time());
		
		// 琩高场
//		List<BulletinVO> list = dao.getAllBack();
//		for (BulletinVO aBulletin : list) {
//			System.out.print(aBulletin.getBulletin_no() + ",");
//			System.out.print(aBulletin.getGroups_no() + ",");
//			System.out.print(aBulletin.getMember_no() + ",");
//			System.out.print(aBulletin.getBulletin_content() + ",");
//			System.out.print(aBulletin.getBulletin_time() + ",");
//			System.out.println(aBulletin.getBulletin_status());
//		}
		
//		// ㄏノ琩高场
//		List<BulletinVO> list = dao.getAllFront();
//		for (BulletinVO aBulletin : list) {
//			System.out.print(aBulletin.getBulletin_no() + ",");
//			System.out.print(aBulletin.getGroups_no() + ",");
//			System.out.print(aBulletin.getMember_no() + ",");
//			System.out.print(aBulletin.getBulletin_content() + ",");
//			System.out.println(aBulletin.getBulletin_time());
//		}
	}

}
