package com.ntscorp.intern.reservation.repository.impl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.ntscorp.intern.reservation.model.ReservationInfo;
import com.ntscorp.intern.reservation.model.ReservationInfoPrice;
import com.ntscorp.intern.reservation.repository.ReservationRepository;

@Repository
public class ReservationRepositoryImpl implements ReservationRepository {
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final SimpleJdbcInsert insertReservationInfoAction;
	private final SimpleJdbcInsert insertReservationInfoPriceAction;

	public ReservationRepositoryImpl(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.insertReservationInfoAction = new SimpleJdbcInsert(dataSource)
			.withTableName("reservation_info").usingGeneratedKeyColumns("id");
		this.insertReservationInfoPriceAction = new SimpleJdbcInsert(dataSource)
			.withTableName("reservation_info_price").usingGeneratedKeyColumns("id");
	}

	@Override
	public int insertReservationInfo(ReservationInfo reservationInfo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfo);
		return insertReservationInfoAction.executeAndReturnKey(params).intValue();
	}

	@Override
	public int insertReservationInfoPrice(ReservationInfoPrice reservationInfoPrice) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfoPrice);
		return insertReservationInfoPriceAction.execute(params);
	}
}