package com.adv.model;

import java.util.*;
import java.io.*;
import java.sql.*;

public class AdvJDBCDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "chenlin1";
	String passwd = "chenlin1";
	
	private static final String INSERT_STMT = 
			"INSERT INTO ADVERTISING (adv_no,sup_no,adv_pic,adv_note) VALUES (advertising_seq.NEXTVAL, ?, ?, ? )";
		private static final String GET_ALL_STMT = 
			"SELECT adv_no,sup_no,adv_pic,adv_note FROM ADVERTISING order by adv_no";
		private static final String GET_ONE_STMT = 
			"SELECT adv_no,sup_no,adv_pic,adv_note FROM ADVERTISING where adv_no = ?";
		private static final String DELETE = 
			"DELETE FROM ADVERTISING where adv_no = ?";
		private static final String UPDATE = 
			"UPDATE ADVERTISING set sup_no=?, adv_pic=?, adv_note=? where adv_no = ?";
		
	
		public void insert(AdvVO AdvVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);
				
				byte[] adv_pic = AdvVO.getAdv_pic();
				int flen =adv_pic.length;
				InputStream fin = new ByteArrayInputStream(adv_pic);

				pstmt.setString(1, AdvVO.getSup_no());
				pstmt.setBinaryStream(2, fin,flen);
				pstmt.setString(3, AdvVO.getAdv_note());
				
				pstmt.executeUpdate();

				// Handle any SQL errors
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
		
	
		public void update(AdvVO AdvVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);
 
				byte[] adv_pic = AdvVO.getAdv_pic();
				int flen2 =adv_pic.length;
				InputStream fin2 = new ByteArrayInputStream(adv_pic);
				
				pstmt.setString(1, AdvVO.getSup_no());
				pstmt.setBinaryStream(2, fin2,flen2);
				pstmt.setString(3, AdvVO.getAdv_note());
				pstmt.setString(4, AdvVO.getAdv_no());
				
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
		
		public void delete(String adv_no) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, adv_no);

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
	
		public AdvVO findByPrimaryKey(String adv_no) {

			AdvVO AdvVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, adv_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 嘿 Domain objects
					AdvVO = new AdvVO();
					AdvVO.setAdv_no(rs.getString("adv_no"));
					AdvVO.setSup_no(rs.getString("sup_no"));
					AdvVO.setAdv_pic(rs.getBytes("adv_pic"));
					AdvVO.setAdv_note(rs.getString("adv_note"));
					
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
			return AdvVO;
		}
	
		public List<AdvVO> getAll() {
			List<AdvVO> list = new ArrayList<AdvVO>();
			AdvVO AdvVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 嘿 Domain objects
					AdvVO = new AdvVO();
					AdvVO.setAdv_no(rs.getString("adv_no"));
					AdvVO.setSup_no(rs.getString("sup_no"));
					AdvVO.setAdv_pic(rs.getBytes("adv_pic"));
					AdvVO.setAdv_note(rs.getString("adv_note"));
					list.add(AdvVO); // Store the row in the list
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
			return list;
		}
		public static void main(String[] args) throws Throwable{
			AdvJDBCDAO dao = new AdvJDBCDAO();
			
			        //穝糤
			            
//			            File pic = new File("images/picFrom", "theleeshotel4.jpg");
//			            InputStream fin = new FileInputStream(pic);
//			            byte[] buffer = new byte[fin.available()];
//			            fin.read(buffer);
//			           
//			            AdvVO AdvVO1 = new AdvVO();
//					    AdvVO1.setSup_no("6001");
//					    AdvVO1.setAdv_pic(buffer);
//					    AdvVO1.setAdv_note("浆堕甶地堕穦繻 琎ら疭基 ,Τ00穦蝶基キА把σ基59だ");
//					    dao.insert(AdvVO1);
//					    fin.close();

//			         э
//			           File pic = new File("images/picFrom", "yayuan3.jpg");
//			           InputStream fin2 = new FileInputStream(pic);
//			           byte[] buffer2 = new byte[fin2.available()];
//			           fin2.read(buffer2);
//			           
//			           AdvVO AdvVO2 = new AdvVO();
//					   AdvVO2.setAdv_no("4001");
//					   AdvVO2.setSup_no("6001");
//					   AdvVO2.setAdv_pic(buffer2);
//					   AdvVO2.setAdv_note("地簓逗┍(地栋刮) さら疭基,Τ00穦蝶基キА把σ基89だ");
//					   dao.update(AdvVO2);
//			           fin2.close();
			        
		       	
					//埃
//				    dao.delete("1");
			
			      //琩高掸
		          AdvVO AdvVO3 = dao.findByPrimaryKey("4001");
		            System.out.print(AdvVO3.getAdv_no() + ",");	
					System.out.print(AdvVO3.getSup_no() + ",");
					System.out.print(AdvVO3.getAdv_pic() + ",");
					System.out.println(AdvVO3.getAdv_note() + ",");
					
					System.out.println("------------------------");

					//琩高
					List<AdvVO> list = dao.getAll();
					for (AdvVO aAdv : list) {
				    System.out.print(aAdv.getAdv_no() + ",");	
					System.out.print(aAdv.getSup_no() + ",");
					System.out.print(aAdv.getAdv_pic() + ",");
					System.out.print(aAdv.getAdv_note() + ",");
					
			//	System.out.print(aEmp.getDeptno());
					System.out.println();
		 }
		}	

	}