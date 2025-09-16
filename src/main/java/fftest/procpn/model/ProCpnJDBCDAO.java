//package fftest.procpn.model;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ProCpnJDBCDAO implements ProCpnDAO_interface {
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/myproject?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "abc1477383";
//	private static final String INSERT_STMT = "INSERT INTO pro_cpn "
//			+ "(cpn_name, disc_type, disc_value, min_spend, start_date, valid_days, cpn_desc, is_active, appl_scope) "
//			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//	private static final String GET_ALL_STMT = "SELECT pro_cpn_id, cpn_name, disc_type, disc_value, min_spend, start_date, valid_days, cpn_desc, is_active, crt_at, appl_scope "
//			+ "FROM pro_cpn ORDER BY pro_cpn_id";
//	private static final String GET_ONE_STMT = "SELECT pro_cpn_id, cpn_name, disc_type, disc_value, min_spend, "
//			+ "start_date, valid_days, cpn_desc, is_active, crt_at, appl_scope " + "FROM pro_cpn WHERE pro_cpn_id = ?";
//	private static final String DELETE = "DELETE FROM pro_cpn where pro_cpn_id = ?";
//	private static final String UPDATE = "UPDATE pro_cpn SET " + "cpn_name = ?, " + "disc_type = ?, "
//			+ "disc_value = ?, " + "min_spend = ?, " + "start_date = ?, " + "valid_days = ?, " + "cpn_desc = ?, "
//			+ "is_active = ?, " + "appl_scope = ? " + "WHERE pro_cpn_id = ?";
//
//	@Override
//	public void insert(ProCpnVO proCpnVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setString(1, proCpnVO.getCpnName());
//			pstmt.setByte(2, proCpnVO.getDiscType());
//			pstmt.setBigDecimal(3, proCpnVO.getDiscValue());
//			pstmt.setInt(4, proCpnVO.getMinSpend());
//			pstmt.setDate(5, proCpnVO.getStartDate());
//			pstmt.setInt(6, proCpnVO.getValidDays());
//			pstmt.setString(7, proCpnVO.getCpnDesc());
//			pstmt.setByte(8, proCpnVO.getIsActive());
//			pstmt.setByte(9, proCpnVO.getApplScope());
//
//			pstmt.executeUpdate();
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//
//	@Override
//	public void update(ProCpnVO proCpnVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//			pstmt.setString(1, proCpnVO.getCpnName());
//			pstmt.setByte(2, proCpnVO.getDiscType());
//			pstmt.setBigDecimal(3, proCpnVO.getDiscValue());
//			pstmt.setInt(4, proCpnVO.getMinSpend());
//			pstmt.setDate(5, proCpnVO.getStartDate());
//			pstmt.setInt(6, proCpnVO.getValidDays());
//			pstmt.setString(7, proCpnVO.getCpnDesc());
//			pstmt.setByte(8, proCpnVO.getIsActive());
//			pstmt.setByte(9, proCpnVO.getApplScope());
//			pstmt.setInt(10, proCpnVO.getProCpnId()); // WHERE 條件
//			pstmt.executeUpdate();
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//
//	@Override
//	public void delete(Integer proCpnId) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//			pstmt.setInt(1, proCpnId);
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//
//	@Override
//	public ProCpnVO findByPrimaryKey(Integer proCpnId) {
//		ProCpnVO proCpnVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//			pstmt.setInt(1, proCpnId);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				// proCpnVo 也稱為 Domain objects
//				proCpnVO = new ProCpnVO();
//				proCpnVO.setProCpnId(rs.getInt("pro_cpn_id"));
//				proCpnVO.setCpnName(rs.getString("cpn_name"));
//				proCpnVO.setDiscType(rs.getByte("disc_type"));
//				proCpnVO.setDiscValue(rs.getBigDecimal("disc_value"));
//				proCpnVO.setMinSpend(rs.getInt("min_spend"));
//				proCpnVO.setStartDate(rs.getDate("start_date"));
//				proCpnVO.setValidDays(rs.getInt("valid_days"));
//				proCpnVO.setCpnDesc(rs.getString("cpn_desc"));
//				proCpnVO.setIsActive(rs.getByte("is_active"));
//				proCpnVO.setCrtAt(rs.getTimestamp("crt_at"));
//				proCpnVO.setApplScope(rs.getByte("appl_scope"));
//
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return proCpnVO;
//	}
//
//	@Override
//	public List<ProCpnVO> getAll() {
//		List<ProCpnVO> list = new ArrayList<ProCpnVO>();
//		ProCpnVO proCpnVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				// empVO 也稱為 Domain objects
//				proCpnVO = new ProCpnVO();
//				proCpnVO.setProCpnId(rs.getInt("pro_cpn_id"));
//				proCpnVO.setCpnName(rs.getString("cpn_name"));
//				proCpnVO.setDiscType(rs.getByte("disc_type"));
//				proCpnVO.setDiscValue(rs.getBigDecimal("disc_value"));
//				proCpnVO.setMinSpend(rs.getInt("min_spend"));
//				proCpnVO.setStartDate(rs.getDate("start_date"));
//				proCpnVO.setValidDays(rs.getInt("valid_days"));
//				proCpnVO.setCpnDesc(rs.getString("cpn_desc"));
//				proCpnVO.setIsActive(rs.getByte("is_active"));
//				proCpnVO.setCrtAt(rs.getTimestamp("crt_at"));
//				proCpnVO.setApplScope(rs.getByte("appl_scope"));
//				list.add(proCpnVO); // 放進 List
//				// 未看完
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//
//	}
//
//	public static void main(String[] args) {
//
//		ProCpnJDBCDAO dao = new ProCpnJDBCDAO();
//
//		// 新增
////		EmpVO empVO1 = new EmpVO();
////		empVO1.setEname("吳永志1");
////		empVO1.setJob("MANAGER");
////		empVO1.setHiredate(java.sql.Date.valueOf("2005-01-01"));
////		empVO1.setSal(Double.valueOf(50000));
////		empVO1.setComm(Double.valueOf(500));
////		empVO1.setDeptno(10);
////		dao.insert(empVO1);
//
//		// 修改
////		EmpVO empVO2 = new EmpVO();
////		empVO2.setEmpno(7001);
////		empVO2.setEname("吳永志2");
////		empVO2.setJob("MANAGER2");
////		empVO2.setHiredate(java.sql.Date.valueOf("2002-01-01"));
////		empVO2.setSal(Double.valueOf(20000));
////		empVO2.setComm(Double.valueOf(200));
////		empVO2.setDeptno(20);
////		dao.update(empVO2);
//
//		// 刪除
////		dao.delete(7014);
//
//		// 查詢
////		EmpVO empVO3 = dao.findByPrimaryKey(7001);
////		System.out.print(empVO3.getEmpno() + ",");
////		System.out.print(empVO3.getEname() + ",");
////		System.out.print(empVO3.getJob() + ",");
////		System.out.print(empVO3.getHiredate() + ",");
////		System.out.print(empVO3.getSal() + ",");
////		System.out.print(empVO3.getComm() + ",");
////		System.out.println(empVO3.getDeptno());
////		System.out.println("---------------------");
//
//		// 查詢
////		List<ProCpnVO> list = dao.getAll();
////		for (ProCpnVO aProCpn : list) {
////			System.out.print(aProCpn.getProCpnId() + ", ");
////			System.out.print(aProCpn.getCpnName() + ", ");
////			System.out.print(aProCpn.getDiscType() + ", ");
////			System.out.print(aProCpn.getDiscValue() + ", ");
////			System.out.print(aProCpn.getMinSpend() + ", ");
////			System.out.print(aProCpn.getStartDate() + ", ");
////			System.out.print(aProCpn.getValidDays() + ", ");
////			System.out.print(aProCpn.getCpnDesc() + ", ");
////			System.out.print(aProCpn.getIsActive() + ", ");
////			System.out.print(aProCpn.getCrtAt() + ", ");
////			System.out.print(aProCpn.getApplScope());
////			System.out.println();
////		}
//	}
//}