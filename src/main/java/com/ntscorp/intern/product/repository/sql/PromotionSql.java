package com.ntscorp.intern.product.repository.sql;

public class PromotionSql {
	public static final String SELECT_ALL_PROMOTIONS = ""
		+ "SELECT "
		+ "pmt.id, "
		+ "pmt.product_id, "
		+ "pdt.description AS title, "
		+ "pdt.content AS content, "
		+ "dpl.place_name AS place, "
		+ "fl.save_file_name AS product_image_url "
		+ "FROM promotion AS pmt "
		+ "JOIN product AS pdt ON pmt.product_id = pdt.id "
		+ "JOIN display_info AS dpl ON dpl.product_id = pdt.id "
		+ "JOIN product_image AS pdt_img ON pdt.id = pdt_img.product_id AND pdt_img.type = 'th' "
		+ "JOIN file_info AS fl ON pdt_img.file_id = fl.id";
}