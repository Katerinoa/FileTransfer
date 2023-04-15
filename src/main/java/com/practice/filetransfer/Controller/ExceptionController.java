package com.practice.filetransfer.Controller;

import com.practice.filetransfer.Constant.Status;
import com.practice.filetransfer.Exception.FileDeleteException;
import com.practice.filetransfer.Exception.FileQueryException;
import com.practice.filetransfer.Exception.FileTransferException;
import com.practice.filetransfer.Exception.FileValidationException;
import com.practice.filetransfer.Message.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异常处理器
 */
@ControllerAdvice
@RestController
public class ExceptionController {

	// 文件无效异常
	@ExceptionHandler(FileValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handleFileValidationException(FileValidationException ex) {
		return new ErrorMessage(Status.FALSE, ex.getMessage(), ex.getErrorCode());
	}

	// 删除失败异常
	@ExceptionHandler(FileDeleteException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handleFileDeleteException(FileDeleteException ex) {
		return new ErrorMessage(Status.FALSE, ex.getMessage(), ex.getErrorCode());
	}

	// 查询失败异常
	@ExceptionHandler(FileQueryException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handleFileQueryException(FileQueryException ex) {
		return new ErrorMessage(Status.FALSE, ex.getMessage(), ex.getErrorCode());
	}

	@ExceptionHandler(FileTransferException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handleFileTransferException(FileTransferException ex) {
		return new ErrorMessage(Status.FALSE, ex.getMessage(), ex.getErrorCode());
	}
}
