package com.response.model;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.*;




public class ResponseJDBCDAO implements ResponseDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "tony";
	String passwd = "tony0910478393";

	private static final String INSERT_STMT = "INSERT INTO RESPONSE(response_no,article_no,member_no,response_content,response_time) VALUES (?||'-'||response_seq.NEXTVAL,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT response_no,article_no,member_no,response_content,to_char(response_time,'yyyy-mm-dd HH:mm:ss') response_time FROM response order by response_no";
	private static final String GET_ONE_STMT = "SELECT response_no,article_no,member_no,response_content,to_char(response_time,'yyyy-mm-dd HH:mm:ss') response_time FROM response where response_no = ?";
	private static final String DELETE = "DELETE FROM response where response_no = ?";
	private static final String UPDATE = "UPDATE response set article_no=?, member_no=?, response_content=?, response_time=? where response_no=?";
	private static final String GET_OWN_STMT = "SELECT response_no,article_no,member_no,response_content,to_char(response_time,'yyyy-mm-dd HH:mm:ss') response_time FROM response where member_no = ?";
	
	
	@Override
	public int insert(ResponseVO responseVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			 
			pstmt.setString(1, responseVO.getArticle_no());
			pstmt.setString(2, responseVO.getArticle_no());
			pstmt.setString(3, responseVO.getMember_no());
			pstmt.setString(4, responseVO.getResponse_content());
			pstmt.setTimestamp(5, responseVO.getResponse_time());


			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

	@Override
	public int update(ResponseVO responseVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, responseVO.getArticle_no());
			pstmt.setString(2, responseVO.getMember_no());
			pstmt.setString(3, responseVO.getResponse_content());
			pstmt.setTimestamp(4, responseVO.getResponse_time());
			pstmt.setString(5, responseVO.getResponse_no());

			

			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

	@Override
	public int delete(String response_no) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, response_no);
			
			updateCount = pstmt.executeUpdate();

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
		return updateCount;
	}

	@Override
	public ResponseVO findByPrimaryKey(String response_no) {
		ResponseVO responseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, response_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// ResponseVO 也稱為 Domain objects
				responseVO = new ResponseVO();
				responseVO.setResponse_no(rs.getString("response_no"));
				responseVO.setArticle_no(rs.getString("article_no"));
				responseVO.setMember_no(rs.getString("member_no"));				
				responseVO.setResponse_content(rs.getString("response_content"));
				responseVO.setResponse_time(rs.getTimestamp("response_time"));
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
		} catch (Exception xe) {
			throw new RuntimeException("A database error occured. "
					+ xe.getMessage());
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
		return responseVO;
	}
	@Override
	public Set<String> getOwnResponse(String member_no) {
		Set<String> set = new HashSet<String>();
		ResponseVO responseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_OWN_STMT);
			String article_no=null;
			pstmt.setString(1, member_no);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// ResponseVO 也稱為 Domain objects
				article_no=rs.getString("article_no");
				set.add(article_no); // Store the row in the vector
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (Exception xe) {
			throw new RuntimeException("A database error occured. "
					+ xe.getMessage());
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
		return set;
	}
	
	@Override
	public List<ResponseVO> getAll() {
		List<ResponseVO> list = new ArrayList<ResponseVO>();
		ResponseVO responseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// ResponseVO 也稱為 Domain objects
				responseVO = new ResponseVO();
				responseVO.setResponse_no(rs.getString("response_no"));
				responseVO.setArticle_no(rs.getString("article_no"));
				responseVO.setMember_no(rs.getString("member_no"));
				responseVO.setResponse_content(rs.getString("response_content"));
				responseVO.setResponse_time(rs.getTimestamp("response_time"));
				list.add(responseVO); // Store the row in the vector
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
		} catch (Exception xe) {
			throw new RuntimeException("A database error occured. "
					+ xe.getMessage());
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


	public static void main(String[] args) throws UnsupportedEncodingException {
		ResponseJDBCDAO dao = new ResponseJDBCDAO();

		 //新增
//		 ResponseVO responseVO = new ResponseVO();
//		 responseVO.setArticle_no("3");
//		 responseVO.setMember_no("10006");
//		 responseVO.setResponse_content("新增的回覆內容");
//		 responseVO.setResponse_time(java.sql.Timestamp.valueOf("2016-12-26 17:57:30"));
//		 int updateCount_insert = dao.insert(responseVO);
//		 System.out.println(updateCount_insert);
				

		 //修改
//		 ResponseVO responseVO2 = new ResponseVO();
//		 responseVO2.setResponse_no("3-8");
//		 responseVO2.setArticle_no("6");
//		 responseVO2.setMember_no("10002");
//		 responseVO2.setResponse_content("修改後的回覆內容");
//		 responseVO2.setResponse_time(java.sql.Timestamp.valueOf("2016-12-06 17:57:30"));
//		 int updateCount_update = dao.update(responseVO2);
//		 System.out.println(updateCount_update);
				

		 //刪除
//		 int updateCount_delete = dao.delete("3-8");
//		 System.out.println(updateCount_delete);

		// 查詢
//		ResponseVO responseVO3 = dao.findByPrimaryKey("1-1");
//		System.out.print(responseVO3.getResponse_no() + ",");
//		System.out.print(responseVO3.getArticle_no() + ",");
//		System.out.print(responseVO3.getMember_no() + ",");
//		System.out.print(responseVO3.getResponse_content() + ",");
//		System.out.print(responseVO3.getResponse_time());
//		System.out.println("---------------------");
//
//		// 查詢
//		List<ResponseVO> list = dao.getAll();
//		for (ResponseVO aRes : list) {
//			System.out.print(aRes.getResponse_no() + ",");
//			System.out.print(aRes.getArticle_no() + ",");
//			System.out.print(aRes.getMember_no() + ",");
//			System.out.print(aRes.getResponse_content() + ",");
//			System.out.print(aRes.getResponse_time());
//			System.out.println();
//		}


	}

	
}
