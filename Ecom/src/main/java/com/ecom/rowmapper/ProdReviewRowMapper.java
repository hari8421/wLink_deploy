package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.ProdReviewDtl;

public class ProdReviewRowMapper implements RowMapper<ProdReviewDtl> {

	private static final Logger logger = LoggerFactory.getLogger(ProdReviewRowMapper.class);

	public ProdReviewDtl mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProdReviewDtl pr = new ProdReviewDtl();
		try {
			pr.setEarnedRating(rs.getString("TOTAL_RATING"));
			pr.setReview(rs.getString("REVIEW"));
			pr.setTotalRating(rs.getString("TOTAL_RATING"));
			pr.setCdid(rs.getString("CD_ID"));
			pr.setCdName(rs.getString("NAME"));
		} catch (Exception e) {
			logger.error("SingleProdDetails error-->" + e);
		}

		return pr;
	}

}
