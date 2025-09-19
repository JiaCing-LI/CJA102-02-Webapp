package fftest.procpn.service;

import java.math.BigDecimal;

import fftest.procpn.model.ProCpnVO;

public interface ProCpnService {
	BigDecimal applyCpn(ProCpnVO procpn, BigDecimal price);
}
