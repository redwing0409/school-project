package com.order_item.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import jdbc.util.CompositeQuery.CompositeQuery_OrderItem;


public class OrdJDBCDAO implements OrdDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "scott3";
	String passwd = "tiger";
	
	    private static final String INSERT_STMT =
			"INSERT INTO order_item(pur_no,com_no,ord_price,ord_qty,return_qty,ship_status) values(?,?,?,?,?,?)";
	    private static final String GET_ALL_STMT=
	    	"SELECT pur_no,com_no,ord_price,ord_qty,return_qty,ship_status FROM order_item order by pur_no";
	    private static final String GET_ONE_STMT = 
	    	"SELECT pur_no,com_no,ord_price,ord_qty,return_qty,ship_status FROM order_item where pur_no=?";
	    private static final String DELETE = 
	    	"DELETE FROM order_item where (pur_no = ?) and (com_no= ?) ";
	    private static final String UPDATE = 
	    	"UPDATE order_item set ord_price=?,ord_qty=?,return_qty=?,ship_status=? where (pur_no=?) and (com_no=?)";
	    private static final String GET_COMPOSITE_STMT = 
			    "SELECT pur_no,com_no,ord_price,ord_qty,return_qty,ship_status FROM order_item where (pur_no=?) and (com_no=?)";
	   
	    @Override
	    public void insert(OrdVO ordVO) {
	    	Connection con = null;
	    	PreparedStatement pstmt = null;
	    	try{
	    	   Class.forName(driver);
			   con = DriverManager.getConnection(url, userid, passwd);
			   pstmt = con.prepareStatement(INSERT_STMT);
			   
			   pstmt.setString(1,ordVO.getPur_no());
			   pstmt.setString(2,ordVO.getCom_no());
			   pstmt.setInt(3,ordVO.getOrd_price());
			   pstmt.setInt(4,ordVO.getOrd_qty());
			   pstmt.setInt(5,ordVO.getReturn_qty());
			   pstmt.setInt(6,ordVO.getShip_status());
			   
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
		public void update(OrdVO ordVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);
			    
			    pstmt.setInt(1,ordVO.getOrd_price());
			    pstmt.setInt(2,ordVO.getOrd_qty());
			    pstmt.setInt(3,ordVO.getReturn_qty());
			    pstmt.setInt(4,ordVO.getShip_status());
			    pstmt.setString(5,ordVO.getPur_no());
			    pstmt.setString(6,ordVO.getCom_no());
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
		public void delete(String pur_no,String com_no) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);
				pstmt.setString(1, pur_no);
				pstmt.setString(2, com_no);
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
		public OrdVO findByKey(String pur_no) {
    	
	    	OrdVO ordVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, pur_no);
			  //pstmt.setString(2, com_no);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// ordVo 也稱為 Domain objects
					ordVO = new OrdVO();
					ordVO.setPur_no(rs.getString("pur_no"));
					ordVO.setCom_no(rs.getString("com_no"));
					ordVO.setOrd_price(rs.getInt("ord_price"));
					ordVO.setOrd_qty(rs.getInt("ord_qty"));
					ordVO.setReturn_qty(rs.getInt("return_qty"));
					ordVO.setShip_status(rs.getInt("ship_status"));
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
			return ordVO;
		}
	    
	    @Override
		public List<OrdVO> findByPrimaryKey(String pur_no) {

	    	List<OrdVO> list = new ArrayList<OrdVO>(); 
	    	OrdVO ordVO = null;
	    	
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, pur_no);
			  //pstmt.setString(2, com_no);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// ordVo 也稱為 Domain objects
					ordVO = new OrdVO();
					ordVO.setPur_no(rs.getString("pur_no"));
					ordVO.setCom_no(rs.getString("com_no"));
					ordVO.setOrd_price(rs.getInt("ord_price"));
					ordVO.setOrd_qty(rs.getInt("ord_qty"));
					ordVO.setReturn_qty(rs.getInt("return_qty"));
					ordVO.setShip_status(rs.getInt("ship_status"));
				    list.add(ordVO);
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
		public List<OrdVO> getAll() {
			List<OrdVO> list = new ArrayList<OrdVO>();
			OrdVO ordVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// ordVO 也稱為 Domain objects
					ordVO = new OrdVO();
					ordVO.setPur_no(rs.getString("pur_no"));
					ordVO.setCom_no(rs.getString("com_no"));
					ordVO.setOrd_price(rs.getInt("ord_price"));
					ordVO.setOrd_qty(rs.getInt("ord_price"));
					ordVO.setReturn_qty(rs.getInt("return_qty"));
					ordVO.setShip_status(rs.getInt("ship_status"));
					
					list.add(ordVO);	
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
	    public void insert2 (OrdVO ordVO , Connection con) {
	    	PreparedStatement pstmt = null;
	    	try{
	    		pstmt = con.prepareStatement(INSERT_STMT);
	    		pstmt.setString(1,ordVO.getPur_no());
			    pstmt.setString(2,ordVO.getCom_no());
			    pstmt.setInt(3,ordVO.getOrd_price());
			    pstmt.setInt(4,ordVO.getOrd_qty());
			    pstmt.setInt(5,ordVO.getReturn_qty());
			    pstmt.setInt(6,ordVO.getShip_status());
			   
			    pstmt.executeUpdate();
			    
	    	} catch (SQLException se) {
				if (con != null) {
					try {
						// 3●設定於當有exception發生時之catch區塊內
						System.err.print("Transaction is being ");
						System.err.println("rolled back-由-orditem");
						con.rollback();
					} catch (SQLException excep) {
						throw new RuntimeException("rollback error occured. "
								+ excep.getMessage());
					}
				}
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
			}
        }
	    
	    @Override
		public List<OrdVO> getAll(Map<String, String[]> map) {
			List<OrdVO> list = new ArrayList<OrdVO>();
			OrdVO ordVO = null;
		
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			try {
				
				Class.forName(driver);
	    		con = DriverManager.getConnection(url,userid,passwd);
	    		
				String finalSQL = "select * from order_item "
			          + CompositeQuery_OrderItem.get_WhereCondition(map)
			          + "order by pur_no";
				pstmt = con.prepareStatement(finalSQL);
				System.out.println("●●finalSQL(by JDBCDAO) = "+finalSQL);
				rs = pstmt.executeQuery();
		
				while (rs.next()) {
					ordVO = new OrdVO();
					ordVO.setPur_no(rs.getString("pur_no"));
					ordVO.setCom_no(rs.getString("com_no"));
					ordVO.setOrd_price(rs.getInt("ord_price"));
					ordVO.setOrd_qty(rs.getInt("ord_qty"));
					ordVO.setReturn_qty(rs.getInt("return_qty"));
					ordVO.setShip_status(rs.getInt("ship_status"));	
					list.add(ordVO); // Store the row in the List
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
	    
	    @Override
	    public OrdVO get_composite_stmt(String pur_no, String com_no) {
	    	
	    	OrdVO ordVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_COMPOSITE_STMT);
			

				pstmt.setString(1, pur_no);
			    pstmt.setString(2, com_no);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// ordVo 也稱為 Domain objects
					ordVO = new OrdVO();
					ordVO.setPur_no(rs.getString("pur_no"));
					ordVO.setCom_no(rs.getString("com_no"));
					ordVO.setOrd_price(rs.getInt("ord_price"));
					ordVO.setOrd_qty(rs.getInt("ord_qty"));
					ordVO.setReturn_qty(rs.getInt("return_qty"));
					ordVO.setShip_status(rs.getInt("ship_status"));
				}
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
			return ordVO;
		}
	    
	    public static void main(String[] args) {
	    	OrdJDBCDAO dao = new OrdJDBCDAO();
	    	// 新增
//	    	OrdVO ordVO1 = new OrdVO();
//	    	ordVO1.setPur_no("10003");
//	    	ordVO1.setCom_no("10004");
//	    	ordVO1.setOrd_price(999);
//	    	ordVO1.setOrd_qty(6);
//	    	ordVO1.setReturn_qty(0);
//	    	ordVO1.setShip_status(1);
//			dao.insert(ordVO1);
	    	
	    	//修改
//	    	OrdVO ordVO2 = new OrdVO();
//	    	ordVO2.setPur_no("10002");
//	    	ordVO2.setCom_no("10005");
//	    	ordVO2.setOrd_price(99999);
//	    	ordVO2.setOrd_qty(99);
//	    	ordVO2.setReturn_qty(22);
//	    	ordVO2.setShip_status(2);
//		
//			dao.update(ordVO2);
			
			//刪除
 	        // dao.delete("10003","10001");
			
	        //查詢單筆資料(陣列)
//	    	   List<OrdVO> OrdVO3 = dao.findByPrimaryKey("10003");
//			    for (OrdVO aOrd3 : OrdVO3) {
//			    	System.out.print(aOrd3.getPur_no() +",");
//				    System.out.print(aOrd3.getCom_no() +",");
//				    System.out.print(aOrd3.getOrd_price() +",");
//					System.out.print(aOrd3.getOrd_qty() +",");
//					System.out.print(aOrd3.getReturn_qty()+",");	
//					System.out.println(aOrd3.getShip_status());
//			    }
	    	
    	        // 查詢單筆資料(單筆)
	            OrdVO aOrd5  = dao.get_composite_stmt("10011","10014"); 	
		    	System.out.print(aOrd5.getPur_no() +",");
			    System.out.print(aOrd5.getCom_no() +",");
			    System.out.print(aOrd5.getOrd_price() +",");
				System.out.print(aOrd5.getOrd_qty() +",");
				System.out.print(aOrd5.getReturn_qty());
				System.out.println(aOrd5.getShip_status());
				System.out.println("------------------------");
				
		        // 查詢單筆資料(單筆)
//	            OrdVO aOrd3  = dao.findByKey("10003"); 	
//		    	System.out.print(aOrd3.getPur_no() +",");
//			    System.out.print(aOrd3.getCom_no() +",");
//			    System.out.print(aOrd3.getOrd_price() +",");
//				System.out.print(aOrd3.getOrd_qty() +",");
//				System.out.print(aOrd3.getReturn_qty());
//				System.out.println(aOrd3.getShip_status());
//				System.out.println("------------------------");
	   
	    	//查詢全部
//	    	List<OrdVO> list = dao.getAll();
//			for (OrdVO aOrd : list) {
//				System.out.print(aOrd.getPur_no() + ",");
//				System.out.print(aOrd.getCom_no() + ",");
//				System.out.print(aOrd.getOrd_price() + ",");
//				System.out.print(aOrd.getOrd_qty() + ",");
//				System.out.print(aOrd.getReturn_qty() + ",");
//				System.out.print(aOrd.getShip_status());
//				System.out.println();
//			}
		}
}
