package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.FileMangerLocationDtl;

public class FileMLocationDtlRowMapper implements RowMapper<FileMangerLocationDtl> {

	private static final Logger logger = LoggerFactory.getLogger(FileMLocationDtlRowMapper.class);

	public FileMangerLocationDtl mapRow(ResultSet rs, int rowNum) throws SQLException {
		FileMangerLocationDtl fm = new FileMangerLocationDtl();
		try {

			fm.setLocationId(rs.getString("LOCATION_URL"));

		} catch (Exception e) {
			logger.error("FileManager row mapper exception error-->" + e);
		}

		return fm;
	}

}
