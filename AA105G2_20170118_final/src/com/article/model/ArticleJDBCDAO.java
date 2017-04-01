package com.article.model;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.*;

import com.response.model.ResponseVO;

public class ArticleJDBCDAO implements ArticleDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "tony";
	String passwd = "tony0910478393";

	private static final String INSERT_STMT = "INSERT INTO ARTICLE(article_no,member_no,article_title,article_content,article_views,article_time,article_status) VALUES (article_seq.NEXTVAL,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT article_no,member_no,article_title,article_content,article_views,to_char(article_time,'yyyy-mm-dd HH:mm:ss') article_time,article_status FROM article order by article_no";
	private static final String GET_ALL_STMT_FRONT = "SELECT article_no,member_no,article_title,article_content,article_views,to_char(article_time,'yyyy-mm-dd HH:mm:ss') article_time,article_status FROM article where article_status=1 order by article_time";
	private static final String GET_ONE_STMT = "SELECT article_no,member_no,article_title,article_content,article_views,to_char(article_time,'yyyy-mm-dd HH:mm:ss') article_time,article_status FROM article where article_no = ? and article_status=1";
	private static final String DELETE = "DELETE FROM article where article_no = ? and article_status=1";
	private static final String UPDATE = "UPDATE article set member_no=?, article_title=?, article_content=?, article_views=?, article_time=?, article_status=? where article_no=? and article_status=1";
	private static final String GET_OWN_ARTICLE = "SELECT article_no,member_no,article_title,article_content,article_views,to_char(article_time,'yyyy-mm-dd HH:mm:ss') article_time,article_status FROM article where member_no = ? and article_status=1";
	private static final String GET_ALL_RESPONSE = "SELECT response_no,article_no,member_no,response_content,to_char(response_time,'yyyy-mm-dd HH:mm:ss') response_time FROM response where article_no=? order by response_time";

	
	
	@Override
	public int insert(ArticleVO articleVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, articleVO.getMember_no());
			pstmt.setString(2, articleVO.getArticle_title());
			pstmt.setString(3, articleVO.getArticle_content());
			pstmt.setInt(4, articleVO.getArticle_views());
			pstmt.setTimestamp(5, articleVO.getArticle_time());
			pstmt.setInt(6, articleVO.getArticle_status());

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
	public int update(ArticleVO articleVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, articleVO.getMember_no());
			pstmt.setString(2, articleVO.getArticle_title());
			pstmt.setString(3, articleVO.getArticle_content());
			pstmt.setInt(4, articleVO.getArticle_views());
			pstmt.setTimestamp(5, articleVO.getArticle_time());
			pstmt.setInt(6, articleVO.getArticle_status());
			pstmt.setString(7, articleVO.getArticle_no());
			

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
	public int delete(String article_no) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, article_no);
			
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
	public ArticleVO findByPrimaryKey(String article_no) {

		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, article_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// ArticleVO 也稱為 Domain objects
				articleVO = new ArticleVO();
				articleVO.setArticle_no(rs.getString("article_no"));
				articleVO.setMember_no(rs.getString("member_no"));
				articleVO.setArticle_title(rs.getString("article_title"));
				articleVO.setArticle_content(rs.getString("article_content"));
				articleVO.setArticle_views(rs.getInt("article_views"));
				articleVO.setArticle_time(rs.getTimestamp("article_time"));
				articleVO.setArticle_status(rs.getInt("article_status"));
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
		}
		finally {
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
		return articleVO;
	}

	@Override
	public List<ArticleVO> getAllBack() {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// ArticleVO 也稱為 Domain objects
				articleVO = new ArticleVO();
				articleVO.setArticle_no(rs.getString("article_no"));
				articleVO.setMember_no(rs.getString("member_no"));
				articleVO.setArticle_title(rs.getString("article_title"));
				articleVO.setArticle_content(rs.getString("article_content"));
				articleVO.setArticle_views(rs.getInt("article_views"));
				articleVO.setArticle_time(rs.getTimestamp("article_time"));
				articleVO.setArticle_status(rs.getInt("article_status"));
				list.add(articleVO); // Store the row in the vector
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
	
	
	@Override
	public List<ArticleVO> getAllFront() {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT_FRONT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// ArticleVO 也稱為 Domain objects
				articleVO = new ArticleVO();
				articleVO.setArticle_no(rs.getString("article_no"));
				articleVO.setMember_no(rs.getString("member_no"));
				articleVO.setArticle_title(rs.getString("article_title"));
				articleVO.setArticle_content(rs.getString("article_content"));
				articleVO.setArticle_views(rs.getInt("article_views"));
				articleVO.setArticle_time(rs.getTimestamp("article_time"));
				articleVO.setArticle_status(rs.getInt("article_status"));
				list.add(articleVO); // Store the row in the vector
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
	@Override
	public List<ArticleVO> getOwn(String member_no) {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_OWN_ARTICLE);
			
			pstmt.setString(1, member_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArticle_no(rs.getString("article_no"));
				articleVO.setMember_no(rs.getString("member_no"));
				articleVO.setArticle_title(rs.getString("article_title"));
				articleVO.setArticle_content(rs.getString("article_content"));
				articleVO.setArticle_views(rs.getInt("article_views"));
				articleVO.setArticle_time(rs.getTimestamp("article_time"));
				articleVO.setArticle_status(rs.getInt("article_status"));
				list.add(articleVO); // Store the row in the List
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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

	
	@Override
	public List<ArticleVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}	
	
	public static void main(String[] args) throws UnsupportedEncodingException {

		ArticleJDBCDAO dao = new ArticleJDBCDAO();

		 //新增
//		 ArticleVO articleVO = new ArticleVO();
//		 articleVO.setMember_no("10008");
//		 articleVO.setArticle_title("新增的第8篇");
//		 articleVO.setArticle_content("第8篇內容");
//		 articleVO.setArticle_views(1);
//		 articleVO.setArticle_time(java.sql.Timestamp.valueOf("2016-12-26 17:57:30"));
//		 articleVO.setArticle_status(1);
//		 int updateCount_insert = dao.insert(articleVO);
//		 System.out.println(updateCount_insert);
				

		 //修改
//		 ArticleVO articleVO2 = new ArticleVO();
//		 articleVO2.setArticle_no("8");
//		 articleVO2.setMember_no("10001");
//		 articleVO2.setArticle_title("修改後的第8篇");
//		 articleVO2.setArticle_content("修改後的內容");
//		 articleVO2.setArticle_views(0);
//		 articleVO2.setArticle_time(java.sql.Timestamp.valueOf("2016-12-06 17:57:30"));
//		 articleVO2.setArticle_status(1);
//		 int updateCount_update = dao.update(articleVO2);
//		 System.out.println(updateCount_update);
				

		 //刪除
//		 int updateCount_delete = dao.delete("8");
//		 System.out.println(updateCount_delete);

		// 查詢
//		ArticleVO articleVO3 = dao.findByPrimaryKey("1");
//		System.out.print(articleVO3.getArticle_no() + ",");
//		System.out.print(articleVO3.getMember_no() + ",");
//		System.out.print(articleVO3.getArticle_title() + ",");
//		System.out.print(articleVO3.getArticle_content() + ",");
//		System.out.print(articleVO3.getArticle_views() + ",");
//		System.out.print(articleVO3.getArticle_time() + ",");
//		System.out.println(articleVO3.getArticle_status());
//		System.out.println("---------------------");

		// 查詢
//		List<ArticleVO> list = dao.getAllBack();
//		for (ArticleVO aEmp : list) {
//			System.out.print(aEmp.getArticle_no() + ",");
//			System.out.print(aEmp.getMember_no() + ",");
//			System.out.print(aEmp.getArticle_title() + ",");
//			System.out.print(aEmp.getArticle_content() + ",");
//			System.out.print(aEmp.getArticle_views() + ",");
//			System.out.print(aEmp.getArticle_time() + ",");
//			System.out.print(aEmp.getArticle_status());
//			System.out.println();
//		}
	

	
		// 查詢
		List<ArticleVO> list = dao.getAllFront();
		for (ArticleVO aEmp : list) {
			System.out.print(aEmp.getArticle_no() + ",");
			System.out.print(aEmp.getMember_no() + ",");
			System.out.print(aEmp.getArticle_title() + ",");
			System.out.print(aEmp.getArticle_content() + ",");
			System.out.print(aEmp.getArticle_views() + ",");
			System.out.print(aEmp.getArticle_time() + ",");
			System.out.print(aEmp.getArticle_status());
			System.out.println();
		}
	}

	@Override
	public List<ResponseVO> getAllResponse(String article_no) {
		List<ResponseVO> list = new ArrayList<ResponseVO>();
		ResponseVO responseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_RESPONSE);
			
			pstmt.setString(1, article_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				responseVO = new ResponseVO();
				responseVO.setResponse_no(rs.getString("response_no"));
				responseVO.setArticle_no(rs.getString("article_no"));
				responseVO.setMember_no(rs.getString("member_no"));
				responseVO.setResponse_content(rs.getString("response_content"));
				responseVO.setResponse_time(rs.getTimestamp("response_time"));
				list.add(responseVO); // Store the row in the List
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
	
	

}
	


