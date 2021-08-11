package com.ntscorp.intern.reservation.model;

public class CommentsCountAndAverageScore {
	private int totalCount;
	private Float averageScore;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public Float getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(Float averageScore) {
		this.averageScore = averageScore;
	}

	@Override
	public String toString() {
		return "CommentsCountAndAverageScore [totalCount=" + totalCount + ", averageScore=" + averageScore + "]";
	}
}