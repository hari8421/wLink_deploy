package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import com.ecom.domain.BannerHdrDtl;


public class BannerHdrDtlRowMapper implements RowMapper<BannerHdrDtl> {

	private static final Logger logger = LoggerFactory.getLogger(BannerHdrDtlRowMapper.class);

	public BannerHdrDtl mapRow(ResultSet rs, int rowNum) throws SQLException {
		BannerHdrDtl bh = new BannerHdrDtl();
		try {

			bh.setActualRate(rs.getString("ACTUAL_RATE"));
			bh.setBannerDesc1(rs.getString("BANNER_DESCRIPTION1"));
			bh.setBannerDesc3(rs.getString("BANNER_DESCRIPTION3"));
			bh.setBannerDesc2(rs.getString("BANNER_DESCRIPTION2"));
			bh.setBannerSeq(rs.getString("BANNER_SEQUENCE"));
			bh.setBannerTitle(rs.getString("BANNER_TITLE"));
			bh.setComCode(rs.getString("COM_CODE"));
			bh.setComDesc(rs.getString("COM_DESC"));
			bh.setDiscountPer(rs.getString("DISCOUNT_PERCENTAGE"));
			bh.setDiscountRate(rs.getString("DISCOUNT_RATE"));
			bh.setEffectiveEnd(rs.getString("EFFECTIVE_END_DATE"));
			bh.setEffectiveStart(rs.getString("EFFECTIVE_START_DATE"));
			bh.setIsActive(rs.getString("IS_ACTIVE"));
			bh.setOriginalRate(rs.getString("ORIGINAL_RATE"));
			bh.setPbDtlId(rs.getString("PB_DTL_ID"));
			bh.setPbHdrId(rs.getString("PB_HDR_ID"));
			bh.setPbType(rs.getString("PRODUCT_BANNER_TYPE"));
			bh.setPchId(rs.getString("PCH_ID"));
			bh.setTags(rs.getString("TAGS"));
			bh.setUpdatedDtime(rs.getString("LAST_UPDATED_DATETIME"));

		} catch (Exception e) {
			logger.error("BannerHdrDtl row mapper exception error-->" + e);
		}

		return bh;
	}

}
