package fftest.procpn.service.strategy;

public interface Coupon<BigDecimal> {
	/**
	 * 套用折扣
	 * 
	 * @param original 原價
	 * @return 折扣後金額
	 */
	BigDecimal apply(BigDecimal original);
}
