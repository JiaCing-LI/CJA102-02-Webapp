package fftest.mem.model;

public class MemVO implements java.io.Serializable {
	private Integer memId;
	private String memName;
	private byte[] memPic;

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public byte[] getMemPic() {
		return memPic;
	}

	public void setMemPic(byte[] memPic) {
		this.memPic = memPic;
	}

	public MemVO(Integer memId, String memName, byte[] memPic) {
		super();
		this.memId = memId;
		this.memName = memName;
		this.memPic = memPic;
	}

	public MemVO() {
		super();
	}

}
