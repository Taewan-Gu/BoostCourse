package com.ntscorp.intern.reservation.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystemNotFoundException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ntscorp.intern.common.utils.ValidationUtils;
import com.ntscorp.intern.reservation.controller.response.ReservationsResponse;
import com.ntscorp.intern.reservation.model.Comment;
import com.ntscorp.intern.reservation.model.FileInfo;
import com.ntscorp.intern.reservation.model.Reservation;
import com.ntscorp.intern.reservation.model.ReservationCount;
import com.ntscorp.intern.reservation.model.ReservationInfo;
import com.ntscorp.intern.reservation.service.CommentService;
import com.ntscorp.intern.reservation.service.ReservationService;

@RestController
@RequestMapping("/api")
public class ReservationController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
	private static final int NO_CHANGE_CANCEL_FLAG = 0;
	private static final int NO_CREATE_REVIEW = 0;

	private static final String FILE_PATH = "c:/tmp/";
	private static final int BUFFER_SIZE = 1024;
	private static final int READ_BUFFER_END = -1;

	private final ReservationService reservationService;
	private final CommentService commentService;

	@Autowired
	public ReservationController(ReservationService reservationService, CommentService commentService) {
		this.reservationService = reservationService;
		this.commentService = commentService;
	}

	@GetMapping("/reservations")
	public ResponseEntity<?> getMyReservations(@RequestParam
	String reservationEmail, HttpSession session) {
		if (ValidationUtils.isNotValidatedLoginEmail(reservationEmail, session.getAttribute("email").toString())) {
			return new ResponseEntity<>("Email Incorrect", HttpStatus.UNAUTHORIZED);
		}

		if (ValidationUtils.isNotVaildatedEmail(reservationEmail)) {
			throw new IllegalArgumentException("arguments = [reservationEmail: " + reservationEmail + "]");
		}

		List<Reservation> reservations = reservationService.getAllReservationsByEmail(reservationEmail);
		ReservationCount reservationCount = reservationService.getReservationCountByEmail(reservationEmail);
		ReservationsResponse reservationsResponse = new ReservationsResponse(reservations, reservationCount);
		return ResponseEntity.ok(reservationsResponse);
	}

	@PutMapping("/reservations/{reservationInfoId}")
	public ResponseEntity<Integer> changeReservationInfoId(@PathVariable
	int reservationInfoId, HttpSession session) {
		if (ValidationUtils.isNotValidatedReservationInfoId(reservationInfoId)) {
			throw new IllegalArgumentException("arguments = [reservationInfoId: " + reservationInfoId + "]");
		}

		ReservationInfo reservationInfo = reservationService.getReservationInfoById(reservationInfoId);

		if (ValidationUtils.isNotValidatedLoginEmail(reservationInfo.getReservationEmail(),
			session.getAttribute("email").toString())) {
			return new ResponseEntity<>(NO_CHANGE_CANCEL_FLAG, HttpStatus.UNAUTHORIZED);
		}

		if (isNotValidatedReservationDate(reservationInfo.getReservationDate())) {
			return new ResponseEntity<>(NO_CHANGE_CANCEL_FLAG, HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.ok(reservationService.changeReservationInfoCancelFlag(reservationInfoId));
	}

	@PostMapping("/reservations/{reservationInfoId}/comments")
	public ResponseEntity<Integer> createComment(@PathVariable
	int reservationInfoId, @RequestParam(required = false)
	MultipartFile commentImage, @RequestParam
	String comment, @RequestParam
	int score, HttpSession session) {
		if (ValidationUtils.isNotValidatedReservationInfoId(reservationInfoId)) {
			throw new IllegalArgumentException("arguments = [reservationInfoId: " + reservationInfoId + "]");
		}

		if (ValidationUtils.isNotValidatedComment(comment)) {
			throw new IllegalArgumentException("arguments = [comment: " + comment + "]");
		}

		if (ValidationUtils.isNotValidatedCommentScore(score)) {
			throw new IllegalArgumentException("arguments = [score: " + score + "]");
		}

		ReservationInfo reservationInfo = reservationService.getReservationInfoById(reservationInfoId);

		if (ValidationUtils.isNotValidatedLoginEmail(reservationInfo.getReservationEmail(),
			session.getAttribute("email").toString())) {
			return new ResponseEntity<>(NO_CREATE_REVIEW, HttpStatus.UNAUTHORIZED);
		}

		Comment reservationUserComment = new Comment(reservationInfo.getProductId(), reservationInfoId, score, comment);

		if (commentImage != null) {
			String fileName = commentImage.getOriginalFilename();

			LOGGER.info("업로드 파일 이름 : {}", fileName);

			// 폴더가 없다면 디렉토리 생성
			File fileFolder = new File(FILE_PATH);
			if (fileFolder.exists() == false) {
				fileFolder.mkdir();
			}

			try (
				FileOutputStream fileOutputStream = new FileOutputStream(
					FILE_PATH + fileName);
				InputStream inputStream = commentImage.getInputStream();) {
				byte[] buffer = new byte[BUFFER_SIZE];
				int readCount = inputStream.read(buffer);
				while (readCount != READ_BUFFER_END) {
					fileOutputStream.write(buffer, 0, readCount);
					readCount = inputStream.read(buffer);
				}
			} catch (Exception exception) {
				throw new FileSystemNotFoundException("파일 저장 실패: " + commentImage.getOriginalFilename());
			}

			FileInfo fileInfo = new FileInfo(fileName, getSaveFileName(fileName), commentImage.getContentType());

			return ResponseEntity.ok(commentService.saveCommentWithImage(reservationUserComment, fileInfo));
		}

		return ResponseEntity.ok(commentService.saveComment(reservationUserComment));
	}

	@GetMapping("/commentimage/{commentImage}/{contentType}")
	public void createComment(@PathVariable
	String commentImage, @PathVariable
	String contentType, HttpServletResponse response) {
		String commentImageUrl = FILE_PATH + commentImage + "." + contentType;

		response.setHeader("Content-Disposition", "attachment; filename=\"" + commentImageUrl + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Type", contentType);
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");

		try (
			FileInputStream fis = new FileInputStream(commentImageUrl);
			OutputStream out = response.getOutputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = fis.read(buffer)) != -1) {
				out.write(buffer, 0, readCount);
			}

			LOGGER.info("다운로드 파일 이름 : {}.{}", commentImage, contentType);
		} catch (Exception exception) {
			throw new FileSystemNotFoundException("존재하지 않는 파일: " + commentImageUrl);
		}
	}

	private String getSaveFileName(String fileName) {
		return "/api/commentimage/" + fileName.replace(".", "/");
	}

	private boolean isNotValidatedReservationDate(LocalDateTime reservationDate) {
		Timestamp reservationDateTimestamp = Timestamp.valueOf(reservationDate);
		Timestamp nowTimestamp = Timestamp.valueOf(LocalDateTime.now());

		if (reservationDateTimestamp.getTime() < nowTimestamp.getTime()) {
			return true;
		}

		return false;
	}
}
