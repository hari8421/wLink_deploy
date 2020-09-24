package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import com.ecom.domain.ProductHdrDtl;


public class ProductHdrDtlRowMapper implements RowMapper<ProductHdrDtl>{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductHdrDtlRowMapper.class);
	public ProductHdrDtl mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductHdrDtl ph = new ProductHdrDtl();
		try {
			
			ph.setBmId(rs.getString("BM_ID"));
			ph.setBmName(rs.getString("BM_DESC"));
			ph.setBmCode(rs.getString("BM_CODE"));
			ph.setIsOwnBrand(rs.getString("IS_OWN_BRAND"));
			ph.setCeratedDtime(rs.getString("CREATED_DATETIME"));
			ph.setIsActive(rs.getString("IS_ACTIVE"));
			ph.setLastUpdatedTime(rs.getString("LAST_UPDATED_DATETIME"));
			ph.setPcHdrId(rs.getString("PARENT_CATE_HDR_ID"));
			ph.setProdCode(rs.getString("PROD_CODE"));
			ph.setProdDesc(rs.getString("PROD_DESC"));
			ph.setProdId(rs.getString("PROD_ID"));
			ph.setProdName(rs.getString("PROD_NAME"));
			ph.setTags(rs.getString("TAGS"));
			ph.setConversionRate(rs.getString("CONVERSION_RATE"));
			ph.setPrice(rs.getString("PRICE"));
			ph.setPrimaryUom(rs.getString("PRIMARY_UOM"));
			ph.setSecondaryUom(rs.getString("SECONDARY_UOM"));
			ph.setStatus(rs.getString("STATUS"));
			ph.setPsid(rs.getString("PS_ID"));
			ph.setDtlDesc(rs.getString("DTL_DESC"));
			ph.setPcDtlId(rs.getString("PARENT_CATE_DTL_ID"));
		      
		} catch (Exception e) {
			logger.error("ProductHdrDtlRowMapper error-->"+e);
		}
		
	      return ph;
	   }

}
