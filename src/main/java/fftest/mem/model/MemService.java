package fftest.mem.model;

public class MemService {
	private MemJDBCDAO dao = new MemJDBCDAO();

	public MemService() {
		dao = new MemJDBCDAO();
	}

	public MemVO addMem(String memName, byte[] memPic) {
		MemVO memVO = new MemVO();
		memVO.setMemName(memName);
		memVO.setMemPic(memPic);
		dao.insert(memVO);
		return memVO;
	}

	public MemVO getOneMem(Integer memid) {
		return dao.findByPrimaryKey(memid);
	}

}
