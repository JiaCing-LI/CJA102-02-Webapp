package fftest.procpn.model;

import java.util.List;

import org.hibernate.Session;

public interface ProCpnDAO {
	public void insert(Session session, ProCpnVO proCpnVO);

	public void update(Session session, ProCpnVO proCpnVO);

	public void delete(Session session, Integer proCpnId);

	public ProCpnVO findByPrimaryKey(Session session, Integer proCpnId);

	public List<ProCpnVO> getAll(Session session);
	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<ProCpnVO> getAll(Map<String, String[]> map);
}
