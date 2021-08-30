package com.ntscorp.intern.reservation.model;

public class ReservationCount {
	private int totalSize;
	private int confirmSize;
	private int completeSize;
	private int cancelSize;

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getConfirmSize() {
		return confirmSize;
	}

	public void setConfirmSize(int confirmSize) {
		this.confirmSize = confirmSize;
	}

	public int getCompleteSize() {
		return completeSize;
	}

	public void setCompleteSize(int completeSize) {
		this.completeSize = completeSize;
	}

	public int getCancelSize() {
		return cancelSize;
	}

	public void setCancelSize(int cancelSize) {
		this.cancelSize = cancelSize;
	}

	@Override
	public String toString() {
		return "ReservationCount [totalSize=" + totalSize + ", confirmSize=" + confirmSize + ", completeSize="
			+ completeSize + ", cancelSize=" + cancelSize + "]";
	}
}