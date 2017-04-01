package com.response.model;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;




public class ResponseDAO implements ResponseDAO_interface{
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

	private static final String INSERT_STMT = "INSERT INTO RESPONSE(response_no,article_no,member_no,response_content,response_time) VALUES (?||'-'||response_seq.NEXTVAL,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT response_no,article_no,member_no,response_content,to_char(response_time,'yyyy-mm-dd HH:mm:ss') response_time FROM response order by response_time desc";
	private static final String GET_ONE_STMT = "SELECT response_no,article_no,member_no,response_content,to_char(response_time,'yyyy-mm-dd HH:mm:ss') response_time FROM response where response_no = ?";
	private static final String DELETE = "DELETE FROM response where response_no = ?";
	private static final String UPDATE = "UPDATE response set article_no=?, member_no=?, response_content=?, response_time=? where response_no=?";
	private static final String GET_OWN_STMT = "SELECT article_no FROM response where member_no = ?";
	
	
	@Override
	public int insert(ResponseVO responseVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			 
			pstmt.setString(1, responseVO.getArticle_no());
			pstmt.setString(2, responseVO.getArticle_no());
			pstmt.setString(3, responseVO.getMember_no());
			pstmt.setString(4, responseVO.getResponse_content());
			pstmt.setTimestamp(5, responseVO.getResponse_time());


			updateCount = pstmt.executeUpdate();

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

	@Override
	public int update(ResponseVO responseVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, responseVO.getArticle_no());
			pstmt.setString(2, responseVO.getMember_no());
			pstmt.setString(3, responseVO.getResponse_content());
			pstmt.setTimestamp(4, responseVO.getResponse_time());
			pstmt.setString(5, responseVO.getResponse_no());

			

			updateCount = pstmt.executeUpdate();		

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

	@Override
	public int delete(String response_no) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, response_no);
			
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
	public ResponseVO findByPrimaryKey(String response_no) {
		ResponseVO responseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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
			
			con = ds.getConnection();
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

			con = ds.getConnection();
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
	
}
