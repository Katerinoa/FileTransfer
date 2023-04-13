package com.practice.filetransfer.Controller;

import com.practice.filetransfer.Constant.Status;
import com.practice.filetransfer.Exception.FileDeleteException;
import com.practice.filetransfer.Exception.FileQueryException;
import com.practice.filetransfer.Exception.FileValidationException;
import com.practice.filetransfer.Message.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionController {

	@ExceptionHandler(FileValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handleFileValidationException(FileValidationException ex) {
		return new ErrorMessage(Status.FALSE, ex.getMessage(), ex.getErrorCode());
	}

	@ExceptionHandler(FileDeleteException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handleFileDeleteException(FileDeleteException ex) {
		return new ErrorMessage(Status.FALSE, ex.getMessage(), ex.getErrorCode());
	}

	@ExceptionHandler(FileQueryException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handleFileQueryException(FileQueryException ex) {
		return new ErrorMessage(Status.FALSE, ex.getMessage(), ex.getErrorCode());
	}
}
