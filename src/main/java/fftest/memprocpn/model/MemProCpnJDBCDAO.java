package fftest.memprocpn.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MemProCpnJDBCDAO implements MemProCpnDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/myproject?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "abc1477383";
	private static final String INSERT_COUPON = "INSERT INTO mem_pro_cpn (mem_id, pro_cpn_id, cpn_use_status, rcv_at, eff_start, eff_end) "
			+ "VALUES (?, ?, 0, NOW(), ?, ?)";

	private static final String INSERT_STMT = "INSERT INTO mem_pro_cpn (pro_cpn_id, mem_id, cpn_use_status, rcv_at, eff_start, eff_end)VALUES(?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT cpn_holder_detail_id, pro_cpn_id, mem_id, cpn_use_status, "
			+ "crt_at, rcv_at, eff_start, eff_end, used_at " + "FROM mem_pro_cpn ORDER BY cpn_holder_detail_id";

	private static final String GET_ONE_STMT = "SELECT cpn_holder_detail_id, pro_cpn_id, mem_id, cpn_use_status, "
			+ "crt_at, rcv_at, eff_start, eff_end, used_at " + "FROM mem_pro_cpn WHERE cpn_holder_detail_id = ?";
	private static final String UPDATE = "UPDATE mem_pro_cpn "
			+ "SET pro_cpn_id = ?, mem_id = ?, cpn_use_status = ?, rcv_at = ?, eff_start = ?, eff_end = ?, used_at = ? "
			+ "WHERE cpn_holder_detail_id = ?";
	private static final String DELETE = "DELETE FROM mem_pro_cpn where cpn_holder_detail_id = ?";
	private static final String UPDATE_STATUS = "UPDATE MEM_PRO_CPN " + "SET CPN_USE_STATUS = ?, "
			+ "    USED_AT = CASE WHEN ? = 1 THEN NOW() ELSE NULL END " + "WHERE CPN_HOLDER_DETAIL_ID = ?";

	@Override
	public MemProCpnVO insert(int memId, int proCpnId, int validDays) {
		MemProCpnVO vo = null;

		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = con.prepareStatement(INSERT_COUPON, Statement.RETURN_GENERATED_KEYS)) {

			LocalDate start = LocalDate.now();
			LocalDate end = start.plusDays(validDays);

			pstmt.setInt(1, memId);
			pstmt.setInt(2, proCpnId);
			pstmt.setDate(3, java.sql.Date.valueOf(start));
			pstmt.setDate(4, java.sql.Date.valueOf(end));

			pstmt.executeUpdate();

			try (ResultSet rs = pstmt.getGeneratedKeys()) {
				if (rs.next()) {
					vo = new MemProCpnVO();
					vo.setCpnHolderDetailId(rs.getInt(1));
					vo.setMemId(memId);
					vo.setProCpnId(proCpnId);
					vo.setCpnUseStatus((byte) 0);
					vo.setEffStart(java.sql.Date.valueOf(start));
					vo.setEffEnd(java.sql.Date.valueOf(end));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return vo;
	}

	@Override
	public void update(MemProCpnVO memProCpnVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, memProCpnVO.getProCpnId());
			pstmt.setInt(2, memProCpnVO.getMemId());
			pstmt.setByte(3, memProCpnVO.getCpnUseStatus());
			pstmt.setTimestamp(4, memProCpnVO.getRcvAt());
			pstmt.setDate(5, memProCpnVO.getEffStart());
			pstmt.setDate(6, memProCpnVO.getEffEnd());
			pstmt.setTimestamp(7, memProCpnVO.getUsedAt());
			pstmt.setInt(8, memProCpnVO.getCpnHolderDetailId()); // WHERE 條件
			int rows = pstmt.executeUpdate();
			if (rows > 0) {
				System.out.println("修改成功，總共:" + rows + "一筆");
			} else {
				System.out.println("修改失敗");
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer cpnholderdetailid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, cpnholderdetailid);
			int rows = pstmt.executeUpdate();
			if (rows > 0) {
				System.out.println("刪除成功，總共:" + rows + "一筆");
			} else {
				System.out.println("刪除失敗");
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public MemProCpnVO findByPrimaryKey(Integer cpnholderdetailid) {

		MemProCpnVO memProCpnVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, cpnholderdetailid);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				memProCpnVO = new MemProCpnVO();
				memProCpnVO.setCpnHolderDetailId(rs.getInt("cpn_holder_detail_id"));
				memProCpnVO.setProCpnId(rs.getInt("pro_cpn_id"));
				memProCpnVO.setMemId(rs.getInt("mem_id"));
				memProCpnVO.setCpnUseStatus(rs.getByte("cpn_use_status"));
				memProCpnVO.setCrtAt(rs.getTimestamp("crt_at"));
				memProCpnVO.setRcvAt(rs.getTimestamp("rcv_at"));
				memProCpnVO.setEffStart(rs.getDate("eff_start"));
				memProCpnVO.setEffEnd(rs.getDate("eff_end"));
				memProCpnVO.setUsedAt(rs.getTimestamp("used_at"));
			}

			// Handle any driver errors
		} catch (

		ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return memProCpnVO;
	}

	@Override
	public List<MemProCpnVO> getAll() {
		List<MemProCpnVO> list = new ArrayList<MemProCpnVO>();
		MemProCpnVO memProCpnVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				memProCpnVO = new MemProCpnVO();
				memProCpnVO.setCpnHolderDetailId(rs.getInt("cpn_holder_detail_id"));
				memProCpnVO.setProCpnId(rs.getInt("pro_cpn_id"));
				memProCpnVO.setMemId(rs.getInt("mem_id"));
				memProCpnVO.setCpnUseStatus(rs.getByte("cpn_use_status"));
				memProCpnVO.setCrtAt(rs.getTimestamp("crt_at"));
				memProCpnVO.setRcvAt(rs.getTimestamp("rcv_at"));
				memProCpnVO.setEffStart(rs.getDate("eff_start"));
				memProCpnVO.setEffEnd(rs.getDate("eff_end"));
				memProCpnVO.setUsedAt(rs.getTimestamp("used_at"));
				list.add(memProCpnVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void updateStatus(Integer cpnHolderDetailId, Byte cpnUseStatus) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = con.prepareStatement(UPDATE_STATUS)) {

			pstmt.setByte(1, cpnUseStatus); // SET CPN_USE_STATUS=?
			pstmt.setByte(2, cpnUseStatus); // CASE WHEN ?=1 THEN NOW()
			pstmt.setInt(3, cpnHolderDetailId); // WHERE 主鍵
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

//	public static void main(String[] args) {
//		MemProCpnJDBCDAO dao = new MemProCpnJDBCDAO();
//
//		// 新增
////		MemProCpnVO vo = new MemProCpnVO();
////		vo.setProCpnId(1); // 這張券的 ID (FK，要存在於 pro_cpn 表)
////		vo.setMemId(1); // 哪個會員領的 (FK，要存在於 mem 表)
////		vo.setCpnUseStatus((byte) 0); // 0=未使用
////		vo.setRcvAt(new Timestamp(System.currentTimeMillis())); // 領券時間 = 現在
////		vo.setEffStart(Date.valueOf("2025-09-01")); // 生效日
////		vo.setEffEnd(Date.valueOf("2025-09-30")); // 失效日
////		vo.setUsedAt(null); // 還沒使用，所以是 null
////		dao.insert(vo);
//
//		// 修改
////		MemProCpnVO vo2 = new MemProCpnVO();
////		vo2.setCpnHolderDetailId(11); // PK，要更新哪一筆
////		vo2.setProCpnId(1);// PK
////		vo2.setMemId(1);// PK
////		vo2.setCpnUseStatus((byte) 1); // 已使用
////		vo2.setRcvAt(new Timestamp(System.currentTimeMillis()));
////		vo2.setEffStart(Date.valueOf("2025-09-01"));
////		vo2.setEffEnd(Date.valueOf("2025-09-30"));
////		vo2.setUsedAt(new Timestamp(System.currentTimeMillis()));	
////		dao.update(vo2);
//		// 刪除
//		// dao.delete(11);
//		// 單筆查詢
////		MemProCpnVO VO3 = dao.findByPrimaryKey(1);
////		System.out.print(VO3.getCpnHolderDetailId() + ",");
////		System.out.print(VO3.getProCpnId() + ",");
////		System.out.print(VO3.getMemId() + ",");
////		System.out.print(VO3.getCpnUseStatus() + ",");
////		System.out.print(VO3.getCrtAt() + ",");
////		System.out.print(VO3.getRcvAt() + ",");
////		System.out.print(VO3.getEffStart() + ",");
////		System.out.print(VO3.getEffEnd() + ",");
////		System.out.println(VO3.getUsedAt() + ",");
////		System.out.println("---------------------");
//		// 整個查詢
////		List<MemProCpnVO> list = dao.getAll();
////		for (MemProCpnVO memProCpnVO : list) {
////			System.out.print(memProCpnVO.getCpnHolderDetailId() + ",");
////			System.out.print(memProCpnVO.getProCpnId() + ",");
////			System.out.print(memProCpnVO.getMemId() + ",");
////			System.out.print(memProCpnVO.getCpnUseStatus() + ",");
////			System.out.print(memProCpnVO.getCrtAt() + ",");
////			System.out.print(memProCpnVO.getRcvAt() + ",");
////			System.out.print(memProCpnVO.getEffEnd() + ",");
////			System.out.print(memProCpnVO.getUsedAt() + ",");
////			System.out.println();
////		}
//	}