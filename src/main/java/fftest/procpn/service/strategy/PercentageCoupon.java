package fftest.procpn.service.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentageCoupon implements Coupon<BigDecimal> {
	private final BigDecimal rate;

	public PercentageCoupon(BigDecimal rate) {
		if (rate == null) {
			throw new IllegalArgumentException("折扣率不能為 null");
		}
		this.rate = rate;
	}

	@Override
	public BigDecimal apply(BigDecimal original) {
		if (original == null) {
			throw new IllegalArgumentException("原價不能為 null");
		}
		return original.multiply(rate).setScale(0, RoundingMode.HALF_UP);
	}

}
