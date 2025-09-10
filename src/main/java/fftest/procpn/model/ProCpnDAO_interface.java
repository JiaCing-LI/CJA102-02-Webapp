package fftest.procpn.model;

import java.util.List;

public interface ProCpnDAO_interface {
	public void insert(ProCpnVO ProCpnVO);

	public void update(ProCpnVO ProCpnVO);

	public void delete(Integer proCpnId);

	public ProCpnVO findByPrimaryKey(Integer proCpnId);

	public List<ProCpnVO> getAll();
	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<ProCpnVO> getAll(Map<String, String[]> map);
}
