package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.ProductComboDtl;

public class ProductComboDtlRowMapper implements RowMapper<ProductComboDtl>{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductComboDtlRowMapper.class);
	public ProductComboDtl mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductComboDtl ph = new ProductComboDtl();
		try {
			
			ph.setIsActive(rs.getString("IS_ACTIVE"));
			ph.setPcdId(rs.getString("PCD_ID"));
			ph.setPchId(rs.getString("PCH_ID"));
			ph.setProdCode(rs.getString("PROD_NAME"));
			ph.setProdDesc(rs.getString("PROD_DESC"));
			ph.setProdId(rs.getString("PROD_ID"));
			ph.setPsId(rs.getString("PS_ID"));
			ph.setQty(rs.getString("QTY"));
			ph.setProdCatId(rs.getString(""));
		      
		} catch (Exception e) {
			logger.error("ProductComboDtlRowMapper error-->"+e);
		}
		
	      return ph;
	   }

}
