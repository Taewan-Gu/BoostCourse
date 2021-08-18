package com.ntscorp.intern.reservation.repository.sql;

public class ReservationSql {
	public static final String SELECT_PRODUCT_RESERVATION_BY_DISPLAY_INFO_ID = ""
		+ "SELECT pdt.id AS product_id, pdt.category_id, pdt.description AS title, dpl.opening_hours, place_street AS place "
		+ "FROM display_info AS dpl "
		+ "JOIN product AS pdt ON dpl.product_id = pdt.id "
		+ "WHERE dpl.id = :displayInfoId";
}