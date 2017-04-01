package com.order_item.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.commodity.model.ComJDBCDAO;
import com.commodity.model.ComVO;

import jdbc.util.CompositeQuery.CompositeQuery_OrderItem;
public class OrdDAO implements OrdDAO_interface {
	private static DataSource ds = null;
	static {
		try  {
		       Context ctx = new InitialContext();
		       ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
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
	    	   
			   con = ds.getConnection();
			   pstmt = con.prepareStatement(INSERT_STMT);
			   
			   pstmt.setString(1,ordVO.getPur_no());
			   pstmt.setString(2,ordVO.getCom_no());
			   pstmt.setInt(3,ordVO.getOrd_price());
			   pstmt.setInt(4,ordVO.getOrd_qty());
			   pstmt.setInt(5,ordVO.getReturn_qty());
			   pstmt.setInt(6,ordVO.getShip_status());
			   
			   pstmt.executeUpdate();
			   
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
		public void update(OrdVO ordVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
			    
			    pstmt.setInt(1,ordVO.getOrd_price());
			    pstmt.setInt(2,ordVO.getOrd_qty());
			    pstmt.setInt(3,ordVO.getReturn_qty());
			    pstmt.setInt(4,ordVO.getShip_status());
			    pstmt.setString(5,ordVO.getPur_no());
			    pstmt.setString(6,ordVO.getCom_no());
				pstmt.executeUpdate();

				// Handle any SQL errors
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
		}
	    
	    @Override
		public void delete(String pur_no,String com_no) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);
				pstmt.setString(1, pur_no);
				pstmt.setString(2, com_no);
				pstmt.executeUpdate();
								
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

					con = ds.getConnection();
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
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, pur_no);
			  //pstmt.setString(2, com_no);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// comVo 也稱為 Domain objects
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
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// ordVO 也稱為 Domain objects
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
				
	    		con = ds.getConnection();
	    		
				String finalSQL = "select * from order_item "
			          + CompositeQuery_OrderItem.get_WhereCondition(map)
			          + "order by pur_no";
				pstmt = con.prepareStatement(finalSQL);
				System.out.println("●●finalSQL(by DAO) = "+finalSQL);
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
				con = ds.getConnection();
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
}
