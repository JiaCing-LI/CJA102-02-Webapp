package fftest.procpn.service;

import java.math.BigDecimal;
import java.util.List;

import fftest.procpn.model.ProCpnDAO;
import fftest.procpn.model.ProCpnDAOImpl;
import fftest.procpn.model.ProCpnVO;

public class ProCpnService {
	private ProCpnDAO dao;

	public ProCpnService() {
		// dao = new ProCpnJDBCDAO(); // 或改成 HibernateDAO / JNDIDAO
		dao = new ProCpnDAOImpl();
	}

	public ProCpnVO addProCpn(String cpnName, byte discType, BigDecimal discValue, int minSpend,
			java.sql.Date startDate, int validDays, String cpnDesc, byte isActive, byte applScope) {

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
	public ProCpnVO updateProCpn(Integer proCpnId, String cpnName, byte discType, BigDecimal discValue, int minSpend,
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
