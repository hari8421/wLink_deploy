package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import com.ecom.domain.ProductCategoryDtl;


public class ProductCategoryDtlRowMapper implements RowMapper<ProductCategoryDtl>{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductCategoryDtlRowMapper.class);
	public ProductCategoryDtl mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductCategoryDtl pcD = new ProductCategoryDtl();
		try {
			
			pcD.setParentCatHdrid(rs.getString("PARENT_CATE_HDR_ID"));
			pcD.setPcmCode(rs.getString("PCM_CODE"));
			pcD.setPcmDesc(rs.getString("PCM_DESC"));
			pcD.setProdcatDtlId(rs.getString("PARENT_CAT_DTL_ID"));
		      
		} catch (Exception e) {
			logger.error("ProductCategoryDtlRowMapper error-->"+e);
		}
		
	      return pcD;
	   }

}
