package com.ntscorp.intern.reservation.repository.sql;

public class CommentSql {
	public static final String SELECT_ALL_COMMENTS = ""
		+ "SELECT "
		+ "cmt.id, "
		+ "cmt.score, "
		+ "cmt.comment, "
		+ "rsvt.reservation_date, "
		+ "rsvt.reservation_email, "
		+ "fl.save_file_name AS comment_image_url "
		+ "FROM display_info AS dpl "
		+ "JOIN reservation_user_comment AS cmt ON dpl.product_id = cmt.product_id "
		+ "JOIN reservation_info AS rsvt ON cmt.reservation_info_id = rsvt.id "
		+ "LEFT JOIN reservation_user_comment_image AS cmt_img ON cmt_img.reservation_user_comment_id = cmt.id "
		+ "LEFT JOIN file_info AS fl ON cmt_img.file_id = fl.id "
		+ "WHERE dpl.id = :displayInfoId";

	public static final String SELECT_COMMENTS_LIMIT_THREE = ""
		+ "SELECT "
		+ "cmt.id, "
		+ "cmt.score, "
		+ "cmt.comment, "
		+ "rsvt.reservation_date, "
		+ "rsvt.reservation_email, "
		+ "fl.save_file_name AS comment_image_url "
		+ "FROM display_info AS dpl "
		+ "JOIN reservation_user_comment AS cmt ON dpl.product_id = cmt.product_id "
		+ "JOIN reservation_info AS rsvt ON cmt.reservation_info_id = rsvt.id "
		+ "LEFT JOIN reservation_user_comment_image AS cmt_img ON cmt_img.reservation_user_comment_id = cmt.id "
		+ "LEFT JOIN file_info AS fl ON cmt_img.file_id = fl.id "
		+ "WHERE dpl.id = :displayInfoId "
		+ "LIMIT 3";
}