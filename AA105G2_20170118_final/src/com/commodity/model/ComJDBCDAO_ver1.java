package com.commodity.model;

import java.util.*;

import jdbc.util.CompositeQuery.CompositeQuery_Commodity;

import java.sql.*;

public class ComJDBCDAO_ver1 implements ComDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "scott2";
	String passwd = "tiger";
	
	private static final String INSERT_STMT =
		"INSERT INTO commodity(com_no,sup_no,pcm_no,com_name,com_desc,com_price,com_status,com_shelf_date,com_off_date,com_note,com_pic) values(COMMODITY_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";
    private static final String GET_ALL_STMT=
    	"SELECT com_no,sup_no,pcm_no,com_name,com_desc,com_price,com_status,to_char(com_shelf_date,'yyyy-mm-dd HH:mm:ss') com_shelf_date,to_char(com_off_date,'yyyy-mm-dd HH:mm:ss') com_off_date,com_note,com_pic FROM commodity order by com_no";
    private static final String GET_ONE_STMT = 
    	"SELECT com_no,sup_no,pcm_no,com_name,com_desc,com_price,com_status,to_char(com_shelf_date,'yyyy-mm-dd HH:mm:ss') com_shelf_date,to_char(com_off_date,'yyyy-mm-dd HH:mm:ss') com_off_date,com_note,com_pic FROM commodity where com_no=?";
    private static final String DELETE = 
    	"DELETE FROM commodity where com_no = ?";
    private static final String UPDATE = 
    	"UPDATE commodity set sup_no=?,pcm_no=?,com_name=?,com_desc=?,com_price=?,com_status=?,com_shelf_date=?,com_off_date=?,com_note=?,com_pic=? where com_no = ?";
    
    @Override
	public void insert(ComVO comVO) {
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	try{
    	   Class.forName(driver);
		   con = DriverManager.getConnection(url, userid, passwd);
		   pstmt = con.prepareStatement(INSERT_STMT);
		   
		   pstmt.setString(1,comVO.getSup_no());
		   pstmt.setInt(2,comVO.getPcm_no());
		   pstmt.setString(3,comVO.getCom_name());
		   pstmt.setString(4,comVO.getCom_desc());
		   pstmt.setInt(5,comVO.getCom_price());
		   pstmt.setInt(6,comVO.getCom_status());
		   pstmt.setTimestamp(7, comVO.getCom_shelf_date());
		   pstmt.setTimestamp(8, comVO.getCom_off_date());
		   pstmt.setString(9,comVO.getCom_note());
		   pstmt.setNull(10,java.sql.Types.BLOB);      //Use byte[]
		   
		   pstmt.executeUpdate();
		   
		     // Handle any driver errors
    	} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver."
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."
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
	public void update(ComVO comVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1,comVO.getSup_no());
		    pstmt.setInt(2,comVO.getPcm_no());
		    pstmt.setString(3,comVO.getCom_name());
		    pstmt.setString(4,comVO.getCom_desc());
		    pstmt.setInt(5,comVO.getCom_price());
		    pstmt.setInt(6,comVO.getCom_status());
		    pstmt.setTimestamp(7, comVO.getCom_shelf_date());
		    pstmt.setTimestamp(8, comVO.getCom_off_date());
		    pstmt.setString(9,comVO.getCom_note());
		    pstmt.setNull(10,java.sql.Types.BLOB);      //Use byte[]
			pstmt.setString(11,comVO.getCom_no());
			
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
	public void delete(String com_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement("DELETE from ORDER_ITEM where COM_NO=?");
			pstmt.setString(1, com_no);
			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, com_no);
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
	public ComVO findByPrimaryKey(String com_no) {

		ComVO comVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, com_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// comVo 也稱為 Domain objects
				comVO = new ComVO();
				comVO.setCom_no(rs.getString("com_no"));
				comVO.setSup_no(rs.getString("sup_no"));
				comVO.setPcm_no(rs.getInt("pcm_no"));
				comVO.setCom_name(rs.getString("com_name"));
				comVO.setCom_desc(rs.getString("com_desc"));
				comVO.setCom_price(rs.getInt("com_price"));
				comVO.setCom_status(rs.getInt("com_status"));
				comVO.setCom_shelf_date(rs.getTimestamp("com_shelf_date"));
				comVO.setCom_off_date(rs.getTimestamp("com_off_date"));
				comVO.setCom_note(rs.getString("com_note"));
				comVO.setCom_pic(rs.getBytes("com_pic"));    //使用getBytes，但資料仍為空值。
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
		return comVO;
	}
    
    @Override
	public List<ComVO> getAll() {
		List<ComVO> list = new ArrayList<ComVO>();
		ComVO comVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// comVO 也稱為 Domain objects
				comVO = new ComVO();
				comVO.setCom_no(rs.getString("com_no"));
				comVO.setSup_no(rs.getString("sup_no"));
				comVO.setPcm_no(rs.getInt("pcm_no"));
				comVO.setCom_name(rs.getString("com_name"));
				comVO.setCom_desc(rs.getString("com_desc"));
				comVO.setCom_price(rs.getInt("com_price"));
				comVO.setCom_status(rs.getInt("com_status"));
				comVO.setCom_shelf_date(rs.getTimestamp("com_shelf_date"));
				comVO.setCom_off_date(rs.getTimestamp("com_off_date"));
				comVO.setCom_note(rs.getString("com_note"));
				comVO.setCom_pic(rs.getBytes("com_pic"));    //使用getBytes，但資料仍為空值。
				list.add(comVO);	
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
    
    @Override
 	public List<ComVO> getAll(Map<String, String[]> map) {
 		List<ComVO> list = new ArrayList<ComVO>();
 		ComVO comVO = null;
 	
 		Connection con = null;
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
 	
 		try {
 			
 			Class.forName(driver);
     		con = DriverManager.getConnection(url,userid,passwd);
     		
 			String finalSQL = "select * from commodity "
 		          + CompositeQuery_Commodity.get_WhereCondition(map)
 		          + "order by com_no";
 			pstmt = con.prepareStatement(finalSQL);
 			System.out.println("●●finalSQL(by JDBCDAO) = "+finalSQL);
 			rs = pstmt.executeQuery();
 	
 			while (rs.next()) {
 				comVO = new ComVO();
 				comVO.setCom_no(rs.getString("com_no"));
 				comVO.setSup_no(rs.getString("sup_no"));
 				comVO.setPcm_no(rs.getInt("pcm_no"));
 				comVO.setCom_name(rs.getString("com_name"));
 				comVO.setCom_desc(rs.getString("com_desc"));
 				comVO.setCom_price(rs.getInt("com_price"));
 				comVO.setCom_status(rs.getInt("com_status"));
 				comVO.setCom_shelf_date(rs.getTimestamp("com_shelf_date"));
 				comVO.setCom_off_date(rs.getTimestamp("com_off_date"));
 				comVO.setCom_note(rs.getString("com_note"));
 				comVO.setCom_pic(rs.getBytes("com_pic"));
 				list.add(comVO); // Store the row in the List
 			}
 			
 		} catch (ClassNotFoundException e) {
 			throw new RuntimeException("Couldn't load database driver. "
 					+ e.getMessage());
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
    
    public static void main(String[] args) {
    	ComJDBCDAO dao = new ComJDBCDAO();
    	// 新增
    	ComVO comVO1 = new ComVO();
		comVO1.setSup_no("10099");
		comVO1.setPcm_no(2);
		comVO1.setCom_name("喜帖99");
		comVO1.setCom_desc("喜帖999999");
		comVO1.setCom_price(30);
		comVO1.setCom_status(1);
		comVO1.setCom_shelf_date(java.sql.Timestamp.valueOf("2016-12-24 23:59:59"));
		comVO1.setCom_off_date(java.sql.Timestamp.valueOf("2019-12-25 23:59:59"));
		comVO1.setCom_note("null");
		comVO1.setCom_pic(null);
		dao.insert(comVO1);
		
    	//修改
//    	ComVO comVO2 = new ComVO();
//    	comVO2.setCom_no("10013");
//    	comVO2.setSup_no("10001");
//    	comVO2.setPcm_no(9);
//    	comVO2.setCom_name("喜帖12222");
//		comVO2.setCom_desc("愛你9999");
//		comVO2.setCom_price(100);
//		comVO2.setCom_status(2);
//		comVO2.setCom_shelf_date(java.sql.Timestamp.valueOf("2099-12-31 23:59:59"));
//		comVO2.setCom_off_date(java.sql.Timestamp.valueOf("2100-12-31 23:59:59"));
//		comVO2.setCom_note("null");
//		comVO2.setCom_pic(null);
//		dao.update(comVO2);
		//刪除
//		dao.delete("10001");
		
//    	// 查詢單筆資料
//	    ComVO comVO3 = dao.findByPrimaryKey("10001");
//	    System.out.print(comVO3.getCom_no() +",");
//	    System.out.print(comVO3.getSup_no() +",");
//	    System.out.print(comVO3.getPcm_no() +",");
//	    System.out.print(comVO3.getCom_name() +",");
//	    System.out.print(comVO3.getCom_desc() +",");
//	    System.out.print(comVO3.getCom_price() + ",");
//		System.out.print(comVO3.getCom_status() +",");
//		System.out.print(comVO3.getCom_shelf_date() +",");
//		System.out.print(comVO3.getCom_off_date() +",");
//		System.out.print(comVO3.getCom_note() +",");
//		System.out.println(comVO3.getCom_pic());
//		System.out.println("------------------------");
//		
//    	//查詢全部
//    	List<ComVO> list = dao.getAll();
//		for (ComVO aCom : list) {
//			System.out.print(aCom.getCom_no() + ",");
//			System.out.print(aCom.getSup_no() + ",");
//			System.out.print(aCom.getPcm_no() + ",");
//			System.out.print(aCom.getCom_name() + ",");
//			System.out.print(aCom.getCom_desc() + ",");
//			System.out.print(aCom.getCom_price() + ",");
//			System.out.print(aCom.getCom_status() +",");
//			System.out.print(aCom.getCom_shelf_date() +",");
//			System.out.print(aCom.getCom_off_date() +",");
//			System.out.print(aCom.getCom_note() +",");
//			System.out.print(aCom.getCom_pic());
//			System.out.println();
//		}
    }
}
