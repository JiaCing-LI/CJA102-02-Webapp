package fftest.procpn.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class ProCpnDAOImpl implements ProCpnDAO {

	@Override
	public void insert(Session session, ProCpnVO proCpnVO) {
		session.persist(proCpnVO);
	}

	@Override
	public void update(Session session, ProCpnVO proCpnVO) {
		session.merge(proCpnVO); // merge 用於更新
	}

	@Override
	public void delete(Session session, Integer proCpnId) {
		ProCpnVO cpn = session.get(ProCpnVO.class, proCpnId);
		if (cpn != null) {
			session.remove(cpn); // 刪除實體
		}
	}

	@Override
	public ProCpnVO findByPrimaryKey(Session session, Integer proCpnId) {
		return session.get(ProCpnVO.class, proCpnId);
	}

	@Override
	public List<ProCpnVO> getAll(Session session) {
		return session.createQuery("from ProCpnVO", ProCpnVO.class).list();
	}

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			ProCpnDAO dao = new ProCpnDAOImpl();
			ProCpnVO cpn = new ProCpnVO();
			ProCpnVO one = dao.findByPrimaryKey(session, cpn.getProCpnId());
			// ✅ Delete
			dao.delete(session, one.getProCpnId());
//			tx = session.beginTransaction();
//
//			ProCpnDAO dao = new ProCpnDAOImpl();
//
//			ProCpnVO cpn = new ProCpnVO();
//			cpn.setCpnName("滿1000折50");
//			cpn.setDiscType((byte) 0);
//			cpn.setDiscValue(new BigDecimal("50.00"));
//			cpn.setMinSpend(500);
//			cpn.setApplScope((byte) 0);
//			cpn.setIsActive((byte) 1);
//
//			dao.insert(session, cpn);
//
//			tx.commit(); // ✅ 記得提交
//			System.out.println("Insert OK, new ID = " + cpn.getProCpnId());

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
			HibernateUtil.shutdown();
		}

		// 查詢全部
//		try {
//			ProCpnDAO dao = new ProCpnDAOImpl();
//			List<ProCpnVO> list = dao.getAll(session);
//
//			for (ProCpnVO cpn : list) {
//				System.out.println(cpn.getProCpnId() + " | " + cpn.getCpnName() + " | " + cpn.getDiscValue());
//			}
//		} finally {
//			session.close();
//			HibernateUtil.shutdown();
//		}
	}

}
