package com.commodity.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jdbc.util.CompositeQuery.CompositeQuery_Commodity;

public class ComDAO implements ComDAO_interface{
	
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
		"INSERT INTO commodity(com_no,sup_no,pcm_no,com_name,com_desc,com_price,com_status,com_shelf_date,com_off_date,com_note,com_pic) values(COMMODITY_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";
    private static final String GET_ALL_STMT=
    	"SELECT com_no,sup_no,pcm_no,com_name,com_desc,com_price,com_status,to_char(com_shelf_date,'yyyy-mm-dd HH:mm:ss') com_shelf_date,to_char(com_off_date,'yyyy-mm-dd HH:mm:ss') com_off_date,com_note,com_pic FROM commodity order by com_no";
    private static final String GET_ONE_STMT = 
    	"SELECT com_no,sup_no,pcm_no,com_name,com_desc,com_price,com_status,to_char(com_shelf_date,'yyyy-mm-dd HH:mm:ss') com_shelf_date,to_char(com_off_date,'yyyy-mm-dd HH:mm:ss') com_off_date,com_note,com_pic FROM commodity where com_no=?";
    private static final String DELETE = 
    	"DELETE FROM commodity where com_no = ?";
    private static final String UPDATE = 
    	"UPDATE commodity set sup_no=?,pcm_no=?,com_name=?,com_desc=?,com_price=?,com_status=?,com_shelf_date=?,com_off_date=?,com_note=?,com_pic=? where com_no=?";

    @Override
	public void insert(ComVO comVO) {
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	
    	try{
    	   con = ds.getConnection();
		   pstmt = con.prepareStatement(INSERT_STMT);
		   
		   byte[] com_pic = comVO.getCom_pic();
		   int flen = com_pic.length;
		   InputStream fin = new ByteArrayInputStream(com_pic);
	   
		   pstmt.setString(1,comVO.getSup_no());
		   pstmt.setInt(2,comVO.getPcm_no());
		   pstmt.setString(3,comVO.getCom_name());
		   pstmt.setString(4,comVO.getCom_desc());
		   pstmt.setInt(5,comVO.getCom_price());
		   pstmt.setInt(6,comVO.getCom_status());
		   pstmt.setTimestamp(7, comVO.getCom_shelf_date());
		   pstmt.setTimestamp(8, comVO.getCom_off_date());
		   pstmt.setString(9,comVO.getCom_note());
		   pstmt.setBinaryStream(10, fin, flen);     
		   
		   pstmt.executeUpdate();
		  
		    // Handle any driver errors
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
	public void update(ComVO comVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			byte[] com_pic = comVO.getCom_pic();
			int flen = 0;
			InputStream fin =  null;
			if(com_pic!=null) {
			  flen = com_pic.length;
			  fin = new ByteArrayInputStream(com_pic);
			}
			
		
			pstmt.setString(1,comVO.getSup_no());
		    pstmt.setInt(2,comVO.getPcm_no());
		    pstmt.setString(3,comVO.getCom_name());
		    pstmt.setString(4,comVO.getCom_desc());
		    pstmt.setInt(5,comVO.getCom_price());
		    pstmt.setInt(6,comVO.getCom_status());
		    pstmt.setTimestamp(7, comVO.getCom_shelf_date());
		    pstmt.setTimestamp(8, comVO.getCom_off_date());
		    pstmt.setString(9,comVO.getCom_note());
		    pstmt.setBinaryStream(10, fin, flen);   
			pstmt.setString(11,comVO.getCom_no());
			
			pstmt.executeUpdate();
			//fin.close();
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		/*} catch (IOException ioe) {
			ioe.printStackTrace();*/
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
			con = ds.getConnection();
			pstmt = con.prepareStatement("DELETE from ORDER_ITEM where COM_NO=?");
			pstmt.setString(1, com_no);
			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, com_no);
			pstmt.executeUpdate();

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
	public ComVO findByPrimaryKey(String com_no) {

		ComVO comVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {	
			con =ds.getConnection();
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

			con = ds.getConnection();
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
				comVO.setCom_pic(rs.getBytes("com_pic"));  
				list.add(comVO);	
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
 	public List<ComVO> getAll(Map<String, String[]> map) {
 		List<ComVO> list = new ArrayList<ComVO>();
 		ComVO comVO = null;
 	
 		Connection con = null;
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
 	
 		try {
 			
 			
     		con = ds.getConnection();
     		
 			String finalSQL = "select * from commodity "
 		          + CompositeQuery_Commodity.get_WhereCondition(map)
 		          + "order by com_no";
 			pstmt = con.prepareStatement(finalSQL);
 			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
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

}
