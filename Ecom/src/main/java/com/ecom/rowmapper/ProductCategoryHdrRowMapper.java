package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.ProductCategoryHdr;

public class ProductCategoryHdrRowMapper implements RowMapper<ProductCategoryHdr>{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductCategoryHdrRowMapper.class);
	public ProductCategoryHdr mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductCategoryHdr pcH = new ProductCategoryHdr();
		try {
			
			pcH.setIsActive(rs.getString("IS_ACTIVE"));
			pcH.setIsPrimary(rs.getString("IS_PRIMARY"));
			pcH.setParentCatHdr(rs.getString("PARENT_CATE_HDR_ID"));
			pcH.setPcmCode(rs.getString("PCM_CODE"));
			pcH.setPcmDesc(rs.getString("PCM_DESC"));
		      
		} catch (Exception e) {
			logger.error("ProductCategoryHdrRowMapper error-->"+e);
		}
		
	      return pcH;
	   }

}
