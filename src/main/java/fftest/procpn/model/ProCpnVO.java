package fftest.procpn.model;

import java.sql.Date;
import java.sql.Timestamp;

public class ProCpnVO implements java.io.Serializable {
	private Integer proCpnId; // PK
	private String cpnName; // 折價券名稱
	private Byte discType; // 0: 滿額折抵, 1: 百分比
	private Double discValue; // 折扣數值
	private Integer minSpend; // 消費門檻金額
	private Date startDate; // 開始日期
	private Integer validDays; // 有效天數
	private String cpnDesc; // 折價券規則描述
	private Byte isActive; // 0:未啟用, 1:啟用
	private Timestamp crtAt; // 建立時間
	private Byte applScope; // 0:全館, 1:指定小農, 2:指定商品

	public Integer getProCpnId() {
		return proCpnId;
	}

	public void setProCpnId(Integer proCpnId) {
		this.proCpnId = proCpnId;
	}

	public String getCpnName() {
		return cpnName;
	}

	public void setCpnName(String cpnName) {
		this.cpnName = cpnName;
	}

	public Byte getDiscType() {
		return discType;
	}

	public void setDiscType(Byte discType) {
		this.discType = discType;
	}

	public Double getDiscValue() {
		return discValue;
	}

	public void setDiscValue(Double discValue) {
		this.discValue = discValue;
	}

	public Integer getMinSpend() {
		return minSpend;
	}

	public void setMinSpend(Integer minSpend) {
		this.minSpend = minSpend;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Integer getValidDays() {
		return validDays;
	}

	public void setValidDays(Integer validDays) {
		this.validDays = validDays;
	}

	public String getCpnDesc() {
		return cpnDesc;
	}

	public void setCpnDesc(String cpnDesc) {
		this.cpnDesc = cpnDesc;
	}

	public Byte getIsActive() {
		return isActive;
	}

	public void setIsActive(Byte isActive) {
		this.isActive = isActive;
	}

	public Timestamp getCrtAt() {
		return crtAt;
	}

	public void setCrtAt(Timestamp crtAt) {
		this.crtAt = crtAt;
	}

	public Byte getApplScope() {
		return applScope;
	}

	public void setApplScope(Byte applScope) {
		this.applScope = applScope;
	}

	public ProCpnVO() {
		super();
	}

	public ProCpnVO(Integer proCpnId, String cpnName, Byte discType, Double discValue, Integer minSpend, Date startDate,
			Integer validDays, String cpnDesc, Byte isActive, Timestamp crtAt, Byte applScope) {
		super();
		this.proCpnId = proCpnId;
		this.cpnName = cpnName;
		this.discType = discType;
		this.discValue = discValue;
		this.minSpend = minSpend;
		this.startDate = startDate;
		this.validDays = validDays;
		this.cpnDesc = cpnDesc;
		this.isActive = isActive;
		this.crtAt = crtAt;
		this.applScope = applScope;
	}

}
