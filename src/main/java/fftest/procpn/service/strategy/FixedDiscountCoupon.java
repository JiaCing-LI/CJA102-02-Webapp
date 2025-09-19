package fftest.procpn.service.strategy;

import java.math.BigDecimal;

/*
 * 固定金額折抵券
 */
public class FixedDiscountCoupon implements Coupon<BigDecimal> {
	private final BigDecimal discount;

	public FixedDiscountCoupon(BigDecimal discount) {
		this.discount = discount != null ? discount : BigDecimal.ZERO;
	}

	@Override
	public BigDecimal apply(BigDecimal original) {
		if (original == null) {
			throw new IllegalArgumentException("原價不能為 null");
		}
		BigDecimal price = original.subtract(discount);
		if (price.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("折扣後不能為小於0");
		}
		return price;
	}

}
