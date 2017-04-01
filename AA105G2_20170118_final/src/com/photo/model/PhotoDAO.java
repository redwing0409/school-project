package com.photo.model;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class PhotoDAO implements PhotoDAO_interface {
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
			"INSERT INTO PHOTO (photo_no,member_no,groups_no,photo_title,photo_file,photo_createdate,photo_status) VALUES (PHOTO_seq.NEXTVAL,?,?,?,?,?,1)";
	private static final String GET_ALL_STMT_Back = 
			"SELECT photo_no,member_no,groups_no,photo_title,photo_file,to_char(photo_createdate,'YYYY-MM-DD HH24:MI:SS') photo_createdate,photo_status FROM PHOTO order by photo_no";
	private static final String GET_ALL_STMT_Front = 
			"SELECT photo_no,member_no,groups_no,photo_title,photo_file,to_char(photo_createdate,'YYYY-MM-DD HH24:MI:SS') photo_createdate FROM PHOTO where photo_status = 1 order by photo_no";
	private static final String GET_GROUPS_PHOTO_STMT = 
			"SELECT photo_no,member_no,groups_no,photo_title,photo_file,to_char(photo_createdate,'YYYY-MM-DD HH24:MI:SS') photo_createdate FROM PHOTO where groups_no = ? and photo_status = 1 order by photo_createdate desc";
	private static final String GET_ONE_STMT = 
			"SELECT photo_no,member_no,groups_no,photo_title,photo_file,to_char(photo_createdate,'YYYY-MM-DD HH24:MI:SS') photo_createdate FROM PHOTO where photo_no = ? and photo_status = 1";
	private static final String DELETE = 
			"UPDATE PHOTO set photo_status = 0 where photo_no = ?";
	private static final String UPDATE = 
			"UPDATE PHOTO set member_no=?,groups_no=?,photo_title=?,photo_file=?,photo_createdate=? where photo_no = ? and photo_status = 1";
	private static final String GET_ONE_STMT_PICTURE = 
			"SELECT photo_no,member_no,groups_no,photo_title,photo_file,to_char(photo_createdate,'YYYY-MM-DD HH24:MI:SS') photo_createdate FROM PHOTO where MEMBER_NO = ?";
	private static final String GET_ONE_STMT_FORAll = 
			"SELECT photo_no,member_no,groups_no,photo_title,photo_file,to_char(photo_createdate,'YYYY-MM-DD HH24:MI:SS') photo_createdate FROM PHOTO where MEMBER_NO = ? and GROUPS_NO = ? and photo_status = 1";

	
	
	@Override
	public void insert(PhotoVO photoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			byte[] photo_file = photoVO.getPhoto_file();
			int flen = photo_file.length;
			InputStream fin = new ByteArrayInputStream(photo_file);
			
			pstmt.setString(1, photoVO.getMember_no());
			pstmt.setString(2, photoVO.getGroups_no());
			pstmt.setString(3, photoVO.getPhoto_title());
			pstmt.setBinaryStream(4, fin , flen);
			pstmt.setTimestamp(5, photoVO.getPhoto_createdate());
			
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
	public void update(PhotoVO photoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			byte[] photo_file = photoVO.getPhoto_file();
			int flen = photo_file.length;
			InputStream fin = new ByteArrayInputStream(photo_file);
			
			pstmt.setString(1, photoVO.getMember_no());
			pstmt.setString(2, photoVO.getGroups_no());
			pstmt.setString(3, photoVO.getPhoto_title());
			pstmt.setBinaryStream(4, fin , flen);
			pstmt.setTimestamp(5, photoVO.getPhoto_createdate());
			pstmt.setString(6, photoVO.getPhoto_no());

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
	public int delete(String photo_no) {
		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, photo_no);

			count=pstmt.executeUpdate();

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
		return count;				
	}
	

	@Override
	public PhotoVO findByPrimaryKey(String photo_no) {

		PhotoVO photoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, photo_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				photoVO = new PhotoVO();
				photoVO.setPhoto_no(rs.getString("photo_no"));
				photoVO.setMember_no(rs.getString("member_no"));
				photoVO.setGroups_no(rs.getString("groups_no"));
				photoVO.setPhoto_title(rs.getString("photo_title"));
				photoVO.setPhoto_file(rs.getBytes("photo_file"));
				photoVO.setPhoto_createdate(rs.getTimestamp("photo_createdate"));
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
		return photoVO;
	}
	
	@Override
	public List<PhotoVO> findPhotoByGroupsNo(String groups_no) {
		
		List<PhotoVO> list = new ArrayList<PhotoVO>();
		PhotoVO photoVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_GROUPS_PHOTO_STMT);
			
			pstmt.setString(1, groups_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				photoVO = new PhotoVO();
				photoVO.setPhoto_no(rs.getString("photo_no"));
				photoVO.setMember_no(rs.getString("member_no"));
				photoVO.setGroups_no(rs.getString("groups_no"));
				photoVO.setPhoto_title(rs.getString("photo_title"));
				photoVO.setPhoto_file(rs.getBytes("photo_file"));
				photoVO.setPhoto_createdate(rs.getTimestamp("photo_createdate"));
				list.add(photoVO);
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
	public List<PhotoVO> getAllBack() {
		List<PhotoVO> list = new ArrayList<PhotoVO>();
		PhotoVO photoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_Back);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				photoVO = new PhotoVO();
				photoVO.setPhoto_no(rs.getString("photo_no"));
				photoVO.setMember_no(rs.getString("member_no"));
				photoVO.setGroups_no(rs.getString("groups_no"));
				photoVO.setPhoto_title(rs.getString("photo_title"));
				photoVO.setPhoto_file(rs.getBytes("photo_file"));
				photoVO.setPhoto_createdate(rs.getTimestamp("photo_createdate"));
				photoVO.setPhoto_status(rs.getInt("photo_status"));
				list.add(photoVO); // Store the row in the list
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
	public List<PhotoVO> getAllFront() {
		List<PhotoVO> list = new ArrayList<PhotoVO>();
		PhotoVO photoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_Front);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				photoVO = new PhotoVO();
				photoVO.setPhoto_no(rs.getString("photo_no"));
				photoVO.setMember_no(rs.getString("member_no"));
				photoVO.setGroups_no(rs.getString("groups_no"));
				photoVO.setPhoto_title(rs.getString("photo_title"));
				photoVO.setPhoto_file(rs.getBytes("photo_file"));
				photoVO.setPhoto_createdate(rs.getTimestamp("photo_createdate"));
				list.add(photoVO); // Store the row in the list
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
		public List<PhotoVO> findByMemberNo_ForAll(String member_no,String groups_no) {
			
			List<PhotoVO> list = new ArrayList<PhotoVO>();
			PhotoVO photoVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT_FORAll);
				
				pstmt.setString(1, member_no);
				pstmt.setString(2, groups_no);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
//					empVo ¤]ºÙ¬° Domain objects
					photoVO = new PhotoVO();
					photoVO.setPhoto_no(rs.getString("photo_no"));
//					photoVO.setMember_no(rs.getString("member_no"));
//					photoVO.setPhoto_title(rs.getString("photo_title"));
//					memberVO.setMember_addr(rs.getString("member_addr"));
//					memberVO.setMember_email(rs.getString("member_email"));
//					memberVO.setMember_mobile(rs.getString("member_mobile"));
//					memberVO.setMember_sex(rs.getInt("member_sex"));
//					memberVO.setMember_birthday(rs.getDate("member_birthday"));
//					memberVO.setEnroll_time(rs.getDate("enroll_time"));
//					memberVO.setMember_pic(rs.getBytes("member_pic"));
					list.add(photoVO);
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
		}//end of findByMemberNo_ForAll
		@Override
		public int PhotoInsert_FromApp(PhotoVO photoVO) {
			int count = 0;
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				 con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
				
				byte[] photo_file = photoVO.getPhoto_file();
				int flen = photo_file.length;
				InputStream fin = new ByteArrayInputStream(photo_file);
				
				pstmt.setString(1, photoVO.getMember_no());
				pstmt.setString(2, photoVO.getGroups_no());
				pstmt.setString(3, photoVO.getPhoto_title());
//				pstmt.setString(4, photoVO.getPhoto_filename());
//				pstmt.setString(5, photoVO.getPhoto_extname());
				pstmt.setBinaryStream(4, fin , flen);
				pstmt.setTimestamp(5, photoVO.getPhoto_createdate());
				
				count =	pstmt.executeUpdate();
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
			return count;
		} //end of PhotoInsert_FromApp
		
	
	
}
