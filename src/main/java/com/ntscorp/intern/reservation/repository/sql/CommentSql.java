package com.ntscorp.intern.reservation.repository.sql;

public class CommentSql {
	public static final String SELECT_ALL_COMMENTS_BY_DISPLAY_INFO_ID = ""
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
		+ "ORDER BY cmt.id";

	public static final String SELECT_COMMENTS_BY_DISPLAY_INFO_ID = ""
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
		+ "ORDER BY cmt.id "
		+ "LIMIT :limit";

	public static final String SELECT_COMMENTS_COUNT_AND_AVERAGE_SCORE = ""
		+ "SELECT COUNT(*) AS total_count, IFNULL(AVG(cmt.score), 0) AS average_score "
		+ "FROM display_info AS dpl "
		+ "JOIN reservation_user_comment AS cmt ON dpl.product_id = cmt.product_id "
		+ "WHERE dpl.id = :displayInfoId";
}