package com.ntscorp.intern.reservation.model;

public class CommentsCountAndAverageScore {
	private int totalCount;
	private float averageScore;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public float getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(float averageScore) {
		this.averageScore = averageScore;
	}

	@Override
	public String toString() {
		return "CommentsCountAndAverageScore [totalCount=" + totalCount + ", averageScore=" + averageScore + "]";
	}
}