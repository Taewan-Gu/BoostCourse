package com.ntscorp.intern.reservation.repository.impl;

import static com.ntscorp.intern.reservation.repository.sql.ReservationSql.INSERT_RESERVATION_INFO_PRICE;
import static com.ntscorp.intern.reservation.repository.sql.ReservationSql.SELECT_ALL_RESERVATIONS_BY_EMAIL;
import static com.ntscorp.intern.reservation.repository.sql.ReservationSql.SELECT_RESERVATION_COUNT_BY_EMAIL;
import static com.ntscorp.intern.reservation.repository.sql.ReservationSql.SELECT_RESERVATION_INFO_BY_ID;
import static com.ntscorp.intern.reservation.repository.sql.ReservationSql.UPDATE_RESERVATION_INFO_CANCEL_FLAG;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
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
	private final RowMapper<Reservation> reservationRowMapper = BeanPropertyRowMapper
		.newInstance(Reservation.class);
	private final RowMapper<ReservationCount> reservationCountRowMapper = BeanPropertyRowMapper
		.newInstance(ReservationCount.class);
	private final RowMapper<ReservationInfo> reservationInfoRowMapper = BeanPropertyRowMapper
		.newInstance(ReservationInfo.class);

	public ReservationRepositoryImpl(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.insertReservationInfoAction = new SimpleJdbcInsert(dataSource)
			.withTableName("reservation_info").usingGeneratedKeyColumns("id");
	}

	@Override
	public int insertReservationInfo(ReservationInfo reservationInfo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfo);
		return insertReservationInfoAction.executeAndReturnKey(params).intValue();
	}

	@Override
	public void insertReservationInfoPrice(List<ReservationInfoPrice> reservationInfoPrices) {
		SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(reservationInfoPrices.toArray());
		namedParameterJdbcTemplate.batchUpdate(INSERT_RESERVATION_INFO_PRICE, batch);
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

	@Override
	public int updateReservationInfoCancelFlag(int cancelFlag, int reservationInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("reservationInfoId", reservationInfoId);
		params.put("cancelFlag", cancelFlag);
		return namedParameterJdbcTemplate.update(UPDATE_RESERVATION_INFO_CANCEL_FLAG, params);
	}

	@Override
	public ReservationInfo selectReservationInfoById(int reservationInfoId) {
		Map<String, ?> param = Collections.singletonMap("reservationInfoId", reservationInfoId);
		return namedParameterJdbcTemplate.queryForObject(SELECT_RESERVATION_INFO_BY_ID, param,
			reservationInfoRowMapper);
	}
}