package com.ecom.domain;

import java.io.Serializable;

public class ProdReviewDtl implements Serializable {

	private static final long serialVersionUID = -1L;

	private String review;
	private String totalRating;
	private String earnedRating;
	private String cdid;
	private String cdName;

	public String getCdid() {
		return cdid;
	}

	public void setCdid(String cdid) {
		this.cdid = cdid;
	}

	public String getCdName() {
		return cdName;
	}

	public void setCdName(String cdName) {
		this.cdName = cdName;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getTotalRating() {
		return totalRating;
	}

	public void setTotalRating(String totalRating) {
		this.totalRating = totalRating;
	}

	public String getEarnedRating() {
		return earnedRating;
	}

	public void setEarnedRating(String earnedRating) {
		this.earnedRating = earnedRating;
	}

}
