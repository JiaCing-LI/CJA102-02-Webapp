package fftest.memprocpn.model;

import java.util.List;

public interface MemProCpnDAO_interface {
	public MemProCpnVO insert(int memId, int proCpnId, int validDays);

	public void update(MemProCpnVO memProCpnVO);

	public void delete(Integer cpnholderdetailid);

	public MemProCpnVO findByPrimaryKey(Integer cpnholderdetailid);

	public List<MemProCpnVO> getAll();

	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<MemProCpnVO> getAll(Map<String, String[]> map); 
//	public List<MemProCpnVO> findByMemId(Integer memId);
//  public List<MemProCpnVO> findByStatus(Integer memId, Byte status);

	public void updateStatus(Integer cpnHolderDetailId, Byte cpnUseStatus);
}
