package fftest.memprocpn.model;

import java.sql.Date;
import java.sql.Timestamp;

public class MemProCpnVO implements java.io.Serializable {
	private Integer cpnHolderDetailId; // PK
	private Integer proCpnId; // FK
	private Integer memId; // FK
	private Byte cpnUseStatus; // 折價券使用狀態 (0=未使用,1=已使用,2=已過期)
	private Timestamp crtAt; // 建立時間
	private Timestamp rcvAt; // 領券時間
	private Date effStart; // 實際生效起
	private Date effEnd;// 實際失效止
	private Timestamp usedAt;// 使用時間

	public Integer getCpnHolderDetailId() {
		return cpnHolderDetailId;
	}

	public void setCpnHolderDetailId(Integer cpnHolderDetailId) {
		this.cpnHolderDetailId = cpnHolderDetailId;
	}

	public Integer getProCpnId() {
		return proCpnId;
	}

	public void setProCpnId(Integer proCpnId) {
		this.proCpnId = proCpnId;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public Byte getCpnUseStatus() {
		return cpnUseStatus;
	}

	public void setCpnUseStatus(Byte cpnUseStatus) {
		this.cpnUseStatus = cpnUseStatus;
	}

	public Timestamp getCrtAt() {
		return crtAt;
	}

	public void setCrtAt(Timestamp crtAt) {
		this.crtAt = crtAt;
	}

	public Timestamp getRcvAt() {
		return rcvAt;
	}

	public void setRcvAt(Timestamp rcvAt) {
		this.rcvAt = rcvAt;
	}

	public Date getEffStart() {
		return effStart;
	}

	public void setEffStart(Date effStart) {
		this.effStart = effStart;
	}

	public Date getEffEnd() {
		return effEnd;
	}

	public void setEffEnd(Date effEnd) {
		this.effEnd = effEnd;
	}

	public Timestamp getUsedAt() {
		return usedAt;
	}

	public void setUsedAt(Timestamp usedAt) {
		this.usedAt = usedAt;
	}

	public MemProCpnVO(Integer cpnHolderDetailId, Integer proCpnId, Integer memId, Byte cpnUseStatus, Timestamp crtAt,
			Timestamp rcvAt, Date effStart, Date effEnd, Timestamp usedAt) {
		super();
		this.cpnHolderDetailId = cpnHolderDetailId;
		this.proCpnId = proCpnId;
		this.memId = memId;
		this.cpnUseStatus = cpnUseStatus;
		this.crtAt = crtAt;
		this.rcvAt = rcvAt;
		this.effStart = effStart;
		this.effEnd = effEnd;
		this.usedAt = usedAt;
	}

	public MemProCpnVO() {
		super();
	}

}
