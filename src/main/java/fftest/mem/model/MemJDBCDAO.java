package fftest.mem.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemJDBCDAO implements MemDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/myproject?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "abc1477383";
	private static final String INSERT_STMT = "INSERT INTO mem (mem_name ,mem_pic) VALUES (? , ?)";
	private static final String GET_ONE_SQL = "SELECT mem_id,,mem_name, mem_pic FROM member WHERE mem_id = ?";

	@Override
	public void insert(MemVO memVO) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)) {

			System.out.println("即將存入: memId=" + memVO.getMemId() + ", memName=" + memVO.getMemName() + ", pic="
					+ (memVO.getMemPic() != null ? memVO.getMemPic().length : "null"));
			pstmt.setString(1, memVO.getMemName());
			pstmt.setBytes(2, memVO.getMemPic());
			int rows = pstmt.executeUpdate();
			System.out.println("成功插入筆數 = " + rows);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(MemVO memVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer memId) {
		// TODO Auto-generated method stub

	}

	@Override
	public MemVO findByPrimaryKey(Integer memId) {
		MemVO memVO = null;
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = con.prepareStatement(GET_ONE_SQL)) {

			pstmt.setInt(1, memId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				memVO = new MemVO();
				memVO.setMemId(rs.getInt("mem_id"));
				memVO.setMemName(rs.getString("mem_name"));
				memVO.setMemPic(rs.getBytes("mem_pic"));
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memVO;
	}

}
