package fftest.memprocpn.model;

import java.util.List;

/**
 * 折價券明細 Service
 */
public class MemProCpnService {
	private MemProCpnDAO_interface dao;

	public MemProCpnService() {
		dao = new MemProCpnJDBCDAO(); // 或改成 HibernateDAO / JNDIDAO // 你要有 DAO 的實作類別
	}

	// 新增領券紀錄
//	public MemProCpnVO addMemProCpn(MemProCpnVO memProCpnVO) {
//		dao.insert(memProCpnVO);
//		return memProCpnVO;
//	}
	/**
	 * 給指定會員一張折價券
	 *
	 * @param memId     會員 ID
	 * @param proCpnId  折價券 ID
	 * @param validDays 折價券有效天數
	 */
	public MemProCpnVO addMemProCpn(Integer memId, Integer proCpnId, Integer validDays) {
		return dao.insert(memId, proCpnId, validDays);
	}

	public MemProCpnVO updateMemProCpn(MemProCpnVO memProCpnVO) {
		dao.update(memProCpnVO);
		return memProCpnVO;
	}

	public void deleteMemProCpn(Integer cpnHolderDetailId) {
		dao.delete(cpnHolderDetailId);
	}

	public MemProCpnVO getOneMemProCpn(Integer cpnHolderDetailId) {
		return dao.findByPrimaryKey(cpnHolderDetailId);
	}

	public List<MemProCpnVO> getAll() {
		return dao.getAll();
	}

	public MemProCpnVO updateStatusMemProCpn(Integer cpnHolderDetailId, Byte cpnUseStatus) {
		dao.updateStatus(cpnHolderDetailId, cpnUseStatus);
		// 更新後再查一次最新資料回傳
		return dao.findByPrimaryKey(cpnHolderDetailId);
	}

}
