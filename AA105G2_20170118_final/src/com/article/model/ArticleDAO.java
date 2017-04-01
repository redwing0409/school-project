package com.article.model;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.response.model.ResponseVO;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Article;

public class ArticleDAO implements ArticleDAO_interface {
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

	private static final String INSERT_STMT = "INSERT INTO ARTICLE(article_no,member_no,article_title,article_content,article_views,article_time,article_status) VALUES (article_seq.NEXTVAL,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT article_no,member_no,article_title,article_content,article_views,to_char(article_time,'yyyy-mm-dd HH:mm:ss') article_time,article_status FROM article order by article_time desc";
	private static final String GET_ALL_STMT_FRONT = "SELECT article_no,member_no,article_title,article_content,article_views,to_char(article_time,'yyyy-mm-dd HH:mm:ss') article_time,article_status FROM article where article_status=1 order by article_time desc";
	private static final String GET_ONE_STMT = "SELECT article_no,member_no,article_title,article_content,article_views,to_char(article_time,'yyyy-mm-dd HH:mm:ss') article_time,article_status FROM article where article_no = ? and article_status=1";
	private static final String DELETE = "DELETE FROM article where article_no = ? and article_status=1";
	private static final String UPDATE = "UPDATE article set member_no=?, article_title=?, article_content=?, article_views=?, article_time=?, article_status=? where article_no=? and article_status=1";
	private static final String GET_OWN_ARTICLE = "SELECT article_no,member_no,article_title,article_content,article_views,to_char(article_time,'yyyy-mm-dd HH:mm:ss') article_time,article_status FROM article where member_no = ? and article_status=1 order by article_time desc";
	private static final String GET_ALL_RESPONSE = "SELECT response_no,article_no,member_no,response_content,to_char(response_time,'yyyy-mm-dd HH:mm:ss') response_time FROM response where article_no=? order by response_time desc";
	
	
	
	@Override
	public int insert(ArticleVO articleVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, articleVO.getMember_no());
			pstmt.setString(2, articleVO.getArticle_title());
			pstmt.setString(3, articleVO.getArticle_content());
			pstmt.setInt(4, articleVO.getArticle_views());
			pstmt.setTimestamp(5, articleVO.getArticle_time());
			pstmt.setInt(6, articleVO.getArticle_status());

			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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

			con = ds.getConnection();
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
		}  catch (SQLException se) {
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, article_no);
			
			updateCount = pstmt.executeUpdate();

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
	public ArticleVO findByPrimaryKey(String article_no) {

		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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
		}  catch (SQLException se) {
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

			con = ds.getConnection();
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
	public List<ArticleVO> getAllFront() {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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
	public List<ArticleVO> getOwn(String member_no) {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String sql=jdbcUtil_CompositeQuery_Article.get_WhereCondition(map);
			String finalSQL=null;
			if(sql.equals("")){
				finalSQL = "select * from article where article_status=1 order by article_time";
			}
			else{
				finalSQL = "select * from article "
		          + sql + "order by article_time";
			}
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
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
		return list;
	}

	@Override
	public List<ResponseVO> getAllResponse(String article_no) {
		List<ResponseVO> list = new ArrayList<ResponseVO>();
		ResponseVO responseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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
