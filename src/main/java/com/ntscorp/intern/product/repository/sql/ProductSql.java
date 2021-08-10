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

	public static final String SELECT_PRODUCT_DESCRIPTION_BY_DISPLAY_INFO_ID = ""
		+ "SELECT "
		+ "dpl.id, "
		+ "pdt.id AS product_id, "
		+ "pdt.description AS title, "
		+ "pdt.content, "
		+ "fl.save_file_name AS map_image_url, "
		+ "dpl.place_street, "
		+ "dpl.place_lot, "
		+ "dpl.place_name, "
		+ "dpl.tel "
		+ "FROM display_info AS dpl "
		+ "JOIN product AS pdt ON dpl.product_id = pdt.id "
		+ "JOIN display_info_image AS dpl_img ON dpl.id = dpl_img.display_info_id "
		+ "JOIN file_info AS fl ON dpl_img.file_id = fl.id "
		+ "WHERE dpl.id = :displayInfoId";

	public static final String SELECT_PRODUCT_IMAGES_BY_DISPLAY_INFO_ID = ""
		+ "SELECT dpl.id, dpl.product_id, fl.save_file_name AS product_image_url "
		+ "FROM display_info AS dpl "
		+ "JOIN product_image AS pdt_img "
		+ "ON dpl.product_id = pdt_img.product_id AND (pdt_img.type = 'ma' OR pdt_img.`type` = 'et') "
		+ "JOIN file_info AS fl ON pdt_img.file_id = fl.id "
		+ "WHERE dpl.id = :displayInfoId "
		+ "ORDER BY pdt_img.id";
}