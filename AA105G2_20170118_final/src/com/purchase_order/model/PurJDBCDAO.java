package com.purchase_order.model;

import java.sql.*;
import java.util.*;

import com.commodity.model.*;
import com.order_item.model.*;

import jdbc.util.CompositeQuery.CompositeQuery_PurchaseOrder;


public class PurJDBCDAO implements PurDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "scott3";
	String passwd = "tiger";
	
	    private static final String INSERT_STMT =
			"INSERT INTO purchase_order(pur_no,member_no,pur_date,pur_money,pur_product,pur_close,pur_status,pur_sum,pur_name,pur_add,pur_tel,pur_memo) values(PURCHASE_ORDER_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?)";
	    private static final String GET_ALL_STMT=
	    	"SELECT pur_no,member_no,to_char(pur_date,'yyyy-mm-dd HH:mm:ss') pur_date,to_char(pur_money,'yyyy-mm-dd HH:mm:ss') pur_money,to_char(pur_product,'yyyy-mm-dd HH:mm:ss') pur_product,to_char(pur_close,'yyyy-mm-dd HH:mm:ss') pur_close,pur_status,pur_sum,pur_name,pur_add,pur_tel,pur_memo FROM purchase_order order by pur_no";
	    private static final String GET_ONE_STMT = 
	        "SELECT pur_no,member_no,to_char(pur_date,'yyyy-mm-dd HH:mm:ss') pur_date,to_char(pur_money,'yyyy-mm-dd HH:mm:ss') pur_money,to_char(pur_product,'yyyy-mm-dd HH:mm:ss') pur_product,to_char(pur_close,'yyyy-mm-dd HH:mm:ss') pur_close,pur_status,pur_sum,pur_name,pur_add,pur_tel,pur_memo FROM purchase_order where pur_no=?";
	    private static final String DELETE = 
	    	"DELETE FROM purchase_order where pur_no = ?";
	    private static final String UPDATE = 
	    	"UPDATE purchase_order set member_no=?,pur_date=?,pur_money=?,pur_product=?,pur_close=?,pur_status=?,pur_sum=?,pur_name=?,pur_add=?,pur_tel=?,pur_memo=? where pur_no = ?";
	    private static final String GET_OrdItem_ByPur_no_STMT="SELECT pur_no,com_no,ord_price,ord_qty,return_qty,ship_status FROM order_item where pur_no=? order by pur_no";
	    
	    @Override
		public void insert(PurVO purVO) {
	    	Connection con = null;
	    	PreparedStatement pstmt = null;
	    	try{
	    	   Class.forName(driver);
			   con = DriverManager.getConnection(url, userid, passwd);
			   pstmt = con.prepareStatement(INSERT_STMT);
			   
			   pstmt.setString(1,purVO.getMember_no());
			   pstmt.setTimestamp(2, purVO.getPur_date());
			   pstmt.setTimestamp(3, purVO.getPur_money());
			   pstmt.setTimestamp(4, purVO.getPur_product());
			   pstmt.setTimestamp(5, purVO.getPur_close());
			   pstmt.setInt(6,purVO.getPur_status());
			   pstmt.setInt(7,purVO.getPur_sum());
			   pstmt.setString(8,purVO.getPur_name());
			   pstmt.setString(9,purVO.getPur_add());
			   pstmt.setString(10,purVO.getPur_tel());
			   pstmt.setString(11,purVO.getPur_memo());

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
		public void update(PurVO purVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);

			    pstmt.setString(1,purVO.getMember_no());
			    pstmt.setTimestamp(2, purVO.getPur_date());
			    pstmt.setTimestamp(3, purVO.getPur_money());
			    pstmt.setTimestamp(4, purVO.getPur_product());
			    pstmt.setTimestamp(5, purVO.getPur_close());
			    pstmt.setInt(6,purVO.getPur_status());
			    pstmt.setInt(7,purVO.getPur_sum());
			    pstmt.setString(8,purVO.getPur_name());
			    pstmt.setString(9,purVO.getPur_add());
			    pstmt.setString(10,purVO.getPur_tel());
			    pstmt.setString(11,purVO.getPur_memo());
				pstmt.setString(12,purVO.getPur_no());

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
		public void delete(String pur_no) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement("DELETE from ORDER_ITEM where PUR_NO=?");
				pstmt.setString(1, pur_no);
				pstmt.executeUpdate();
				
				pstmt = con.prepareStatement(DELETE);
				pstmt.setString(1, pur_no);
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
		public PurVO findByPrimaryKey(String pur_no) {

	    	PurVO purVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, pur_no);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// purVO 也稱為 Domain objects
					purVO = new PurVO();
					purVO.setPur_no(rs.getString("pur_no"));
					purVO.setMember_no(rs.getString("member_no"));
					purVO.setPur_date(rs.getTimestamp("pur_date"));
					purVO.setPur_money(rs.getTimestamp("pur_money"));
					purVO.setPur_product(rs.getTimestamp("pur_product"));
					purVO.setPur_close(rs.getTimestamp("pur_close"));
					purVO.setPur_status(rs.getInt("pur_status"));
					purVO.setPur_sum(rs.getInt("pur_sum"));
					purVO.setPur_name(rs.getString("pur_name"));
					purVO.setPur_add(rs.getString("pur_add"));
					purVO.setPur_tel(rs.getString("pur_tel"));
					purVO.setPur_memo(rs.getString("pur_memo"));
					
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
			return purVO;
		}
	    
	    @Override
		public List<PurVO> getAll() {
			List<PurVO> list = new ArrayList<PurVO>();
			PurVO purVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// purVO 也稱為 Domain objects
					purVO = new PurVO();
					purVO.setPur_no(rs.getString("pur_no"));
					purVO.setMember_no(rs.getString("member_no"));
					purVO.setPur_date(rs.getTimestamp("pur_date"));
					purVO.setPur_money(rs.getTimestamp("pur_money"));
					purVO.setPur_product(rs.getTimestamp("pur_product"));
					purVO.setPur_close(rs.getTimestamp("pur_close"));
					purVO.setPur_status(rs.getInt("pur_status"));
					purVO.setPur_sum(rs.getInt("pur_sum"));
					purVO.setPur_name(rs.getString("pur_name"));
					purVO.setPur_add(rs.getString("pur_add"));
					purVO.setPur_tel(rs.getString("pur_tel"));
					purVO.setPur_memo(rs.getString("pur_memo"));
					list.add(purVO);	
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
		public Set<OrdVO> getOrdItemByPurno(String pur_no) {
	         Set<OrdVO> set = new HashSet<OrdVO>();
	         OrdVO ordVO = null;
	         
	         Connection  con = null;
	         PreparedStatement pstmt = null;
	         ResultSet rs = null;
	         
	         try{
	        	 
	        	 Class.forName(driver);
	        	 con = DriverManager.getConnection(url,userid,passwd);
	        	 pstmt = con.prepareStatement(GET_OrdItem_ByPur_no_STMT);
	        	 pstmt.setString(1, pur_no);
	        	 rs = pstmt.executeQuery();
	        	 while(rs.next()) {
	        		  ordVO = new OrdVO();
	        		  ordVO.setPur_no(rs.getString("pur_no"));
	        		  ordVO.setCom_no(rs.getString("com_no"));
	        		  ordVO.setOrd_price(rs.getInt("ord_price"));
	        		  ordVO.setOrd_qty(rs.getInt("ord_qty"));
	        		  ordVO.setReturn_qty(rs.getInt("return_qty"));
	        		  ordVO.setShip_status(rs.getInt("ship_status"));
	        		  set.add(ordVO); // Store the row in the vector
	        	 }
	        		// Handle any driver errors
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
	 		return set;
	    }
	    
	    @Override
	  	public void insertWithOrditems(PurVO purVO, List<OrdVO> list) {
	  	    
	    	Connection con  = null;
	    	PreparedStatement pstmt = null;
	    	
	    	try {
	    		Class.forName(driver);
	    		con = DriverManager.getConnection(url,userid,passwd);
	    		
	    		// 1●設定於 pstm.executeUpdate()之前
	    		con.setAutoCommit(false);
	    		
	    		// 先新增訂單
	    		String cols[] = {"PUR_NO"};
	    		pstmt = con.prepareStatement(INSERT_STMT , cols);		
	    		pstmt.setString(1,purVO.getMember_no());
			    pstmt.setTimestamp(2, purVO.getPur_date());
			    pstmt.setTimestamp(3, purVO.getPur_money());
			    pstmt.setTimestamp(4, purVO.getPur_product());
			    pstmt.setTimestamp(5, purVO.getPur_close());
			    pstmt.setInt(6,purVO.getPur_status());
			    pstmt.setInt(7,purVO.getPur_sum());
			    pstmt.setString(8,purVO.getPur_name());
			    pstmt.setString(9,purVO.getPur_add());
			    pstmt.setString(10,purVO.getPur_tel());
			    pstmt.setString(11,purVO.getPur_memo());
			    pstmt.executeUpdate();
				//掘取對應的自增主鍵值
			   String next_purno = null;
			   ResultSet rs = pstmt.getGeneratedKeys();
			   if(rs.next()) {
				   next_purno = rs.getString(1);
				   System.out.println("自增主鍵值= " + next_purno +"(剛新增成功的訂單編號)");
			   }else {
				   System.out.println("未取得自增主鍵值");
			   }
			   rs.close();
			   //再同時新增訂單明細
			   OrdJDBCDAO dao = new OrdJDBCDAO();
			   System.out.println("list-size()-A="+list.size());
			   for(OrdVO aOrditem : list) {
				   aOrditem.setPur_no(new String(next_purno)) ;
				   dao.insert2(aOrditem,con);
			   }
			   
			// 2●設定於 pstm.executeUpdate()之後
			   con.commit();
			   con.setAutoCommit(true);
			   System.out.println("list.size()-B="+list.size());
			   System.out.println("新增訂單編號" + next_purno + "時,共有訂單明細" + list.size()
						+ "筆同時被新增");
	    	}catch (ClassNotFoundException e) {
	    		throw new RuntimeException("Couldn't load database driver. "
	    				+ e.getMessage());
				// Handle any SQL errors
	    	}catch (SQLException se) {
	    		if (con != null) {
	    			try{
	    				// 3●設定於當有exception發生時之catch區塊內
						System.err.print("Transaction is being ");
						System.err.println("rolled back-由-purchaser_order");
						con.rollback();
	    			}catch (SQLException excep) {
						throw new RuntimeException("rollback error occured. "
								+ excep.getMessage());
					}
				}
	    		throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
	    	}finally {
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
		public List<PurVO> getAll(Map<String, String[]> map) {
			List<PurVO> list = new ArrayList<PurVO>();
			PurVO purVO = null;
		
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			try {
				
				Class.forName(driver);
	    		con = DriverManager.getConnection(url,userid,passwd);
	    		
				String finalSQL = "select * from purchase_order "
			          + CompositeQuery_PurchaseOrder.get_WhereCondition(map)
			          + "order by pur_no";
				pstmt = con.prepareStatement(finalSQL);
				System.out.println("●●finalSQL(by JDBCDAO) = "+finalSQL);
				rs = pstmt.executeQuery();
		
				while (rs.next()) {
					purVO = new PurVO();
					purVO.setPur_no(rs.getString("pur_no"));
					purVO.setMember_no(rs.getString("member_no"));
					purVO.setPur_date(rs.getTimestamp("pur_date"));
					purVO.setPur_money(rs.getTimestamp("pur_money"));
					purVO.setPur_product(rs.getTimestamp("pur_product"));
					purVO.setPur_close(rs.getTimestamp("pur_close"));
					purVO.setPur_status(rs.getInt("pur_status"));
					purVO.setPur_sum(rs.getInt("pur_sum"));
					purVO.setPur_name(rs.getString("pur_name"));
					purVO.setPur_add(rs.getString("pur_add"));
					purVO.setPur_tel(rs.getString("pur_tel"));
					purVO.setPur_memo(rs.getString("pur_memo"));
					list.add(purVO); // Store the row in the List
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
	   PurJDBCDAO dao = new PurJDBCDAO();
		// 自主鍵新增訂單以及訂單明細
       PurVO purVO_auto = new PurVO();
       purVO_auto.setMember_no("10009");
       purVO_auto.setPur_date(new java.sql.Timestamp(new java.util.Date().getTime()));
       purVO_auto.setPur_money(java.sql.Timestamp.valueOf("2017-11-24 23:59:59"));
       purVO_auto.setPur_product(java.sql.Timestamp.valueOf("2017-11-24 23:59:59"));
       purVO_auto.setPur_close(java.sql.Timestamp.valueOf("2017-11-24 23:59:59"));
       purVO_auto.setPur_status(2);
       purVO_auto.setPur_sum(999);
       purVO_auto.setPur_name("陳老師123");
       purVO_auto.setPur_add("99桃園市中壢區中大路999號");
       purVO_auto.setPur_tel("03-12");
       purVO_auto.setPur_memo("無");
         
	   List<OrdVO> ordlist =  new ArrayList<OrdVO>();// 準備新增訂單明細
	   OrdVO ordlistXX = new OrdVO();                          //第一筆訂單明細
	   ordlistXX.setCom_no("10005");
	   ordlistXX.setOrd_price(99);
	   ordlistXX.setOrd_qty(3);
	   ordlistXX.setReturn_qty(0);
	   ordlistXX.setShip_status(1);
	    
	   OrdVO ordlistYY = new OrdVO();                          //第二筆訂單明細
	   ordlistYY.setCom_no("10009");
	   ordlistYY.setOrd_price(77);
	   ordlistYY.setOrd_qty(2);
	   ordlistYY.setReturn_qty(1);
	   ordlistYY.setShip_status(2);
	    
	   ordlist.add(ordlistXX);
	   ordlist.add(ordlistYY);
	   dao.insertWithOrditems(purVO_auto, ordlist);
	    
	    
	    	// 新增
//   	        PurVO purVO1 = new PurVO();
//   	        purVO1.setMember_no("10010");
//   	        purVO1.setPur_date(new java.sql.Timestamp(new java.util.Date().getTime()));
//   	        purVO1.setPur_money(java.sql.Timestamp.valueOf("2016-12-24 23:59:59"));
//   	        purVO1.setPur_product(java.sql.Timestamp.valueOf("2016-12-24 23:59:59"));
//   	        purVO1.setPur_close(java.sql.Timestamp.valueOf("2016-12-24 23:59:59"));
//   	        purVO1.setPur_status(2);
//            purVO1.setPur_sum(999);
//            purVO1.setPur_name("陳老師123");
//            purVO1.setPur_add("99桃園市中壢區中大路999號");
//            purVO1.setPur_tel("03-12");
//            purVO1.setPur_memo("無");
//   	        
//			dao.insert(purVO1);
   	        
	    	//修改
//			PurVO purVO2 = new PurVO();
//			purVO2.setPur_no("10003");
//		    purVO2.setMember_no("10003");
//			purVO2.setPur_date(java.sql.Timestamp.valueOf("2016-12-24 23:50:00"));
//			purVO2.setPur_money(java.sql.Timestamp.valueOf("2017-12-25 23:55:20"));
//			purVO2.setPur_product(java.sql.Timestamp.valueOf("2018-12-26 23:59:30"));
//			purVO2.setPur_close(java.sql.Timestamp.valueOf("2019-12-25 27:40:40"));
//		    purVO2.setPur_status(0);
//			purVO2.setPur_sum(168);
//			purVO2.setPur_name("林阿姨");
//			purVO2.setPur_add("320桃園市中壢區中大路399號");
//			purVO2.setPur_tel("03-1234567");
//			purVO2.setPur_memo("無");
//			dao.update(purVO2);
	    	
			//刪除
//		    dao.delete("10001");
			
	    	// 查詢單筆資料
//		    PurVO purVO3 = dao.findByPrimaryKey("10003");
//		    System.out.print(purVO3.getPur_no() + ",");
//			System.out.print(purVO3.getMember_no() + ",");
//			System.out.print(purVO3.getPur_date() + ",");
//			System.out.print(purVO3.getPur_money() + ",");
//			System.out.print(purVO3.getPur_product() + ",");
//			System.out.print(purVO3.getPur_close() + ",");
//			System.out.print(purVO3.getPur_status() +",");
//			System.out.print(purVO3.getPur_sum() +",");
//			System.out.print(purVO3.getPur_name() +",");
//			System.out.print(purVO3.getPur_add() +",");
//			System.out.print(purVO3.getPur_tel());
//			System.out.println(purVO3.getPur_memo());
//			System.out.println("------------------------");
			
	    	//查詢全部
//	    	List<PurVO> list = dao.getAll();
//			for (PurVO aPur : list) {
//				System.out.print(aPur.getPur_no() + ",");
//				System.out.print(aPur.getMember_no() + ",");
//				System.out.print(aPur.getPur_date() + ",");
//				System.out.print(aPur.getPur_money() + ",");
//				System.out.print(aPur.getPur_product() + ",");
//				System.out.print(aPur.getPur_close() + ",");
//				System.out.print(aPur.getPur_status() +",");
//				System.out.print(aPur.getPur_sum() +",");
//				System.out.print(aPur.getPur_name() +",");
//				System.out.print(aPur.getPur_add() +",");
//				System.out.print(aPur.getPur_tel());
//				System.out.print(aPur.getPur_memo());
//				System.out.println();
//			}
	    
	    //查詢一筆訂單下所有訂單明細
//	    Set<OrdVO> set = dao.getOrdItemByPurno("10001");
//		for (OrdVO aOrdItems : set) {
//			System.out.print(aOrdItems.getPur_no() + ",");
//			System.out.print(aOrdItems.getCom_no() + ",");
//			System.out.print(aOrdItems.getOrd_price() + ",");
//			System.out.print(aOrdItems.getOrd_qty() + ",");
//			System.out.print(aOrdItems.getReturn_qty() + ",");
//			System.out.print(aOrdItems.getShip_status() + ",");
//			System.out.println();
//		  }
	 }
}
