package com.employee.model;

import java.util.*;
import java.sql.*;

public class EmployeeJDBCDAO implements EmployeeDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "AA105";
	String passwd = "wwejohncena";
	
	private static final String INSERT_STMT = 
		"INSERT INTO employee (emp_no,emp_account,emp_psw,emp_name,emp_sex,emp_mobile) VALUES (EMPLOYEE_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
	private static final String UPDATE = 
		"UPDATE employee set emp_no=?, emp_account=?, emp_psw=?, emp_name=?, emp_sex=?, emp_mobile=? where emp_no = ?";
	private static final String DELETE = 
		 "DELETE FROM employee where emp_no = ?";
	private static final String GET_ONE_STMT = 
		 "SELECT emp_no,emp_account,emp_psw,emp_name,emp_sex,emp_mobile FROM employee where emp_no = ?";
	private static final String GET_ALL_STMT = 
		"SELECT emp_no,emp_account,emp_psw,emp_name,emp_sex,emp_mobile FROM employee order by emp_no";

	
	@Override
	public int insert(EmployeeVO employeeVo) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
//			pstmt.setString(1, employeeVo.getEmp_no());
			pstmt.setString(1, employeeVo.getEmp_account());
			pstmt.setString(2, employeeVo.getEmp_psw());
			pstmt.setString(3, employeeVo.getEmp_name());
			   pstmt.setInt(4, employeeVo.getEmp_sex());
			pstmt.setString(5, employeeVo.getEmp_mobile());

			updateCount = pstmt.executeUpdate();

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
		return updateCount;
	}
//***************** insert end*****************
	
	@Override
	public int update(EmployeeVO employeeVo) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, employeeVo.getEmp_no());
			pstmt.setString(2, employeeVo.getEmp_account());
			pstmt.setString(3, employeeVo.getEmp_psw());
			pstmt.setString(4, employeeVo.getEmp_name());
			   pstmt.setInt(5, employeeVo.getEmp_sex());
			pstmt.setString(6, employeeVo.getEmp_mobile());
			pstmt.setString(7, employeeVo.getEmp_no());

			updateCount = pstmt.executeUpdate();

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
		return updateCount;
	}
	//***************** update end*****************
	
	@Override
	public int delete(String emp_no) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, emp_no);
			
			updateCount = pstmt.executeUpdate();

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
		return updateCount;
	}	//***************** delete end*****************
	
	@Override
	public EmployeeVO findByPrimaryKey(String emp_no) {

		EmployeeVO employeeVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, emp_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				employeeVo = new EmployeeVO();
				employeeVo.setEmp_no(rs.getString("emp_no"));
				employeeVo.setEmp_account(rs.getString("emp_account"));
				employeeVo.setEmp_psw(rs.getString("emp_psw"));
				employeeVo.setEmp_name(rs.getString("emp_name"));
				employeeVo.setEmp_sex(rs.getInt("emp_sex"));
				employeeVo.setEmp_mobile(rs.getString("emp_mobile"));
			
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
		return employeeVo;
	}//***************** findByPrimaryKey end*****************
	
	@Override
	public List<EmployeeVO> getAll() {
		List<EmployeeVO> list = new ArrayList<EmployeeVO>();
		EmployeeVO employeeVo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				employeeVo = new EmployeeVO();
				employeeVo.setEmp_no(rs.getString("emp_no"));
				employeeVo.setEmp_account(rs.getString("emp_account"));
				employeeVo.setEmp_psw(rs.getString("emp_psw"));
				employeeVo.setEmp_name(rs.getString("emp_name"));
				employeeVo.setEmp_sex(rs.getInt("emp_sex"));
				employeeVo.setEmp_mobile(rs.getString("emp_mobile"));
				list.add(employeeVo); // Store the row in the vector
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
	}//***************** getAll end*****************
	
	public static void main(String[] args) {
		EmployeeJDBCDAO dao = new EmployeeJDBCDAO();

            // 新增
//		EmployeeVo empVO1 = new EmployeeVo();
//		 empVO1.setEmp_account("M456");
//		 empVO1.setEmp_psw("MANAGER1");
//		 empVO1.setEmp_name("ZZZ12");
//		 empVO1.setEmp_sex(0);
//		 empVO1.setEmp_mobile("0974185236");
//		 int updateCount_insert = dao.insert(empVO1);
//		 System.out.println("insert:"+updateCount_insert);
		
	 
		    //刪除
//		 int updateCount_delete = dao.delete("5");
//		 System.out.println("delete:"+updateCount_delete);
		 
		   //修改
//		EmployeeVo empVO2 = new EmployeeVo();
//		 empVO2.setEmp_no("3");
//		 empVO2.setEmp_account("clerk3");
//		 empVO2.setEmp_psw("clerk3");
//		 empVO2.setEmp_name("clerk21");
//		 empVO2.setEmp_sex(1);
//		 empVO2.setEmp_mobile("09QAZXSWED");
//		 int updateCount_update = dao.update(empVO2);
//		 System.out.println("update:"+updateCount_update); 		 
	 
		// 查詢單筆資料
		EmployeeVO empVO3 = dao.findByPrimaryKey("3");
		System.out.print(empVO3.getEmp_no() + ",");
		System.out.print(empVO3.getEmp_account() + ",");
		System.out.print(empVO3.getEmp_psw() + ",");
		System.out.print(empVO3.getEmp_name() + ",");
		System.out.print(empVO3.getEmp_sex() + ",");
		System.out.print(empVO3.getEmp_mobile() + ",");
		System.out.println();
		System.out.println("---------------------");
	
		// 查詢全部資料
//		List<EmployeeVo> list = dao.getAll();
//		for (EmployeeVo aEmp : list) {
//			System.out.print(aEmp.getEmp_no() + ",");	
//			System.out.print(aEmp.getEmp_account() + ",");
//			System.out.print(aEmp.getEmp_psw() + ",");
//			System.out.print(aEmp.getEmp_name() + ",");
//			System.out.print(aEmp.getEmp_sex() + ",");
//			System.out.print(aEmp.getEmp_mobile() + ",");
//			System.out.println();
//		}
		
	}	
}