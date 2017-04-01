package com.member.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.*;

public class MemberDAO implements MemberDAO_interface  {
	
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
			"INSERT INTO MEMBER (MEMBER_NO,MEMBER_ACC,MEMBER_PW,MEMBER_NAME,MEMBER_ADDR,MEMBER_EMAIL,MEMBER_MOBILE,MEMBER_SEX,MEMBER_BIRTHDAY,ENROLL_TIME,MEMBER_PIC) VALUES (MEMBER_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT MEMBER_NO,MEMBER_ACC,MEMBER_PW,MEMBER_NAME,MEMBER_ADDR,MEMBER_EMAIL,MEMBER_MOBILE,MEMBER_SEX,to_char(MEMBER_BIRTHDAY,'yyyy-mm-dd') MEMBER_BIRTHDAY,to_char(ENROLL_TIME,'yyyy-mm-dd') ENROLL_TIME,MEMBER_PIC FROM MEMBER order by MEMBER_NO";
	private static final String GET_ONE_STMT = 
			"SELECT MEMBER_NO,MEMBER_ACC,MEMBER_PW,MEMBER_NAME,MEMBER_ADDR,MEMBER_EMAIL,MEMBER_MOBILE,MEMBER_SEX,to_char(MEMBER_BIRTHDAY,'yyyy-mm-dd') MEMBER_BIRTHDAY,to_char(ENROLL_TIME,'yyyy-mm-dd') ENROLL_TIME,MEMBER_PIC FROM MEMBER where MEMBER_NO = ?";
	
	private static final String UPDATE = 
			"UPDATE MEMBER set MEMBER_ACC=?,MEMBER_PW=?,MEMBER_NAME=?,MEMBER_ADDR=?,MEMBER_EMAIL=?,MEMBER_MOBILE=?,MEMBER_SEX=?,MEMBER_BIRTHDAY=?,ENROLL_TIME=?,MEMBER_PIC=? where MEMBER_NO = ?";
	private static final String LOGIN_CHECK = "SELECT member_no ,member_name  FROM MEMBER WHERE member_acc=? AND member_pw=? ";
	
	private static final String LOGIN_CHECK_MEMBERVO = 
			"SELECT MEMBER_NO,MEMBER_ACC,MEMBER_PW,MEMBER_NAME,MEMBER_ADDR,MEMBER_EMAIL,MEMBER_MOBILE,MEMBER_SEX,to_char(MEMBER_BIRTHDAY,'yyyy-mm-dd') MEMBER_BIRTHDAY,to_char(ENROLL_TIME,'yyyy-mm-dd') ENROLL_TIME,MEMBER_PIC FROM MEMBER WHERE member_acc=? AND member_pw=?";
			
	private static final String GET_ONE_STMT_PICTURE = 
			"SELECT MEMBER_NO,MEMBER_ACC,MEMBER_PW,MEMBER_NAME,MEMBER_ADDR,MEMBER_EMAIL,MEMBER_MOBILE,MEMBER_SEX,to_char(MEMBER_BIRTHDAY,'yyyy-mm-dd') MEMBER_BIRTHDAY,to_char(ENROLL_TIME,'yyyy-mm-dd') ENROLL_TIME,MEMBER_PIC FROM MEMBER  WHERE member_no=?";

