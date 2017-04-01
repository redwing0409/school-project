package com.message.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MessageDAO implements MessageDAO_interface{
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
	"INSERT INTO message (message_no,sender_no,recipient_no,message_time,message_extname,message_content,message_visibility) VALUES (MESSAGE_SEQ.NEXTVAL,?,?,?,?,?,?)";

			private static final String UPDATE = 
	"UPDATE message set message_no=?, sender_no=?,recipient_no=?, message_time=?, message_extname=?, message_content=?, message_visibility=? where message_no = ?";	
	private static final String DELETE = 
			 "DELETE FROM message where message_no = ?";
	private static final String GET_ONE_STMT = 
	 "SELECT message_no,sender_no,recipient_no,to_char(message_time,'yyyy-mm-dd HH:mm:ss') message_time,message_extname,message_content,message_visibility FROM message where message_no = ?";

	private static final String GET_ALL_STMT = 
	"SELECT message_no,sender_no,recipient_no,to_char(message_time,'yyyy-mm-dd HH:mm:ss') message_time,message_extname,message_content,message_visibility FROM message order by message_no";

	
	@Override
	public int insert(MessageVO messageVo) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
//			pstmt.setString(1, messageVo.getMessage_no());
			pstmt.setString(1, messageVo.getSender_no());
			pstmt.setString(2, messageVo.getRecipient_no());
			pstmt.setTimestamp(3, messageVo.getMessage_time());
			pstmt.setString(4, messageVo.getMessage_extname());
			pstmt.setString(5, messageVo.getMessage_content());
			pstmt.setInt(6, messageVo.getMessage_visibility());
		
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
//***************** insert end*****************
	@Override
	public int update(MessageVO messageVo) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, messageVo.getMessage_no());
			pstmt.setString(2, messageVo.getSender_no());
			pstmt.setString(3, messageVo.getRecipient_no());
			pstmt.setTimestamp(4, messageVo.getMessage_time());
		   pstmt.setString(5, messageVo.getMessage_extname());
			pstmt.setString(6, messageVo.getMessage_content());
			pstmt.setInt(7, messageVo.getMessage_visibility());
			pstmt.setString(8, messageVo.getMessage_no());
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
	//***************** update end*****************
	@Override
	public int delete(String message_no) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, message_no);
			
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
	}	//***************** delete end*****************
	
	@Override
	public MessageVO findByPrimaryKey(String message_no) {

		MessageVO messageVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, message_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				messageVo = new MessageVO();
				messageVo.setMessage_no(rs.getString("message_no"));
				messageVo.setSender_no(rs.getString("sender_no"));
				messageVo.setRecipient_no(rs.getString("recipient_no"));				
				messageVo.setMessage_time(rs.getTimestamp("message_time"));
				messageVo.setMessage_extname(rs.getString("message_extname"));
				messageVo.setMessage_content(rs.getString("message_content"));
				messageVo.setMessage_visibility(rs.getInt("message_visibility"));
			
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
		return messageVo;
	}//***************** findByPrimaryKey end*****************
	
	@Override
	public List<MessageVO> getAll() {
		List<MessageVO> list = new ArrayList<MessageVO>();
		MessageVO messageVo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				messageVo = new MessageVO();
				messageVo.setMessage_no(rs.getString("message_no"));
				messageVo.setSender_no(rs.getString("sender_no"));
				
				messageVo.setRecipient_no(rs.getString("recipient_no"));
				messageVo.setMessage_time(rs.getTimestamp("message_time"));
				messageVo.setMessage_extname(rs.getString("message_extname"));
				messageVo.setMessage_content(rs.getString("message_content"));
				messageVo.setMessage_visibility(rs.getInt("message_visibility"));
				list.add(messageVo); // Store the row in the vector
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
	}//***************** getAll end*****************
	
	
		
}
