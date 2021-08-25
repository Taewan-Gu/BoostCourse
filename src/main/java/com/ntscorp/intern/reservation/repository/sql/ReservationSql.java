package com.ntscorp.intern.reservation.repository.sql;

public class ReservationSql {
	public static final String SELECT_ALL_RESERVATIONS_BY_EMAIL = ""
		+ "SELECT "
		+ "rsvt.id, "
		+ "rsvt.display_info_id, "
		+ "pdt.description AS title, "
		+ "rsvt.reservation_date, "
		+ "dpl.place_street, "
		+ "SUM(pdt_prc.price * rsvt_prc.count) AS total_price, "
		+ "rsvt.cancel_flag "
		+ "FROM reservation_info AS rsvt "
		+ "JOIN display_info AS dpl ON rsvt.display_info_id = dpl.id "
		+ "JOIN product AS pdt ON rsvt.product_id = pdt.id "
		+ "JOIN reservation_info_price AS rsvt_prc ON rsvt.id = rsvt_prc.reservation_info_id "
		+ "JOIN product_price AS pdt_prc ON rsvt_prc.product_price_id = pdt_prc.id "
		+ "WHERE rsvt.reservation_email = :reservationEmail "
		+ "GROUP BY rsvt.id";

	public static final String SELECT_RESERVATION_COUNT_BY_EMAIL = ""
		+ "SELECT "
		+ "COUNT(*) AS total_size, "
		+ "COUNT(CASE WHEN cancel_flag=0 AND reservation_date>=NOW() THEN 1 END) AS confirm_size, "
		+ "COUNT(CASE WHEN cancel_flag=0 AND reservation_date<NOW() THEN 1 END) AS complete_size, "
		+ "COUNT(CASE WHEN cancel_flag=1 THEN 1 END) AS cancel_size "
		+ "FROM reservation_info AS rsvt "
		+ "WHERE rsvt.reservation_email = :reservationEmail";

	public static final String UPDATE_RESERVATION_INFO_CANCEL_FLAG = ""
		+ "UPDATE reservation_info "
		+ "SET cancel_flag = :cancelFlag, modify_date = NOW() WHERE id = :reservationInfoId";

	public static final String SELECT_RESERVATION_INFO_BY_ID = ""
		+ "SELECT reservation_email, reservation_date FROM reservation_info WHERE id = :reservationInfoId";
}