	@Override
	public void insert(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			byte[] member_pic = memberVO.getMember_pic();
			int flen = member_pic.length;
			InputStream fin = new ByteArrayInputStream(member_pic);

			pstmt.setString(1, memberVO.getMember_acc());
			pstmt.setString(2, memberVO.getMember_pw());
			pstmt.setString(3, memberVO.getMember_name());
			pstmt.setString(4, memberVO.getMember_addr());
			pstmt.setString(5, memberVO.getMember_email());
			pstmt.setString(6, memberVO.getMember_mobile());
			pstmt.setInt(7, memberVO.getMember_sex());
			pstmt.setDate(8, memberVO.getMember_birthday());
			pstmt.setDate(9, memberVO.getEnroll_time());
			pstmt.setBinaryStream(10, fin , flen);

			pstmt.executeUpdate();
			fin.close();

			// Handle any driver errors
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
	public void update(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			byte[] member_pic = memberVO.getMember_pic();
			int flen = member_pic.length;
			InputStream fin = new ByteArrayInputStream(member_pic);

			pstmt.setString(1, memberVO.getMember_acc());
			pstmt.setString(2, memberVO.getMember_pw());
			pstmt.setString(3, memberVO.getMember_name());
			pstmt.setString(4, memberVO.getMember_addr());
			pstmt.setString(5, memberVO.getMember_email());
			pstmt.setString(6, memberVO.getMember_mobile());
			pstmt.setInt(7, memberVO.getMember_sex());
			pstmt.setDate(8, memberVO.getMember_birthday());
			pstmt.setDate(9, memberVO.getEnroll_time());
			pstmt.setBinaryStream(10, fin , flen);
			pstmt.setString(11, memberVO.getMember_no());

			pstmt.executeUpdate();
			fin.close();

			// Handle any driver errors
		}catch (SQLException se) {
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
	public MemberVO findByPrimaryKey(String memberNo) {

		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, memberNo);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
//				empVo 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMember_no(rs.getString("member_no"));
				memberVO.setMember_acc(rs.getString("member_acc"));
				memberVO.setMember_pw(rs.getString("member_pw"));
				memberVO.setMember_name(rs.getString("member_name"));
				memberVO.setMember_addr(rs.getString("member_addr"));
				memberVO.setMember_email(rs.getString("member_email"));
				memberVO.setMember_mobile(rs.getString("member_mobile"));
				memberVO.setMember_sex(rs.getInt("member_sex"));
				memberVO.setMember_birthday(rs.getDate("member_birthday"));
				memberVO.setEnroll_time(rs.getDate("enroll_time"));
				memberVO.setMember_pic(rs.getBytes("member_pic"));
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
		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMember_no(rs.getString("member_no"));
				memberVO.setMember_acc(rs.getString("member_acc"));
				memberVO.setMember_pw(rs.getString("member_pw"));
				memberVO.setMember_name(rs.getString("member_name"));
				memberVO.setMember_addr(rs.getString("member_addr"));
				memberVO.setMember_email(rs.getString("member_email"));
				memberVO.setMember_mobile(rs.getString("member_mobile"));
				memberVO.setMember_sex(rs.getInt("member_sex"));
				memberVO.setMember_birthday(rs.getDate("member_birthday"));
				memberVO.setEnroll_time(rs.getDate("enroll_time"));
				memberVO.setMember_pic(rs.getBytes("member_pic"));
				list.add(memberVO); // Store the row in the list
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
		public String findByACCOUNT_NAME(String member_acc, String member_pw) {     //check login account password 

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String member_name = "None";
//			String member_no = "None";

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(LOGIN_CHECK);
				
				pstmt.setString(1, member_acc);
				pstmt.setString(2, member_pw);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
//					System.out.println("while (rs.next()) {");
//					member_no = rs.getString("member_no");
					member_name = rs.getString("member_name");
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
//			System.out.println("loginCheck:"+member_no);
			
		 return member_name;
		}
		
		@Override
		public MemberVO findByPrimaryKeyForPicture(String member_no) {  //check get member_pic

			MemberVO memberVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT_PICTURE);
				
				pstmt.setString(1, member_no);

				rs = pstmt.executeQuery();
				
				while (rs.next()) {
//					empVo 也稱為 Domain objects
					memberVO = new MemberVO();
					memberVO.setMember_pic(rs.getBytes("member_pic"));
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
			return memberVO;
		}
		@Override
		public MemberVO findByAccountPassword(String member_acc, String member_pw) {

			MemberVO memberVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(LOGIN_CHECK_MEMBERVO);
				
				pstmt.setString(1, member_acc);
				pstmt.setString(2, member_pw);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
//					empVo 也稱為 Domain objects
					memberVO = new MemberVO();
					memberVO.setMember_no(rs.getString("member_no"));
					memberVO.setMember_acc(rs.getString("member_acc"));
					memberVO.setMember_pw(rs.getString("member_pw"));
					memberVO.setMember_name(rs.getString("member_name"));
//					memberVO.setMember_addr(rs.getString("member_addr"));
//					memberVO.setMember_email(rs.getString("member_email"));
//					memberVO.setMember_mobile(rs.getString("member_mobile"));
//					memberVO.setMember_sex(rs.getInt("member_sex"));
//					memberVO.setMember_birthday(rs.getDate("member_birthday"));
//					memberVO.setEnroll_time(rs.getDate("enroll_time"));
//					memberVO.setMember_pic(rs.getBytes("member_pic"));
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
			return memberVO;
		}
	
	
}
