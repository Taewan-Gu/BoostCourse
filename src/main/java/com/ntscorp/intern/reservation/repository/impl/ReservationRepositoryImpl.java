package com.ntscorp.intern.reservation.repository.impl;

import static com.ntscorp.intern.reservation.repository.sql.ReservationSql.SELECT_PRODUCT_RESERVATION_BY_DISPLAY_INFO_ID;

import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ntscorp.intern.reservation.model.ProductReservation;
import com.ntscorp.intern.reservation.repository.ReservationRepository;

@Repository
public class ReservationRepositoryImpl implements ReservationRepository {
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final RowMapper<ProductReservation> reservationProductRowMapper = BeanPropertyRowMapper
		.newInstance(ProductReservation.class);

	public ReservationRepositoryImpl(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public ProductReservation selectReservaionProductByDisplayInfoId(int displayInfoId) {
		Map<String, ?> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return namedParameterJdbcTemplate.queryForObject(SELECT_PRODUCT_RESERVATION_BY_DISPLAY_INFO_ID, param,
			reservationProductRowMapper);
	}
}