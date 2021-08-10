package com.ntscorp.intern.product.repository.sql;

public class ProductSql {
	public static final String SELECT_ALL_PRODUCT_SUMMARIES = ""
		+ "SELECT "
		+ "pdt.id, "
		+ "dpl.id AS display_info_id, "
		+ "pdt.description AS title, "
		+ "pdt.content, "
		+ "dpl.place_name AS place, "
		+ "fl.save_file_name AS product_image_url "
		+ "FROM product AS pdt "
		+ "JOIN display_info AS dpl ON pdt.id = dpl.product_id "
		+ "JOIN product_image AS pdt_img ON pdt_img.product_id = pdt.id AND pdt_img.type = 'th' "
		+ "JOIN file_info AS fl ON fl.id = pdt_img.file_id "
		+ "LIMIT :start, 4";

	public static final String SELECT_PRODUCT_SUMMARIES_BY_CATEGORY_ID = ""
		+ "SELECT "
		+ "pdt.id, "
		+ "dpl.id AS display_info_id, "
		+ "pdt.description AS title, "
		+ "pdt.content, "
		+ "dpl.place_name AS place, "
		+ "fl.save_file_name AS product_image_url "
		+ "FROM product AS pdt "
		+ "JOIN display_info AS dpl ON pdt.id = dpl.product_id "
		+ "JOIN product_image AS pdt_img ON pdt_img.product_id = pdt.id AND pdt_img.type = 'th' "
		+ "JOIN file_info AS fl ON fl.id = pdt_img.file_id "
		+ "WHERE pdt.category_id = :categoryId "
		+ "LIMIT :start, 4";

	public static final String COUNT_PRODUCT_SUMMARIES_BY_CATEGORY_ID = ""
		+ "SELECT COUNT(*) AS total_count "
		+ "FROM product AS pdt "
		+ "JOIN display_info AS dpl ON pdt.id = dpl.product_id "
		+ "WHERE category_id = :categoryId";

	public static final String COUNT_ALL_PRODUCT_SUMMARIES = ""
		+ "SELECT COUNT(*) AS total_count "
		+ "FROM product AS pdt "
		+ "JOIN display_info AS dpl ON pdt.id = dpl.product_id";
}