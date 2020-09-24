package com.ecom.jpaEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_information_varient_dtl")
public class OrderVarientJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OIV_ID")
	private Long oidvlId;
	@Column(name = "OID_DTL")
	private String oiDtlId;
	@Column(name = "VARIENT_TYPE_CODE")
	private String varientCode;
	@Column(name = "VALUE")
	private String value;
	
	@ManyToOne
    @JoinColumn(name = "OID_DTL", nullable = false, updatable = false,insertable = false)
    private OrderDtlJpaEntity orderDtlJpaEntity;

	public Long getOidvlId() {
		return oidvlId;
	}

	public void setOidvlId(Long oidvlId) {
		this.oidvlId = oidvlId;
	}

	public String getVarientCode() {
		return varientCode;
	}

	public void setVarientCode(String varientCode) {
		this.varientCode = varientCode;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	

}
