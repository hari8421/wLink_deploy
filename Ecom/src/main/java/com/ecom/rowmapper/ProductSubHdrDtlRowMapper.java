package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import com.ecom.domain.ProductSubHdrDtl;


public class ProductSubHdrDtlRowMapper implements RowMapper<ProductSubHdrDtl>{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductSubHdrDtlRowMapper.class);
	public ProductSubHdrDtl mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductSubHdrDtl ps = new ProductSubHdrDtl();
		try {
			
			ps.setConversionRate(rs.getString("CONVERSION_RATE"));
			ps.setDtlDesc(rs.getString("DTL_DESC"));
			ps.setPrice(rs.getString("PRICE"));
			ps.setPrimaryUom(rs.getString("PRIMARY_UOM"));
			ps.setProdId(rs.getString("PROD_ID"));
			ps.setPsdId(rs.getString("PSD_ID"));
			ps.setPsId(rs.getString("PS_ID"));
			ps.setStatus(rs.getString("STATUS"));
			ps.setValue(rs.getString("VALUE"));
			ps.setVarientDesc(rs.getString("VARIENT_TYPE_DESC"));
			ps.setVarientTypeCode(rs.getString("VARIENT_TYPE_CODE"));
		      
		} catch (Exception e) {
			logger.error("ProductSubHdrDtlRowMapper error-->"+e);
		}
		
	      return ps;
	   }

}
