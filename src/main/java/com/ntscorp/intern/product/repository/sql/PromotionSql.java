package com.ntscorp.intern.product.repository.sql;

public class PromotionSql {
	public static final String FIND_ALL_PROMOTION_IMAGES = ""
		+ "SELECT pmt.id, pmt.product_id, fl.save_file_name AS product_image_url "
		+ "FROM promotion AS pmt "
		+ "JOIN product_image AS pdt_img ON pmt.id = pdt_img.product_id AND pdt_img.type = 'th' "
		+ "JOIN file_info AS fl ON pdt_img.file_id = fl.id";
}