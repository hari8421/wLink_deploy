package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.ProductComboHdr;


public class ProductComboHdrRowMapper implements RowMapper<ProductComboHdr>{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductComboHdrRowMapper.class);
	public ProductComboHdr mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductComboHdr ph = new ProductComboHdr();
		try {
			
			ph.setPchId(rs.getString("PCH_ID"));
			ph.setTag(rs.getString("TAGS"));
			ph.setOriginalRate(rs.getString("ORIGINAL_RATE"));
			ph.setIsActive(rs.getString("IS_ACTIVE"));
			ph.setEffectiveStartDate(rs.getString("EFFECTIVE_START_DATE"));
			ph.setEffectiveEndDate(rs.getString("EFFECTIVE_END_DATE"));
			ph.setDiscountRate(rs.getString("DISCOUNT_RATE"));
			ph.setDiscountPercentage(rs.getString("DISCOUNT_PERCENTAGE"));
			ph.setCom_desc(rs.getString("COM_DESC"));
			ph.setCom_code(rs.getString("COM_CODE"));
			ph.setActualRate(rs.getString("ACTUAL_RATE"));
			ph.setComLongDesc(rs.getString("COM_LONG_DESC"));
			ph.setComType(rs.getString("PC_TYPE"));
			ph.setPsId(rs.getString("PS_ID"));
			ph.setProdId(rs.getString("PROD_ID"));
			ph.setPcdId(rs.getString("PCD_ID"));
			ph.setQty(rs.getString("QTY"));
		      
		} catch (Exception e) {
			logger.error("ProductComboHdrRowMapper error-->"+e);
		}
		
	      return ph;
	   }

}
