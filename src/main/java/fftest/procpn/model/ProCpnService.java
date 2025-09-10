package fftest.procpn.model;

import java.util.List;

public class ProCpnService {
	private ProCpnDAO_interface dao;

	public ProCpnService() {
		dao = new ProCpnJDBCDAO(); // 或改成 HibernateDAO / JNDIDAO
	}

	public ProCpnVO addProCpn(String cpnName, byte discType, double discValue, int minSpend, java.sql.Date startDate,
			int validDays, String cpnDesc, byte isActive, byte applScope) {

		ProCpnVO proCpnVO = new ProCpnVO();
		proCpnVO.setCpnName(cpnName);
		proCpnVO.setDiscType(discType);
		proCpnVO.setDiscValue(discValue);
		proCpnVO.setMinSpend(minSpend);
		proCpnVO.setStartDate(startDate);
		proCpnVO.setValidDays(validDays);
		proCpnVO.setCpnDesc(cpnDesc);
		proCpnVO.setIsActive(isActive);
		proCpnVO.setApplScope(applScope);

		dao.insert(proCpnVO);
		return proCpnVO;
	}

	/**
	 * 修改折價券
	 */
	public ProCpnVO updateProCpn(Integer proCpnId, String cpnName, byte discType, double discValue, int minSpend,
			java.sql.Date startDate, int validDays, String cpnDesc, byte isActive, byte applScope) {

		ProCpnVO proCpnVO = new ProCpnVO();
		proCpnVO.setProCpnId(proCpnId);
		proCpnVO.setCpnName(cpnName);
		proCpnVO.setDiscType(discType);
		proCpnVO.setDiscValue(discValue);
		proCpnVO.setMinSpend(minSpend);
		proCpnVO.setStartDate(startDate);
		proCpnVO.setValidDays(validDays);
		proCpnVO.setCpnDesc(cpnDesc);
		proCpnVO.setIsActive(isActive);
		proCpnVO.setApplScope(applScope);

		dao.update(proCpnVO);
		return proCpnVO;
	}

	/**
	 * 刪除折價券
	 */
	public void deleteProCpn(Integer proCpnId) {
		dao.delete(proCpnId);
	}

	/**
	 * 查詢單一折價券
	 */
	public ProCpnVO getOneProCpn(Integer proCpnId) {
		return dao.findByPrimaryKey(proCpnId);
	}

	/**
	 * 查詢全部折價券
	 */
	public List<ProCpnVO> getAll() {
		return dao.getAll();
	}
}
