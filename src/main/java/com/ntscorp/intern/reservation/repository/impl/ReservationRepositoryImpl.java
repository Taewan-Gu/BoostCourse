package com.ntscorp.intern.reservation.repository.impl;

import static com.ntscorp.intern.reservation.repository.sql.ReservationSql.SELECT_ALL_RESERVATIONS_BY_EMAIL;
import static com.ntscorp.intern.reservation.repository.sql.ReservationSql.SELECT_RESERVATION_COUNT_BY_EMAIL;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.ntscorp.intern.reservation.model.Reservation;
import com.ntscorp.intern.reservation.model.ReservationCount;
import com.ntscorp.intern.reservation.model.ReservationInfo;
import com.ntscorp.intern.reservation.model.ReservationInfoPrice;
import com.ntscorp.intern.reservation.repository.ReservationRepository;

@Repository
public class ReservationRepositoryImpl implements ReservationRepository {
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final SimpleJdbcInsert insertReservationInfoAction;
	private final SimpleJdbcInsert insertReservationInfoPriceAction;
	private final RowMapper<Reservation> reservationRowMapper = BeanPropertyRowMapper
		.newInstance(Reservation.class);
	private final RowMapper<ReservationCount> reservationCountRowMapper = BeanPropertyRowMapper
		.newInstance(ReservationCount.class);

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

	@Override
	public List<Reservation> selectAllReservationsByEmail(String reservationEmail) {
		Map<String, ?> param = Collections.singletonMap("reservationEmail", reservationEmail);
		return namedParameterJdbcTemplate.query(SELECT_ALL_RESERVATIONS_BY_EMAIL, param, reservationRowMapper);
	};

	@Override
	public ReservationCount selectReservationCountByEmail(String reservationEmail) {
		Map<String, ?> param = Collections.singletonMap("reservationEmail", reservationEmail);
		return namedParameterJdbcTemplate.queryForObject(SELECT_RESERVATION_COUNT_BY_EMAIL, param,
			reservationCountRowMapper);
	}
}