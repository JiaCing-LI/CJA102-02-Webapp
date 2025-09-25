package fftest.mem.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mem")
public class MemVO implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 對應 AUTO_INCREMENT
	@Column(name = "mem_id")
	private Integer memId;
	@Column(name = "mem_acc", nullable = false, length = 40)
	private String memAcc;
	private String memPwd;
	private Byte accStatus = 0;
	private String memName;
	private LocalDate memBirthday;
	private String memMobile;
	private String memEmail;
	private byte[] memPic;
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public String getMemAcc() {
		return memAcc;
	}
	public void setMemAcc(String memAcc) {
		this.memAcc = memAcc;
	}
	public String getMemPwd() {
		return memPwd;
	}
	public void setMemPwd(String memPwd) {
		this.memPwd = memPwd;
	}
	public Byte getAccStatus() {
		return accStatus;
	}
	public void setAccStatus(Byte accStatus) {
		this.accStatus = accStatus;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public LocalDate getMemBirthday() {
		return memBirthday;
	}
	public void setMemBirthday(LocalDate memBirthday) {
		this.memBirthday = memBirthday;
	}
	public String getMemMobile() {
		return memMobile;
	}
	public void setMemMobile(String memMobile) {
		this.memMobile = memMobile;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public byte[] getMemPic() {
		return memPic;
	}
	public void setMemPic(byte[] memPic) {
		this.memPic = memPic;
	}
	public MemVO() {
		super();
	}
	public MemVO(Integer memId, String memAcc, String memPwd, Byte accStatus,
			String memName, LocalDate memBirthday, String memMobile,
			String memEmail, byte[] memPic) {
		super();
		this.memId = memId;
		this.memAcc = memAcc;
		this.memPwd = memPwd;
		this.accStatus = accStatus;
		this.memName = memName;
		this.memBirthday = memBirthday;
		this.memMobile = memMobile;
		this.memEmail = memEmail;
		this.memPic = memPic;
	}

}
